package com.aleksey.decorations.Core;

import net.minecraft.block.Block;

import com.aleksey.decorations.Blocks.BlockCustomGem;
import com.aleksey.decorations.Items.ItemCustomGem;

import cpw.mods.fml.common.registry.GameRegistry;

public class BlockList
{
    public static int GemRenderId;
    
    public static Block Gem;
    
    public static void registerBlocks()
    {
        GameRegistry.registerBlock(Gem, Gem.getUnlocalizedName().substring(5));
    }
    
    public static void loadBlocks()
    {
        Gem = new BlockCustomGem().setBlockName("Gem").setHardness(0.25f);
    }
}
