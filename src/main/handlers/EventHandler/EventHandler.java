package main.handlers.EventHandler;

import arc.Events;
import main.handlers.EventHandler.Events.BuildEvents;
import main.handlers.EventHandler.Events.PlayerEvents;
import mindustry.game.EventType;

public class EventHandler {
    public static void init() {
        Events.on(EventType.PlayerJoin.class, PlayerEvents::JoinEvent);
        Events.on(EventType.BuildSelectEvent.class, BuildEvents::BuildRestrictions);
    }
}
