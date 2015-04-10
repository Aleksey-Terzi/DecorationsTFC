package com.aleksey.decorations.Core;

import com.bioxx.tfc.Core.FluidBaseTFC;

public class DyeFluid extends FluidBaseTFC
{
    public int TFCDyeIndex;
    
    public DyeFluid(String fluidName, int tfcDyeIndex)
    {
        super(fluidName);
        
        TFCDyeIndex = tfcDyeIndex;
    }
}
