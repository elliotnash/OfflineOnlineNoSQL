package offlineonline.offlineonline.cache;

import com.google.common.collect.Maps;

import java.util.*;

public class ManageCache {

        private List<String> crackedPlayerCache = new LinkedList<>();

        public void addPlayerCache(String name) {
                if (!this.crackedPlayerCache.contains(name)) {
                        crackedPlayerCache.add(name);
                }
        }

        public void setPlayerCache(List<String> list){
                crackedPlayerCache = list;
        }

        public boolean contains(String p) {
                return this.crackedPlayerCache.contains(p);
        }

        public void removePlayerCache(String p) {
                if (this.crackedPlayerCache.contains(p)) {
                        crackedPlayerCache.remove(p);
                }
        }

        public List<String> playerCacheList() {
                return crackedPlayerCache;
        }

}
