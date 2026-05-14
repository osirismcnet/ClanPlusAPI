package dev.alesixdev.clanPlusHook.examples;

import com.cortezromeo.clansplus.api.ClanPlus;
import com.cortezromeo.clansplus.api.storage.IClanData;
import com.cortezromeo.clansplus.api.storage.IPlayerData;
import dev.alesixdev.clanPlusHook.ClanPlusHook;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * MEMBER MANAGEMENT — Add, check, and inspect clan members.
 *
 * Covers:
 * - Adding a player to a clan
 * - Checking if a player is in any clan
 * - Getting a player's clan info
 * - Listing all members of a clan
 */
public class MemberManagementExample {

    private final ClanPlus api = ClanPlusHook.getAPI();

    /**
     * Adds a player to a clan.
     *
     * @param playerName    the player to add
     * @param clanName      the target clan
     * @param forceLeaveOld if true, the player auto-leaves their current clan.
     *                      if false and they're already in a clan, this may fail silently.
     */
    public void addPlayer(String playerName, String clanName, boolean forceLeaveOld) {
        api.getClanManager().addPlayerToAClan(playerName, clanName, forceLeaveOld);
    }

    /**
     * Checks if a player belongs to any clan.
     * Works with both player name (String) and online Player object.
     */
    public boolean isInAnyClan(Player player) {
        return api.getClanManager().isPlayerInClan(player);
    }

    public boolean isInAnyClan(String playerName) {
        return api.getClanManager().isPlayerInClan(playerName);
    }

    /**
     * Gets the clan name a player belongs to.
     * Returns null if the player is not in a clan.
     */
    public String getPlayerClanName(String playerName) {
        IPlayerData data = api.getPluginDataManager().getPlayerDatabase(playerName);
        return data != null ? data.getClan() : null;
    }

    /**
     * Gets all member names of a clan.
     */
    public List<String> getMembers(String clanName) {
        IClanData clanData = api.getPluginDataManager().getClanDatabase(clanName);
        return clanData.getMembers();
    }

    /**
     * Gets the maximum member capacity of a clan.
     */
    public int getMaxMembers(String clanName) {
        IClanData clanData = api.getPluginDataManager().getClanDatabase(clanName);
        return clanData.getMaxMembers();
    }

    /**
     * Checks if a clan is full.
     */
    public boolean isClanFull(String clanName) {
        IClanData clanData = api.getPluginDataManager().getClanDatabase(clanName);
        return clanData.getMembers().size() >= clanData.getMaxMembers();
    }
}
