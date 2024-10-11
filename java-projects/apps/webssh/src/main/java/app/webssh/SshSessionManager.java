package app.webssh;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SshSessionManager {

    public static final Map<String, SshSession> sshMap = new ConcurrentHashMap<>();

    public SshSession get(String key) {
        return sshMap.get(key);
    }

    public void put(String key, SshSession sshSession) {
        sshMap.put(key, sshSession);
    }
}
