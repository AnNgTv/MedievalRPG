package me.anngtv.medievalrpg.listeners;

import me.anngtv.medievalrpg.MedievalRPG;
import me.anngtv.medievalrpg.managers.LocaleManager;
import me.anngtv.medievalrpg.models.PlayerData;
import me.anngtv.medievalrpg.models.RPGClass;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;

public class RPGListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getClickedInventory() == null) return;
        Player player = (Player) event.getWhoClicked();
        PlayerData data = MedievalRPG.getInstance().getPlayerManager().getPlayerData(player.getUniqueId());
        String lang = data.getLang();
        LocaleManager lm = MedievalRPG.getInstance().getLocaleManager();

        String title = event.getView().getTitle();

        if (title.equals(lm.getMessage(lang, "gui.class-title"))) {
            event.setCancelled(true);
            int slot = event.getRawSlot();
            RPGClass chosen = null;
            if (slot == 10) chosen = RPGClass.WARRIOR;
            else if (slot == 12) chosen = RPGClass.MAGE;
            else if (slot == 14) chosen = RPGClass.PALADIN;
            else if (slot == 16) chosen = RPGClass.THIEF;

            if (chosen != null) {
                data.setRpgClass(chosen);
                player.sendMessage(lm.getWithPrefix(lang, "commands.class-chosen").replace("{class}", chosen.getDisplayName()));
                player.closeInventory();
            }
        } else if (title.equals(lm.getMessage(lang, "gui.lang-title"))) {
            event.setCancelled(true);
            int slot = event.getRawSlot();
            if (slot == 3) data.setLang("en");
            else if (slot == 5) data.setLang("vi");

            if (slot == 3 || slot == 5) {
                player.sendMessage(lm.getWithPrefix(data.getLang(), "commands.lang-updated"));
                player.closeInventory();
            }
        }
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            Player player = (Player) event.getDamager();
            PlayerData data = MedievalRPG.getInstance().getPlayerManager().getPlayerData(player.getUniqueId());
            double bonusDamage = data.getStrength() * 0.2;
            event.setDamage(event.getDamage() + bonusDamage);
        }

        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            PlayerData data = MedievalRPG.getInstance().getPlayerManager().getPlayerData(player.getUniqueId());
            double reduction = data.getConstitution() * 0.1;
            double finalDamage = Math.max(0.5, event.getDamage() - reduction);
            event.setDamage(finalDamage);
        }
    }

    @EventHandler
    public void onDeath(EntityDeathEvent event) {
        if (event.getEntity().getKiller() != null) {
            Player killer = event.getEntity().getKiller();
            PlayerData data = MedievalRPG.getInstance().getPlayerManager().getPlayerData(killer.getUniqueId());
            data.addXp(20);
        }
    }
}
