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
            TFCItems.GemRuby = new ItemCustomGem(TFCItems.GemRuby).setUnlocalizedName(TFCItems.GemRuby.getUnlocalizedName().substring(5));
            TFCItems.GemSapphire = new ItemCustomGem(TFCItems.GemSapphire).setUnlocalizedName(TFCItems.GemSapphire.getUnlocalizedName().substring(5));
            TFCItems.GemEmerald = new ItemCustomGem(TFCItems.GemEmerald).setUnlocalizedName(TFCItems.GemEmerald.getUnlocalizedName().substring(5));
            TFCItems.GemTopaz = new ItemCustomGem(TFCItems.GemTopaz).setUnlocalizedName(TFCItems.GemTopaz.getUnlocalizedName().substring(5));
            TFCItems.GemTourmaline = new ItemCustomGem(TFCItems.GemTourmaline).setUnlocalizedName(TFCItems.GemTourmaline.getUnlocalizedName().substring(5));
            TFCItems.GemJade = new ItemCustomGem(TFCItems.GemJade).setUnlocalizedName(TFCItems.GemJade.getUnlocalizedName().substring(5));
            TFCItems.GemBeryl = new ItemCustomGem(TFCItems.GemBeryl).setUnlocalizedName(TFCItems.GemBeryl.getUnlocalizedName().substring(5));
            TFCItems.GemAgate = new ItemCustomGem(TFCItems.GemAgate).setUnlocalizedName(TFCItems.GemAgate.getUnlocalizedName().substring(5));
            TFCItems.GemOpal = new ItemCustomGem(TFCItems.GemOpal).setUnlocalizedName(TFCItems.GemOpal.getUnlocalizedName().substring(5));
            TFCItems.GemGarnet = new ItemCustomGem(TFCItems.GemGarnet).setUnlocalizedName(TFCItems.GemGarnet.getUnlocalizedName().substring(5));
            TFCItems.GemJasper = new ItemCustomGem(TFCItems.GemJasper).setUnlocalizedName(TFCItems.GemJasper.getUnlocalizedName().substring(5));
            TFCItems.GemAmethyst = new ItemCustomGem(TFCItems.GemAmethyst).setUnlocalizedName(TFCItems.GemAmethyst.getUnlocalizedName().substring(5));
            TFCItems.GemDiamond = new ItemCustomGem(TFCItems.GemDiamond).setUnlocalizedName(TFCItems.GemDiamond.getUnlocalizedName().substring(5));
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
            GameRegistry.addSubstitutionAlias("terrafirmacraft:" + TFCItems.GemRuby.getUnlocalizedName(), GameRegistry.Type.ITEM, TFCItems.GemRuby);
            GameRegistry.addSubstitutionAlias("terrafirmacraft:" + TFCItems.GemSapphire.getUnlocalizedName(), GameRegistry.Type.ITEM, TFCItems.GemSapphire);
            GameRegistry.addSubstitutionAlias("terrafirmacraft:" + TFCItems.GemEmerald.getUnlocalizedName(), GameRegistry.Type.ITEM, TFCItems.GemEmerald);
            GameRegistry.addSubstitutionAlias("terrafirmacraft:" + TFCItems.GemTopaz.getUnlocalizedName(), GameRegistry.Type.ITEM, TFCItems.GemTopaz);
            GameRegistry.addSubstitutionAlias("terrafirmacraft:" + TFCItems.GemTourmaline.getUnlocalizedName(), GameRegistry.Type.ITEM, TFCItems.GemTourmaline);
            GameRegistry.addSubstitutionAlias("terrafirmacraft:" + TFCItems.GemJade.getUnlocalizedName(), GameRegistry.Type.ITEM, TFCItems.GemJade);
            GameRegistry.addSubstitutionAlias("terrafirmacraft:" + TFCItems.GemBeryl.getUnlocalizedName(), GameRegistry.Type.ITEM, TFCItems.GemBeryl);
            GameRegistry.addSubstitutionAlias("terrafirmacraft:" + TFCItems.GemAgate.getUnlocalizedName(), GameRegistry.Type.ITEM, TFCItems.GemAgate);
            GameRegistry.addSubstitutionAlias("terrafirmacraft:" + TFCItems.GemOpal.getUnlocalizedName(), GameRegistry.Type.ITEM, TFCItems.GemOpal);
            GameRegistry.addSubstitutionAlias("terrafirmacraft:" + TFCItems.GemGarnet.getUnlocalizedName(), GameRegistry.Type.ITEM, TFCItems.GemGarnet);
            GameRegistry.addSubstitutionAlias("terrafirmacraft:" + TFCItems.GemJasper.getUnlocalizedName(), GameRegistry.Type.ITEM, TFCItems.GemJasper);
            GameRegistry.addSubstitutionAlias("terrafirmacraft:" + TFCItems.GemAmethyst.getUnlocalizedName(), GameRegistry.Type.ITEM, TFCItems.GemAmethyst);
            GameRegistry.addSubstitutionAlias("terrafirmacraft:" + TFCItems.GemDiamond.getUnlocalizedName(), GameRegistry.Type.ITEM, TFCItems.GemDiamond);
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
            OreDictionary.registerOre("gemChippedAgate", new ItemStack(TFCItems.GemAgate));
            OreDictionary.registerOre("gemChippedAmethyst", new ItemStack(TFCItems.GemAmethyst));
            OreDictionary.registerOre("gemChippedBeryl", new ItemStack(TFCItems.GemBeryl));
            OreDictionary.registerOre("gemChippedDiamond", new ItemStack(TFCItems.GemDiamond));
            OreDictionary.registerOre("gemChippedEmerald", new ItemStack(TFCItems.GemEmerald));
            OreDictionary.registerOre("gemChippedGarnet", new ItemStack(TFCItems.GemGarnet));
            OreDictionary.registerOre("gemChippedJade", new ItemStack(TFCItems.GemJade));
            OreDictionary.registerOre("gemChippedJasper", new ItemStack(TFCItems.GemJasper));
            OreDictionary.registerOre("gemChippedOpal", new ItemStack(TFCItems.GemOpal));
            OreDictionary.registerOre("gemChippedRuby", new ItemStack(TFCItems.GemRuby));
            OreDictionary.registerOre("gemChippedSapphire", new ItemStack(TFCItems.GemSapphire));
            OreDictionary.registerOre("gemChippedTopaz", new ItemStack(TFCItems.GemTopaz));
            OreDictionary.registerOre("gemChippedTourmaline", new ItemStack(TFCItems.GemTourmaline));
    
            OreDictionary.registerOre("gemChipped", new ItemStack(TFCItems.GemAgate));
            OreDictionary.registerOre("gemChipped", new ItemStack(TFCItems.GemAmethyst));
            OreDictionary.registerOre("gemChipped", new ItemStack(TFCItems.GemBeryl));
            OreDictionary.registerOre("gemChipped", new ItemStack(TFCItems.GemDiamond));
            OreDictionary.registerOre("gemChipped", new ItemStack(TFCItems.GemEmerald));
            OreDictionary.registerOre("gemChipped", new ItemStack(TFCItems.GemGarnet));
            OreDictionary.registerOre("gemChipped", new ItemStack(TFCItems.GemJade));
            OreDictionary.registerOre("gemChipped", new ItemStack(TFCItems.GemJasper));
            OreDictionary.registerOre("gemChipped", new ItemStack(TFCItems.GemOpal));
            OreDictionary.registerOre("gemChipped", new ItemStack(TFCItems.GemRuby));
            OreDictionary.registerOre("gemChipped", new ItemStack(TFCItems.GemSapphire));
            OreDictionary.registerOre("gemChipped", new ItemStack(TFCItems.GemTopaz));
            OreDictionary.registerOre("gemChipped", new ItemStack(TFCItems.GemTourmaline));
    
            OreDictionary.registerOre("gemFlawedAgate", new ItemStack(TFCItems.GemAgate, 1, 1));
            OreDictionary.registerOre("gemFlawedAmethyst", new ItemStack(TFCItems.GemAmethyst, 1, 1));
            OreDictionary.registerOre("gemFlawedBeryl", new ItemStack(TFCItems.GemBeryl, 1, 1));
            OreDictionary.registerOre("gemFlawedDiamond", new ItemStack(TFCItems.GemDiamond, 1, 1));
            OreDictionary.registerOre("gemFlawedEmerald", new ItemStack(TFCItems.GemEmerald, 1, 1));
            OreDictionary.registerOre("gemFlawedGarnet", new ItemStack(TFCItems.GemGarnet, 1, 1));
            OreDictionary.registerOre("gemFlawedJade", new ItemStack(TFCItems.GemJade, 1, 1));
            OreDictionary.registerOre("gemFlawedJasper", new ItemStack(TFCItems.GemJasper, 1, 1));
            OreDictionary.registerOre("gemFlawedOpal", new ItemStack(TFCItems.GemOpal, 1, 1));
            OreDictionary.registerOre("gemFlawedRuby", new ItemStack(TFCItems.GemRuby, 1, 1));
            OreDictionary.registerOre("gemFlawedSapphire", new ItemStack(TFCItems.GemSapphire, 1, 1));
            OreDictionary.registerOre("gemFlawedTopaz", new ItemStack(TFCItems.GemTopaz, 1, 1));
            OreDictionary.registerOre("gemFlawedTourmaline", new ItemStack(TFCItems.GemTourmaline, 1, 1));
    
            OreDictionary.registerOre("gemAgate", new ItemStack(TFCItems.GemAgate, 1, 2));
            OreDictionary.registerOre("gemAmethyst", new ItemStack(TFCItems.GemAmethyst, 1, 2));
            OreDictionary.registerOre("gemBeryl", new ItemStack(TFCItems.GemBeryl, 1, 2));
            OreDictionary.registerOre("gemDiamond", new ItemStack(TFCItems.GemDiamond, 1, 2));
            OreDictionary.registerOre("gemEmerald", new ItemStack(TFCItems.GemEmerald, 1, 2));
            OreDictionary.registerOre("gemGarnet", new ItemStack(TFCItems.GemGarnet, 1, 2));
            OreDictionary.registerOre("gemJade", new ItemStack(TFCItems.GemJade, 1, 2));
            OreDictionary.registerOre("gemJasper", new ItemStack(TFCItems.GemJasper, 1, 2));
            OreDictionary.registerOre("gemOpal", new ItemStack(TFCItems.GemOpal, 1, 2));
            OreDictionary.registerOre("gemRuby", new ItemStack(TFCItems.GemRuby, 1, 2));
            OreDictionary.registerOre("gemSapphire", new ItemStack(TFCItems.GemSapphire, 1, 2));
            OreDictionary.registerOre("gemTopaz", new ItemStack(TFCItems.GemTopaz, 1, 2));
            OreDictionary.registerOre("gemTourmaline", new ItemStack(TFCItems.GemTourmaline, 1, 2));
    
            OreDictionary.registerOre("gemFlawlessAgate", new ItemStack(TFCItems.GemAgate, 1, 3));
            OreDictionary.registerOre("gemFlawlessAmethyst", new ItemStack(TFCItems.GemAmethyst, 1, 3));
            OreDictionary.registerOre("gemFlawlessBeryl", new ItemStack(TFCItems.GemBeryl, 1, 3));
            OreDictionary.registerOre("gemFlawlessDiamond", new ItemStack(TFCItems.GemDiamond, 1, 3));
            OreDictionary.registerOre("gemFlawlessEmerald", new ItemStack(TFCItems.GemEmerald, 1, 3));
            OreDictionary.registerOre("gemFlawlessGarnet", new ItemStack(TFCItems.GemGarnet, 1, 3));
            OreDictionary.registerOre("gemFlawlessJade", new ItemStack(TFCItems.GemJade, 1, 3));
            OreDictionary.registerOre("gemFlawlessJasper", new ItemStack(TFCItems.GemJasper, 1, 3));
            OreDictionary.registerOre("gemFlawlessOpal", new ItemStack(TFCItems.GemOpal, 1, 3));
            OreDictionary.registerOre("gemFlawlessRuby", new ItemStack(TFCItems.GemRuby, 1, 3));
            OreDictionary.registerOre("gemFlawlessSapphire", new ItemStack(TFCItems.GemSapphire, 1, 3));
            OreDictionary.registerOre("gemFlawlessTopaz", new ItemStack(TFCItems.GemTopaz, 1, 3));
            OreDictionary.registerOre("gemFlawlessTourmaline", new ItemStack(TFCItems.GemTourmaline, 1, 3));
    
            OreDictionary.registerOre("gemExquisiteAgate", new ItemStack(TFCItems.GemAgate, 1, 4));
            OreDictionary.registerOre("gemExquisiteAmethyst", new ItemStack(TFCItems.GemAmethyst, 1, 4));
            OreDictionary.registerOre("gemExquisiteBeryl", new ItemStack(TFCItems.GemBeryl, 1, 4));
            OreDictionary.registerOre("gemExquisiteDiamond", new ItemStack(TFCItems.GemDiamond, 1, 4));
            OreDictionary.registerOre("gemExquisiteEmerald", new ItemStack(TFCItems.GemEmerald, 1, 4));
            OreDictionary.registerOre("gemExquisiteGarnet", new ItemStack(TFCItems.GemGarnet, 1, 4));
            OreDictionary.registerOre("gemExquisiteJade", new ItemStack(TFCItems.GemJade, 1, 4));
            OreDictionary.registerOre("gemExquisiteJasper", new ItemStack(TFCItems.GemJasper, 1, 4));
            OreDictionary.registerOre("gemExquisiteOpal", new ItemStack(TFCItems.GemOpal, 1, 4));
            OreDictionary.registerOre("gemExquisiteRuby", new ItemStack(TFCItems.GemRuby, 1, 4));
            OreDictionary.registerOre("gemExquisiteSapphire", new ItemStack(TFCItems.GemSapphire, 1, 4));
            OreDictionary.registerOre("gemExquisiteTopaz", new ItemStack(TFCItems.GemTopaz, 1, 4));
            OreDictionary.registerOre("gemExquisiteTourmaline", new ItemStack(TFCItems.GemTourmaline, 1, 4));
        }
    }
}
