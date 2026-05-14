package dev.alesixdev.clanPlusHook.examples;

import com.cortezromeo.clansplus.api.ClanPlus;
import com.cortezromeo.clansplus.api.storage.IClanData;
import com.cortezromeo.clansplus.api.storage.IPlayerData;
import dev.alesixdev.clanPlusHook.ClanPlusHook;

import java.util.HashMap;
import java.util.TreeMap;

/**
 * DATABASE OPERATIONS — Load, save, and bulk manage data.
 *
 * Covers:
 * - Loading data into memory before reading
 * - Saving modified data to persistent storage
 * - HashMap vs Storage saves
 * - Bulk load/save operations
 * - Getting all cached data
 *
 * IMPORTANT: ClansPlus uses a two-layer system:
 *   1. In-memory HashMap (fast, lost on restart)
 *   2. Persistent storage (YAML/MySQL, survives restart)
 *
 * - saveClanDatabaseToHashMap()  → saves ONLY to memory (fast, temporary)
 * - saveClanDatabaseToStorage()  → saves to BOTH memory and disk (use this for permanent changes)
 */
public class DatabaseExample {

    private final ClanPlus api = ClanPlusHook.getAPI();

    /**
     * Loads a specific clan into memory.
     * MUST be called before getClanDatabase() if the clan might not be cached yet.
     */
    public void loadClan(String clanName) {
        api.getPluginDataManager().loadClanDatabase(clanName);
    }

    /**
     * Loads a specific player into memory.
     */
    public void loadPlayer(String playerName) {
        api.getPluginDataManager().loadPlayerDatabase(playerName);
    }

    /**
     * Saves clan data permanently (memory + disk).
     * Use this after modifying any IClanData properties.
     */
    public void saveClan(String clanName, IClanData data) {
        api.getPluginDataManager().saveClanDatabaseToStorage(clanName, data);
    }

    /**
     * Saves clan data permanently using whatever is currently in memory.
     * Shorthand — you don't need to pass the IClanData object.
     */
    public void saveClan(String clanName) {
        api.getPluginDataManager().saveClanDatabaseToStorage(clanName);
    }

    /**
     * Saves clan data ONLY to memory (not to disk).
     * Faster, but changes are lost on server restart.
     * Use for temporary/batch operations, then call saveClanDatabaseToStorage() at the end.
     */
    public void saveClanToMemoryOnly(String clanName, IClanData data) {
        api.getPluginDataManager().saveClanDatabaseToHashMap(clanName, data);
    }

    /**
     * Gets ALL loaded clans.
     * Key = clan name, Value = IClanData.
     * Sorted alphabetically (TreeMap).
     */
    public TreeMap<String, IClanData> getAllClans() {
        return api.getPluginDataManager().getClanDatabase();
    }

    /**
     * Gets ALL loaded players.
     * Key = player name, Value = IPlayerData.
     */
    public HashMap<String, IPlayerData> getAllPlayers() {
        return api.getPluginDataManager().getPlayerDatabase();
    }

    /**
     * Loads ALL clans and players into memory.
     * Heavy operation — use sparingly (e.g. on plugin enable).
     */
    public void loadAll() {
        api.getPluginDataManager().loadAllDatabase();
    }

    /**
     * Saves ALL cached data to persistent storage.
     * Heavy operation — use sparingly (e.g. on plugin disable or scheduled backup).
     */
    public void saveAll() {
        api.getPluginDataManager().saveAllDatabase();
    }
}
