package main;

import arc.util.Log;
import mindustry.content.Blocks;
import mindustry.content.Items;
import mindustry.type.Item;
import mindustry.world.Block;

import java.util.*;

public class PluginVars{
    public static final String pluginName = "Castle Wars";
    public static void init() {
        Log.info(String.format("[%s]: main.PluginVars initialized.", pluginName));
    }
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
    public static final Set<Item> allItems = new HashSet<>(Arrays.asList(
            Items.copper,
            Items.lead,
            Items.graphite,
            Items.metaglass,
            Items.silicon,
            Items.coal,
            Items.sand,
            Items.titanium,
            Items.thorium,
            Items.plastanium,
            Items.phaseFabric,
            Items.surgeAlloy,
            Items.sporePod,
            Items.pyratite,
            Items.blastCompound,
            Items.beryllium,
            Items.tungsten,
            Items.oxide,
            Items.carbide
    ));
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
    public static void deleteAllData(String uuid) {
        dataMap.clear();
        Log.info(String.format("[%s]: Players data was deleted.", pluginName));
    }
    public static void updateBalance(String uuid) {
        PlayerData data = dataMap.get(uuid);
        data.balance += data.income;
        dataMap.put(uuid, data);
    }
    public static void updateIncome(String uuid, int x) {
        PlayerData data = dataMap.get(uuid);
        Log.info(String.format("[%s]: %s income increased by %d", pluginName, uuid, x));
        data.income += x;
        dataMap.put(uuid, data);
    }
    /* Методы получения и обновления данных в хеш-таблице.
     * Если при попытке получить данные в таблице ничего нет - добавить стартовые значения.
     */
    /* TODO:
     * Сделать нормальный шаблон Tower
     * Сделать классы башен для каждой турели со своими апгрейдами и ценами
     * Реализовать систему апгрейдов
     */
    public class Upgrade {
        int cost;
        String name;
        String description;
        Item ammo;
        public Upgrade(int cost, String name, String description, Item ammo) {
            this.cost = cost;
            this.name = name;
            this.description = description;
            this.ammo = ammo;
        }
    }
    public abstract class Tower {
        public final int cost;
        public final int size;
        public final Block block;
        public List<Upgrade> upgrades = new ArrayList<>();
        public List<Upgrade> availableUpgrades = new ArrayList<>();
        public Tower(int cost, Block block) {
            this.block = block;
            this.size = block.size;
            this.cost = cost;
        }
        public void addUpgrade(Upgrade upgrade) {
            availableUpgrades.add(upgrade);
        }
        public void applyUpgrade(Upgrade upgrade) {
            if (availableUpgrades.contains(upgrade)) {
                upgrades.add(upgrade);
            }
        }
    }
    public class DuoTower extends Tower {
        public DuoTower() {
            super(100, Blocks.duo);
            availableUpgrades.add(new Upgrade(100, "Ammo Upgrade #1", "Your turret will get silicon ammo", Items.silicon));
        }
    }
    public class ScatterTower extends Tower {
        public ScatterTower() {
            super(150, Blocks.scatter);
            availableUpgrades.add(new Upgrade(100, "Ammo Upgrade #1", "Your turret will get metaglass ammo", Items.metaglass));
        }
    }
}