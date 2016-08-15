package com.mrcrayfish.device.api.app.component;

import com.mrcrayfish.device.api.app.Application;
import com.mrcrayfish.device.api.app.Component;
import com.mrcrayfish.device.api.app.listener.ClickListener;
import com.mrcrayfish.device.core.Laptop;
import com.mrcrayfish.device.util.GuiHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import scala.actors.threadpool.Arrays;

public class Button extends Component
{
	protected static final ResourceLocation BUTTON_TEXTURES = new ResourceLocation("textures/gui/widgets.png");
	
	protected String text, toolTip, toolTipTitle;
	protected boolean hovered;
	protected int width, height;
	
	protected boolean hasIcon = false;
	protected ResourceLocation icon = null;
	protected int iconU, iconV;
	protected int iconWidth, iconHeight;
	
	protected ClickListener clickListener = null;
	
	public Button(String text, int x, int y, int left, int top, int width, int height) 
	{
		super(x, y, left, top);
		this.text = text;
		this.width = width;
		this.height = height;
	}
	
	public Button(int x, int y, int left, int top, ResourceLocation icon, int iconU, int iconV, int iconWidth, int iconHeight)
	{
		this("", x, y, left, top, iconWidth + 6, iconHeight + 6);
		this.hasIcon = true;
		this.icon = icon;
		this.iconU = iconU;
		this.iconV = iconV;
		this.iconWidth = iconWidth;
		this.iconHeight = iconHeight;
	}

	@Override
	public void render(Laptop laptop, Minecraft mc, int mouseX, int mouseY, boolean windowActive, float partialTicks) 
	{
		if (this.visible)
        {
            FontRenderer fontrenderer = mc.fontRendererObj;
            mc.getTextureManager().bindTexture(Component.COMPONENTS_GUI);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            this.hovered = isInside(mouseX, mouseY) && windowActive;
            int i = this.getHoverState(this.hovered);
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            GlStateManager.blendFunc(770, 771);
            GlStateManager.disableDepth();
            
            /* Corners */
            GuiHelper.drawModalRectWithUV(xPosition, yPosition, 96 + i * 5, 12, 2, 2, 2, 2);
            GuiHelper.drawModalRectWithUV(xPosition + width - 2, yPosition, 99 + i * 5, 12, 2, 2, 2, 2);
            GuiHelper.drawModalRectWithUV(xPosition + width - 2, yPosition + height - 2, 99 + i * 5, 15, 2, 2, 2, 2);
            GuiHelper.drawModalRectWithUV(xPosition, yPosition + height - 2, 96 + i * 5, 15, 2, 2, 2, 2);

            /* Middles */
            GuiHelper.drawModalRectWithUV(xPosition + 2, yPosition, 98 + i * 5, 12, width - 4, 2, 1, 2);
            GuiHelper.drawModalRectWithUV(xPosition + width - 2, yPosition + 2, 99 + i * 5, 14, 2, height - 4, 2, 1);
            GuiHelper.drawModalRectWithUV(xPosition + 2, yPosition + height - 2, 98 + i * 5, 15, width - 4, 2, 1, 2);
            GuiHelper.drawModalRectWithUV(xPosition, yPosition + 2, 96 + i * 5, 14, 2, height - 4, 2, 1);
            
            /* Center */
            GuiHelper.drawModalRectWithUV(xPosition + 2, yPosition + 2, 98 + i * 5, 14, width - 4, height - 4, 1, 1);
            
            int j = 14737632;

            if (!this.enabled)
            {
                j = 10526880;
            }
            else if (this.hovered)
            {
                j = 16777120;
            }
            
            if(hasIcon)
            {
            	mc.getTextureManager().bindTexture(icon);
            	this.drawTexturedModalRect(xPosition + 3, yPosition + 3, iconU, iconV, iconWidth, iconHeight);
            }
            else
            {
            	this.drawCenteredString(fontrenderer, this.text, this.xPosition + this.width / 2, this.yPosition + (this.height - 8) / 2, j);
            }
            
            GlStateManager.enableDepth();
        }
	}
	
	@Override
	public void renderOverlay(Laptop laptop, Minecraft mc, int mouseX, int mouseY, boolean windowActive) 
	{
        if(GuiHelper.isMouseInside(mouseX, mouseY, xPosition, yPosition, xPosition + width -1, yPosition + height) && this.toolTip != null)
        {
        	laptop.drawHoveringText(Arrays.asList(new String[] { EnumChatFormatting.GOLD + this.toolTipTitle, this.toolTip }), mouseX, mouseY);
        }
	}

	@Override
	public void handleClick(Application app, int mouseX, int mouseY, int mouseButton) 
	{
		if(!this.visible || !this.enabled)
			return;
		
		if(this.hovered) 
		{
			if(clickListener != null)
			{
				clickListener.onClick(this, mouseButton);
			}
			playClickSound(Minecraft.getMinecraft().getSoundHandler());
		}
	}
	
	public void setClickListener(ClickListener clickListener) 
	{
		this.clickListener = clickListener;
	}
	
	protected int getHoverState(boolean mouseOver)
    {
        int i = 1;

        if (!this.enabled)
        {
            i = 0;
        }
        else if (mouseOver)
        {
            i = 2;
        }

        return i;
    }
	
	public void playClickSound(SoundHandler handler) 
	{
		handler.playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
	}
	
	public boolean isInside(int mouseX, int mouseY)
	{
		return mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
	}
	
	public void setToolTip(String toolTipTitle, String toolTip)
	{
		this.toolTipTitle = toolTipTitle;
		this.toolTip = toolTip;
	}
}