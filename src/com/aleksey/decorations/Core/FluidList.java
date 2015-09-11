package com.aleksey.decorations.Core;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

import com.aleksey.decorations.DecorationsMod;
import com.bioxx.tfc.Core.FluidBaseTFC;
import com.bioxx.tfc.Items.ItemDyeCustom;
import com.bioxx.tfc.api.TFCFluids;
import com.bioxx.tfc.api.TFCItems;

public class FluidList
{
    public static Fluid[] AlcoholFluids;
    
    public static FluidBaseTFC Plaster = new FluidBaseTFC("plaster").setBaseColor(0xD5D1C0);
    
    public static DyeFluid[] LiquidDyes;
    
    public static void register()
    {
        AlcoholFluids = new Fluid[]
        {
    		TFCFluids.RUM,
    		TFCFluids.BEER,
    		TFCFluids.RYEWHISKEY,
    		TFCFluids.WHISKEY,
    		TFCFluids.CORNWHISKEY,
    		TFCFluids.SAKE,
    		TFCFluids.VODKA,
    		TFCFluids.CIDER
        };
        
        FluidRegistry.registerFluid(Plaster);
        
        LiquidDyes = new DyeFluid[ItemDyeCustom.DYE_COLOR_NAMES.length];
        
        for(int i = 0; i < LiquidDyes.length; i++)
        {
            int dyeIndex;
            
            if(i == 0)
                dyeIndex = LiquidDyes.length - 1;
            else if(i == LiquidDyes.length - 1)
                dyeIndex = 0;
            else
                dyeIndex = i;
            
            String fluidName = "liquid_dye." + ItemDyeCustom.DYE_COLOR_NAMES[dyeIndex];
            int color = Constants.DyeColors[dyeIndex];
            DyeFluid fluid = new DyeFluid(fluidName, dyeIndex);
            
            fluid.setBaseColor(color);
            
            FluidRegistry.registerFluid(LiquidDyes[i] = fluid);
        }
    }
    
    public static void registerFluidContainers()
    {
        if(DecorationsMod.isLanternsEnabled)
        {
            for(int i = 0; i < Constants.Lanterns.length; i++)
            {
                Item core = ItemList.LanternCores[i]; 
                ItemStack coreEmpty = new ItemStack(core, 1, 0);

                for(int k = 0; k < FluidList.AlcoholFluids.length; k++)
                {
                    FluidStack fluid = new FluidStack(AlcoholFluids[k], 2000);
                    ItemStack coreFilled = new ItemStack(core, 1, k + 1);

                    FluidContainerRegistry.registerFluidContainer(fluid, coreFilled, coreEmpty);
                }
            }
        }
        
        FluidContainerRegistry.registerFluidContainer(new FluidStack(Plaster, 1000), new ItemStack(ItemList.Plaster), new ItemStack(TFCItems.woodenBucketEmpty));
        
        for(int i = 0; i < LiquidDyes.length; i++)
            FluidContainerRegistry.registerFluidContainer(new FluidStack(LiquidDyes[i], 1000), new ItemStack(ItemList.LiquidDye, 1, i), new ItemStack(TFCItems.woodenBucketEmpty));
    }
}
