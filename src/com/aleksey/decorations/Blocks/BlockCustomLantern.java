package com.aleksey.decorations.Blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.aleksey.decorations.Core.BlockList;
import com.aleksey.decorations.Core.Data.LanternInfo;
import com.bioxx.tfc.Core.TFCTabs;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockCustomLantern extends Block
{
    private LanternInfo _info;
    
    @SideOnly(Side.CLIENT)
    private IIcon _sideIcon;
      
    @SideOnly(Side.CLIENT)
    private IIcon _topIcon;

    public BlockCustomLantern(LanternInfo info)
    {
        super(Material.iron);
        
        _info = info;
        
        setHardness(4.0F);
        setResistance(10.0F);
        setBlockBounds(0.25F, 0.125F, 0.25F, 0.75F, 0.75F, 0.75F);
        setLightLevel((float)_info.LightLevel / 15.0F);
        setStepSound(Block.soundTypeMetal);
        setCreativeTab(TFCTabs.TFCMisc);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
        if (!player.isSneaking() || player.inventory.getCurrentItem() != null || world.isRemote)
            return false;

        int meta = world.getBlockMetadata(x, y, z);
        int newMeta = meta == 0 ? 1: 0;
        
        world.setBlockMetadataWithNotify(x, y, z, newMeta, 2);
          
        return true;
    }

    @Override
    public void onBlockAdded(World world, int x, int y, int z)
    {
        int meta = world.getBlockMetadata(x, y, z);
        
        if (meta == 0)
            world.setBlockMetadataWithNotify(x, y, z, 1, 2);
    }

    @Override
    public int getLightValue(IBlockAccess world, int x, int y, int z)
    {
        return world.getBlockMetadata(x, y, z) == 0 ? 0: getLightValue();
    }

    @Override
    public IIcon getIcon(int side, int metadata)
    {
        return side <= 1 ? _topIcon: _sideIcon;
    }

    @Override
    public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        return true;
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }
      
    @SideOnly(Side.CLIENT)
    @Override
    public void randomDisplayTick(World world, int x, int y, int z, Random rand)
    {
        int meta = world.getBlockMetadata(x, y, z);
        double centerX = x + 0.5;
        double centerY = y + 0.5;
        double centerZ = z + 0.5;
        
        if (meta != 0)
            world.spawnParticle("flame", centerX, centerY, centerZ, 0.0D, 0.0D, 0.0D);
    }

    @Override
    public int getRenderType()
    {
        return BlockList.LanternRenderId;
    }

    @Override
    public int damageDropped(int meta)
    {
        return 0;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister register)
    {
        _topIcon = register.registerIcon("decorations:lanterns/LanternTop" + _info.LanternName);
        _sideIcon = register.registerIcon("decorations:lanterns/LanternSide" + _info.LanternName);
    }
}
