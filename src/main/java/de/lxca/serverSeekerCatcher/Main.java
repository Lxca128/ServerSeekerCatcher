package de.lxca.serverSeekerCatcher;

import de.lxca.serverSeekerCatcher.listeners.PlayerJoinListener;
import de.lxca.serverSeekerCatcher.objects.configurations.ConfigYml;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Objects;

public final class Main extends JavaPlugin {

    private static ConfigYml configYml;
    private static final ArrayList<String> serverPool = new ArrayList<>();

    @Override
    public void onEnable() {
        configYml = new ConfigYml();
        for (Object server : Objects.requireNonNull(configYml.getYmlConfig().getList("ServerPool"))) {
            serverPool.add(server.toString());
        }

        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Logger getLogger(Class<?> clazz) {
        return LogManager.getLogger(clazz);
    }

    public static ConfigYml getConfigYml() {
        return configYml;
    }

    public static ArrayList<String> getServerPool() {
        return serverPool;
    }
}
