package me.anngtv.medievalrpg.managers;

import com.cryptomorin.xseries.XMaterial;
import me.anngtv.medievalrpg.MedievalRPG;
import me.anngtv.medievalrpg.models.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class GUIManager {

    public void openClassMenu(org.bukkit.entity.Player player) {
        PlayerData data = MedievalRPG.getInstance().getPlayerManager().getPlayerData(player.getUniqueId());
        String lang = data.getLang();
        LocaleManager lm = MedievalRPG.getInstance().getLocaleManager();

        Inventory inv = Bukkit.createInventory(null, 27, lm.getMessage(lang, "gui.class-title"));

        inv.setItem(10, createItem(XMaterial.IRON_SWORD, lm.getMessage(lang, "gui.warrior-name"), lm.getStringList(lang, "gui.warrior-lore")));
        inv.setItem(12, createItem(XMaterial.BLAZE_ROD, lm.getMessage(lang, "gui.mage-name"), lm.getStringList(lang, "gui.mage-lore")));
        inv.setItem(14, createItem(XMaterial.GOLDEN_CHESTPLATE, lm.getMessage(lang, "gui.paladin-name"), lm.getStringList(lang, "gui.paladin-lore")));
        inv.setItem(16, createItem(XMaterial.BOW, lm.getMessage(lang, "gui.thief-name"), lm.getStringList(lang, "gui.thief-lore")));

        player.openInventory(inv);
    }

    public void openLanguageMenu(org.bukkit.entity.Player player) {
        PlayerData data = MedievalRPG.getInstance().getPlayerManager().getPlayerData(player.getUniqueId());
        String lang = data.getLang();
        LocaleManager lm = MedievalRPG.getInstance().getLocaleManager();

        Inventory inv = Bukkit.createInventory(null, 9, lm.getMessage(lang, "gui.lang-title"));

        inv.setItem(3, createItem(XMaterial.WHITE_BANNER, lm.getMessage(lang, "gui.en-name"), null));
        inv.setItem(5, createItem(XMaterial.RED_BANNER, lm.getMessage(lang, "gui.vi-name"), null));

        player.openInventory(inv);
    }

    private ItemStack createItem(XMaterial material, String name, List<String> lore) {
        ItemStack item = material.parseItem();
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        if (lore != null) {
            List<String> coloredLore = new ArrayList<>();
            for (String line : lore) coloredLore.add(org.bukkit.ChatColor.translateAlternateColorCodes('&', line));
            meta.setLore(coloredLore);
        }
        item.setItemMeta(meta);
        return item;
    }
}
