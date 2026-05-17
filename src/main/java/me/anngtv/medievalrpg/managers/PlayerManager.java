package me.anngtv.medievalrpg.managers;

import me.anngtv.medievalrpg.MedievalRPG;
import me.anngtv.medievalrpg.models.PlayerData;
import me.anngtv.medievalrpg.models.RPGClass;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerManager {
    private final Map<UUID, PlayerData> playerDataMap = new HashMap<>();
    private File file;
    private FileConfiguration config;

    public void loadAll() {
        this.file = new File(MedievalRPG.getInstance().getDataFolder(), "players.yml");
        if (!file.exists()) {
            try { file.createNewFile(); } catch (IOException e) { e.printStackTrace(); }
        }
        this.config = YamlConfiguration.loadConfiguration(file);

        if (config.getConfigurationSection("players") != null) {
            for (String key : config.getConfigurationSection("players").getKeys(false)) {
                UUID uuid = UUID.fromString(key);
                PlayerData data = new PlayerData(uuid);
                data.setRpgClass(RPGClass.valueOf(config.getString("players." + key + ".class", "NONE")));
                data.setLevel(config.getInt("players." + key + ".level", 1));
                data.setXp(config.getInt("players." + key + ".xp", 0));
                data.setStrength(config.getInt("players." + key + ".strength", 5));
                data.setDexterity(config.getInt("players." + key + ".dexterity", 5));
                data.setIntelligence(config.getInt("players." + key + ".intelligence", 5));
                data.setConstitution(config.getInt("players." + key + ".constitution", 5));
                playerDataMap.put(uuid, data);
            }
        }
    }

    public void saveAll() {
        for (PlayerData data : playerDataMap.values()) {
            String path = "players." + data.getUuid().toString();
            config.set(path + ".class", data.getRpgClass().name());
            config.set(path + ".level", data.getLevel());
            config.set(path + ".xp", data.getXp());
            config.set(path + ".strength", data.getStrength());
            config.set(path + ".dexterity", data.getDexterity());
            config.set(path + ".intelligence", data.getIntelligence());
            config.set(path + ".constitution", data.getConstitution());
        }
        try { config.save(file); } catch (IOException e) { e.printStackTrace(); }
    }

    public PlayerData getPlayerData(UUID uuid) {
        return playerDataMap.computeIfAbsent(uuid, PlayerData::new);
    }
}
