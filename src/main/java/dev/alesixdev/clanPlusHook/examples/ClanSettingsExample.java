package dev.alesixdev.clanPlusHook.examples;

import com.cortezromeo.clansplus.api.ClanPlus;
import com.cortezromeo.clansplus.api.enums.Rank;
import com.cortezromeo.clansplus.api.enums.Subject;
import com.cortezromeo.clansplus.api.storage.IClanData;
import dev.alesixdev.clanPlusHook.ClanPlusHook;
import org.bukkit.Location;

import java.util.HashMap;

/**
 * CLAN SETTINGS — Customize clan appearance, message, spawn, and permissions.
 *
 * Covers:
 * - Setting display name (with color codes)
 * - Setting clan MOTD / message
 * - Setting clan spawn point
 * - Setting clan icon
 * - Configuring subject permissions (who can invite, kick, etc.)
 * - Discord integration fields
 */
public class ClanSettingsExample {

    private final ClanPlus api = ClanPlusHook.getAPI();

    /**
     * Sets the clan display name.
     * Supports Minecraft color codes: "&b" for aqua, "&6" for gold, etc.
     * Also supports hex colors.
     */
    public void setDisplayName(String clanName, String displayName) {
        IClanData data = api.getPluginDataManager().getClanDatabase(clanName);
        data.setCustomName(displayName); // e.g. "&6Warriors" or "&bSuper Clan"
        api.getPluginDataManager().saveClanDatabaseToStorage(clanName, data);
    }

    /**
     * Sets the clan MOTD (message of the day).
     * Shown to members when they view clan info.
     */
    public void setMessage(String clanName, String message) {
        IClanData data = api.getPluginDataManager().getClanDatabase(clanName);
        data.setMessage(message);
        api.getPluginDataManager().saveClanDatabaseToStorage(clanName, data);
    }

    /**
     * Sets the clan spawn point.
     * Members can teleport here with /clansplus spawn.
     */
    public void setSpawnPoint(String clanName, Location location) {
        IClanData data = api.getPluginDataManager().getClanDatabase(clanName);
        data.setSpawnPoint(location);
        api.getPluginDataManager().saveClanDatabaseToStorage(clanName, data);
    }

    /**
     * Gets the clan spawn point (can be null if not set).
     */
    public Location getSpawnPoint(String clanName) {
        return api.getPluginDataManager().getClanDatabase(clanName).getSpawnPoint();
    }

    /**
     * Sets permissions for clan actions.
     * Each Subject (INVITE, KICK, etc.) requires a minimum Rank to perform.
     *
     * Example: only MANAGER+ can invite, only LEADER can kick.
     */
    public void setPermissions(String clanName, HashMap<Subject, Rank> permissions) {
        IClanData data = api.getPluginDataManager().getClanDatabase(clanName);
        data.setSubjectPermission(permissions);
        api.getPluginDataManager().saveClanDatabaseToStorage(clanName, data);
    }

    /**
     * Gets the current permission map.
     */
    public HashMap<Subject, Rank> getPermissions(String clanName) {
        return api.getPluginDataManager().getClanDatabase(clanName).getSubjectPermission();
    }

    /**
     * Sets up Discord integration for a clan.
     */
    public void setDiscordInfo(String clanName, long channelId, String joinLink) {
        IClanData data = api.getPluginDataManager().getClanDatabase(clanName);
        data.setDiscordChannelID(channelId);
        data.setDiscordJoinLink(joinLink);
        api.getPluginDataManager().saveClanDatabaseToStorage(clanName, data);
    }

    /**
     * Gets the formatted display name of a clan (with colors applied).
     * Uses ClansPlus formatting engine.
     */
    public String getFormattedName(String clanName) {
        IClanData data = api.getPluginDataManager().getClanDatabase(clanName);
        return api.getClanManager().getFormatClanCustomName(data);
    }

    /**
     * Gets the formatted MOTD of a clan (with colors applied).
     */
    public String getFormattedMessage(String clanName) {
        IClanData data = api.getPluginDataManager().getClanDatabase(clanName);
        return api.getClanManager().getFormatClanMessage(data);
    }
}
