package com.aleksey.decorations.Core;

import java.util.Map;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import com.aleksey.decorations.DecorationsMod;
import com.aleksey.decorations.Core.Data.LanternInfo;
import com.aleksey.decorations.Crafting.BarrelPlasterRecipe;
import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.TFCCrafting;
import com.bioxx.tfc.api.TFCFluids;
import com.bioxx.tfc.api.TFCItems;
import com.bioxx.tfc.api.Constant.Global;
import com.bioxx.tfc.api.Crafting.AnvilManager;
import com.bioxx.tfc.api.Crafting.AnvilRecipe;
import com.bioxx.tfc.api.Crafting.BarrelManager;
import com.bioxx.tfc.api.Crafting.BarrelRecipe;
import com.bioxx.tfc.api.Crafting.PlanRecipe;
import com.bioxx.tfc.api.Enums.RuleEnum;

import cpw.mods.fml.common.registry.GameRegistry;

public class Recipes
{
    private static final String _lanternCorePlan = "lanterncore";
    
    public static void registerRecipes()
    {
        if(DecorationsMod.isLanternsEnabled)
            registerLanternRecipes();
        
        if(DecorationsMod.isGemsEnabled)
        {
            if (TFCCrafting.diamondConversion == true)
            {
                GameRegistry.addShapelessRecipe(new ItemStack(Items.diamond, 1), new Object[] {new ItemStack(TFCItems.gemDiamond,1,2)});
                GameRegistry.addShapelessRecipe(new ItemStack(Items.diamond, 2), new Object[] {new ItemStack(TFCItems.gemDiamond,1,3)});
                GameRegistry.addShapelessRecipe(new ItemStack(Items.diamond, 3), new Object[] {new ItemStack(TFCItems.gemDiamond,1,4)});
                GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.gemDiamond,1,2), new Object[] {new ItemStack(Items.diamond)});
                GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.gemDiamond,1,3), new Object[] {new ItemStack(Items.diamond), new ItemStack(Items.diamond)});
                GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.gemDiamond,1,4), new Object[] {new ItemStack(Items.diamond), new ItemStack(Items.diamond), new ItemStack(Items.diamond)});
            }
            
            if (TFCCrafting.emeraldConversion == true)
            {
                GameRegistry.addShapelessRecipe(new ItemStack(Items.emerald, 1), new Object[] {new ItemStack(TFCItems.gemEmerald,1,2)});
                GameRegistry.addShapelessRecipe(new ItemStack(Items.emerald, 2), new Object[] {new ItemStack(TFCItems.gemEmerald,1,3)});
                GameRegistry.addShapelessRecipe(new ItemStack(Items.emerald, 3), new Object[] {new ItemStack(TFCItems.gemEmerald,1,4)});
                GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.gemEmerald,1,2), new Object[] {new ItemStack(Items.emerald)});
                GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.gemEmerald,1,3), new Object[] {new ItemStack(Items.emerald), new ItemStack(Items.emerald)});
                GameRegistry.addShapelessRecipe(new ItemStack(TFCItems.gemEmerald,1,4), new Object[] {new ItemStack(Items.emerald), new ItemStack(Items.emerald), new ItemStack(Items.emerald)});
            }
        }
        
        //Gypsum Powder
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ItemList.Powder, 6, 0), new Object[] { new ItemStack(TFCItems.oreChunk, 1, 17), "itemHammer" }));
        
        GameRegistry.addRecipe(new ItemStack(ItemList.Brush), new Object[] { "w", "r", "t", Character.valueOf('w'), new ItemStack(TFCItems.wool), Character.valueOf('r'), new ItemStack(TFCItems.rope), Character.valueOf('t'), new ItemStack(TFCItems.stick) });
        
        registerMudBrickRecipes();
        registerBarrelRecipes();
    }
    
    private static void registerLanternRecipes()
    {
        ItemStack stick = new ItemStack(TFCItems.stick, 1);
        ItemStack yarn = new ItemStack(TFCItems.woolYarn, 1);
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
    
    private static void registerMudBrickRecipes()
    {
        for(int i = 0; i < 16; i++)
        {
            GameRegistry.addShapelessRecipe(new ItemStack(BlockList.MudBrickRaws[i], 1, 0), new Object[] { new ItemStack(TFCBlocks.dirt, 1, i), new ItemStack(TFCItems.clayBall, 1, 0), new ItemStack(TFCItems.straw) });
            GameRegistry.addShapelessRecipe(new ItemStack(BlockList.MudBrickRaws[i], 1, 0), new Object[] { new ItemStack(TFCBlocks.sand, 1, i), new ItemStack(TFCItems.clayBall, 1, 0), new ItemStack(TFCItems.straw) });
        }
            
        for(int i = 0; i < Global.STONE_ALL.length - 16; i++)
        {
            GameRegistry.addShapelessRecipe(new ItemStack(BlockList.MudBrickRaws[16 + i], 1, 0), new Object[] { new ItemStack(TFCBlocks.dirt2, 1, i), new ItemStack(TFCItems.clayBall, 1, 0), new ItemStack(TFCItems.straw) });
            GameRegistry.addShapelessRecipe(new ItemStack(BlockList.MudBrickRaws[16 + i], 1, 0), new Object[] { new ItemStack(TFCBlocks.sand2, 1, i), new ItemStack(TFCItems.clayBall, 1, 0), new ItemStack(TFCItems.straw) });
        }

        for(int i = 0; i < 16; i++)
            GameRegistry.addRecipe(new ItemStack(BlockList.MudBricks, 4, i), new Object[] { "mm", "mm", Character.valueOf('m'), new ItemStack(BlockList.MudBrickRaws[i], 1, 1) });
            
        for(int i = 0; i < Global.STONE_ALL.length - 16; i++)
            GameRegistry.addRecipe(new ItemStack(BlockList.MudBricks2, 4, i), new Object[] { "mm", "mm", Character.valueOf('m'), new ItemStack(BlockList.MudBrickRaws[16 + i], 1, 1) });
    }
    
    private static void registerBarrelRecipes()
    {
        BarrelManager.getInstance().addRecipe(new BarrelRecipe(new ItemStack(ItemList.Powder, 1, 0), new FluidStack(TFCFluids.FRESHWATER, 500), null, new FluidStack(FluidList.Plaster, 500), 0).setMinTechLevel(0).setSealedRecipe(false).setRemovesLiquid(false).setAllowAnyStack(false));
        BarrelManager.getInstance().addRecipe(new BarrelRecipe(new ItemStack(TFCBlocks.sand, 1, 32767), new FluidStack(FluidList.Plaster, 100), new ItemStack(TFCItems.mortar, 16), new FluidStack(FluidList.Plaster, 100)).setMinTechLevel(0));
        BarrelManager.getInstance().addRecipe(new BarrelRecipe(new ItemStack(TFCBlocks.sand2, 1, 32767), new FluidStack(FluidList.Plaster, 100), new ItemStack(TFCItems.mortar, 16), new FluidStack(FluidList.Plaster, 100)).setMinTechLevel(0));
        BarrelManager.getInstance().addRecipe(new BarrelPlasterRecipe(new ItemStack(ItemList.Powder, 1, 0), new FluidStack(FluidList.Plaster, 50), new ItemStack(BlockList.Alabaster, 1), new FluidStack(FluidList.Plaster, 50)).setMinTechLevel(0));
        
        for(int i = 0; i < FluidList.LiquidDyes.length; i++)
        {
            DyeFluid dye = FluidList.LiquidDyes[i];
            
            BarrelManager.getInstance().addRecipe(new BarrelRecipe(new ItemStack(TFCItems.dye, 1, dye.TFCDyeIndex), new FluidStack(TFCFluids.FRESHWATER, 400), null, new FluidStack(dye, 400), 0).setMinTechLevel(0).setSealedRecipe(false).setRemovesLiquid(false).setAllowAnyStack(false));
        }
    }
    
    public static boolean areAnvilRecipesRegistered()
    {
        if(!DecorationsMod.isLanternsEnabled)
            return true;
        
        Map map = AnvilManager.getInstance().getPlans();
        
        return map.containsKey(_lanternCorePlan);
    }
    
    public static void registerAnvilRecipes(World world)
    {
        if(!DecorationsMod.isLanternsEnabled)
            return;
        
        AnvilManager manager = AnvilManager.getInstance();
        //We need to set the world ref so that all anvil recipes can generate correctly
        AnvilManager.world = world;
        
        manager.addPlan(_lanternCorePlan, new PlanRecipe(new RuleEnum[] { RuleEnum.HITLAST, RuleEnum.PUNCHNOTLAST, RuleEnum.HITNOTLAST }));
        
        for(int i = 0; i < Constants.Lanterns.length; i++)
        {
            LanternInfo info = Constants.Lanterns[i];
            Item sheetItem = GameRegistry.findItem("terrafirmacraft", info.SheetName);            
            ItemStack lanternCore = new ItemStack(ItemList.LanternCores[i], 1, 0);

            manager.addRecipe(new AnvilRecipe(new ItemStack(sheetItem), null, _lanternCorePlan, false, info.Anvil, lanternCore).addRecipeSkill(Global.SKILL_GENERAL_SMITHING));
        }
    }
}
