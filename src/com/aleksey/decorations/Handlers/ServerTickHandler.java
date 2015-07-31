package com.aleksey.decorations.Handlers;

import net.minecraft.world.World;

import com.aleksey.decorations.Core.Recipes;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.common.gameevent.TickEvent.WorldTickEvent;

public class ServerTickHandler
{
    @SubscribeEvent
    public void onServerWorldTick(WorldTickEvent event)
    {
        World world = event.world;
        
        if(event.phase == Phase.START)
        {
            if(world.provider.dimensionId == 0 && !Recipes.areAnvilRecipesRegistered())
            {
                Recipes.registerAnvilRecipes(world);
            }
        }
    }
}
