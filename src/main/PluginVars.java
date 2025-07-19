package main;

import mindustry.content.Blocks;
import mindustry.mod.Plugin;
import mindustry.world.Block;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class PluginVars{
    public static final String pluginName = "Castle Wars";
    public static final Set<Block> allowedBlocks = new HashSet<>(Arrays.asList(
            Blocks.metalFloor2,
            Blocks.metalFloor3
    ));
};