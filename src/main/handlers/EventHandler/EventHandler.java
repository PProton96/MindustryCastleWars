package main.handlers.EventHandler;

import arc.Events;
import arc.util.Log;
import main.PluginVars;
import main.handlers.EventHandler.Events.BuildEvents;
import main.handlers.EventHandler.Events.PlayerEvents;
import main.handlers.EventHandler.Events.PluginEvents;
import main.handlers.EventHandler.Events.WorldEvents;
import mindustry.game.EventType;

public class EventHandler {
    public static void init() {
        eventsInit();
        listen();
        Log.info(String.format("[%s]: main.handlers.EventHandler initialized.", PluginVars.pluginName));
    }
    public static void eventsInit() {
        PlayerEvents.init();
        BuildEvents.init();
        WorldEvents.init();
        PluginEvents.init();
    }
    public static void listen() {
        Events.on(EventType.WorldLoadEndEvent.class, WorldEvents::WorldLoadEndEvent);
        /* TODO:
         * Установка точек спавна при загрузке мира.
         * Система спавна юнитов врагам или у себя на базе.
         */
        Events.on(EventType.PlayerJoin.class, PlayerEvents::JoinEvent);
        Events.on(EventType.BuildSelectEvent.class, BuildEvents::BuildRestrictions);
        Events.on(EventType.CoreChangeEvent.class, WorldEvents::CoreChangeEvent);
    }
}
