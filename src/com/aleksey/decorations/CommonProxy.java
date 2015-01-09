package com.aleksey.decorations;

import com.aleksey.decorations.Handlers.ServerTickHandler;
import com.aleksey.decorations.TileEntities.TileEntityGem;
import com.aleksey.decorations.TileEntities.TileEntityMudBrickRaw;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy
{
    public void registerRenderInformation()
    {
    }
    
    public void registerTileEntities()
    {
        registerServerTileEntities();
        registerCommonTileEntities();
    }
    
    protected void registerServerTileEntities()
    {
        GameRegistry.registerTileEntity(TileEntityGem.class, "Gem");
    }
    
    protected void registerCommonTileEntities()
    {
        GameRegistry.registerTileEntity(TileEntityMudBrickRaw.class, "MudBrickRow");
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
