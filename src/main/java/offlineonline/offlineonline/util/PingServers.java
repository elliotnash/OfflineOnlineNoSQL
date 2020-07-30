package offlineonline.offlineonline.util;

import offlineonline.offlineonline.Main;
import net.md_5.bungee.api.Callback;
import net.md_5.bungee.api.ServerPing;

public class PingServers implements Callback<ServerPing> {
        private boolean serverIsOnline;

        @Override public void done(ServerPing serverPing, Throwable error) {
                synchronized (this) {
                        serverIsOnline = error == null;
                        if (error != null) {
                                Main.getPlugin().getLogger().severe("AuthServer down:" + error.getMessage());
                        }
                        notify();
                }
        }

        public boolean serverIsOnline() {
                return serverIsOnline;
        }
}
