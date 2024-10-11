package app.webssh;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;


@Component
@Slf4j
public class WebSSHWebSocketHandler implements WebSocketHandler {

    @Autowired
    private SshSessionManager sshSessionManager;

    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) {
        log.info("用户:{},连接WebSSH", webSocketSession.getAttributes().get(Consts.SSH_SESSION_ID));
        //调用初始化连接
        new SshSession()
                .setWebSocketSession(new WsSession(webSocketSession, sshSessionManager))
                .setSshSessionManager(sshSessionManager)
                .initConnection();
    }

    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) {
        switch (webSocketMessage) {
            case TextMessage textMessage -> {
                log.info("用户:{},发送命令:{}", webSocketSession.getAttributes().get(Consts.SSH_SESSION_ID), webSocketMessage);
                //调用service接收消息
                new WsSession(webSocketSession, sshSessionManager).handleReceiveWebsocketMessage(textMessage.getPayload());
            }
            case BinaryMessage ignored -> {
            }
            case PongMessage ignored -> {
            }
            default -> System.out.println("Unexpected WebSocket message type: " + webSocketMessage);
        }
    }

    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) {
        log.error("数据传输错误");
    }

    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) {
        log.info("用户:{}断开webssh连接", webSocketSession.getAttributes().get(Consts.SSH_SESSION_ID));
        //调用service关闭连接
        new WsSession(webSocketSession, sshSessionManager).close();
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
