package main.handlers.EventHandler;

import arc.util.Log;
import main.PluginVars;
import mindustry.game.EventType;
import mindustry.gen.Call;
import mindustry.gen.Player;
import mindustry.world.Tile;

public class BuildEvents {
    public static void BuildRestrictions(EventType.BuildSelectEvent event) {
        // Should be preventing players from building turrets on unallowed floor blocks
        Log.info("e.t.block() == " + event.tile.block().toString());
        if (!event.breaking && event.builder.getPlayer() != null && !PluginVars.allowedBlocks.contains(event.tile.block())) {
            Tile tile = event.tile;
            Player player = event.builder.getPlayer();
            Call.removeQueueBlock(player.con, tile.x, tile.y, false);
            Call.removeTile(tile);
            Call.label(player.con, "[red]You are not allowed to build it here", 1f, tile.worldx(), tile.worldy());
        };
    }
}
