package com.aleksey.decorations.Handlers;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.aleksey.decorations.Core.ItemList;
import com.bioxx.tfc.Core.Recipes;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;

public class CraftingHandler
{
    @SubscribeEvent
    public void onCrafting(ItemCraftedEvent e)
    {
        Item item = e.crafting.getItem();
        ItemStack itemstack = e.crafting;
        int isDmg = e.crafting.getItemDamage();
        IInventory iinventory = e.craftMatrix;

        if(iinventory != null)
        {
            if(item == ItemList.Powder && isDmg == 0)
                com.bioxx.tfc.Handlers.CraftingHandler.handleItem(e.player, e.craftMatrix, Recipes.hammers);
        }
    }
}
