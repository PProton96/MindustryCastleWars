package main.handlers.CommandsHandlers;

import arc.util.CommandHandler;
import arc.util.Log;
import main.PluginVars;
import main.handlers.EventHandler.Events.PluginEvents;
import main.handlers.MenuHandler.MenuHandler;
import mindustry.gen.Call;
import mindustry.gen.Player;

import static main.PluginVars.*;

public class ClientCommandsHandler {
    public static void init() {
        Log.info(String.format("[%s]: main.handlers.CommandsHandlers.ClientCommandsHandler initialized.", pluginName));
    }
    public static void accessDenied(Player player) {
        player.sendMessage(String.format("[purple][ [cyan]%s[purple] ][white]: иди нахуй", pluginName));
        /* Набор действий который будет выполняться в случае если игрок не обладает необходимыми для исполнения команды правами.
         * В данном случае - сообщение в чат игроку.
         */
    }
    public static void register(CommandHandler handler) {
        handler.<Player>register("cheat", "Cheats activation command", (args, player) -> {
            if (player.admin) {
                Log.info(String.format("[%s]: Player [%s] activated cheats", PluginVars.pluginName, player.plainName()));
                playersData.put(player.uuid(), new PlayerData(10000000, 100000));
                allItems.forEach(item -> {
                    player.team().core().items().add(item, player.team().core().getMaximumAccepted(item));
                });
            }
            else {accessDenied(player);}
        });
        handler.<Player>register("spawnunit", "<unit> [amount]", "Spawns selected unit at enemy's base.", (args, player) -> {
          if (player.admin) {
              if (args.length == 2) {
                  try {
                      int amount = Integer.parseInt(args[1]);
                      sendableUnits.forEach(data -> {
                          if (data.key.toString().equals(args[0])) {
                              for (int i = 0; i < amount; i++) {
                                  PluginEvents.spawnUnit(player.team(), data.key);
                              }
                              Log.info(String.format("[%s]: Player %s spawned %d %s's", PluginVars.pluginName, player.plainName(), amount, args[0]));
                              Log.info(player.team());
                          }
                      });
                  } catch (NumberFormatException e) {
                      Log.warn(String.format("[%s]: main.handlers.CommandsHandlers.ClientCommandsHandler catched exception NumberFormatException", PluginVars.pluginName));
                  }
              }
              sendableUnits.forEach(data -> {
                  if (data.key.toString().equals(args[0])) {
                      PluginEvents.spawnUnit(player.team(), data.key);
                      Log.info(String.format("[%s]: Player %s spawned %s", PluginVars.pluginName, player.plainName(), args[0]));
                      Log.info(player.team());
                  }
              });
          }
          else {accessDenied(player);}
        });
        handler.<Player>register("uichaos", "Starts ui chaos.", (args, player) -> {
            if (!player.admin) {
                accessDenied(player);
                return;
            }
            Call.menu(
                    player.con,
                    1,
                    "menu",
                    "Title",
                    new String[][]{
                            {"OK"},{"ok2"}
                    }
            );
            Call.announce(player.con, "ANNOUNCE.MESSAGE");
            Call.followUpMenu(
                    player.con,
                    1,
                    "followUpMenu",
                    "message",
                    new String[][]{
                            {"opt1.1"},{"opt2.1"}
                    }
            );
            Call.infoMessage(player.con, "infoMessage");
            Call.infoPopup(player.con, "infoPopup.message", 3f, 5, 5, 5, 5, 5);
            Call.infoToast(player.con, "toast.message", 5f);
        });
        // Просто кусок дерьма для того, чтобы понять какой метод что вызывает на экране.
        handler.<Player>register("menu", "Calls menu.", (args, player) -> {
            MenuHandler.showWelcomeMenu(player.con);
        });
    }
}
