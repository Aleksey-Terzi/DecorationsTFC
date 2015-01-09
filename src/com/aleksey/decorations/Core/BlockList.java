package com.aleksey.decorations.Core;

import net.minecraft.block.Block;

import com.aleksey.decorations.DecorationsMod;
import com.aleksey.decorations.Blocks.BlockAlabaster;
import com.aleksey.decorations.Blocks.BlockCustomGem;
import com.aleksey.decorations.Blocks.BlockCustomLantern;
import com.aleksey.decorations.Blocks.BlockMudBrickRaw;
import com.aleksey.decorations.Blocks.BlockMudBricks;
import com.aleksey.decorations.Core.Data.GemInfo;
import com.aleksey.decorations.Core.Data.LanternInfo;
import com.aleksey.decorations.Items.ItemBlocks.ItemAlabaster;
import com.aleksey.decorations.Items.ItemBlocks.ItemLantern;
import com.aleksey.decorations.Items.ItemBlocks.ItemMudBrickRaw;
import com.aleksey.decorations.Items.ItemBlocks.ItemMudBricks;

import cpw.mods.fml.common.registry.GameRegistry;

public class BlockList
{
    public static int LanternRenderId;
    public static int MudBrickRawRenderId;
    
    public static Block[] Lanterns;
    public static Block[] Gems;
    public static Block Alabaster;
    public static Block MudBrickRaw;
    public static Block MudBricks;
    
    public static void registerBlocks()
    {
        if(DecorationsMod.isLanternsEnabled)
        {
            for(int i = 0; i < Lanterns.length; i++)
                GameRegistry.registerBlock(Lanterns[i], ItemLantern.class, Lanterns[i].getUnlocalizedName().substring(5));
        }
        
        for(int i = 0; i < Gems.length; i++)
            GameRegistry.registerBlock(Gems[i], Gems[i].getUnlocalizedName().substring(5));
        
        GameRegistry.registerBlock(Alabaster, ItemAlabaster.class, Alabaster.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock(MudBrickRaw, ItemMudBrickRaw.class, MudBrickRaw.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock(MudBricks, ItemMudBricks.class, MudBricks.getUnlocalizedName().substring(5));
    }
    
    public static void loadBlocks()
    {
        if(DecorationsMod.isLanternsEnabled)
        {
            //Lanterns
            Lanterns = new Block[Constants.Lanterns.length];
            
            for(int i = 0; i < Constants.Lanterns.length; i++)
            {
                LanternInfo info = Constants.Lanterns[i];
                String name = "Lantern." + info.LanternName;
                
                Lanterns[i] = new BlockCustomLantern(info).setBlockName(name);
            }
        }
        
        //Gems
        Gems = new Block[Constants.Gems.length];
        
        for(int i = 0; i < Gems.length; i++)
        {
            GemInfo info = Constants.Gems[i];
            String name = "Gem." + info.GemName; 
            
            Gems[i] = new BlockCustomGem(info).setBlockName(name).setHardness(0.25f);
        }
        
        //Other
        Alabaster = new BlockAlabaster().setBlockName("Alabaster");
        MudBrickRaw = new BlockMudBrickRaw().setBlockName("MudBrickRaw");
        MudBricks = new BlockMudBricks().setBlockName("MudBricks");
    }
}
