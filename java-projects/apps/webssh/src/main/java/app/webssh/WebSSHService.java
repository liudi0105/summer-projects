package app.webssh;

import com.jcraft.jsch.JSch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class WebSSHService {
    //存放ssh连接信息的map

    private Logger logger = LoggerFactory.getLogger(WebSSHService.class);
    //线程池
    public static ExecutorService executorService = Executors.newCachedThreadPool();

    @Autowired
    private SshSessionManager sshMap;

    public void initConnection(WebSocketSession session) {
        JSch jSch = new JSch();
        SshSession sshSession = new SshSession();
        sshSession.setJSch(jSch);
        sshSession.setWebSocketSession(session);
        String uuid = String.valueOf(session.getAttributes().get(Consts.SSH_SESSION_ID));
        //将这个ssh连接信息放入map中
        sshMap.put(uuid, sshSession);
    }

    /**
     * @Description: 将消息转发到终端
     * @Param: [channel, data]
     * @return: void
     * @Author: NoCortY
     * @Date: 2020/3/7
     */
}
