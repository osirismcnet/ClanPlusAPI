package dev.alesixdev.clanPlusHook.examples;

import com.cortezromeo.clansplus.api.ClanPlus;
import dev.alesixdev.clanPlusHook.ClanPlusHook;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * CLAN CHAT & ALERTS — Send messages and check chat state.
 *
 * Covers:
 * - Broadcasting a message to all online clan members
 * - Sending the clan broadcast message to a player
 * - Checking who is using clan chat / chat spy / PvP toggle
 */
public class ClanChatExample {

    private final ClanPlus api = ClanPlusHook.getAPI();

    /**
     * Sends a message to ALL online members of a clan.
     * This is the main way to broadcast to a clan.
     */
    public void sendAlert(String clanName, String message) {
        api.getClanManager().alertClan(clanName, message);
    }

    /**
     * Sends the configured clan broadcast message to a specific player.
     * Typically used on join to show the clan MOTD/welcome.
     */
    public void sendBroadcast(Player player) {
        api.getClanManager().sendClanBroadCast(player);
    }

    /**
     * Gets all players currently using clan chat mode.
     * (Messages go only to their clan, not global chat)
     */
    public List<Player> getPlayersInClanChat() {
        return api.getClanManager().getPlayerUsingClanChat();
    }

    /**
     * Gets all players currently toggling PvP off with their clan.
     */
    public List<Player> getPlayersWithPvPOff() {
        return api.getClanManager().getPlayerTogglingPvP();
    }

    /**
     * Gets all players currently using chat spy (seeing all clan chats).
     * Typically admins with the clanplus.admin permission.
     */
    public List<Player> getChatSpies() {
        return api.getClanManager().getPlayerUsingChatSpy();
    }

    /**
     * Checks if the server console has chat spy enabled.
     */
    public boolean isConsoleSpy() {
        return api.getClanManager().isConsoleUsingChatSpy();
    }
}
