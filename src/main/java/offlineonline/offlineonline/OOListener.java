package offlineonline.offlineonline;


import offlineonline.offlineonline.util.Msg;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.event.PreLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.connection.InitialHandler;
import net.md_5.bungee.event.EventHandler;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class OOListener implements Listener {
        private final Pattern pattern = Pattern.compile("^[a-zA-Z0-9_]{2,16}$");
        private final Main main;
        private final String kick_invalid_name;

        public OOListener(Main main, String invalid) {
                this.main = main;
                this.kick_invalid_name = ChatColor.translateAlternateColorCodes('&', invalid);
        }

        @EventHandler(priority = 64)
        public void onPreLogin(PreLoginEvent e) {


                if (e.isCancelled()) {
                        return;
                }

                if (e.getConnection().getName().length() > 16) {
                        main.getLogger().info(Msg.TOO_LONG_PASSWORD.toString()
                                .replace("$player", e.getConnection().getName()));
                        e.setCancelReason(TextComponent.fromLegacyText(this.kick_invalid_name));

                        e.setCancelled(true);

                        return;
                }
                if (!validate(e.getConnection().getName())) {
                        main.getLogger().info(Msg.INVALID_CHARACTER.toString()
                                .replace("$player", e.getConnection().getName()));
                        e.setCancelReason(TextComponent.fromLegacyText(this.kick_invalid_name));

                        e.setCancelled(true);

                        return;
                }



                if (Main.getManageCache().contains(e.getConnection().getName())) {
                        ServerInfo target = ProxyServer.getInstance()
                                .getServerInfo(Main.getConfig().getString("Settings.authServer"));

                        InitialHandler handler = (InitialHandler) e.getConnection();

                        if (target == null) {
                                e.setCancelled(true);
                                e.setCancelReason(
                                        TextComponent.fromLegacyText(Msg.AUTH_SERVER_DOWN.toString()));
                                return;

                        }

                        this.main.getLogger().info("\u001B[31m" + Msg.SUCCESSFUL_CONNECTION.toString()
                                .replace("$player", e.getConnection().getName()) + "\u001B[0m");

                        handler.setOnlineMode(false);
                }


        }

        public boolean validate(String username) {
                return (username != null) && (this.pattern.matcher(username).matches());
        }

}
