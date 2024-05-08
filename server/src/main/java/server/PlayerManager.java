package server;

import client.model.Player;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// used a singleton pattern so there will only one membership manager object.
public class PlayerManager {

    private static volatile Map<String, Player> playerMap = new HashMap<>();
    private static volatile PlayerManager playerManager;

    // default constructor
    private PlayerManager() {
    }

    // return a singleton object
    public static synchronized PlayerManager getInstance() {
        if (playerManager == null) {
            playerManager = new PlayerManager();
        }
        return playerManager;
    }

    public List<Player> getPlayers() {
        return new ArrayList<>(playerMap.values());
    }

    public boolean isEmpty() {
        return playerMap.isEmpty();
    }

    public boolean isUniqueId(String id) {
        return !playerMap.containsKey(id);
    }

    // add members
    public void addPlayer(Player player) {
        playerMap.put(player.getId(), player);
    }

    // get member based on id
    public Player getPlayer(String id) {
        return playerMap.get(id);
    }

    // return true if found by id
    public synchronized boolean removeMember(Player player) {
        if (playerMap.containsKey(player.getId())) {
            playerMap.remove(player.getId());
            return true;
        }

        return false;
    }

}
