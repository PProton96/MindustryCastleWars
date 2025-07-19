package main;

import mindustry.content.Blocks;
import mindustry.world.Block;

import java.util.*;

public class PluginVars{
    public static final String pluginName = "Castle Wars";
    public static final int starterIncome = 15;
    public static final int starterBalance = 45;
    public static final Set<Block> allowedFloors = new HashSet<>(Arrays.asList(
            Blocks.metalFloor2,
            Blocks.metalFloor3
    ));
    public static final Set<Block> bannedBlocks = new HashSet<>(Arrays.asList(
            Blocks.duo,
            Blocks.scatter,
            Blocks.scorch,
            Blocks.hail,
            Blocks.wave,
            Blocks.lancer,
            Blocks.arc,
            Blocks.parallax,
            Blocks.swarmer,
            Blocks.salvo,
            Blocks.segment,
            Blocks.tsunami,
            Blocks.fuse,
            Blocks.ripple,
            Blocks.cyclone,
            Blocks.foreshadow,
            Blocks.spectre,
            Blocks.meltdown,
            Blocks.breach,
            Blocks.diffuse,
            Blocks.sublimate,
            Blocks.titan,
            Blocks.disperse,
            Blocks.afflict,
            Blocks.lustre,
            Blocks.scathe,
            Blocks.smite,
            Blocks.malign,
            Blocks.overdriveDome,
            Blocks.overdriveProjector
    ));
    /* TODO:
     * Сделать нормальный шаблон Tower
     * Сделать классы башен для каждой турели со своими апгрейдами и ценами
     * Реализовать систему апгрейдов
     */
    public class Upgrade {
        int cost;
        String name;
        String description;
        public Upgrade(int cost, String name, String description) {
            this.cost = cost;
            this.name = name;
            this.description = description;
        }
    }
    public abstract class Tower {
        public final int cost;
        public final int size;
        public final Block block;
        public final List<Upgrade> upgrades = new ArrayList<>();
        public Tower(int cost, Block block) {
            this.block = block;
            this.size = block.size;
            this.cost = cost;
        }
        public void addUpgrade(Upgrade upgrade) {
            upgrades.add(upgrade);
        }
    }
    public class DuoTower extends Tower {
        int cost = 100;
        Block block = Blocks.duo;
        public DuoTower(int cost, Block block) {
            super(cost, block);
        }

    }
    public static class PlayerData {
        public int balance;
        public int income;
        public PlayerData(int balance, int income) {
            this.balance = balance;
            this.income = income;
        }
    }
    public static HashMap<String, PlayerData> dataMap = new HashMap<>();
    /*
    * Хеш-таблица, хранящая данные в формате
    * {String uuid: PlayerData(data)}
    */
    public static PlayerData getOrCreateData(String uuid) {
        return dataMap.computeIfAbsent(uuid, k ->(new PlayerData(starterBalance, starterIncome)));
    }
    /*
     * Метод получения данных из хеш-таблицы.
     * Если данных нет - заполняем предварительно установленными значениями.
     * */
    public static int getBalance(String uuid) {
        PlayerData data = dataMap.computeIfAbsent(uuid, k ->(new PlayerData(starterBalance, starterIncome)));
        return data.balance;
    }
    public static int getIncome(String uuid) {
        PlayerData data = dataMap.computeIfAbsent(uuid, k ->(new PlayerData(starterBalance, starterIncome)));
        return data.income;
    }
}