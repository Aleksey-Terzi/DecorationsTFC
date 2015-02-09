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
import com.bioxx.tfc.api.Constant.Global;

import cpw.mods.fml.common.registry.GameRegistry;

public class BlockList
{
    public static int LanternRenderId;
    public static int MudBrickRawRenderId;
    
    public static Block[] Lanterns;
    public static Block[] Gems;
    public static Block Alabaster;
    public static Block[] MudBrickRaws;
    public static Block MudBricks;
    public static Block MudBricks2;
    
    public static void registerBlocks()
    {
        if(DecorationsMod.isLanternsEnabled)
        {
            for(int i = 0; i < Lanterns.length; i++)
                GameRegistry.registerBlock(Lanterns[i], ItemLantern.class, Lanterns[i].getUnlocalizedName().substring(5));
        }
        
        if(DecorationsMod.isGemsEnabled)
        {
            for(int i = 0; i < Gems.length; i++)
                GameRegistry.registerBlock(Gems[i], Gems[i].getUnlocalizedName().substring(5));
        }
        
        GameRegistry.registerBlock(Alabaster, ItemAlabaster.class, Alabaster.getUnlocalizedName().substring(5));
        
        for(int i = 0; i < MudBrickRaws.length; i++)
            GameRegistry.registerBlock(MudBrickRaws[i], ItemMudBrickRaw.class, MudBrickRaws[i].getUnlocalizedName().substring(5));
        
        GameRegistry.registerBlock(MudBricks, ItemMudBricks.class, MudBricks.getUnlocalizedName().substring(5));
        GameRegistry.registerBlock(MudBricks2, ItemMudBricks.class, MudBricks2.getUnlocalizedName().substring(5));
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
        
        if(DecorationsMod.isGemsEnabled)
        {
            //Gems
            Gems = new Block[Constants.Gems.length];
            
            for(int i = 0; i < Gems.length; i++)
            {
                GemInfo info = Constants.Gems[i];
                String name = "Gem." + info.GemName; 
                
                Gems[i] = new BlockCustomGem(info).setBlockName(name).setHardness(0.25f);
            }
        }
        
        //Gypsum
        Alabaster = new BlockAlabaster().setBlockName("Alabaster");
        
        //Mud Bricks
        MudBrickRaws = new Block[Global.STONE_ALL.length];
        
        for(int i = 0; i < MudBrickRaws.length; i++)
            MudBrickRaws[i] = new BlockMudBrickRaw(i).setBlockName("MudBrickRaw." + Global.STONE_ALL[i].replaceAll(" ", ""));
        
        MudBricks = new BlockMudBricks(0).setBlockName("MudBricks");
        MudBricks2 = new BlockMudBricks(16).setBlockName("MudBricks2");
    }
}
