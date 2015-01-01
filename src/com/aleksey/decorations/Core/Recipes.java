package com.aleksey.decorations.Core;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.bioxx.tfc.TFCItems;
import com.bioxx.tfc.api.TFCCrafting;

import cpw.mods.fml.common.registry.GameRegistry;

public class Recipes
{
    public static void registerRecipes()
    {
        if (TFCCrafting.diamondConversion == true)
        {
            GameRegistry.addShapelessRecipe(new ItemStack(Items.diamond, 1), new Object[] {new ItemStack(TFCItems.GemDiamond,1,2)});
            GameRegistry.addShapelessRecipe(new ItemStack(Items.diamond, 2), new Object[] {new ItemStack(TFCItems.GemDiamond,1,3)});
            GameRegistry.addShapelessRecipe(new ItemStack(Items.diamond, 3), new Object[] {new ItemStack(TFCItems.GemDiamond,1,4)});
            GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.GemDiamond,1,2), new Object[] {new ItemStack(Items.diamond)});
            GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.GemDiamond,1,3), new Object[] {new ItemStack(Items.diamond), new ItemStack(Items.diamond)});
            GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.GemDiamond,1,4), new Object[] {new ItemStack(Items.diamond), new ItemStack(Items.diamond), new ItemStack(Items.diamond)});
        }
        
        if (TFCCrafting.emeraldConversion == true)
        {
            GameRegistry.addShapelessRecipe(new ItemStack(Items.emerald, 1), new Object[] {new ItemStack(TFCItems.GemEmerald,1,2)});
            GameRegistry.addShapelessRecipe(new ItemStack(Items.emerald, 2), new Object[] {new ItemStack(TFCItems.GemEmerald,1,3)});
            GameRegistry.addShapelessRecipe(new ItemStack(Items.emerald, 3), new Object[] {new ItemStack(TFCItems.GemEmerald,1,4)});
            GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.GemEmerald,1,2), new Object[] {new ItemStack(Items.emerald)});
            GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.GemEmerald,1,3), new Object[] {new ItemStack(Items.emerald), new ItemStack(Items.emerald)});
            GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.GemEmerald,1,4), new Object[] {new ItemStack(Items.emerald), new ItemStack(Items.emerald), new ItemStack(Items.emerald)});
        }
    }
}
