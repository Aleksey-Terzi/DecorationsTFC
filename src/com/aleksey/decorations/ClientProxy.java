package com.aleksey.decorations;

import com.aleksey.decorations.TESR.TESRGem;
import com.aleksey.decorations.TileEntities.TileEntityGem;

import cpw.mods.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy
{
    public void registerRenderInformation()
    {
    }
    
    public void registerTileEntities()
    {
        ClientRegistry.registerTileEntity(TileEntityGem.class, "Gem", new TESRGem());
    }

    public boolean isRemote()
    {
        return true;
    }
}
