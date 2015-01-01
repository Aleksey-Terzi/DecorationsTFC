package com.aleksey.decorations.Core;

import net.minecraft.block.Block;

import com.aleksey.decorations.Blocks.BlockCustomGem;
import com.aleksey.decorations.Items.ItemCustomGem;

import cpw.mods.fml.common.registry.GameRegistry;

public class BlockList
{
    private static String[] _gemNames = new String[]{ "Chipped", "Flawed", "Normal", "Flawless", "Exquisite" };
    private static int[] _gemLightLevels = new int[]{ 11, 12, 13, 14, 15 };
    
    public static Block[] Gems;
    
    public static void registerBlocks()
    {
        for(int i = 0; i < _gemNames.length; i++)
            GameRegistry.registerBlock(Gems[i], Gems[i].getUnlocalizedName().substring(5));
    }
    
    public static void loadBlocks()
    {
        Gems = new Block[_gemNames.length];
        
        for(int i = 0; i < _gemNames.length; i++)
            Gems[i] = new BlockCustomGem(_gemLightLevels[i]).setBlockName("Gem." + _gemNames[i]).setHardness(0.25f);
    }
}
