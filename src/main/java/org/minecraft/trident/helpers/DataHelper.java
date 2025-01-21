package org.minecraft.trident.helpers;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import org.bukkit.Bukkit;

import org.minecraft.trident.Trident;

import javax.annotation.Nullable;

import java.io.IOException;
import java.io.File;

public class DataHelper {
    private static DataHelper INSTANCE = null;

    private final File configFile = new File(Trident.INSTANCE.getDataFolder(), "data.yml");
    private final FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);

    private void Save() {
        try {
            config.save(configFile);
        } catch (IOException e) {
            Bukkit.getLogger().severe(e.getMessage());
        }
    }

    private boolean Exists(String parent) {
        return config.getConfigurationSection(parent) != null;
    }

    public void Set(String parent, String child, Object value) {
        config.set(parent + "." + child, value);

        Save();
    }

    @Nullable
    public Object Get(String parent, String child) {
        if (Exists(parent)) {
            return config.get(parent + "." + child);
        }

        return null;
    }

    public static synchronized DataHelper getInstance() {
        if (INSTANCE == null)
            INSTANCE = new DataHelper();

        return INSTANCE;
    }
}
