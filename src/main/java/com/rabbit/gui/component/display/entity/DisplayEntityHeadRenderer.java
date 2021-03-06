package com.rabbit.gui.component.display.entity;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class DisplayEntityHeadRenderer implements IRenderFactory {

	@Override
	public Render createRenderFor(RenderManager manager) {
		return new RenderDisplayEntity(manager, new ModelDisplayEntityHead(), 0.3F);
	}

}