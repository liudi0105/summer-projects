package app.webssh;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;


@Component
public class WebSSHWebSocketHandler implements WebSocketHandler{
    @Autowired
    private WebSSHService webSSHService;

    @Autowired
    private SshSessionManager sshSessionManager;

    private Logger logger = LoggerFactory.getLogger(WebSSHWebSocketHandler.class);

    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
        logger.info("用户:{},连接WebSSH", webSocketSession.getAttributes().get(Consts.SSH_SESSION_ID));
        //调用初始化连接
        webSSHService.initConnection(webSocketSession);
    }

    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {
        if (webSocketMessage instanceof TextMessage) {
            logger.info("用户:{},发送命令:{}", webSocketSession.getAttributes().get(Consts.SSH_SESSION_ID), webSocketMessage.toString());
            //调用service接收消息
            new WsSession(webSocketSession, sshSessionManager).handleReceiveWebsocketMessage(((TextMessage) webSocketMessage).getPayload());
        } else if (webSocketMessage instanceof BinaryMessage) {

        } else if (webSocketMessage instanceof PongMessage) {

        } else {
            System.out.println("Unexpected WebSocket message type: " + webSocketMessage);
        }
    }

    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {
        logger.error("数据传输错误");
    }

    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
        logger.info("用户:{}断开webssh连接", String.valueOf(webSocketSession.getAttributes().get(Consts.SSH_SESSION_ID)));
        //调用service关闭连接
        new WsSession(webSocketSession, sshSessionManager).close();
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
