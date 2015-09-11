package com.aleksey.decorations.Items;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

import com.aleksey.decorations.Core.FluidList;
import com.aleksey.decorations.Core.Data.LanternInfo;
import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.Items.ItemTerra;
import com.bioxx.tfc.api.Enums.EnumSize;
import com.bioxx.tfc.api.Enums.EnumWeight;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemLanternCore extends ItemTerra
{
    private LanternInfo _info;
    private IIcon _lanternCoreIcon;
    private IIcon _overlayIcon;
    
    public ItemLanternCore(LanternInfo info)
    {
        super();
        
        _info = info;
        
        setMaxDamage(0);
        setCreativeTab(TFCTabs.TFC_MISC);
    }
    
    @Override
    public void getSubItems(Item item, CreativeTabs tabs, List list)
    {
        for(int i = 0; i <= FluidList.AlcoholFluids.length; i++)
            list.add(new ItemStack(this, 1, i));
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister registerer)
    {
        _lanternCoreIcon = registerer.registerIcon("decorations:lanterns/LanternCore" + _info.LanternName);
        _overlayIcon = registerer.registerIcon("decorations:lanterns/LanternCoreOverlay");
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(ItemStack is, int pass)
    {
        int meta = is.getItemDamage();
        
        return meta > 0 && pass == 1 ? _overlayIcon : _lanternCoreIcon;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getColorFromItemStack(ItemStack is, int pass)
    {
        int meta = is.getItemDamage();
        
        return meta > 0 && pass == 1
            ? FluidList.AlcoholFluids[meta - 1].getColor()
            : super.getColorFromItemStack(is, pass);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean requiresMultipleRenderPasses()
    {
        return true;
    }
    
    @Override
    public String getUnlocalizedName(ItemStack is)
    {
        int meta = is.getItemDamage();
        String name = super.getUnlocalizedName(is);
        
        if(meta > 0)
            name += ".Filled";

        return name;
    }
    
    @Override
    public String getItemStackDisplayName(ItemStack is)
    {
        int meta = is.getItemDamage();
        String displayName = super.getItemStackDisplayName(is);
        
        if(meta == 0)
            return displayName;
        
        Fluid fluid = FluidList.AlcoholFluids[meta - 1];
        String fluidName = fluid.getLocalizedName(new FluidStack(fluid, 1));
        
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
        return EnumWeight.HEAVY;
    }
    
    @Override
    public boolean canStack()
    {
      return false;
    }
}
