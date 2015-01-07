package com.aleksey.decorations.Core;

import com.bioxx.tfc.Core.TFCFluid;

public class DyeFluid extends TFCFluid
{
    public int TFCDyeIndex;
    
    public DyeFluid(String fluidName, int tfcDyeIndex)
    {
        super(fluidName);
        
        TFCDyeIndex = tfcDyeIndex;
    }
}
