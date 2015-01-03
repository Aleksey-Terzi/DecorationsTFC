package com.aleksey.decorations.Blocks;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

import com.bioxx.tfc.Blocks.Terrain.BlockSmooth;
import com.bioxx.tfc.Core.TFCTabs;

public class BlockAlabaster extends BlockSmooth
{
    public BlockAlabaster()
    {
        super(Material.rock);
        this.setCreativeTab(TFCTabs.TFCBuilding);
        this.names = new String[] { "Alabaster" };
        this.icons = new IIcon[1];
    }
    
    @Override
    public void registerBlockIcons(IIconRegister iconRegisterer)
    {
        icons[0] = iconRegisterer.registerIcon("decorations:Alabaster");
    }
}
