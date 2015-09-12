package com.aleksey.decorations.Items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.World;

import com.aleksey.decorations.Core.FluidList;
import com.bioxx.tfc.Items.ItemTerra;
import com.bioxx.tfc.api.TFCItems;
import com.bioxx.tfc.api.Enums.EnumItemReach;
import com.bioxx.tfc.api.Enums.EnumSize;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemPlaster extends ItemTerra
{
    private IIcon _overlayIcon;
    
    public ItemPlaster()
    {
        super();
        
        this.setSize(EnumSize.MEDIUM);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister registerer)
    {
        this.itemIcon = registerer.registerIcon("decorations:WoodenBucketEmpty");
        _overlayIcon = registerer.registerIcon("decorations:WoodenBucketOverlay");
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
        return pass == 1
            ? FluidList.Plaster.getColor()
            : super.getColorFromItemStack(is, pass);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean requiresMultipleRenderPasses()
    {
        return true;
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
    public boolean onItemUse(ItemStack is, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
    {
        /*
        int[][] map = {{0,-1,0},{0,1,0},{0,0,-1},{0,0,1},{-1,0,0},{1,0,0}};

        if (!isEmpty && world.isAirBlock( x + map[side][0], y + map[side][1], z + map[side][2] ) ) {
            world.setBlock( x + map[side][0], y + map[side][1], z + map[side][2], TFCBlocks.FreshWater, 2, 0x1 );
            player.setCurrentItemOrArmor(0, new ItemStack(TFCItems.WoodenBucketEmpty));
            return true;
        }
        */
        
        return false;
    }

    @Override
    public EnumItemReach getReach(ItemStack is)
    {
        return EnumItemReach.SHORT;
    }
}
