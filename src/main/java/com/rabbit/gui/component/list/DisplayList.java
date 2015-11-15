package com.rabbit.gui.component.list;

import java.util.Arrays;
import java.util.List;

import com.rabbit.gui.component.GuiWidget;
import com.rabbit.gui.component.WidgetContainer;
import com.rabbit.gui.component.list.DisplayList.ListEntry;
import com.rabbit.gui.layout.LayoutComponent;
import com.rabbit.gui.render.Renderer;
import com.rabbit.gui.utils.GeometryUtils;

@LayoutComponent
public class DisplayList extends GuiWidget implements WidgetContainer<ListEntry>{

    @LayoutComponent
    protected int slotHeight;
    
    @LayoutComponent
    protected List<ListEntry> content;
    
    protected DisplayList(){}
    
    public DisplayList(int xPos, int yPos, int width, int height, int slotHeight, List<ListEntry> content) {
        super(xPos, yPos, width, height);
        this.slotHeight = slotHeight;
        this.content = content;
    }
    
    @Override
    public DisplayList add(ListEntry object) {
        this.content.add(object);
        return this;
    }

    @Override
    public DisplayList addAll(ListEntry... objects) {
        this.content.addAll(Arrays.asList(objects));
        return this;
    }

    @Override
    public DisplayList remove(ListEntry object) {
        this.content.remove(object);
        return this;
    }

    @Override
    public DisplayList clear() {
        this.content.clear();
        return this;
    }

    @Override
    public List<ListEntry> getContent() {
        return content;
    }
    
    @Override
    public void onDraw(int mouseX, int mouseY, float partialTicks) {
        drawListBackground();
        drawListContent(mouseX, mouseY);
        super.onDraw(mouseX, mouseY, partialTicks);
    }
    
    protected void drawListBackground(){
        Renderer.drawRect(getX() - 1, getY() - 1, getX() + this.width + 1, getY() + this.height + 1, -6250336);
        Renderer.drawRect(getX(), getY(), getX() + this.width, getY() + this.height, -0xFFFFFF-1);
    }
    
    protected void drawListContent(int mouseX, int mouseY){
        for(int i = 0; i < content.size(); i++){
            ListEntry entry = content.get(i);
            int slotPosX = getX();
            int slotPosY = getY() + i * slotHeight;
            int slotWidth = this.width;
            int slotHeight = this.slotHeight;
            entry.onDraw(this, slotPosX, slotPosY, slotWidth, slotHeight, mouseX, mouseY);
        }
    }

    @Override
    public void onMouseClicked(int posX, int posY, int mouseButtonIndex) {
        super.onMouseClicked(posX, posY, mouseButtonIndex);
        boolean clickedOnList = GeometryUtils.isDotInArea(getX(), getY(), this.width, this.height, posX, posY);
        if(clickedOnList){
            handleMouseClickList(posX, posY);
        }
    }
    
    protected void handleMouseClickList(int mouseX, int mouseY){
        for(int i = 0; i < content.size(); i++){
            ListEntry entry = content.get(i);
            int slotPosX = getX();
            int slotPosY = getY() + i * slotHeight;
            int slotWidth = this.width;
            int slotHeight = this.slotHeight;
            boolean clickedOnEntry = GeometryUtils.isDotInArea(slotPosX, slotPosY, slotWidth, slotHeight, mouseX, mouseY);
            if(clickedOnEntry) entry.onClick(this, mouseX, mouseY);
        } 
    }

    @Override
    public DisplayList setId(String id) {
        assignId(id);
        return this;
    }
    
    public interface ListEntry{

        public default void onDraw(DisplayList list, int posX, int posY, int width, int height, int mouseX, int mouseY){}
        
        public default void onClick(DisplayList list, int mouseX, int mouseY){}
    
    }

}