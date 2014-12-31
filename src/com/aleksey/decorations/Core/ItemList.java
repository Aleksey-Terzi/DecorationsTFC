package com.aleksey.decorations.Core;

import net.minecraft.item.Item;

import com.aleksey.decorations.Items.ItemCustomGem;

import cpw.mods.fml.common.registry.GameRegistry;

public class ItemList
{
    public static Item GemDiamond;
    
    public static void Setup()
    {
        GemDiamond = new ItemCustomGem().setUnlocalizedName("Diamond");

        GameRegistry.registerItem(GemDiamond, GemDiamond.getUnlocalizedName());
    }
}
