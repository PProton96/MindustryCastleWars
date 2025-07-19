package main.handlers.EventHandler.Events;

import arc.util.Timer;
import main.PluginVars;
import mindustry.game.EventType;
import mindustry.gen.*;

import java.util.List;

import static main.PluginVars.*;
import static mindustry.gen.Groups.player;


public class PlayerEvents {
    public static void JoinEvent(EventType.PlayerJoin event) {
        Player player = event.player;
        String uuid = player.uuid();
        String balance = String.valueOf(getBalance(uuid));
        String income = String.valueOf(getIncome(uuid));
        String text = "[forest]Ваш баланс: [yellow]" + balance + "\n[forest]Ваш доход: [yellow]" + income;
        Timer.schedule(() -> {
            Call.setHudText(player.con, text);
        }, 0f, 0.5f);

        /* Проверяем наличие данных об игроке в хеш-таблице, если их нет - создаём. */
    }
}