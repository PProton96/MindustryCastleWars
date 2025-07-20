package main.handlers.EventHandler.Events;

import arc.util.Log;
import main.PluginVars;
import mindustry.game.Team;
import mindustry.type.UnitType;

public class PluginEvents {
    public static void init() {
        Log.info(String.format("[%s]: main.handlers.EventHandler.Events.PluginEvents initialized.", PluginVars.pluginName));
    }
    public static void spawnUnit(Team invokerTeam, UnitType unit) {
        if (PluginVars.navalUnits.contains(unit)) {
            if (invokerTeam == Team.sharded) {
                PluginVars.shardedSpawns.select(tile -> tile.floor().isLiquid).random();
            }
            else if (invokerTeam == Team.blue) {
                PluginVars.blueSpawns.select(tile -> tile.floor().isLiquid).random();
            }
        }
        else if (invokerTeam == Team.sharded) {
            PluginVars.shardedSpawns.select(tile -> tile.floor().solid).random();
        }
        else if (invokerTeam == Team.blue) {
            PluginVars.blueSpawns.select(tile -> tile.floor().solid).random();
        }
        // TODO
    }
}
