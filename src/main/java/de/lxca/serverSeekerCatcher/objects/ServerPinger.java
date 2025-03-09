package de.lxca.serverSeekerCatcher.objects;

import de.lxca.serverSeekerCatcher.Main;

import java.io.IOException;
import java.net.*;

public class ServerPinger {

    private static final int pingTimeout = Main.getConfigYml().getYmlConfig().getInt("PingTimeout");

    private final String ip;
    private final int port;

    public ServerPinger(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public boolean isReachable() {
        return isReachable(ip, port);
    }

    public String getIpString() {
        return ip + ":" + port;
    }

    public static boolean isReachable(String ip, int port) {
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(ip, port), pingTimeout);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
