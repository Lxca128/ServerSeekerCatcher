package de.lxca.serverSeekerCatcher.listeners;

import de.lxca.serverSeekerCatcher.Main;
import de.lxca.serverSeekerCatcher.objects.ServerPinger;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Random;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (Main.getServerPool().isEmpty()) {
            Main.getLogger(this.getClass()).warn("Server pool is empty!");
            return;
        }

        Player player = event.getPlayer();
        int retries = Main.getConfigYml().getYmlConfig().getInt("Retries");
        Random random = new Random();

        for (int i = 0; i < retries; i++) {
            String server = Main.getServerPool().get(random.nextInt(Main.getServerPool().size()));
            String ip;
            int port;

            if (server.contains(":")) {
                ip = server.split(":")[0];
                port = Integer.parseInt(server.split(":")[1]);
            } else {
                ip = server;
                port = 25565;
            }

            if (new ServerPinger(ip, port).isReachable()) {
                player.transfer(ip, port);
                Main.getLogger(this.getClass()).info("Player {} connected to {}:{}!", player.getDisplayName(), ip, port);
                return;
            } else {
                Main.getLogger(this.getClass()).warn("Failed to connect to {}:{}! Retrying...", ip, port);
            }
        }

        player.kick();
        Main.getLogger(this.getClass()).warn("Player {} was kicked due to connection failure!", player.getDisplayName());
    }
}
