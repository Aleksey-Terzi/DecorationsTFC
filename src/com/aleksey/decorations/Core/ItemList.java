package com.aleksey.decorations.Core;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import com.aleksey.decorations.DecorationsMod;
import com.aleksey.decorations.Core.Data.LanternInfo;
import com.aleksey.decorations.Items.ItemBrush;
import com.aleksey.decorations.Items.ItemCustomGem;
import com.aleksey.decorations.Items.ItemGypsumPowder;
import com.aleksey.decorations.Items.ItemLanternCore;
import com.aleksey.decorations.Items.ItemLiquidDye;
import com.aleksey.decorations.Items.ItemPlaster;
import com.bioxx.tfc.api.TFCItems;

import cpw.mods.fml.common.registry.ExistingSubstitutionException;
import cpw.mods.fml.common.registry.GameRegistry;

public class ItemList
{
    public static Item[] LanternCores;
    public static Item Powder;
    public static Item Plaster;
    public static Item LiquidDye;
    public static Item Brush;
    
    public static void setup() throws ExistingSubstitutionException
    {
        if(DecorationsMod.isGemsEnabled)
        {
            TFCItems.gemRuby = new ItemCustomGem(TFCItems.gemRuby).setUnlocalizedName(TFCItems.gemRuby.getUnlocalizedName().substring(5));
            TFCItems.gemSapphire = new ItemCustomGem(TFCItems.gemSapphire).setUnlocalizedName(TFCItems.gemSapphire.getUnlocalizedName().substring(5));
            TFCItems.gemEmerald = new ItemCustomGem(TFCItems.gemEmerald).setUnlocalizedName(TFCItems.gemEmerald.getUnlocalizedName().substring(5));
            TFCItems.gemTopaz = new ItemCustomGem(TFCItems.gemTopaz).setUnlocalizedName(TFCItems.gemTopaz.getUnlocalizedName().substring(5));
            TFCItems.gemTourmaline = new ItemCustomGem(TFCItems.gemTourmaline).setUnlocalizedName(TFCItems.gemTourmaline.getUnlocalizedName().substring(5));
            TFCItems.gemJade = new ItemCustomGem(TFCItems.gemJade).setUnlocalizedName(TFCItems.gemJade.getUnlocalizedName().substring(5));
            TFCItems.gemBeryl = new ItemCustomGem(TFCItems.gemBeryl).setUnlocalizedName(TFCItems.gemBeryl.getUnlocalizedName().substring(5));
            TFCItems.gemAgate = new ItemCustomGem(TFCItems.gemAgate).setUnlocalizedName(TFCItems.gemAgate.getUnlocalizedName().substring(5));
            TFCItems.gemOpal = new ItemCustomGem(TFCItems.gemOpal).setUnlocalizedName(TFCItems.gemOpal.getUnlocalizedName().substring(5));
            TFCItems.gemGarnet = new ItemCustomGem(TFCItems.gemGarnet).setUnlocalizedName(TFCItems.gemGarnet.getUnlocalizedName().substring(5));
            TFCItems.gemJasper = new ItemCustomGem(TFCItems.gemJasper).setUnlocalizedName(TFCItems.gemJasper.getUnlocalizedName().substring(5));
            TFCItems.gemAmethyst = new ItemCustomGem(TFCItems.gemAmethyst).setUnlocalizedName(TFCItems.gemAmethyst.getUnlocalizedName().substring(5));
            TFCItems.gemDiamond = new ItemCustomGem(TFCItems.gemDiamond).setUnlocalizedName(TFCItems.gemDiamond.getUnlocalizedName().substring(5));
        }
        
        if(DecorationsMod.isLanternsEnabled)
        {
            LanternCores = new Item[Constants.Lanterns.length];
            
            for(int i = 0; i < LanternCores.length; i++)
            {
                LanternInfo info = Constants.Lanterns[i];
                LanternCores[i] = new ItemLanternCore(info).setUnlocalizedName("LanternCore" + "." + info.LanternName);
            }
        }
        
        Powder = new ItemGypsumPowder().setUnlocalizedName("Powders.Gypsum");
        Plaster = new ItemPlaster().setUnlocalizedName("Plaster");
        LiquidDye = new ItemLiquidDye().setUnlocalizedName("LiquidDye");
        Brush = new ItemBrush().setUnlocalizedName("Brush");

        registerItems();
        registerOreDict();
    }
    
    private static void registerItems() throws ExistingSubstitutionException
    {
        if(DecorationsMod.isGemsEnabled)
        {
            GameRegistry.addSubstitutionAlias("terrafirmacraft:" + TFCItems.gemRuby.getUnlocalizedName(), GameRegistry.Type.ITEM, TFCItems.gemRuby);
            GameRegistry.addSubstitutionAlias("terrafirmacraft:" + TFCItems.gemSapphire.getUnlocalizedName(), GameRegistry.Type.ITEM, TFCItems.gemSapphire);
            GameRegistry.addSubstitutionAlias("terrafirmacraft:" + TFCItems.gemEmerald.getUnlocalizedName(), GameRegistry.Type.ITEM, TFCItems.gemEmerald);
            GameRegistry.addSubstitutionAlias("terrafirmacraft:" + TFCItems.gemTopaz.getUnlocalizedName(), GameRegistry.Type.ITEM, TFCItems.gemTopaz);
            GameRegistry.addSubstitutionAlias("terrafirmacraft:" + TFCItems.gemTourmaline.getUnlocalizedName(), GameRegistry.Type.ITEM, TFCItems.gemTourmaline);
            GameRegistry.addSubstitutionAlias("terrafirmacraft:" + TFCItems.gemJade.getUnlocalizedName(), GameRegistry.Type.ITEM, TFCItems.gemJade);
            GameRegistry.addSubstitutionAlias("terrafirmacraft:" + TFCItems.gemBeryl.getUnlocalizedName(), GameRegistry.Type.ITEM, TFCItems.gemBeryl);
            GameRegistry.addSubstitutionAlias("terrafirmacraft:" + TFCItems.gemAgate.getUnlocalizedName(), GameRegistry.Type.ITEM, TFCItems.gemAgate);
            GameRegistry.addSubstitutionAlias("terrafirmacraft:" + TFCItems.gemOpal.getUnlocalizedName(), GameRegistry.Type.ITEM, TFCItems.gemOpal);
            GameRegistry.addSubstitutionAlias("terrafirmacraft:" + TFCItems.gemGarnet.getUnlocalizedName(), GameRegistry.Type.ITEM, TFCItems.gemGarnet);
            GameRegistry.addSubstitutionAlias("terrafirmacraft:" + TFCItems.gemJasper.getUnlocalizedName(), GameRegistry.Type.ITEM, TFCItems.gemJasper);
            GameRegistry.addSubstitutionAlias("terrafirmacraft:" + TFCItems.gemAmethyst.getUnlocalizedName(), GameRegistry.Type.ITEM, TFCItems.gemAmethyst);
            GameRegistry.addSubstitutionAlias("terrafirmacraft:" + TFCItems.gemDiamond.getUnlocalizedName(), GameRegistry.Type.ITEM, TFCItems.gemDiamond);
        }
        
        if(DecorationsMod.isLanternsEnabled)
        {
            for(int i = 0; i < LanternCores.length; i++)
            {
                Item lanternCore = LanternCores[i];
                
                GameRegistry.registerItem(lanternCore, lanternCore.getUnlocalizedName());
            }
        }
        
        GameRegistry.registerItem(Powder, Powder.getUnlocalizedName());
        GameRegistry.registerItem(Plaster, Plaster.getUnlocalizedName());
        GameRegistry.registerItem(LiquidDye, LiquidDye.getUnlocalizedName());
        GameRegistry.registerItem(Brush, Brush.getUnlocalizedName());
    }
    
    private static void registerOreDict()
    {
        if(DecorationsMod.isGemsEnabled)
        {
            //Gems
            OreDictionary.registerOre("gemChippedAgate", new ItemStack(TFCItems.gemAgate));
            OreDictionary.registerOre("gemChippedAmethyst", new ItemStack(TFCItems.gemAmethyst));
            OreDictionary.registerOre("gemChippedBeryl", new ItemStack(TFCItems.gemBeryl));
            OreDictionary.registerOre("gemChippedDiamond", new ItemStack(TFCItems.gemDiamond));
            OreDictionary.registerOre("gemChippedEmerald", new ItemStack(TFCItems.gemEmerald));
            OreDictionary.registerOre("gemChippedGarnet", new ItemStack(TFCItems.gemGarnet));
            OreDictionary.registerOre("gemChippedJade", new ItemStack(TFCItems.gemJade));
            OreDictionary.registerOre("gemChippedJasper", new ItemStack(TFCItems.gemJasper));
            OreDictionary.registerOre("gemChippedOpal", new ItemStack(TFCItems.gemOpal));
            OreDictionary.registerOre("gemChippedRuby", new ItemStack(TFCItems.gemRuby));
            OreDictionary.registerOre("gemChippedSapphire", new ItemStack(TFCItems.gemSapphire));
            OreDictionary.registerOre("gemChippedTopaz", new ItemStack(TFCItems.gemTopaz));
            OreDictionary.registerOre("gemChippedTourmaline", new ItemStack(TFCItems.gemTourmaline));
    
            OreDictionary.registerOre("gemChipped", new ItemStack(TFCItems.gemAgate));
            OreDictionary.registerOre("gemChipped", new ItemStack(TFCItems.gemAmethyst));
            OreDictionary.registerOre("gemChipped", new ItemStack(TFCItems.gemBeryl));
            OreDictionary.registerOre("gemChipped", new ItemStack(TFCItems.gemDiamond));
            OreDictionary.registerOre("gemChipped", new ItemStack(TFCItems.gemEmerald));
            OreDictionary.registerOre("gemChipped", new ItemStack(TFCItems.gemGarnet));
            OreDictionary.registerOre("gemChipped", new ItemStack(TFCItems.gemJade));
            OreDictionary.registerOre("gemChipped", new ItemStack(TFCItems.gemJasper));
            OreDictionary.registerOre("gemChipped", new ItemStack(TFCItems.gemOpal));
            OreDictionary.registerOre("gemChipped", new ItemStack(TFCItems.gemRuby));
            OreDictionary.registerOre("gemChipped", new ItemStack(TFCItems.gemSapphire));
            OreDictionary.registerOre("gemChipped", new ItemStack(TFCItems.gemTopaz));
            OreDictionary.registerOre("gemChipped", new ItemStack(TFCItems.gemTourmaline));
    
            OreDictionary.registerOre("gemFlawedAgate", new ItemStack(TFCItems.gemAgate, 1, 1));
            OreDictionary.registerOre("gemFlawedAmethyst", new ItemStack(TFCItems.gemAmethyst, 1, 1));
            OreDictionary.registerOre("gemFlawedBeryl", new ItemStack(TFCItems.gemBeryl, 1, 1));
            OreDictionary.registerOre("gemFlawedDiamond", new ItemStack(TFCItems.gemDiamond, 1, 1));
            OreDictionary.registerOre("gemFlawedEmerald", new ItemStack(TFCItems.gemEmerald, 1, 1));
            OreDictionary.registerOre("gemFlawedGarnet", new ItemStack(TFCItems.gemGarnet, 1, 1));
            OreDictionary.registerOre("gemFlawedJade", new ItemStack(TFCItems.gemJade, 1, 1));
            OreDictionary.registerOre("gemFlawedJasper", new ItemStack(TFCItems.gemJasper, 1, 1));
            OreDictionary.registerOre("gemFlawedOpal", new ItemStack(TFCItems.gemOpal, 1, 1));
            OreDictionary.registerOre("gemFlawedRuby", new ItemStack(TFCItems.gemRuby, 1, 1));
            OreDictionary.registerOre("gemFlawedSapphire", new ItemStack(TFCItems.gemSapphire, 1, 1));
            OreDictionary.registerOre("gemFlawedTopaz", new ItemStack(TFCItems.gemTopaz, 1, 1));
            OreDictionary.registerOre("gemFlawedTourmaline", new ItemStack(TFCItems.gemTourmaline, 1, 1));
    
            OreDictionary.registerOre("gemAgate", new ItemStack(TFCItems.gemAgate, 1, 2));
            OreDictionary.registerOre("gemAmethyst", new ItemStack(TFCItems.gemAmethyst, 1, 2));
            OreDictionary.registerOre("gemBeryl", new ItemStack(TFCItems.gemBeryl, 1, 2));
            OreDictionary.registerOre("gemDiamond", new ItemStack(TFCItems.gemDiamond, 1, 2));
            OreDictionary.registerOre("gemEmerald", new ItemStack(TFCItems.gemEmerald, 1, 2));
            OreDictionary.registerOre("gemGarnet", new ItemStack(TFCItems.gemGarnet, 1, 2));
            OreDictionary.registerOre("gemJade", new ItemStack(TFCItems.gemJade, 1, 2));
            OreDictionary.registerOre("gemJasper", new ItemStack(TFCItems.gemJasper, 1, 2));
            OreDictionary.registerOre("gemOpal", new ItemStack(TFCItems.gemOpal, 1, 2));
            OreDictionary.registerOre("gemRuby", new ItemStack(TFCItems.gemRuby, 1, 2));
            OreDictionary.registerOre("gemSapphire", new ItemStack(TFCItems.gemSapphire, 1, 2));
            OreDictionary.registerOre("gemTopaz", new ItemStack(TFCItems.gemTopaz, 1, 2));
            OreDictionary.registerOre("gemTourmaline", new ItemStack(TFCItems.gemTourmaline, 1, 2));
    
            OreDictionary.registerOre("gemFlawlessAgate", new ItemStack(TFCItems.gemAgate, 1, 3));
            OreDictionary.registerOre("gemFlawlessAmethyst", new ItemStack(TFCItems.gemAmethyst, 1, 3));
            OreDictionary.registerOre("gemFlawlessBeryl", new ItemStack(TFCItems.gemBeryl, 1, 3));
            OreDictionary.registerOre("gemFlawlessDiamond", new ItemStack(TFCItems.gemDiamond, 1, 3));
            OreDictionary.registerOre("gemFlawlessEmerald", new ItemStack(TFCItems.gemEmerald, 1, 3));
            OreDictionary.registerOre("gemFlawlessGarnet", new ItemStack(TFCItems.gemGarnet, 1, 3));
            OreDictionary.registerOre("gemFlawlessJade", new ItemStack(TFCItems.gemJade, 1, 3));
            OreDictionary.registerOre("gemFlawlessJasper", new ItemStack(TFCItems.gemJasper, 1, 3));
            OreDictionary.registerOre("gemFlawlessOpal", new ItemStack(TFCItems.gemOpal, 1, 3));
            OreDictionary.registerOre("gemFlawlessRuby", new ItemStack(TFCItems.gemRuby, 1, 3));
            OreDictionary.registerOre("gemFlawlessSapphire", new ItemStack(TFCItems.gemSapphire, 1, 3));
            OreDictionary.registerOre("gemFlawlessTopaz", new ItemStack(TFCItems.gemTopaz, 1, 3));
            OreDictionary.registerOre("gemFlawlessTourmaline", new ItemStack(TFCItems.gemTourmaline, 1, 3));
    
            OreDictionary.registerOre("gemExquisiteAgate", new ItemStack(TFCItems.gemAgate, 1, 4));
            OreDictionary.registerOre("gemExquisiteAmethyst", new ItemStack(TFCItems.gemAmethyst, 1, 4));
            OreDictionary.registerOre("gemExquisiteBeryl", new ItemStack(TFCItems.gemBeryl, 1, 4));
            OreDictionary.registerOre("gemExquisiteDiamond", new ItemStack(TFCItems.gemDiamond, 1, 4));
            OreDictionary.registerOre("gemExquisiteEmerald", new ItemStack(TFCItems.gemEmerald, 1, 4));
            OreDictionary.registerOre("gemExquisiteGarnet", new ItemStack(TFCItems.gemGarnet, 1, 4));
            OreDictionary.registerOre("gemExquisiteJade", new ItemStack(TFCItems.gemJade, 1, 4));
            OreDictionary.registerOre("gemExquisiteJasper", new ItemStack(TFCItems.gemJasper, 1, 4));
            OreDictionary.registerOre("gemExquisiteOpal", new ItemStack(TFCItems.gemOpal, 1, 4));
            OreDictionary.registerOre("gemExquisiteRuby", new ItemStack(TFCItems.gemRuby, 1, 4));
            OreDictionary.registerOre("gemExquisiteSapphire", new ItemStack(TFCItems.gemSapphire, 1, 4));
            OreDictionary.registerOre("gemExquisiteTopaz", new ItemStack(TFCItems.gemTopaz, 1, 4));
            OreDictionary.registerOre("gemExquisiteTourmaline", new ItemStack(TFCItems.gemTourmaline, 1, 4));
        }
    }
}
