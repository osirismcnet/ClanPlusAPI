package dev.alesixdev.clanPlusHook.examples;

import com.cortezromeo.clansplus.api.ClanPlus;
import com.cortezromeo.clansplus.api.enums.Rank;
import com.cortezromeo.clansplus.api.storage.IClanData;
import com.cortezromeo.clansplus.api.storage.IPlayerData;
import dev.alesixdev.clanPlusHook.ClanPlusHook;

/**
 * RANK MANAGEMENT — Promote, demote, and transfer ownership.
 *
 * Ranks (highest to lowest):
 * - Rank.LEADER  — one per clan, full control
 * - Rank.MANAGER — can manage members
 * - Rank.MEMBER  — default rank when joining
 *
 * Covers:
 * - Promoting a member to manager
 * - Demoting a manager to member
 * - Transferring clan ownership (leader → another player)
 * - Checking if a player meets a rank requirement
 */
public class RankManagementExample {

    private final ClanPlus api = ClanPlusHook.getAPI();

    /**
     * Promotes a player to MANAGER.
     * Only makes sense if they are currently a MEMBER.
     */
    public void promoteToManager(String playerName) {
        IPlayerData data = api.getPluginDataManager().getPlayerDatabase(playerName);
        data.setRank(Rank.MANAGER);
        api.getPluginDataManager().savePlayerDatabaseToStorage(playerName, data);
    }

    /**
     * Demotes a player back to MEMBER.
     * Useful for removing manager privileges without kicking.
     */
    public void demoteToMember(String playerName) {
        IPlayerData data = api.getPluginDataManager().getPlayerDatabase(playerName);
        data.setRank(Rank.MEMBER);
        api.getPluginDataManager().savePlayerDatabaseToStorage(playerName, data);
    }

    /**
     * Transfers clan ownership from the current leader to a new player.
     *
     * Steps:
     * 1. Demote the old leader to MANAGER (or MEMBER)
     * 2. Promote the new player to LEADER
     * 3. Update the clan's owner field
     * 4. Save both players + clan data
     */
    public void transferOwnership(String clanName, String oldLeader, String newLeader) {
        // Demote old leader
        IPlayerData oldData = api.getPluginDataManager().getPlayerDatabase(oldLeader);
        oldData.setRank(Rank.MANAGER);

        // Promote new leader
        IPlayerData newData = api.getPluginDataManager().getPlayerDatabase(newLeader);
        newData.setRank(Rank.LEADER);

        // Update clan owner
        IClanData clanData = api.getPluginDataManager().getClanDatabase(clanName);
        clanData.setOwner(newLeader);

        // Save everything
        api.getPluginDataManager().savePlayerDatabaseToStorage(oldLeader, oldData);
        api.getPluginDataManager().savePlayerDatabaseToStorage(newLeader, newData);
        api.getPluginDataManager().saveClanDatabaseToStorage(clanName, clanData);
    }

    /**
     * Checks if a player has at least the given rank.
     * Example: isPlayerRankSatisfied("Steve", Rank.MANAGER) → true if Steve is MANAGER or LEADER.
     */
    public boolean hasRank(String playerName, Rank requiredRank) {
        return api.getClanManager().isPlayerRankSatisfied(playerName, requiredRank);
    }

    /**
     * Gets the formatted/colored rank name (as configured in ClansPlus).
     */
    public String getFormattedRank(Rank rank) {
        return api.getClanManager().getFormatRank(rank);
    }
}
