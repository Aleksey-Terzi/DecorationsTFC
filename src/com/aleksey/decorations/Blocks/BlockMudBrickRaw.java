package com.aleksey.decorations.Blocks;

import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.aleksey.decorations.Core.BlockList;
import com.aleksey.decorations.Core.Constants;
import com.aleksey.decorations.Render.Blocks.RenderMudBrickRaw;
import com.aleksey.decorations.TileEntities.TileEntityMudBrickRaw;
import com.bioxx.tfc.Blocks.BlockTerraContainer;
import com.bioxx.tfc.Core.TFCTabs;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockMudBrickRaw extends BlockTerraContainer
{
    @SideOnly(Side.CLIENT)
    private IIcon _sideIcon;
      
    @SideOnly(Side.CLIENT)
    private IIcon _topIcon;
    
    public BlockMudBrickRaw()
    {
        super(Material.ground);
        
        this.setHardness(0.3f);
        this.setResistance(10.0f);
        this.setCreativeTab(TFCTabs.TFCMaterials);
        this.setBlockBounds((float)RenderMudBrickRaw.VoxelSizeScaled, 0, (float)RenderMudBrickRaw.VoxelSizeScaled, 1 - (float)RenderMudBrickRaw.VoxelSizeScaled, 8 * (float)RenderMudBrickRaw.VoxelSizeScaled, 1 - (float)RenderMudBrickRaw.VoxelSizeScaled);
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public int getRenderColor(int meta)
    {
        return meta == 0 ? Constants.MudBrick_ColorWet: Constants.MudBrick_ColorDry;
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public int colorMultiplier(IBlockAccess world, int x, int y, int z)
    {
        int meta = world.getBlockMetadata(x, y, z);
        
        return getRenderColor(meta);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List list)
    {
        list.add(new ItemStack(this, 1, 0));
        list.add(new ItemStack(this, 1, 1));
    }

    @Override
    public int damageDropped(int i)
    {
        return i;
    }

    @Override
    public IIcon getIcon(int side, int metadata)
    {
        return side <= 1 ? _topIcon: _sideIcon;
    }

    @Override
    public void registerBlockIcons(IIconRegister register)
    {
        _topIcon = register.registerIcon("decorations:mudbricks/AndesiteBrickTop");
        _sideIcon = register.registerIcon("decorations:mudbricks/AndesiteBrickSide");
    }
    
    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess par1iBlockAccess, int par2, int par3, int par4, int par5)
    {
        return true;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public boolean addDestroyEffects(World world, int x, int y, int z, int meta, EffectRenderer effectRenderer)
    {
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean addHitEffects(World worldObj, MovingObjectPosition target, EffectRenderer effectRenderer)
    {
        return true;
    }

    @Override
    public int getRenderType()
    {
        return BlockList.MudBrickRawRenderId;
    }
    
    @Override
    public TileEntity createNewTileEntity(World world, int metadata)
    {
        return metadata == 0 ? new TileEntityMudBrickRaw(): null;
    }
}
