package com.aleksey.decorations;

import net.minecraftforge.common.config.Configuration;

import com.aleksey.decorations.Core.Constants;
import com.aleksey.decorations.Core.Data.GemInfo;
import com.aleksey.decorations.Core.Data.LanternInfo;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class DecorationConfig
{
    private static final String CategoryName_Lanterns = "Lanterns Light Levels";
    private static final String CategoryName_Gems = "Gems Light Levels";
    private static final String CategoryName_Modules = "Modules";
    
    public static void loadConfig(FMLPreInitializationEvent event)
    {
        Configuration config = new Configuration(event.getSuggestedConfigurationFile());
        
        loadLanterns(config);
        loadGems(config);
        loadModules(config);
        
        config.save();
    }
    
    private static void loadLanterns(Configuration config)
    {
        config.setCategoryComment(CategoryName_Lanterns, "Light level must be specified in a range 1 - 15");
        
        for(int i = 0; i < Constants.Lanterns.length; i++)
        {
            LanternInfo info = Constants.Lanterns[i];
            
            info.LightLevel = config.get(CategoryName_Lanterns, info.LanternName, info.LightLevel).getInt();
            
            if(info.LightLevel < 1)
                info.LightLevel = 1;
            else if(info.LightLevel > 15)
                info.LightLevel = 15;
        }
    }
    
    private static void loadGems(Configuration config)
    {
        config.setCategoryComment(CategoryName_Gems, "Light level must be specified in a range 1 - 15");
        
        for(int i = 0; i < Constants.Gems.length; i++)
        {
            GemInfo info = Constants.Gems[i];
            
            info.LightLevel = config.get(CategoryName_Gems, info.GemName, info.LightLevel).getInt();
            
            if(info.LightLevel < 0)
                info.LightLevel = 0;
            else if(info.LightLevel > 15)
                info.LightLevel = 15;
        }
    }
    
    private static void loadModules(Configuration config)
    {
        DecorationsMod.instance.isGemsEnabled = config.get(CategoryName_Modules, "GemsEnabled", true).getBoolean();
    }
}
