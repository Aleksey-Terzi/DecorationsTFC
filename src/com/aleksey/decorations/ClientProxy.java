package com.aleksey.decorations;

import com.aleksey.decorations.Core.BlockList;
import com.aleksey.decorations.Render.Blocks.RenderDetailed;
import com.aleksey.decorations.Render.Blocks.RenderLantern;
import com.aleksey.decorations.TESR.TESRGem;
import com.aleksey.decorations.TileEntities.TileEntityGem;
import com.bioxx.tfc.TFCBlocks;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy
{
    public void registerRenderInformation()
    {
        RenderingRegistry.registerBlockHandler(BlockList.LanternRenderId = RenderingRegistry.getNextAvailableRenderId(), new RenderLantern());
        
        RenderingRegistry.registerBlockHandler(TFCBlocks.detailedRenderId, new RenderDetailed());
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
