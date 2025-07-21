package main.handlers.MenuHandler;

import arc.util.Log;
import main.PluginVars;
import mindustry.gen.Call;
import mindustry.net.NetConnection;

public class MenuHandler {
    public static void init() {
        Log.info(String.format("[%s]: main.handlers.MenuHandler initialized.", PluginVars.pluginName));
    }
    public static void welcomeMenu(NetConnection playerCon) {
        Call.menu(
                playerCon,
                1,
                "[purple]< Добро пожаловать! >",
                "Режим находится в разработке, тут пока-что ничего не работает как должно.",
                new String[][]{
                        {"понятно"},{"понятно"}
                }
        );
    }
}
