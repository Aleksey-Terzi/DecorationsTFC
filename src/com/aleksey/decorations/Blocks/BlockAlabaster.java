package com.aleksey.decorations.Blocks;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

import com.aleksey.decorations.Core.FluidList;
import com.bioxx.tfc.Blocks.Terrain.BlockSmooth;
import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.Items.ItemDyeCustom;

public class BlockAlabaster extends BlockSmooth
{
    public BlockAlabaster()
    {
        super(Material.rock);
        this.setHardness(12f);
        this.setResistance(10.0f);
        this.setCreativeTab(TFCTabs.TFCBuilding);
        
        this.names = new String[16];
        for(int i = 0; i < this.names.length; i++)
        {
            String dyeName = ItemDyeCustom.dyeColorNames[FluidList.LiquidDyes[i].TFCDyeIndex];

            this.names[i] = "Alabaster" + dyeName.substring(0, 1).toUpperCase() + dyeName.substring(1);
        }
        
        this.icons = new IIcon[16];
    }
    
    @Override
    public IIcon getIcon(int side, int meta)
    {
        return this.icons[meta];
    }
    
    @Override
    public void registerBlockIcons(IIconRegister iconRegisterer)
    {
        for(int i = 0; i < this.icons.length; i++)
            this.icons[i] = iconRegisterer.registerIcon("decorations:alabasters/" + this.names[i]);
    }
}
