package app.webssh.util;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Properties;

@Setter
@Getter
@Accessors(chain = true)
public class SshSession {
    private WsSession webSocketSession;
    private JSch jSch;
    private Channel channel;
    private SshSessionManager sshSessionManager;

    public void transToSSH(Channel channel, String command) throws IOException {
        if (channel != null) {
            OutputStream outputStream = channel.getOutputStream();
            outputStream.write(command.getBytes());
            outputStream.flush();
        }
    }

    public void initConnection() {
        setJSch(new JSch());
        String uuid = String.valueOf(webSocketSession.getSession().getAttributes().get(Consts.SSH_SESSION_ID));
        //将这个ssh连接信息放入map中
        sshSessionManager.put(uuid, this);
    }

    public void connectToSSH(SshConnectMessage sshConnectMessage) throws JSchException, IOException {
        Properties config = new Properties();
        config.put("StrictHostKeyChecking", "no");
        //获取jsch的会话
        Session session = getJSch().getSession(sshConnectMessage.getUsername(), sshConnectMessage.getHost(), sshConnectMessage.getPort());
        session.setConfig(config);
        //设置密码
        session.setPassword(sshConnectMessage.getPassword());
        //连接  超时时间30s
        session.connect(30000);

        //开启shell通道
        Channel channel = session.openChannel("shell");

        //通道连接 超时时间3s
        channel.connect(3000);

        //设置channel
        setChannel(channel);

        //转发消息
        transToSSH(channel, "\r");

        //读取终端返回的信息流
        try (InputStream inputStream = channel.getInputStream()) {
            //循环读取
            byte[] buffer = new byte[1024];
            int i;
            //如果没有数据来，线程会一直阻塞在这个地方等待数据。
            while ((i = inputStream.read(buffer)) != -1) {
                webSocketSession.sendMessage(Arrays.copyOfRange(buffer, 0, i));
            }

        } finally {
            //断开连接后关闭会话
            session.disconnect();
            channel.disconnect();
        }
    }

}
