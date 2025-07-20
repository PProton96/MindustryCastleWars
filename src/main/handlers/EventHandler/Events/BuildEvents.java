package main.handlers.EventHandler.Events;

import arc.util.Log;
import main.PluginVars;
import mindustry.game.EventType;
import mindustry.gen.Call;
import mindustry.gen.Player;
import mindustry.world.Block;
import mindustry.world.Tile;

public class BuildEvents {
    public static void init() {
        Log.info(String.format("[%s]: EventHandler.Events.BuildEvents initialized.", PluginVars.pluginName));
    }
    public static void BuildRestrictions(EventType.BuildSelectEvent event) {
        // Запрещает игрокам строить турели и ускоряющие проекторы/купола на непредназначенных для этого плитках
        Tile tile = event.tile;
        Block block = event.builder.buildPlan().block;
        Log.info("e.t.block() == " + block.toString()); // debug
        if (!event.breaking && event.builder.getPlayer() != null && !PluginVars.allowedFloors.contains(tile.floor()) && PluginVars.bannedBlocks.contains(block)) {
            Player player = event.builder.getPlayer();
            Call.removeQueueBlock(player.con, tile.x, tile.y, false);
            Call.removeTile(tile);
            Call.label(player.con, "[#f]✖", 1f, tile.worldx(), tile.worldy());
        };
    }
}
