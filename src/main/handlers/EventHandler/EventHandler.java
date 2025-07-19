package main.handlers.EventHandler;

import arc.Events;
import mindustry.game.EventType;
import mindustry.world.Build;

public class EventHandler {
    public static void init() {
        Events.on(EventType.BuildSelectEvent.class, BuildEvents::BuildRestrictions);
    }
}
