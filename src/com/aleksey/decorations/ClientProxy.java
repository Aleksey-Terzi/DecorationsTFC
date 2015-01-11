package com.aleksey.decorations;

import com.aleksey.decorations.Core.BlockList;
import com.aleksey.decorations.Render.Blocks.RenderLantern;
import com.aleksey.decorations.Render.Blocks.RenderMudBrickRaw;
import com.aleksey.decorations.TESR.TESRGem;
import com.aleksey.decorations.TileEntities.TileEntityGem;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy
{
    @Override
    public void registerRenderInformation()
    {
        RenderingRegistry.registerBlockHandler(BlockList.LanternRenderId = RenderingRegistry.getNextAvailableRenderId(), new RenderLantern());
        RenderingRegistry.registerBlockHandler(BlockList.MudBrickRawRenderId = RenderingRegistry.getNextAvailableRenderId(), new RenderMudBrickRaw());
    }
    
    @Override
    public void registerTileEntities()
    {
        registerCommonTileEntities();
        
        ClientRegistry.registerTileEntity(TileEntityGem.class, "Gem", new TESRGem());
    }

    @Override
    public boolean isRemote()
    {
        return true;
    }
}
