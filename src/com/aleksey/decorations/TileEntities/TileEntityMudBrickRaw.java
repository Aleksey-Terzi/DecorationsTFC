package com.aleksey.decorations.TileEntities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

import com.aleksey.decorations.Core.Constants;
import com.bioxx.tfc.Core.TFC_Time;

public class TileEntityMudBrickRaw extends TileEntity
{
    private long _dryStartTime;
    private long _dryTime;
    private boolean _canDry;
    
    public TileEntityMudBrickRaw()
    {
        _dryStartTime = 0;
        _dryTime = 0;
        _canDry = false;
    }
    
    @Override
    public void updateEntity()
    {
      if (this.worldObj.isRemote)
          return;
      
      if(_canDry)
          _dryTime += TFC_Time.getTotalHours() - _dryStartTime;
      
      if(_dryTime >= Constants.MudBrick_DryTimeInHours)
      {
          this.worldObj.setBlockMetadataWithNotify(this.xCoord, this.yCoord, this.zCoord, 1, 2);
          this.invalidate();
      }
      else
      {
          _dryStartTime = TFC_Time.getTotalHours();
          _canDry = canDry();
      }
    }
    
    @Override
    public void readFromNBT(NBTTagCompound tag)
    {
      super.readFromNBT(tag);
      
      _dryStartTime = tag.getLong("DryStartTime");
      _dryTime = tag.getLong("DryTime");
      _canDry = tag.getBoolean("CanDry");
    }
    
    @Override
    public void writeToNBT(NBTTagCompound tag)
    {
      super.writeToNBT(tag);
      
      tag.setLong("DryStartTime", _dryStartTime);
      tag.setLong("DryTime", _dryTime);
      tag.setBoolean("CanDry", _canDry);
    }
    
    @Override
    public Packet getDescriptionPacket()
    {
        NBTTagCompound nbt = new NBTTagCompound();
        writeToNBT(nbt);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, nbt);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
    {
        readFromNBT(pkt.func_148857_g());
    }
    
    private boolean canDry()
    {
        return !this.worldObj.isRaining()
                && this.worldObj.canBlockSeeTheSky(this.xCoord, this.yCoord, this.zCoord)
                && this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord) == 0;
    }
}
