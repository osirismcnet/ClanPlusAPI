package dev.alesixdev.clanPlusHook.examples;

import com.cortezromeo.clansplus.api.ClanPlus;
import com.cortezromeo.clansplus.api.enums.Rank;
import com.cortezromeo.clansplus.api.storage.IClanData;
import dev.alesixdev.clanPlusHook.ClanPlusHook;

/**
 * CLAN LIFECYCLE — Create and delete clans.
 *
 * Covers:
 * - Checking if a clan exists
 * - Creating a new clan from scratch
 * - Deleting a clan entirely
 */
public class ClanLifecycleExample {

    private final ClanPlus api = ClanPlusHook.getAPI();

    /**
     * Creates a brand-new clan with an owner.
     *
     * Steps:
     * 1. Check the clan doesn't already exist
     * 2. Load an empty clan entry into memory
     * 3. Add the founder as a member
     * 4. Promote the founder to LEADER and set as owner
     * 5. Save everything to persistent storage
     */
    public boolean createClan(String clanName, String founderName) {
        // Always check first — loading an existing clan would overwrite data
        if (api.getClanManager().isClanExisted(clanName)) {
            return false; // Clan already exists
        }

        // This creates an empty clan entry in memory
        api.getPluginDataManager().loadClanDatabase(clanName);

        // Add the founder to the clan
        // false = don't force-leave if they're in another clan (will fail if they are)
        api.getClanManager().addPlayerToAClan(founderName, clanName, false);

        // Promote to leader
        api.getPluginDataManager().getPlayerDatabase(founderName).setRank(Rank.LEADER);

        // Set the owner on the clan data
        IClanData clanData = api.getPluginDataManager().getClanDatabase(clanName);
        clanData.setOwner(founderName);

        // IMPORTANT: always save after modifying data
        api.getPluginDataManager().saveClanDatabaseToStorage(clanName, clanData);
        api.getPluginDataManager().savePlayerDatabaseToStorage(founderName);

        return true;
    }

    /**
     * Deletes a clan permanently.
     *
     * Returns true if the clan was found and deleted, false otherwise.
     * Note: this removes the clan from storage — members will lose their clan reference.
     */
    public boolean deleteClan(String clanName) {
        if (!api.getClanManager().isClanExisted(clanName)) {
            return false;
        }

        return api.getPluginDataManager().deleteClanData(clanName);
    }

    /**
     * Checks if a clan exists in the database.
     */
    public boolean exists(String clanName) {
        return api.getClanManager().isClanExisted(clanName);
    }
}
