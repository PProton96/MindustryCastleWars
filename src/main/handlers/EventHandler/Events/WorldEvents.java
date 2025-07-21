package main.handlers.EventHandler.Events;

import arc.Core;
import arc.util.Log;
import arc.util.Timer;
import main.PluginVars;
import mindustry.Vars;
import mindustry.content.Blocks;
import mindustry.core.GameState;
import mindustry.core.Logic;
import mindustry.game.EventType;
import mindustry.game.Team;
import mindustry.world.blocks.storage.CoreBlock;

public class WorldEvents {
    public static void init() {
        Log.info(String.format("[%s]: EventHandler.Events.WorldEvents initialized.", PluginVars.pluginName));
    }
    public static void WorldLoadEndEvent(EventType.WorldLoadEndEvent event) {
        Log.info(String.format("[%s]: WorldLoadEndEvent activation.", PluginVars.pluginName));
        Vars.world.tiles.forEach(tile -> {
            if (tile.block() == Blocks.powerVoid && tile.team() == Team.sharded) {
                PluginVars.shardedSpawns.add(tile);
                tile.setNet(Blocks.air);
            }
            else if (tile.block() == Blocks.powerVoid && tile.team() == Team.blue) {
                PluginVars.blueSpawns.add(tile);
                tile.setNet(Blocks.air);
            }
            else if (tile.block() == Blocks.powerVoid && tile.team() != Team.blue && tile.team() != Team.sharded){
                Log.warn(String.format("[%s]: Encountered error while inspecting spawn tiles. Expected tile.team() = Team.sharded or Team.blue, got %s.", PluginVars.pluginName, tile.team().toString()));
            }
            /* WIP
             * Система установки спавнов и записи их в отдельные переменные.
             * Учтены исключения когда тайлам присвоены неверные команды которые плагин не использует.
             */
        });
    }
    public static void CoreChangeEvent(EventType.CoreChangeEvent event) {
        //TODO пофиксить краш сервера от этого ивента и сделать чтобы победа засчитывалась оставшейся команде.
        /*if (event.tile.build instanceof CoreBlock.CoreBuild) {
            for (Team team : Team.all) {
                if (team.active() && !team.cores().isEmpty() || team.cores().size < 1) {
                    Logic.gameOver(team);
                }
            }
        }*/
    }
}
