package offlineonline.offlineonline;

import offlineonline.offlineonline.util.Msg;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OOCommand extends Command {

        private final Main main;

        public OOCommand(Main main) {
                super("OfflineOnline", "OfflineOnline.admin", "oo");

                this.main = main;

        }

        @Override
        public void execute(CommandSender sender, String[] args) {
                if (args.length <= 0) {
                        Msg.sendHelp(sender);
                } else {
                        switch (args[0].toLowerCase()) {
                                case "disable":
                                        Main.setDisable(true);
                                        sender.sendMessage(TextComponent.fromLegacyText(ChatColor.GOLD
                                                + Msg.PREFIX.toString() + Msg.PLUGIN_DISABLE));
                                        break;
                                case "enable":
                                        Main.setDisable(false);
                                        sender.sendMessage(
                                                TextComponent.fromLegacyText(ChatColor.GOLD + Msg.PREFIX.toString()
                                                        + Msg.PLUGIN_ENABLE));
                                        break;
                                case "add":
                                        final String name = args[1];

                                        //add player to config
                                        Main.getManageCache().addPlayerCache(name);
                                        Main.getConfig().set("allowedPlayers", Main.getManageCache().playerCacheList());
                                        Main.getInstance().saveConfig();


                                        sender.sendMessage(
                                                TextComponent.fromLegacyText(ChatColor.GOLD + Msg.PREFIX.toString()
                                                        + Msg.PLAYER_ADDED_TO_ALLOWED_CRACKED_LIST.toString()
                                                        .replace("$player", name)));
                                        break;
                                case "remove":
                                        final String name2 = args[1];

                                        //remove player from config
                                        Main.getManageCache().removePlayerCache(name2);
                                        Main.getConfig().set("allowedPlayers", Main.getManageCache().playerCacheList());
                                        Main.getInstance().saveConfig();

                                        sender.sendMessage(
                                                TextComponent.fromLegacyText(ChatColor.GOLD + Msg.PREFIX.toString()
                                                        + Msg.PLAYER_REMOVED_FROM_ALLOWED_CRACKED_LIST.toString()
                                                        .replace("$player", name2)));
                                        break;
                                case "list":
                                        StringBuilder msg = new StringBuilder(
                                                Msg.PREFIX.toString() + Msg.LIST_ALLOWED_PLAYERS);
                                        for (String pc : Main.getManageCache().playerCacheList()) {
                                                msg.append(pc).append(", ");
                                        }
                                        msg = new StringBuilder(msg.substring(1, msg.length() - 2));
                                        sender.sendMessage(
                                                TextComponent.fromLegacyText(ChatColor.GOLD + msg.toString()));
                                        break;
                                case "reload":
                                        main.loadConfig();
                                        sender.sendMessage(
                                                TextComponent.fromLegacyText(ChatColor.GOLD + "Config reloaded!"));

                                        break;
                                default:
                                        Msg.sendHelp(sender);
                        }

                }
        }
}
