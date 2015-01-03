package com.aleksey.decorations.Core;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

import com.aleksey.decorations.DecorationsMod;
import com.bioxx.tfc.TFCItems;
import com.bioxx.tfc.Core.TFCFluid;

public class FluidList
{
    public static Fluid[] AlcoholFluids;

    public static final TFCFluid Plaster = new TFCFluid("plaster").setBaseColor(0xD5D1C0);
    
    public static void register()
    {
        AlcoholFluids = new Fluid[]
        {
            TFCFluid.RUM,
            TFCFluid.BEER,
            TFCFluid.RYEWHISKEY,
            TFCFluid.WHISKEY,
            TFCFluid.CORNWHISKEY,
            TFCFluid.SAKE,
            TFCFluid.VODKA,
            TFCFluid.CIDER
        };
        
        FluidRegistry.registerFluid(Plaster);
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
                    FluidStack fluid = new FluidStack(FluidList.AlcoholFluids[k], 2000);
                    ItemStack coreFilled = new ItemStack(core, 1, k + 1);

                    FluidContainerRegistry.registerFluidContainer(fluid, coreFilled, coreEmpty);
                }
            }
        }
        
        FluidContainerRegistry.registerFluidContainer(new FluidStack(FluidList.Plaster, 1000), new ItemStack(ItemList.Plaster), new ItemStack(TFCItems.WoodenBucketEmpty));
    }
}
