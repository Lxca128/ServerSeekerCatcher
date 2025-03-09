package de.lxca.serverSeekerCatcher.objects.configurations;

import java.util.Collections;

public class ConfigYml extends Yml {

    private static final String filePath = "plugins/ServerSeekerCatcher/";
    private static final String fileName = "config.yml";

    public ConfigYml() {
        super(filePath, fileName);
        if (getYmlConfig().getKeys(true).isEmpty()) {
            setDefaultYmlKeys();
        }
    }

    @Override
    protected void setDefaultYmlKeys() {
        createConfigKey("PingTimeout", 15);
        createConfigKey("Retries", 5);
        createConfigKey("ServerPool", Collections.emptyList());
        saveYmlConfig();
    }
}
