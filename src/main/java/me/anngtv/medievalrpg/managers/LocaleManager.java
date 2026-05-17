package me.anngtv.medievalrpg.managers;

import me.anngtv.medievalrpg.MedievalRPG;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class LocaleManager {
    private final Map<String, FileConfiguration> langFiles = new HashMap<>();
    private final String defaultLang = "en";

    public void loadLanguages() {
        String[] codes = {"en", "vi"};
        for (String code : codes) {
            File file = new File(MedievalRPG.getInstance().getDataFolder(), "messages_" + code + ".yml");
            if (!file.exists()) {
                MedievalRPG.getInstance().saveResource("messages_" + code + ".yml", false);
            }
            langFiles.put(code, YamlConfiguration.loadConfiguration(file));
        }
    }

    public String getMessage(String lang, String path) {
        FileConfiguration config = langFiles.getOrDefault(lang, langFiles.get(defaultLang));
        String message = config.getString(path, "Missing path: " + path);
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public String getWithPrefix(String lang, String path) {
        return getMessage(lang, "prefix") + getMessage(lang, path);
    }

    public java.util.List<String> getStringList(String lang, String path) {
        FileConfiguration config = langFiles.getOrDefault(lang, langFiles.get(defaultLang));
        return config.getStringList(path);
    }
}
