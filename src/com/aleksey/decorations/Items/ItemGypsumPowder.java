package com.aleksey.decorations.Items;

import net.minecraft.client.renderer.texture.IIconRegister;

import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.Items.ItemTerra;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemGypsumPowder extends ItemTerra
{
    public ItemGypsumPowder()
    {
        super();
        
        setCreativeTab(TFCTabs.TFCMaterials);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister registerer)
    {
        this.itemIcon = registerer.registerIcon("decorations:GypsumPowder");
    }
}
