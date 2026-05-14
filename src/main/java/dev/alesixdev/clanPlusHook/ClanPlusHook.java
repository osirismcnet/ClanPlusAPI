package dev.alesixdev.clanPlusHook;

import com.cortezromeo.clansplus.api.ClanPlus;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * ClanPlusHook — Example plugin showing ClansPlus API integration.
 * See the {@code examples} package for real usage of every API feature.
 */
public final class ClanPlusHook extends JavaPlugin {

    private static ClanPlus clansPlusAPI;

    @Override
    public void onEnable() {
        if (Bukkit.getPluginManager().getPlugin("ClansPlus") == null) {
            getLogger().severe("ClansPlus not found. Disabling plugin...");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        RegisteredServiceProvider<ClanPlus> registration =
                Bukkit.getServicesManager().getRegistration(ClanPlus.class);

        if (registration == null) {
            getLogger().severe("Could not obtain ClansPlus API. Disabling plugin...");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        clansPlusAPI = registration.getProvider();
        getLogger().info("ClanPlusHook enabled. ClansPlus API connected.");
    }

    @Override
    public void onDisable() {
        getLogger().info("ClanPlusHook disabled.");
    }

    public static ClanPlus getAPI() {
        return clansPlusAPI;
    }
}