package com.aleksey.decorations.Blocks;

import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

import com.aleksey.decorations.Core.Constants;
import com.bioxx.tfc.Blocks.BlockTerra;
import com.bioxx.tfc.Core.TFCTabs;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockMudBricks extends BlockTerra
{
    @SideOnly(Side.CLIENT)
    private IIcon _icon;
    
    public BlockMudBricks()
    {
        super(Material.ground);
        
        this.setHardness(2f);
        this.setResistance(10.0f);
        this.setCreativeTab(TFCTabs.TFCBuilding);
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public int getRenderColor(int meta)
    {
        return Constants.MudBrick_ColorDry;
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public int colorMultiplier(IBlockAccess world, int x, int y, int z)
    {
        return Constants.MudBrick_ColorDry;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List list)
    {
        list.add(new ItemStack(this, 1, 0));
    }

    @Override
    public int damageDropped(int i)
    {
        return i;
    }

    @Override
    public IIcon getIcon(int side, int metadata)
    {
        return _icon;
    }

    @Override
    public void registerBlockIcons(IIconRegister register)
    {
        _icon = register.registerIcon("decorations:mudbricks/AndesiteBricks");
    }
}
