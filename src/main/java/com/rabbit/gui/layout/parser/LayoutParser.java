package com.rabbit.gui.layout.parser;

import java.net.URI;

import com.rabbit.gui.show.LayoutShow;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * Parser of gui layout of any type
 *
 */
@SideOnly(Side.CLIENT)
public interface LayoutParser {
    
    /**
     * Returns parsed show
     * @param path - location of parsable file
     * @return parsed show
     */
    LayoutShow from(URI path);

}
