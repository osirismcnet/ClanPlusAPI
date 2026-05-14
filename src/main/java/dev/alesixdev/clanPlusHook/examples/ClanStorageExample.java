package dev.alesixdev.clanPlusHook.examples;

import com.cortezromeo.clansplus.api.ClanPlus;
import com.cortezromeo.clansplus.api.storage.IClanData;
import dev.alesixdev.clanPlusHook.ClanPlusHook;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;

/**
 * CLAN STORAGE — Open and inspect shared clan inventories.
 *
 * Covers:
 * - Opening a storage page for a player
 * - Reading storage contents
 * - Checking max storage slots
 */
public class ClanStorageExample {

    private final ClanPlus api = ClanPlusHook.getAPI();

    /**
     * Opens a clan storage page for a player.
     *
     * @param player        the player who will see the inventory GUI
     * @param clanName      the clan whose storage to open
     * @param storageNumber the storage page number (starts at 1)
     * @param skipDisabled  if true, opens even if storage is disabled in config
     */
    public void openStorage(Player player, String clanName, int storageNumber, boolean skipDisabled) {
        api.getClanManager().openClanStorage(player, clanName, storageNumber, skipDisabled);
    }

    /**
     * Opens the first storage page for a player (most common use).
     */
    public void openFirstStorage(Player player, String clanName) {
        api.getClanManager().openClanStorage(player, clanName, 1, false);
    }

    /**
     * Gets all storage inventories for a clan.
     * Key = storage number, Value = Bukkit Inventory.
     */
    public HashMap<Integer, Inventory> getAllStorages(String clanName) {
        IClanData data = api.getPluginDataManager().getClanDatabase(clanName);
        return data.getStorageHashMap();
    }

    /**
     * Gets the maximum number of storage pages a clan can have.
     */
    public int getMaxStorage(String clanName) {
        IClanData data = api.getPluginDataManager().getClanDatabase(clanName);
        return data.getMaxStorage();
    }
}
