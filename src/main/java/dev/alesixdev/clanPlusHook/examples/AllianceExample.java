package dev.alesixdev.clanPlusHook.examples;

import com.cortezromeo.clansplus.api.ClanPlus;
import com.cortezromeo.clansplus.api.storage.IClanData;
import dev.alesixdev.clanPlusHook.ClanPlusHook;

import java.util.List;

/**
 * ALLIANCES — Manage clan alliances and invitations.
 *
 * Covers:
 * - Checking current allies
 * - Checking pending ally invitations
 * - Modifying alliance lists directly
 */
public class AllianceExample {

    private final ClanPlus api = ClanPlusHook.getAPI();

    /**
     * Gets the list of allied clan names.
     */
    public List<String> getAllies(String clanName) {
        IClanData data = api.getPluginDataManager().getClanDatabase(clanName);
        return data.getAllies();
    }

    /**
     * Gets pending alliance invitations received by this clan.
     */
    public List<String> getPendingInvitations(String clanName) {
        IClanData data = api.getPluginDataManager().getClanDatabase(clanName);
        return data.getAllyInvitation();
    }

    /**
     * Checks if two clans are allied.
     */
    public boolean areAllied(String clanA, String clanB) {
        IClanData data = api.getPluginDataManager().getClanDatabase(clanA);
        return data.getAllies().contains(clanB);
    }

    /**
     * Adds an alliance between two clans (both sides).
     * You must add each clan to the other's ally list and save both.
     */
    public void addAlliance(String clanA, String clanB) {
        IClanData dataA = api.getPluginDataManager().getClanDatabase(clanA);
        IClanData dataB = api.getPluginDataManager().getClanDatabase(clanB);

        List<String> alliesA = dataA.getAllies();
        List<String> alliesB = dataB.getAllies();

        if (!alliesA.contains(clanB)) alliesA.add(clanB);
        if (!alliesB.contains(clanA)) alliesB.add(clanA);

        dataA.setAllies(alliesA);
        dataB.setAllies(alliesB);

        api.getPluginDataManager().saveClanDatabaseToStorage(clanA, dataA);
        api.getPluginDataManager().saveClanDatabaseToStorage(clanB, dataB);
    }
}
