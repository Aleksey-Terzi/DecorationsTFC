package com.aleksey.decorations.TESR;

import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

import com.aleksey.decorations.TileEntities.TileEntityGem;
import com.bioxx.tfc.Render.TESR.TESRBase;

public class TESRGem extends TESRBase
{
    private static float _shift = 0.021f;
    
    public TESRGem()
    {
    }

    @Override
    public void renderTileEntityAt(TileEntity par1TileEntity, double par2, double par4, double par6, float par8)
    {
        this.renderAt((TileEntityGem)par1TileEntity, par2, par4, par6, par8);
    }
    
    private void renderAt(TileEntityGem te, double x, double y, double z, float f)
    {
        if (te.getWorldObj() == null)
            return;
        
        ItemStack itemStack = te.getStackInSlot(0);

        if(te.renderItem == null)
        {
            te.renderItem = new EntityItem(field_147501_a.field_147550_f); //tileEntityRenderer.worldObj
            te.renderItem.hoverStart = 0f;
            te.renderItem.setEntityItemStack(itemStack);
        }

        if (itemStack == null)
            return;

        if (itemStack.getItemSpriteNumber() == 0)
            this.bindTexture(TextureMap.locationBlocksTexture);
        else
            this.bindTexture(TextureMap.locationItemsTexture);

        GL11.glPushMatrix();
        
        int attachedToSide = te.getWorldObj().getBlockMetadata(te.xCoord, te.yCoord, te.zCoord);
        
        switch(attachedToSide)
        {
            case 0:
                attachToDown(x, y, z);
                break;
            case 1:
                attachToUp(x, y, z);
                break;
            case 2:
                attachToNorth(x, y, z);
                break;
            case 3:
                attachToSouth(x, y, z);
                break;
            case 4:
                attachToWest(x, y, z);
                break;
            case 5:
                attachToEast(x, y, z);
                break;
        }

        itemRenderer.doRender(te.renderItem, 0, 0, 0, 0, 0);
        
        GL11.glPopMatrix();
    }
    
    private void attachToDown(double x, double y, double z)
    {
        GL11.glTranslatef((float)x + 0.5f, (float)y + (1f - _shift), (float)z + 0.25f);
        GL11.glRotatef(90, 1, 0, 0);
    }
    
    private void attachToUp(double x, double y, double z)
    {
        GL11.glTranslatef((float)x + 0.5f, (float)y + _shift, (float)z + 0.25f);
        GL11.glRotatef(90, 1, 0, 0);
    }
    
    private void attachToNorth(double x, double y, double z)
    {
        GL11.glTranslatef((float)x + 0.5f, (float)y + 0.25f, (float)z + (1f - _shift));
    }

    private void attachToSouth(double x, double y, double z)
    {
        GL11.glTranslatef((float)x + 0.5f, (float)y + 0.25f, (float)z + _shift);
    }

    private void attachToWest(double x, double y, double z)
    {
        GL11.glTranslatef((float)x + (1 - _shift), (float)y + 0.25f, (float)z + 0.5f);
        GL11.glRotatef(90, 0, 1, 0);
    }
    
    private void attachToEast(double x, double y, double z)
    {
        GL11.glTranslatef((float)x + _shift, (float)y + 0.25f, (float)z + 0.5f);
        GL11.glRotatef(90, 0, 1, 0);
    }    
}