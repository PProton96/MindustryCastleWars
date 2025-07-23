package main.handlers.MenuHandler;

import arc.util.Log;
import main.PluginVars;
import mindustry.gen.Call;
import mindustry.net.NetConnection;

public class MenuHandler {
    public static void init() {
        Log.info(String.format("[%s]: main.handlers.MenuHandler.MenuHandler initialized.", PluginVars.pluginName));
    }
    /* Simplest Menu Template:
     *
    Call.menu(
                playerCon,
                1,
                "title",
                "message",
                new String[][] {
                        {"button1"},{}
                }
        );
     *
     * Some information
     * NetConnection(player connection), menuID(id), title, message, options(buttons)
     * Options format is like {{"buttons row 1"},{"buttons row 2"}}
     */
    public static void showWelcomeMenu(NetConnection playerCon) {
        Call.menu(
                playerCon,
                1,
                "[purple]<<< Welcome! >>>",
                "Welcome!",
                new String[][] {
                        {"close"},{"close2"}
                }
        );
    }
}
