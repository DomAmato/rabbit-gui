package com.rabbit.gui.proxy;

import com.rabbit.gui.RabbitGui;
import com.rabbit.gui.base.Stage;
import com.rabbit.gui.component.display.entity.DisplayEntity;
import com.rabbit.gui.component.display.entity.DisplayEntityRenderer;
import com.rabbit.gui.show.IShow;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class Client implements Proxy {

	/**
	 * If there are any currently opened Stage it will display given show in it
	 * <br>
	 * Otherwise will create new Stage
	 *
	 * @param show
	 */
	@Override
	public void display(IShow show) {
		Stage current = getCurrentStage();
		if (current != null) {
			current.setShow(show);
			current.reinitShow();
		} else {
			Minecraft.getMinecraft().displayGuiScreen(new Stage(show));
		}
	}

	/**
	 * Returns currently opened Stage, may be null
	 */
	@Override
	public Stage getCurrentStage() {
		return Minecraft.getMinecraft().currentScreen instanceof Stage ? (Stage) Minecraft.getMinecraft().currentScreen
				: null;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see forge.reference.proxy.Proxy#renderGUI()
	 */
	@Override
	public void renderGUI() {
		// Render GUI when on call from client
	}

	@Override
	public void preInit() {
		RenderingRegistry.registerEntityRenderingHandler(DisplayEntity.class, new DisplayEntityRenderer());		
	}

	@Override
	public void postInit() {
		// TODO Auto-generated method stub
		
	}
}