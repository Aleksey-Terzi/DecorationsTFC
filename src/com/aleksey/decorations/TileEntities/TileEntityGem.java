package com.aleksey.decorations.TileEntities;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.AxisAlignedBB;

import com.aleksey.decorations.Blocks.BlockCustomGem;
import com.aleksey.decorations.Core.Data.Bound;
import com.bioxx.tfc.TileEntities.NetworkTileEntity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntityGem extends NetworkTileEntity implements IInventory
{
    private ItemStack[] _storage = new ItemStack[1];

    public EntityItem renderItem;
    
    public TileEntityGem()
    {
    }

    @Override
    public boolean canUpdate()
    {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public double getMaxRenderDistanceSquared()
    {
        return 1024.0D;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox()
    {
        int attachedToSide = this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord);
        
        Bound bound = BlockCustomGem.getBound(attachedToSide);
        
        return AxisAlignedBB.getBoundingBox(xCoord + bound.MinX, yCoord + bound.MinY, zCoord + bound.MinZ, xCoord + bound.MaxX, yCoord + bound.MaxY, zCoord + bound.MaxZ);
    }

    @Override
    public int getSizeInventory()
    {
        return 1;
    }

    @Override
    public ItemStack getStackInSlot(int i)
    {
        return _storage[i];
    }

    @Override
    public ItemStack decrStackSize(int i, int j)
    {
        _storage[i].stackSize -= j;
        return _storage[i];
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int i)
    {
        return null;
    }

    @Override
    public void setInventorySlotContents(int i, ItemStack itemstack)
    {
        _storage[i] = itemstack;
    }

    @Override
    public String getInventoryName()
    {
        return "Gem";
    }

    @Override
    public boolean hasCustomInventoryName()
    {
        return false;
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 1;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer entityplayer)
    {
        return false;
    }

    @Override
    public void openInventory()
    {
    }

    @Override
    public void closeInventory()
    {
    }

    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemstack)
    {
        return false;
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) 
    {
        super.writeToNBT(nbt);
        
        NBTTagList nbttaglist = new NBTTagList();
        
        for(int i = 0; i < _storage.length; i++)
        {
            if(_storage[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                _storage[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        nbt.setTag("Items", nbttaglist);
    }
    
    @Override
    public void readFromNBT(NBTTagCompound nbt) 
    {
        super.readFromNBT(nbt);
        
        NBTTagList nbttaglist = nbt.getTagList("Items", 10);
        
        _storage = new ItemStack[1];
        
        for(int i = 0; i < nbttaglist.tagCount(); i++)
        {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            byte byte0 = nbttagcompound1.getByte("Slot");
            if(byte0 >= 0 && byte0 < _storage.length)
                _storage[byte0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
        }
    }

    @Override
    public void handleInitPacket(NBTTagCompound nbt)
    {
        if(nbt.hasKey("id"))
            this._storage[0] = ItemStack.loadItemStackFromNBT(nbt);
    }

    @Override
    public void handleDataPacket(NBTTagCompound nbt)
    {
    }

    @Override
    public void createDataNBT(NBTTagCompound nbt)
    {
    }

    @Override
    public void createInitNBT(NBTTagCompound nbt)
    {
        if(_storage[0] != null)
            this._storage[0].writeToNBT(nbt);
    }
}
