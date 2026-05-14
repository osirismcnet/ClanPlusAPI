package dev.alesixdev.clanPlusHook.examples;

import com.cortezromeo.clansplus.api.ClanPlus;
import com.cortezromeo.clansplus.api.enums.Rank;
import com.cortezromeo.clansplus.api.storage.IClanData;
import com.cortezromeo.clansplus.api.storage.IPlayerData;
import dev.alesixdev.clanPlusHook.ClanPlusHook;

/**
 * PLAYER DATA — Read and modify individual player stats.
 *
 * Covers:
 * - Getting a player's full clan profile
 * - Reading stats (score, join date, activity, points lost)
 * - Modifying player stats
 * - Looking up a player's clan data directly
 */
public class PlayerDataExample {

    private final ClanPlus api = ClanPlusHook.getAPI();

    /**
     * Prints a full summary of a player's clan profile.
     */
    public String getPlayerSummary(String playerName) {
        IPlayerData data = api.getPluginDataManager().getPlayerDatabase(playerName);

        if (data == null || data.getClan() == null) {
            return playerName + " is not in any clan.";
        }

        return String.format(
                "Player: %s | UUID: %s | Clan: %s | Rank: %s | Score: %d | Points Lost: %d",
                data.getPlayerName(),
                data.getUUID(),
                data.getClan(),
                data.getRank().name(),
                data.getScoreCollected(),
                data.getPointsLost()
        );
    }

    /**
     * Gets the player's current rank in their clan.
     * Returns null if the player has no data.
     */
    public Rank getPlayerRank(String playerName) {
        IPlayerData data = api.getPluginDataManager().getPlayerDatabase(playerName);
        return data != null ? data.getRank() : null;
    }

    /**
     * Gets when a player joined their current clan (epoch millis).
     */
    public long getJoinDate(String playerName) {
        return api.getPluginDataManager().getPlayerDatabase(playerName).getJoinDate();
    }

    /**
     * Gets the last time a player was active (epoch millis).
     */
    public long getLastActive(String playerName) {
        return api.getPluginDataManager().getPlayerDatabase(playerName).getLastActivated();
    }

    /**
     * Gets the clan data for whatever clan a player belongs to.
     * Shortcut — no need to know the clan name first.
     */
    public IClanData getPlayerClanData(String playerName) {
        return api.getPluginDataManager().getClanDatabaseByPlayerName(playerName);
    }

    /**
     * Resets a player's collected score to zero.
     */
    public void resetScore(String playerName) {
        IPlayerData data = api.getPluginDataManager().getPlayerDatabase(playerName);
        data.setScoreCollected(0);
        api.getPluginDataManager().savePlayerDatabaseToStorage(playerName, data);
    }

    /**
     * Clears a player's cached data from memory.
     * Useful for cleanup when a player leaves the server.
     */
    public void clearCache(String playerName) {
        api.getPluginDataManager().clearPlayerDatabase(playerName);
    }
}
