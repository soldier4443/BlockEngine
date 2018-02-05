package com.midasit.blockengine.entity;

import com.midasit.blockengine.lwjgl.Vector3f;
import com.midasit.blockengine.models.TexturedModel;

/**
 * Created by nyh0111 on 2018-01-18.
 */

public class Entity {
    private TexturedModel model;
    private Vector3f position;
    private float rx, ry, rz;
    private float scale;
    
    private boolean isChanged;
    
    public Entity(TexturedModel model, Vector3f position, float rx, float ry, float rz, float scale) {
        this.model = model;
        this.position = position;
        this.rx = rx;
        this.ry = ry;
        this.rz = rz;
        this.scale = scale;
        this.isChanged = true;
    }
    
    /**
     * Called immediate before render.
     */
    public void update() {
        if (isChanged) {
            onUpdate();
            isChanged = false;
        }
    }
    
    public void onUpdate() {
    }
    
    public void setPosition(float dx, float dy, float dz) {
        position.x = dx;
        position.y = dy;
        position.z = dz;
        isChanged = true;
    }
    
    public void setRotation(float dx, float dy, float dz) {
        rx = dx;
        ry = dy;
        rz = dz;
        isChanged = true;
    }
    
    public void move(float dx, float dy, float dz) {
        position.x += dx;
        position.y += dy;
        position.z += dz;
        isChanged = true;
    }
    
    public void rotate(float dx, float dy, float dz) {
        rx += dx;
        ry += dy;
        rz += dz;
        isChanged = true;
    }
    
    
    public TexturedModel getModel() {
        return model;
    }
    
    public void setModel(TexturedModel model) {
        this.model = model;
    }
    
    public Vector3f getPosition() {
        return position;
    }
    
    public void setPosition(Vector3f position) {
        this.position = position;
    }
    
    public float getRx() {
        return rx;
    }
    
    public void setRx(float rx) {
        this.rx = rx;
    }
    
    public float getRy() {
        return ry;
    }
    
    public void setRy(float ry) {
        this.ry = ry;
    }
    
    public float getRz() {
        return rz;
    }
    
    public void setRz(float rz) {
        this.rz = rz;
    }
    
    public float getScale() {
        return scale;
    }
    
    public void setScale(float scale) {
        this.scale = scale;
    }
    
    public boolean isChanged() {
        return isChanged;
    }
    
    public void setChanged(boolean changed) {
        isChanged = changed;
    }
}
