package me.anngtv.medievalrpg.commands;

import me.anngtv.medievalrpg.MedievalRPG;
import me.anngtv.medievalrpg.managers.LocaleManager;
import me.anngtv.medievalrpg.models.PlayerData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RPGCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command!");
            return true;
        }

        Player player = (Player) sender;
        PlayerData data = MedievalRPG.getInstance().getPlayerManager().getPlayerData(player.getUniqueId());
        String lang = data.getLang();
        LocaleManager lm = MedievalRPG.getInstance().getLocaleManager();

        if (args.length == 0 || args[0].equalsIgnoreCase("menu")) {
            MedievalRPG.getInstance().getGuiManager().openClassMenu(player);
            return true;
        }

        if (args[0].equalsIgnoreCase("lang")) {
            MedievalRPG.getInstance().getGuiManager().openLanguageMenu(player);
            return true;
        }

        if (args[0].equalsIgnoreCase("stats")) {
            sendStats(player, data, lm);
            return true;
        }

        return true;
    }

    private void sendStats(Player player, PlayerData data, LocaleManager lm) {
        String lang = data.getLang();
        player.sendMessage(lm.getMessage(lang, "stats.title"));
        player.sendMessage(lm.getMessage(lang, "stats.class").replace("{class}", data.getRpgClass().getDisplayName()));
        player.sendMessage(lm.getMessage(lang, "stats.level").replace("{level}", String.valueOf(data.getLevel())));
        player.sendMessage(lm.getMessage(lang, "stats.xp").replace("{xp}", String.valueOf(data.getXp())).replace("{max_xp}", String.valueOf(data.getLevel() * 100)));
        player.sendMessage(lm.getMessage(lang, "stats.strength").replace("{val}", String.valueOf(data.getStrength())));
        player.sendMessage(lm.getMessage(lang, "stats.constitution").replace("{val}", String.valueOf(data.getConstitution())));
        player.sendMessage(lm.getMessage(lang, "stats.intelligence").replace("{val}", String.valueOf(data.getIntelligence())));
        player.sendMessage(lm.getMessage(lang, "stats.dexterity").replace("{val}", String.valueOf(data.getDexterity())));
    }
}
