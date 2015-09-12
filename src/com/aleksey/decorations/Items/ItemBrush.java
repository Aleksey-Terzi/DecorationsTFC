package com.aleksey.decorations.Items;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidContainerItem;

import com.aleksey.decorations.Blocks.BlockAlabaster;
import com.aleksey.decorations.Core.Constants;
import com.aleksey.decorations.Core.FluidList;
import com.bioxx.tfc.Core.FluidBaseTFC;
import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.Items.ItemTerra;
import com.bioxx.tfc.api.Enums.EnumSize;
import com.bioxx.tfc.api.Enums.EnumWeight;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBrush extends ItemTerra implements IFluidContainerItem
{
    private IIcon _overlayIcon;
    
    public ItemBrush()
    {
        super();
        
        setMaxDamage(Constants.Brush_Capacity);
        setCreativeTab(TFCTabs.TFC_MISC);
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister registerer)
    {
        this.itemIcon = registerer.registerIcon("decorations:Brush");
        _overlayIcon = registerer.registerIcon("decorations:BrushOverlay");
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(ItemStack is, int pass)
    {
        return pass == 1 && getFluid(is) != null ? _overlayIcon : this.itemIcon;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getColorFromItemStack(ItemStack is, int pass)
    {
        FluidStack fluidStack = getFluid(is);
        
        if(pass == 0 || fluidStack == null)
            return super.getColorFromItemStack(is, pass);
        
        FluidBaseTFC fluid = (FluidBaseTFC)fluidStack.getFluid(); 
        
        return fluid.getColor();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean requiresMultipleRenderPasses()
    {
        return true;
    }
    
    @Override
    public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
    {
        if (!entityplayer.canPlayerEdit(x, y, z, side, itemstack) || itemstack.getItemDamage() == itemstack.getMaxDamage())
            return false;
        
        FluidStack fluidStack = getFluid(itemstack);
        
        if(fluidStack == null)
            return false;

        Block block = world.getBlock(x, y, z);
        
        if(!(block instanceof BlockAlabaster))
        {
            if(!entityplayer.isSneaking())
                return false;
            
            ItemStack newItemStack = itemstack.copy();
            
            drain(newItemStack, fluidStack.amount, true);
            
            entityplayer.inventory.setInventorySlotContents(entityplayer.inventory.currentItem, newItemStack);        
            entityplayer.onUpdate();

            return true;
        }
        
        if(!(block instanceof BlockAlabaster)
                || !world.canMineBlock(entityplayer, x, y, z)
                || !entityplayer.canPlayerEdit(x, y, z, side, itemstack)
                )
        {
            return false;
        }
        
        int dyeIndex = getDyeIndex(fluidStack);
        
        world.setBlockMetadataWithNotify(x, y, z, dyeIndex, 2);
        
        ItemStack newItemStack = itemstack.copy();
        
        drain(newItemStack, Constants.Brush_MbPerUse, true);
        
        entityplayer.inventory.setInventorySlotContents(entityplayer.inventory.currentItem, newItemStack);        
        entityplayer.onUpdate();

        return true;
    }
    
    @Override
    public String getItemStackDisplayName(ItemStack is)
    {
        String displayName = super.getItemStackDisplayName(is);
        
        if(is.getItemDamage() == is.getMaxDamage())
            return displayName;
        
        FluidStack fluidStack = getFluid(is);
        
        if(fluidStack == null)
            return displayName;
        
        String fluidName = fluidStack.getFluid().getLocalizedName(fluidStack);
        
        return displayName + " (" + fluidName + ")";
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
    public boolean canStack()
    {
      return false;
    }
    
    //************ IFluidContainerItem ************//
    
    @Override
    public FluidStack getFluid(ItemStack container)
    {
        if (container.stackTagCompound == null || !container.stackTagCompound.hasKey("Fluid"))
            return null;

        return FluidStack.loadFluidStackFromNBT(container.stackTagCompound.getCompoundTag("Fluid"));
    }

    @Override
    public int getCapacity(ItemStack container)
    {
        return Constants.Brush_Capacity;
    }

    @Override
    public int fill(ItemStack container, FluidStack resource, boolean doFill)
    {
        if(getDyeIndex(resource) < 0)
            return 0;
        
        NBTTagCompound fluidTag = container.stackTagCompound != null && container.stackTagCompound.hasKey("Fluid")
                ? container.stackTagCompound.getCompoundTag("Fluid")
                : null;
        
        FluidStack stack = fluidTag != null
                ? FluidStack.loadFluidStackFromNBT(fluidTag)
                : null;
                
        int resourceAmount = Constants.Brush_MbPerUse * (resource.amount / Constants.Brush_MbPerUse);
                
        int filled = stack != null
                ? Math.min(Constants.Brush_Capacity - stack.amount, resourceAmount)
                : Math.min(Constants.Brush_Capacity, resourceAmount);
                
        if (!doFill || filled == 0)
            return filled;
        
        if (stack != null && !stack.isFluidEqual(resource))
            return 0;

        if (container.stackTagCompound == null)
            container.stackTagCompound = new NBTTagCompound();

        if(stack == null)
        {
            stack = new FluidStack(resource.getFluid(), filled);
            fluidTag = new NBTTagCompound(); 
        }
        else
            stack.amount += filled;

        container.stackTagCompound.setTag("Fluid", stack.writeToNBT(fluidTag));
        container.setItemDamage(container.getMaxDamage() - stack.amount);

        return filled;
    }
    
    private static int getDyeIndex(FluidStack fluidStacks)
    {
        Fluid fluid = fluidStacks.getFluid();
        
        for(int i = 0; i < FluidList.LiquidDyes.length; i++)
        {
            if(fluid == FluidList.LiquidDyes[i])
                return i;
        }
        
        return -1;
    }

    @Override
    public FluidStack drain(ItemStack container, int maxDrain, boolean doDrain)
    {
        NBTTagCompound fluidTag = container.stackTagCompound != null && container.stackTagCompound.hasKey("Fluid")
                ? container.stackTagCompound.getCompoundTag("Fluid")
                : null;
        
        FluidStack stack = fluidTag != null
                ? FluidStack.loadFluidStackFromNBT(fluidTag)
                : null;

        if (stack == null)
            return null;
        
        maxDrain = Constants.Brush_MbPerUse * (maxDrain / Constants.Brush_MbPerUse);

        int currentAmount = stack.amount;
        stack.amount = Math.min(stack.amount, maxDrain);
        
        if (!doDrain)
            return stack;
        
        if (currentAmount == stack.amount)
        {
            container.stackTagCompound.removeTag("Fluid");

            if (container.stackTagCompound.hasNoTags())
                container.stackTagCompound = null;

            container.setItemDamage(0);

            return stack;
        }

        fluidTag.setInteger("Amount", currentAmount - stack.amount);
        container.stackTagCompound.setTag("Fluid", fluidTag);
        
        container.setItemDamage(container.getMaxDamage() - (currentAmount - stack.amount));

        return stack;
    }
}
