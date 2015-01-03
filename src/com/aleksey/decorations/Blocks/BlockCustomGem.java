package com.aleksey.decorations.Blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import com.aleksey.decorations.Core.Data.Bound;
import com.aleksey.decorations.Core.Data.GemInfo;
import com.aleksey.decorations.TileEntities.TileEntityGem;
import com.bioxx.tfc.Blocks.BlockTerraContainer;
import com.bioxx.tfc.Core.TFCTabs;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockCustomGem extends BlockTerraContainer
{
    public BlockCustomGem(GemInfo info)
    {
        super(Material.glass);
        
        setLightLevel(info.LightLevel / 15.0f);
        setStepSound(Block.soundTypeGlass);
        //setCreativeTab(TFCTabs.TFCMisc);
    }
    
    @Override
    public boolean getBlocksMovement(IBlockAccess bAccess, int x, int y, int z)
    {
        return true;
    }

    @Override
    public void onBlockPreDestroy(World world, int x, int y, int z, int meta) 
    {
        if(!world.isRemote)
        {
            TileEntity te = world.getTileEntity(x, y, z);
            
            if (te instanceof IInventory)
            {
                IInventory inv = (IInventory) te;
                
                for (int i = 0; i< inv.getSizeInventory(); i++)
                {
                    if (inv.getStackInSlot(i) != null)
                    {
                        EntityItem ei = new EntityItem(world, x + 0.5, y + 0.5, z + 0.5, inv.getStackInSlot(i));
                        inv.setInventorySlotContents(i, null);
                        ei.motionX = 0;
                        ei.motionY = 0;
                        ei.motionZ = 0;
                        world.spawnEntityInWorld(ei);
                    }
                }
            }
        }
        
        super.onBlockPreDestroy(world, x, y, z, meta);
    }
    
    @Override
    public Item getItemDropped(int metadata, Random rand, int fortune)
    {
        return null;
    }

    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z)
    {
        return null;
    }
    
    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }
    
    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
    {
        int attachedToSide = world.getBlockMetadata(x, y, z);
        int attachX = x;
        int attachY = y;
        int attachZ = z;
        
        switch(attachedToSide)
        {
            case 0:
                attachY = y + 1;
                break;
            case 1:
                attachY = y - 1;
                break;
            case 2:
                attachZ = z + 1;
                break;
            case 3:
                attachZ = z - 1;
                break;
            case 4:
                attachX = x + 1;
                break;
            case 5:
                attachX = x - 1;
                break;
            default:
                return;
        }
        
        if (world.isAirBlock(attachX, attachY, attachZ)
                || !world.getBlock(attachX, attachY, attachZ).isSideSolid(world, attachX, attachY, attachZ, ForgeDirection.VALID_DIRECTIONS[attachedToSide]))
        {
            world.setBlockToAir(x, y, z);
        }
    }
    
    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    @Override
    public boolean isReplaceable(IBlockAccess world, int x, int y, int z)
    {
        return false;
    }

    @Override
    public boolean canBeReplacedByLeaves(IBlockAccess world, int x, int y, int z)
    {
        return false;
    }
    
    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k)
    {
        return null;
    }

    @Override
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z)
    {
        setBlockBoundsBasedOnState(world, x, y, z);
        
        return super.getSelectedBoundingBoxFromPool(world, x, y, z);
    }
    
    @Override
    public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB AABB, List list, Entity entity)
    {
        setBlockBoundsBasedOnState(world, x, y, z);
        
        super.addCollisionBoxesToList(world, x, y, z, AABB, list, entity);
    }
    
    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess bAccess, int x, int y, int z)
    {
        Bound bound = getBound(bAccess.getBlockMetadata(x, y, z));
        
        setBlockBounds((float)bound.MinX, (float)bound.MinY, (float)bound.MinZ, (float)bound.MaxX, (float)bound.MaxY, (float)bound.MaxZ);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register)
    {
        this.blockIcon = register.registerIcon("decorations:Invisible");
    }
    
    @Override
    public TileEntity createNewTileEntity(World world, int meta)
    {
        return new TileEntityGem();
    }
    
    public static Bound getBound(int attachedToSide)
    {
        switch(attachedToSide)
        {
            case 0:
                return new Bound(0, 1 - 0.25, 0, 1, 1, 1);
            case 1:
                return new Bound(0, 0, 0, 1, 0.25, 1);
            case 2:
                return new Bound(0, 0, 1 - 0.25, 1, 1, 1);
            case 3:
                return new Bound(0, 0, 0, 1, 1, 0.25);
            case 4:
                return new Bound(1 - 0.25, 0, 0, 1, 1, 1);
            case 5:
                return new Bound(0, 0, 0, 0.25, 1, 1);
        }
        
        return null;
    }
}