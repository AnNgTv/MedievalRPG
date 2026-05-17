package me.anngtv.medievalrpg.models;

public enum RPGClass {
    NONE("None", "Select a class to begin."),
    WARRIOR("Warrior", "High health and physical damage."),
    MAGE("Mage", "High magic power and mana."),
    PALADIN("Paladin", "Ultimate tank with healing abilities."),
    THIEF("Thief", "High speed and critical strike chance.");

    private final String displayName;
    private final String description;

    RPGClass(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getDescription() {
        return description;
    }
}
