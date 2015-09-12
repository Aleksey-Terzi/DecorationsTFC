package com.aleksey.decorations.Blocks;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraftforge.common.MinecraftForge;

import com.bioxx.tfc.Blocks.Terrain.BlockSmooth;
import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.api.Constant.Global;

public class BlockMudBricks extends BlockSmooth
{
    private int _startIndex;
    
    public int getStartIndex()
    {
        return _startIndex;
    }
    
    public BlockMudBricks(int startIndex)
    {
        super(Material.ground);
        
        this.setHardness(3f);
        this.setResistance(10.0f);
        this.setHarvestLevel("pickaxe", 0);
        this.setCreativeTab(TFCTabs.TFC_BUILDING);
        
        _startIndex = startIndex;
        
        int count = _startIndex == 0 ? 16: Global.STONE_ALL.length - _startIndex;
        
        this.names = new String[count];
        this.icons = new IIcon[count];
    }
    
    @Override
    public IIcon getIcon(int side, int meta)
    {
        return this.icons[meta];
    }

    @Override
    public void registerBlockIcons(IIconRegister register)
    {
        for(int i = 0; i < this.icons.length; i++)
            this.icons[i] = register.registerIcon("decorations:mudbricks/" + Global.STONE_ALL[_startIndex + i].replaceAll(" ", "") + "Bricks");
    }
}
