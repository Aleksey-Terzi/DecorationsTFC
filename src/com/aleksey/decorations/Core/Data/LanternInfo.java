package com.aleksey.decorations.Core.Data;

import com.bioxx.tfc.api.Crafting.AnvilReq;

public class LanternInfo
{
    public String LanternName;
    public String SheetName;
    public AnvilReq Anvil;
    public int LightLevel;
    
    public LanternInfo(String lanternName, String sheetName, AnvilReq anvil, int lightLevel)
    {
        LanternName = lanternName;
        SheetName = sheetName;
        Anvil = anvil;
        LightLevel = lightLevel;
    }
}
