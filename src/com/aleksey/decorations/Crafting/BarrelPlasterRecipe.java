package com.aleksey.decorations.Crafting;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import com.bioxx.tfc.api.Crafting.BarrelRecipe;

public class BarrelPlasterRecipe extends BarrelRecipe
{
    private FluidStack _outputFluid;
    
    public BarrelPlasterRecipe(ItemStack inputItem, FluidStack inputFluid, ItemStack outIS, FluidStack outputFluid)
    {
        super(inputItem, inputFluid, outIS, outputFluid);
        
        _outputFluid = outputFluid;
    }
    
    @Override
    public FluidStack getResultFluid(ItemStack inIS, FluidStack inFS, int sealedTime)
    {
        if(_outputFluid == null)
            return null;

        FluidStack fs = _outputFluid.copy();
        
        if(!removesLiquid && fs != null)
        {
            fs.amount = inFS.amount;
        }
        else if(fs != null)
        {
            fs.amount *= inIS.stackSize;
        }
        
        return fs;
    }
}
