package dev.alesixdev.clanPlusHook.examples;

import com.cortezromeo.clansplus.api.ClanPlus;
import dev.alesixdev.clanPlusHook.ClanPlusHook;

import java.util.HashMap;
import java.util.List;

/**
 * LEADERBOARDS — Retrieve sorted clan rankings.
 *
 * Covers:
 * - Score leaderboard
 * - Member count leaderboard
 * - Creation date ranking
 * - Custom name list
 */
public class LeaderboardExample {

    private final ClanPlus api = ClanPlusHook.getAPI();

    /**
     * Gets all clans sorted by score.
     * Key = clan name, Value = score.
     */
    public HashMap<String, Integer> getScoreLeaderboard() {
        return api.getClanManager().getClansScoreHashMap();
    }

    /**
     * Gets all clans sorted by member count.
     * Key = clan name, Value = number of members.
     */
    public HashMap<String, Integer> getMemberCountLeaderboard() {
        return api.getClanManager().getClansPlayerSize();
    }

    /**
     * Gets all clans sorted by creation date.
     * Key = clan name, Value = epoch millis when created.
     */
    public HashMap<String, Long> getCreationDateLeaderboard() {
        return api.getClanManager().getClansCreatedDate();
    }

    /**
     * Gets a list of all clan custom display names.
     */
    public List<String> getAllCustomNames() {
        return api.getClanManager().getClansCustomName();
    }
}
