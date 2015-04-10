package com.aleksey.decorations;

import net.minecraftforge.common.MinecraftForge;

import com.aleksey.decorations.Core.BlockList;
import com.aleksey.decorations.Core.FluidList;
import com.aleksey.decorations.Core.ItemList;
import com.aleksey.decorations.Core.Recipes;
import com.aleksey.decorations.Core.Player.PlayerTracker;
import com.aleksey.decorations.Handlers.ChunkEventHandler;
import com.aleksey.decorations.Handlers.CraftingHandler;
import com.aleksey.decorations.Handlers.Network.InitClientWorldPacket;
import com.bioxx.tfc.TerraFirmaCraft;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.ExistingSubstitutionException;

@Mod(modid="DecorationsTFC", name="Decorations", version="1.0.17", dependencies="required-after:terrafirmacraft")
public class DecorationsMod
{
    @Instance("DecorationsTFC")
    public static DecorationsMod instance;

    @SidedProxy(clientSide = "com.aleksey.decorations.ClientProxy", serverSide = "com.aleksey.decorations.CommonProxy")
    public static CommonProxy proxy;
    
    public static boolean isLanternsEnabled;
    public static boolean isGemsEnabled;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) throws ExistingSubstitutionException
    {
        DecorationConfig.loadConfig(event);
        
        isLanternsEnabled = !Loader.isModLoaded("LanternsTFC");
        
        System.out.println("DecorationsTFC Lanterns Enabled = " + String.valueOf(isLanternsEnabled));
        
        proxy.registerTickHandler();
        proxy.registerTileEntities();
        
        FluidList.register();
        
        BlockList.loadBlocks();
        BlockList.registerBlocks();
                
        ItemList.setup();
        
        //proxy.registerGuiHandler();
    }
  
    @EventHandler
    public void initialize(FMLInitializationEvent event)
    {
        TerraFirmaCraft.packetPipeline.registerPacket(InitClientWorldPacket.class);
        
        FMLCommonHandler.instance().bus().register(new PlayerTracker());
        
        //Register Crafting Handler
        FMLCommonHandler.instance().bus().register(new CraftingHandler());
        
        // Register the Chunk Load/Save Handler
        MinecraftForge.EVENT_BUS.register(new ChunkEventHandler());
        
        proxy.registerRenderInformation();
        
        FluidList.registerFluidContainers();
        
        Recipes.registerRecipes();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
    }
}