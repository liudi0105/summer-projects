package app.webssh;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class SshSessionManager {

    private final Map<String, SshSession> sshMap = new ConcurrentHashMap<>();

    public static ExecutorService executorService = Executors.newCachedThreadPool();

    public SshSession get(String key) {
        return sshMap.get(key);
    }

    public void put(String key, SshSession sshSession) {
        sshMap.put(key, sshSession);
    }

    public void remove(String key) {
        sshMap.remove(key);
    }
}
