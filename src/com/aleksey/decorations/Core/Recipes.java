package com.aleksey.decorations.Core;

import java.util.Map;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;

import com.aleksey.decorations.DecorationsMod;
import com.aleksey.decorations.Core.Data.LanternInfo;
import com.bioxx.tfc.TFCItems;
import com.bioxx.tfc.api.TFCCrafting;
import com.bioxx.tfc.api.Constant.Global;
import com.bioxx.tfc.api.Crafting.AnvilManager;
import com.bioxx.tfc.api.Crafting.AnvilRecipe;
import com.bioxx.tfc.api.Crafting.PlanRecipe;
import com.bioxx.tfc.api.Enums.RuleEnum;

import cpw.mods.fml.common.registry.GameRegistry;

public class Recipes
{
    private static final String _lanternCorePlan = "lanterncore";
    
    public static void registerRecipes()
    {
        if(DecorationsMod.isLanternsEnabled)
        {
            registerLanternRecipes();
            registerFluidContainers();
        }
        
        if (TFCCrafting.diamondConversion == true)
        {
            GameRegistry.addShapelessRecipe(new ItemStack(Items.diamond, 1), new Object[] {new ItemStack(TFCItems.GemDiamond,1,2)});
            GameRegistry.addShapelessRecipe(new ItemStack(Items.diamond, 2), new Object[] {new ItemStack(TFCItems.GemDiamond,1,3)});
            GameRegistry.addShapelessRecipe(new ItemStack(Items.diamond, 3), new Object[] {new ItemStack(TFCItems.GemDiamond,1,4)});
            GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.GemDiamond,1,2), new Object[] {new ItemStack(Items.diamond)});
            GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.GemDiamond,1,3), new Object[] {new ItemStack(Items.diamond), new ItemStack(Items.diamond)});
            GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.GemDiamond,1,4), new Object[] {new ItemStack(Items.diamond), new ItemStack(Items.diamond), new ItemStack(Items.diamond)});
        }
        
        if (TFCCrafting.emeraldConversion == true)
        {
            GameRegistry.addShapelessRecipe(new ItemStack(Items.emerald, 1), new Object[] {new ItemStack(TFCItems.GemEmerald,1,2)});
            GameRegistry.addShapelessRecipe(new ItemStack(Items.emerald, 2), new Object[] {new ItemStack(TFCItems.GemEmerald,1,3)});
            GameRegistry.addShapelessRecipe(new ItemStack(Items.emerald, 3), new Object[] {new ItemStack(TFCItems.GemEmerald,1,4)});
            GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.GemEmerald,1,2), new Object[] {new ItemStack(Items.emerald)});
            GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.GemEmerald,1,3), new Object[] {new ItemStack(Items.emerald), new ItemStack(Items.emerald)});
            GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.GemEmerald,1,4), new Object[] {new ItemStack(Items.emerald), new ItemStack(Items.emerald), new ItemStack(Items.emerald)});
        }
    }
    
    private static void registerLanternRecipes()
    {
        ItemStack stick = new ItemStack(TFCItems.Stick, 1);
        ItemStack yarn = new ItemStack(TFCItems.WoolYarn, 1);
        ItemStack glassPane = new ItemStack(Blocks.glass_pane, 1);        
        
        for(int i = 0; i < Constants.Lanterns.length; i++)
        {
            ItemStack lantern = new ItemStack(BlockList.Lanterns[i], 2);
            Item lanternCore = ItemList.LanternCores[i];

            for(int k = 0; k < FluidList.AlcoholFluids.length; k++)
            {
                ItemStack coreFilled = new ItemStack(lanternCore, 1, k + 1);

                GameRegistry.addRecipe(lantern, new Object[] { "tgy", "gsg", "tgy", Character.valueOf('g'), glassPane, Character.valueOf('s'), coreFilled, Character.valueOf('t'), stick, Character.valueOf('y'), yarn });
                GameRegistry.addRecipe(lantern, new Object[] { "ygt", "gsg", "ygt", Character.valueOf('g'), glassPane, Character.valueOf('s'), coreFilled, Character.valueOf('t'), stick, Character.valueOf('y'), yarn });
            }
        }
    }
    
    private static void registerFluidContainers()
    {
        for(int i = 0; i < Constants.Lanterns.length; i++)
        {
            Item core = ItemList.LanternCores[i]; 
            ItemStack coreEmpty = new ItemStack(core, 1, 0);

            for(int k = 0; k < FluidList.AlcoholFluids.length; k++)
            {
                FluidStack fluid = new FluidStack(FluidList.AlcoholFluids[k], 2000);
                ItemStack coreFilled = new ItemStack(core, 1, k + 1);

                FluidContainerRegistry.registerFluidContainer(fluid, coreFilled, coreEmpty);
            }
        }
    }
    
    public static boolean areAnvilRecipesRegistered()
    {
        if(!DecorationsMod.isLanternsEnabled)
            return true;
        
        Map map = AnvilManager.getInstance().getPlans();
        
        return map.containsKey(_lanternCorePlan);
    }
    
    public static void registerAnvilRecipes()
    {
        if(DecorationsMod.isLanternsEnabled)
        {
            AnvilManager manager = AnvilManager.getInstance();
            
            manager.addPlan(_lanternCorePlan, new PlanRecipe(new RuleEnum[] { RuleEnum.HITLAST, RuleEnum.PUNCHANY, RuleEnum.HITANY }));
            
            for(int i = 0; i < Constants.Lanterns.length; i++)
            {
                LanternInfo info = Constants.Lanterns[i];
                Item sheetItem = GameRegistry.findItem("terrafirmacraft", info.SheetName);            
                ItemStack lanternCore = new ItemStack(ItemList.LanternCores[i], 1, 0);
    
                manager.addRecipe(new AnvilRecipe(new ItemStack(sheetItem), null, _lanternCorePlan, false, info.Anvil, lanternCore).addRecipeSkill(Global.SKILL_GENERAL_SMITHING));
            }
        }
    }
}
