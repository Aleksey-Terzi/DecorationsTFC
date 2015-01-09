package com.aleksey.decorations.Items.ItemBlocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import com.bioxx.tfc.Items.ItemTerra;
import com.bioxx.tfc.api.Enums.EnumItemReach;
import com.bioxx.tfc.api.Enums.EnumSize;
import com.bioxx.tfc.api.Enums.EnumWeight;
import com.bioxx.tfc.api.Interfaces.ISize;

public class ItemMudBricks extends ItemBlock implements ISize
{
    public ItemMudBricks(Block block)
    {
        super(block);
        
        setHasSubtypes(true);
    }
    
    @Override
    public String getUnlocalizedName(ItemStack itemstack)
    {
        return getUnlocalizedName();
        /*
        int meta = itemstack.getItemDamage();
        
        if(meta < 0 || meta >= _names.length)
            meta = 0;
        
        return getUnlocalizedName() + "." + _names[meta];
        */
    }
    
    @Override
    public void addInformation(ItemStack is, EntityPlayer player, List arraylist, boolean flag)
    {
        ItemTerra.addSizeInformation(is, arraylist);
    }
    
    @Override
    public int getItemStackLimit(ItemStack is)
    {
        return this.getSize(null).stackSize * getWeight(null).multiplier;
    }
    
    @Override
    public boolean canStack()
    {
        return true;
    }
    
    @Override
    public int getMetadata(int i)
    {
        return i;
    }
    
    @Override
    public EnumSize getSize(ItemStack is)
    {
        return EnumSize.SMALL;
    }

    @Override
    public EnumWeight getWeight(ItemStack is)
    {
        return EnumWeight.MEDIUM;
    }
    
    @Override
    public EnumItemReach getReach(ItemStack is)
    {
        return EnumItemReach.SHORT;
    }
}
