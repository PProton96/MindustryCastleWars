package main;

import mindustry.content.Blocks;
import mindustry.world.Block;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class PluginVars{
    public static final String pluginName = "Castle Wars";
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
            Blocks.malign
    ));
};