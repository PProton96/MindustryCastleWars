package main.handlers.EventHandler.Events;

import arc.graphics.Color;
import arc.util.Log;
import arc.util.Timer;
import main.PluginVars;
import mindustry.content.Blocks;
import mindustry.content.Fx;
import mindustry.game.Team;
import mindustry.gen.Call;
import mindustry.gen.Groups;
import mindustry.type.UnitType;
import mindustry.world.Tile;

public class PluginEvents {
    public static void init() {
        scheduleTasks();
        Log.info(String.format("[%s]: main.handlers.EventHandler.Events.PluginEvents initialized.", PluginVars.pluginName));
    }
    public static void scheduleTasks() {
        Timer.schedule(() -> {
            Groups.player.forEach(player -> {
                PluginVars.PlayerData data = PluginVars.getOrCreateData(player.uuid());
                PluginVars.updateBalance(player.uuid());
                String message = String.format("[lime]Ваш баланс: [green]%d\n[lime]Ваш доход: [green]%d", data.balance, data.income);
                Call.setHudText(player.con, message);
            });
        }, 0f, 1.5f);
        /* Обновляем баланс игрокам каждые 1.5 секунды.
         */
        Groups.unit.forEach(unit -> {
            Tile tile = unit.tileOn();
            UnitType uType = unit.type;
            if (tile.floor() == Blocks.space && !PluginVars.playersUnits.contains(uType)) {
                unit.kill();
            }
        });
        Timer.schedule(() -> {
            PluginVars.shardedSpawns.forEach(tile -> {
                Call.effect(Fx.circleColorSpark, tile.worldx(), tile.worldy(), 0f, Color.yellow);
            });
            PluginVars.blueSpawns.forEach(tile -> {
                Call.effect(Fx.circleColorSpark, tile.worldx(), tile.worldy(), 0f, Color.blue);
            });
        }, 0f, 1f);
    }
    public static void spawnUnit(Team invokerTeam, UnitType unit) {
        Tile spawnTile = null;
        if (PluginVars.navalUnits.contains(unit)) {
            if (invokerTeam == Team.sharded) {
                spawnTile = PluginVars.shardedSpawns.select(tile -> tile.floor().isLiquid).random();
                Log.info("Selecting for naval team sharded" + spawnTile.toString());
            }
            else if (invokerTeam == Team.blue) {
                spawnTile = PluginVars.blueSpawns.select(tile -> tile.floor().isLiquid).random();
                Log.info("Selecting for naval team blue" + spawnTile.toString());
            }
        }
        else if (invokerTeam == Team.sharded) {
            spawnTile = PluginVars.shardedSpawns.select(tile -> !tile.floor().isLiquid).random();
            Log.info("Selecting for non-naval team sharded" + spawnTile.toString());
        }
        else if (invokerTeam == Team.blue) {
            spawnTile = PluginVars.blueSpawns.select(tile -> !tile.floor().isLiquid).random();
            Log.info("Selecting for non-naval team blue" + spawnTile.toString());
        }
        if (spawnTile == null) {
            Log.warn(String.format("[%s]: Encountered an error while attempting to spawn unit %s. Tile spawnTile wasn't initialized.", PluginVars.pluginName, unit.toString()));
        }
        else {unit.spawn(spawnTile.getX(), spawnTile.getY());}
        // TODO
    }
}
