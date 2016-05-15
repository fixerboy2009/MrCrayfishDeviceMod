package com.mrcrayfish.device.programs;

import com.mrcrayfish.device.app.Application;
import com.mrcrayfish.device.app.ApplicationBar;
import com.mrcrayfish.device.app.Component;
import com.mrcrayfish.device.app.Layout;
import com.mrcrayfish.device.app.components.Button;
import com.mrcrayfish.device.app.components.ButtonToggle;
import com.mrcrayfish.device.app.components.CheckBox;
import com.mrcrayfish.device.app.components.RadioGroup;
import com.mrcrayfish.device.app.listener.ClickListener;
import com.mrcrayfish.device.object.Game;
import com.mrcrayfish.device.object.TileGrid;
import com.mrcrayfish.device.object.tiles.Tile;

import net.minecraft.nbt.NBTTagCompound;

public class ApplicationBoatRacers extends Application 
{
	private Layout layoutLevelEditor;
	private Game game;
	private TileGrid tileGrid;
	private ButtonToggle btnForeground;
	private ButtonToggle btnBackground;
	private CheckBox checkBoxForeground;
	private CheckBox checkBoxBackground;
	private CheckBox checkBoxPlayer;

	public ApplicationBoatRacers() 
	{
		super("boat_racer", "Boat Racers", ApplicationBar.APP_BAR_GUI, 84, 30);
		this.setDefaultWidth(320);
		this.setDefaultHeight(160);
	}
	
	@Override
	protected void init(int x, int y) 
	{
		super.init(x, y);

		layoutLevelEditor = new Layout(364, 178);
		
		try 
		{
			game = new Game(x, y, 4, 4, 256, 144);
			game.setEditorMode(true);
			game.setRenderPlayer(false);
			game.fill(Tile.grass);
			layoutLevelEditor.addComponent(game);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		tileGrid = new TileGrid(x, y, 266, 3, game);
		layoutLevelEditor.addComponent(tileGrid);
		
		RadioGroup group = new RadioGroup();
		
		btnBackground = new ButtonToggle("BG", x, y, 266, 106, 44, 16);
		btnBackground.setToolTip("Background", "Tiles drawn will be on the background layer");
		btnBackground.setClickListener(new ClickListener()
		{
			@Override
			public void onClick(Component c, int mouseButton)
			{
				game.setCurrentLayer(Game.Layer.BACKGROUND);
			}
		});
		btnBackground.setRadioGroup(group);
		btnBackground.setSelected(true);
		layoutLevelEditor.addComponent(btnBackground);
		
		btnForeground = new ButtonToggle("FG", x, y, 314, 106, 44, 16);
		btnForeground.setToolTip("Foreground", "Tiles drawn will be on the foreground layer");
		btnForeground.setClickListener(new ClickListener()
		{
			@Override
			public void onClick(Component c, int mouseButton)
			{
				game.setCurrentLayer(Game.Layer.FOREGROUND);
			}
		});
		btnForeground.setRadioGroup(group);
		layoutLevelEditor.addComponent(btnForeground);
		
		checkBoxBackground = new CheckBox("Background", x, y, 3, 151);
		checkBoxBackground.setClickListener(new ClickListener()
		{
			@Override
			public void onClick(Component c, int mouseButton)
			{
				game.setRenderBackground(checkBoxBackground.isSelected());
			}
		});
		checkBoxBackground.setSelected(true);
		layoutLevelEditor.addComponent(checkBoxBackground);
		
		checkBoxForeground = new CheckBox("Foreground", x, y, 80, 151);
		checkBoxForeground.setClickListener(new ClickListener()
		{
			@Override
			public void onClick(Component c, int mouseButton)
			{
				game.setRenderForeground(checkBoxForeground.isSelected());
			}
		});
		checkBoxForeground.setSelected(true);
		layoutLevelEditor.addComponent(checkBoxForeground);
		
		checkBoxPlayer = new CheckBox("Player", x, y, 160, 151);
		checkBoxPlayer.setClickListener(new ClickListener()
		{
			@Override
			public void onClick(Component c, int mouseButton)
			{
				game.setRenderPlayer(checkBoxPlayer.isSelected());
			}
		});
		layoutLevelEditor.addComponent(checkBoxPlayer);
		
		setCurrentLayout(layoutLevelEditor);
	}

	@Override
	protected void load(NBTTagCompound tagCompound) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void save(NBTTagCompound tagCompound) {
		// TODO Auto-generated method stub

	}

	
}