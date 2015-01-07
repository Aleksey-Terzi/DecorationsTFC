package com.aleksey.decorations.Blocks;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

import com.aleksey.decorations.Core.Constants;
import com.aleksey.decorations.Core.FluidList;
import com.bioxx.tfc.Blocks.Terrain.BlockSmooth;
import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.Items.ItemDyeCustom;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockAlabaster extends BlockSmooth
{
    public BlockAlabaster()
    {
        super(Material.rock);
        this.setCreativeTab(TFCTabs.TFCBuilding);
        
        this.names = new String[16];
        for(int i = 0; i < this.names.length; i++)
            this.names[i] = "Alabaster";
        
        this.icons = new IIcon[16];
    }
    
    @Override
    public void registerBlockIcons(IIconRegister iconRegisterer)
    {
        this.icons[0] = iconRegisterer.registerIcon("decorations:Alabaster");
        
        for(int i = 1; i < this.icons.length; i++)
            this.icons[i] = this.icons[0]; 
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public int getRenderColor(int meta)
    {
        return FluidList.LiquidDyes[meta].getColor();
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public int colorMultiplier(IBlockAccess world, int x, int y, int z)
    {
        int meta = world.getBlockMetadata(x, y, z);
        
        return FluidList.LiquidDyes[meta].getColor();
    }
}
