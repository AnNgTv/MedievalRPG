package me.anngtv.medievalrpg.models;

import java.util.UUID;

public class PlayerData {
    private final UUID uuid;
    private String lang = "en";
    private RPGClass rpgClass = RPGClass.NONE;
    private int level = 1;
    private int xp = 0;
    
    // Stats
    private int strength = 5;
    private int dexterity = 5;
    private int intelligence = 5;
    private int constitution = 5;

    public PlayerData(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUuid() { return uuid; }
    public String getLang() { return lang; }
    public void setLang(String lang) { this.lang = lang; }
    public RPGClass getRpgClass() { return rpgClass; }
    public void setRpgClass(RPGClass rpgClass) { this.rpgClass = rpgClass; }
    public int getLevel() { return level; }
    public void setLevel(int level) { this.level = level; }
    public int getXp() { return xp; }
    public void setXp(int xp) { this.xp = xp; }
    public int getStrength() { return strength; }
    public void setStrength(int strength) { this.strength = strength; }
    public int getDexterity() { return dexterity; }
    public void setDexterity(int dexterity) { this.dexterity = dexterity; }
    public int getIntelligence() { return intelligence; }
    public void setIntelligence(int intelligence) { this.intelligence = intelligence; }
    public int getConstitution() { return constitution; }
    public void setConstitution(int constitution) { this.constitution = constitution; }

    public void addXp(int amount) {
        this.xp += amount;
        int nextLevelXp = level * 100;
        if (this.xp >= nextLevelXp) {
            this.xp -= nextLevelXp;
            this.level++;
            // Auto-increment stats based on class or just generic
            this.strength++;
            this.constitution++;
        }
    }
}
