package com.aleksey.decorations;

import com.aleksey.decorations.TileEntities.TileEntityGem;
import com.aleksey.decorations.Handlers.ServerTickHandler;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy
{
    public void registerRenderInformation()
    {
    }
    
    public void registerTileEntities()
    {
        GameRegistry.registerTileEntity(TileEntityGem.class, "Gem");
    }
    
    public void registerTickHandler()
    {
        FMLCommonHandler.instance().bus().register(new ServerTickHandler());
    }
    
    public boolean isRemote()
    {
        return false;
    }
}
