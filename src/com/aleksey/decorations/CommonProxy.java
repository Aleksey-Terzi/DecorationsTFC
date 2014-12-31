package com.aleksey.decorations;

import com.aleksey.decorations.TileEntities.TileEntityGem;

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
    
    public boolean isRemote()
    {
        return false;
    }
}
