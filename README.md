# ClanPlusHook

Spigot plugin that hooks into the [ClansPlus](https://github.com/osirismcnet/ClansPlus) API (3.0).

Built as a reference for developers who want to integrate with ClansPlus — every API feature is covered with real, working examples.

## Examples

Located in `src/main/java/.../examples/`:

| Class | What it covers |
|---|---|
| `ClanLifecycleExample` | Create & delete clans |
| `RankManagementExample` | Promote, demote & transfer ownership |
| `MemberManagementExample` | Add players, check membership, list members |
| `ClanSettingsExample` | Display name, MOTD, spawn, permissions, Discord |
| `ClanChatExample` | Alerts, broadcasts, chat spy, PvP toggle |
| `ClanStorageExample` | Open & inspect shared inventories |
| `AllianceExample` | Manage alliances between clans |
| `LeaderboardExample` | Score, size & date rankings |
| `PlayerDataExample` | Player stats, rank, activity, cache |
| `DatabaseExample` | Load, save, bulk ops, memory vs storage |

## Setup

**Maven:**
```xml
<repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
</repository>

<dependency>
    <groupId>com.github.osirismcnet.ClansPlus</groupId>
    <artifactId>clansplus-api</artifactId>
    <version>3.0</version>
    <scope>provided</scope>
</dependency>
```

**API initialization:**
```java
ClanPlus api = Bukkit.getServicesManager().getRegistration(ClanPlus.class).getProvider();
```

## Build

```bash
mvn clean package
```

Requires Java 21. Output JAR goes in `target/`.

## Note

ClansPlus does **not** fire custom Bukkit events. Use standard Bukkit events + API queries to react to clan state changes.
