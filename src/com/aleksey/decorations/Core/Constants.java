package com.aleksey.decorations.Core;

import com.aleksey.decorations.Core.Data.GemInfo;
import com.aleksey.decorations.Core.Data.LanternInfo;
import com.bioxx.tfc.api.Crafting.AnvilReq;

public class Constants
{
    public static LanternInfo[] Lanterns =
    {
        new LanternInfo("Copper", "item.Copper Sheet", AnvilReq.COPPER, 14),
        new LanternInfo("Bronze", "item.Bronze Sheet", AnvilReq.BRONZE, 14),
        new LanternInfo("BismuthBronze", "item.Bismuth Bronze Sheet", AnvilReq.BRONZE, 14),
        new LanternInfo("BlackBronze", "item.Black Bronze Sheet", AnvilReq.BRONZE, 14),
        new LanternInfo("Brass", "item.Brass Sheet", AnvilReq.BRONZE, 14),
        new LanternInfo("Lead", "item.Lead Sheet", AnvilReq.BRONZE, 14),
        new LanternInfo("Gold", "item.Gold Sheet", AnvilReq.BRONZE, 14),
        new LanternInfo("RoseGold", "item.Rose Gold Sheet", AnvilReq.BRONZE, 14),
        new LanternInfo("Silver", "item.Silver Sheet", AnvilReq.BRONZE, 14),
        new LanternInfo("SterlingSilver", "item.Sterling Silver Sheet", AnvilReq.BRONZE, 14),
        new LanternInfo("Platinum", "item.Platinum Sheet", AnvilReq.WROUGHTIRON, 15),
        new LanternInfo("WroughtIron", "item.Wrought Iron Sheet", AnvilReq.WROUGHTIRON, 15),
    };
    
    public static GemInfo[] Gems =
    {
        new GemInfo("Chipped", 11),
        new GemInfo("Flawed", 12),
        new GemInfo("Normal", 13),
        new GemInfo("Flawless", 14),
        new GemInfo("Exquisite", 15),
    };
    
    public static String[] Powders = { "Gypsum Powder" };
    
    public static int[] DyeColors = new int[] { 1973019, 11743532, 3887386, 5320730, 2437522, 8073150, 2651799, 0xB4B8B8, 4408131, 14188952, 4312372, 14602026, 6719955, 12801229, 15435844, 15790320 };
    
    public static int Brush_MbPerUse = 200; 
    public static int Brush_Capacity = 5 * Brush_MbPerUse;
    
    public static int MudBrick_DryTimeInHours = 12;
    public static int MudBrick_ColorWet = 0xAF976F;
    public static int MudBrick_ColorDry = 0xAA9C88;
}