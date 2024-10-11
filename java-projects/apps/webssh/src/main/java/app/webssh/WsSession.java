package app.webssh;

import com.jcraft.jsch.JSchException;
import common.module.util.AppJsons;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

import static app.webssh.SshSessionManager.executorService;

@AllArgsConstructor
@Slf4j
@Getter
public class WsSession {

    private WebSocketSession session;

    private SshSessionManager sshMap;

    public void sendMessage(byte[] buffer) throws IOException {
        session.sendMessage(new TextMessage(buffer));
    }

    public void handleReceiveWebsocketMessage(String buffer) {
        SshConnectMessage sshConnectMessage = AppJsons.fromJson(buffer, SshConnectMessage.class);
        String userId = String.valueOf(session.getAttributes().get(Consts.SSH_SESSION_ID));
        if (Consts.WEBSSH_OPERATE_CONNECT.equals(sshConnectMessage.getOperate())) {
            //找到刚才存储的ssh连接对象
            SshSession sshSession = sshMap.get(userId);
            //启动线程异步处理
            executorService.execute(() -> {
                try {
                    sshSession.connectToSSH(sshConnectMessage);
                } catch (JSchException | IOException e) {
                    log.error("webssh连接异常");
                    log.error("异常信息:{}", e.getMessage());
                    close();
                }
            });
        } else if (Consts.WEBSSH_OPERATE_COMMAND.equals(sshConnectMessage.getOperate())) {
            String command = sshConnectMessage.getCommand();
            SshSession sshSession = sshMap.get(userId);
            if (sshSession != null) {
                try {
                    sshSession.transToSSH(sshSession.getChannel(), command);
                } catch (IOException e) {
                    log.error("webssh连接异常");
                    log.error("异常信息:{}", e.getMessage());
                    close();
                }
            }
        } else {
            log.error("不支持的操作");
            close();
        }
    }

    public void close() {
        String userId = String.valueOf(session.getAttributes().get(Consts.SSH_SESSION_ID));
        SshSession sshSession = sshMap.get(userId);
        if (sshSession != null) {
            //断开连接
            if (sshSession.getChannel() != null) sshSession.getChannel().disconnect();
            //map中移除
            sshMap.get(userId);
        }
    }
}
