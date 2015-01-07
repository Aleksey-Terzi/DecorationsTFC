package com.aleksey.decorations.Render.Blocks;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

import com.bioxx.tfc.Render.RenderBlocksLightCache;
import com.bioxx.tfc.TileEntities.TEDetailed;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RenderDetailed implements ISimpleBlockRenderingHandler
{
    private static RenderBlocksLightCache renderer;
    
    @Override
    public boolean renderWorldBlock(IBlockAccess world, int i, int j, int k, Block block, int modelId, RenderBlocks renderer)
    {
        //TEDetailed te = (TEDetailed)renderer.blockAccess.getTileEntity(i, j, k);
        //Block block2 = Block.getBlockById(te.TypeID) == BlockList.Alabaster ? BlockList.Alabaster: block;
        
        return renderBlockDetailed(block, i, j, k, renderer);
    }

    @Override
    public boolean shouldRender3DInInventory(int i)
    {
        return true;
    }

    @Override
    public int getRenderId()
    {
        return 0;
    }

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
    {
    }
    
    public static boolean renderBlockDetailed(Block block, int i, int j, int k, RenderBlocks renderblocks)
    {
        TEDetailed te = (TEDetailed) renderblocks.blockAccess.getTileEntity(i, j, k);

        if(renderer == null)
            renderer = new RenderBlocksLightCache(renderblocks);
        else
            renderer.update(renderblocks);
        
        Block originalBlock = te.TypeID > 0 ? Block.getBlockById(te.TypeID): block;
        int color = originalBlock.getRenderColor(te.MetaID);

        // Capture full block lighting data...
        renderer.disableRender();
        renderer.setRenderBounds(0,0,0,1,1,1);
        renderStandardBlock(block, i, j, k, color);
        renderer.enableRender();

        if(te.TypeID <= 0)
            return false;

        int type = te.TypeID;
        int meta = te.MetaID;

        IIcon myTexture = renderblocks.overrideBlockTexture == null ? originalBlock.getIcon(0, te.MetaID) : renderblocks.overrideBlockTexture;
        
        for(int subX = 0; subX < 2; subX++)
        {
            for(int subZ = 0; subZ < 2; subZ++)
            {
                for(int subY = 0; subY < 2; subY++)
                {
                    if(!te.isQuadSolid(subX, subY, subZ))
                    {
                        renderMiniBlock(block, i, j, k, subX, subY, subZ, te, type, meta, myTexture);
                    }
                    else
                    {
                        float minX = 0.5f * subX;
                        float maxX = minX + 0.5f;
                        float minY = 0.5f * subY;
                        float maxY = minY + 0.5f;
                        float minZ = 0.5f * subZ;
                        float maxZ = minZ + 0.5f;

                        renderer.setRenderBounds(minX, minY, minZ, maxX, maxY, maxZ);
                        renderer.renderCachedBlock(block, i, j, k, myTexture);
                    }
                }
            }
        }
        renderer.clearOverrideBlockTexture();
        return true;
    }

    private static void renderMiniBlock(Block block, int i, int j, int k, int x, int y, int z, TEDetailed te, int type, int meta, IIcon myTexture)
    {
        for(int subX = x*4; subX < 4+x*4; subX++)
        {
            for(int subZ = z*4; subZ < 4+z*4; subZ++)
            {
                for(int subY = y*4; subY < 4+y*4; subY++)
                {
                    boolean subExists = isOpaque(te,subX, subY, subZ);
                    if ( subExists )
                    {
                        boolean isVisible = isTransparent(te,subX-1, subY, subZ) || 
                                isTransparent(te,subX+1, subY, subZ) ||
                                isTransparent(te,subX, subY-1, subZ) ||
                                isTransparent(te,subX, subY+1, subZ) ||
                                isTransparent(te,subX, subY, subZ-1) ||
                                isTransparent(te,subX, subY, subZ+1);
                        
                        if ( isVisible )
                        {
                            float minX = 0.125f*subX;
                            float maxX = minX + 0.125f;
                            float minY = 0.125f*subY;
                            float maxY = minY + 0.125f;
                            float minZ = 0.125f*subZ;
                            float maxZ = minZ + 0.125f;
    
                            renderer.setRenderBounds(minX, minY, minZ, maxX, maxY, maxZ);
                            renderer.renderCachedBlock(block, i, j, k, myTexture);
                        }
                    }
                }
            }
        }
    }

    private static boolean isOpaque(TEDetailed te, int x, int y, int z)
    {
        return te.data.get((x * 8 + z)*8 + y);
    }
    
    private static boolean isTransparent(TEDetailed te, int x, int y, int z)
    {
        if ( x < 0 || x >= 8 || y < 0 || y >= 8 || z < 0 || z >= 8 )
            return true;
        
        return ! te.data.get((x * 8 + z)*8 + y);
    }
    
    private static boolean renderStandardBlock(Block block, int x, int y, int z, int color)
    {
        int l = color;
        float f = (float)(l >> 16 & 255) / 255.0F;
        float f1 = (float)(l >> 8 & 255) / 255.0F;
        float f2 = (float)(l & 255) / 255.0F;

        if (EntityRenderer.anaglyphEnable)
        {
            float f3 = (f * 30.0F + f1 * 59.0F + f2 * 11.0F) / 100.0F;
            float f4 = (f * 30.0F + f1 * 70.0F) / 100.0F;
            float f5 = (f * 30.0F + f2 * 70.0F) / 100.0F;
            f = f3;
            f1 = f4;
            f2 = f5;
        }

        return Minecraft.isAmbientOcclusionEnabled() && block.getLightValue() == 0 ? (renderer.partialRenderBounds ? renderer.renderStandardBlockWithAmbientOcclusionPartial(block, x, y, z, f, f1, f2) : renderer.renderStandardBlockWithAmbientOcclusion(block, x, y, z, f, f1, f2)) : renderer.renderStandardBlockWithColorMultiplier(block, x, y, z, f, f1, f2);
    }
}