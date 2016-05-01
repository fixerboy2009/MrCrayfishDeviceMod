package com.mrcrayfish.device.gui;

import com.mrcrayfish.device.app.Laptop;
import com.mrcrayfish.device.tileentity.TileEntityLaptop;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler
{
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
	{
		System.out.println("Calleds");
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
	{
		System.out.println("Calledc");
		if(ID == Laptop.ID)
		{
			TileEntity tileEntity = player.worldObj.getTileEntity(new BlockPos(x, y, z));
			if(tileEntity instanceof TileEntityLaptop)
			{
				TileEntityLaptop laptop = (TileEntityLaptop) tileEntity;
				return new Laptop(laptop.getAppData(), x, y, z);
			}
		}
		return null;
	}
}