package com.aleksey.decorations.Items;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

import com.aleksey.decorations.Core.FluidList;
import com.bioxx.tfc.Items.ItemTerra;
import com.bioxx.tfc.api.TFCItems;
import com.bioxx.tfc.api.Enums.EnumItemReach;
import com.bioxx.tfc.api.Enums.EnumSize;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemLiquidDye extends ItemTerra
{
    private IIcon _overlayIcon;
    
    public ItemLiquidDye()
    {
        super();
        
        this.setMaxDamage(0);
        this.setSize(EnumSize.MEDIUM);
        this.setHasSubtypes(true);
    }
    
    @Override
    public void getSubItems(Item item, CreativeTabs tabs, List list)
    {
        for(int i = 0; i < FluidList.LiquidDyes.length; i++)
            list.add(new ItemStack(this, 1, i));
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister registerer)
    {
        this.itemIcon = registerer.registerIcon("decorations:WoodenBucketEmpty");
        _overlayIcon = registerer.registerIcon("decorations:WoodenBucketDyeOverlay");
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(ItemStack is, int pass)
    {
        return pass == 1 ? _overlayIcon : this.itemIcon;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getColorFromItemStack(ItemStack is, int pass)
    {
        int index = is.getItemDamage();
        
        if(index < 0 || index >= FluidList.LiquidDyes.length)
            index = 0;
        
        return pass == 1
            ? FluidList.LiquidDyes[index].getColor()
            : super.getColorFromItemStack(is, pass);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public boolean requiresMultipleRenderPasses()
    {
        return true;
    }
    
    @Override
    public String getItemStackDisplayName(ItemStack is)
    {
        int meta = is.getItemDamage();
        String displayName = super.getItemStackDisplayName(is);
        Fluid fluid = FluidList.LiquidDyes[meta];
        String fluidName = fluid.getLocalizedName(new FluidStack(fluid, 1));
        
        return displayName + " (" + fluidName + ")";
    }

    @Override
    public boolean canStack()
    {
        return false;
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    @Override
    public ItemStack onItemRightClick(ItemStack is, World world, EntityPlayer player)
    {
        MovingObjectPosition mop = this.getMovingObjectPositionFromPlayer(world, player, false);

        if (mop == null)
            return is;

        if (mop.typeOfHit == MovingObjectType.BLOCK)
        {
            int x = mop.blockX;
            int y = mop.blockY;
            int z = mop.blockZ;

            if (!world.canMineBlock(player, x, y, z))
                return is;

            return new ItemStack(TFCItems.woodenBucketEmpty);
        }

        return is;
    }

    @Override
    public EnumItemReach getReach(ItemStack is)
    {
        return EnumItemReach.SHORT;
    }
}
