package main.handlers.EventHandler;

import arc.Events;
import arc.util.Log;
import main.PluginVars;
import main.handlers.EventHandler.Events.BuildEvents;
import main.handlers.EventHandler.Events.PlayerEvents;
import mindustry.game.EventType;

public class EventHandler {
    public static void init() {
        Log.info(String.format("[%s]: main.handlers.EventHandler initialized.", PluginVars.pluginName));
        eventsInit();
        listen();
    }
    public static void eventsInit() {
        PlayerEvents.init();
        BuildEvents.init();
    }
    public static void listen() {
        Events.on(EventType.WorldLoadEndEvent.class, event -> {
            Log.info(String.format("[%s]: WorldLoadEndEvent activation", PluginVars.pluginName));
        });
        /* TODO:
         * Очистка хеш-таблицы с данными игроков при загрузке карты.
         * Установка точек спавна при загрузке мира.
         * Система спавна юнитов врагам или у себя на базе.
         */
        Events.on(EventType.PlayerJoin.class, PlayerEvents::JoinEvent);
        Events.on(EventType.BuildSelectEvent.class, BuildEvents::BuildRestrictions);
    }
}
