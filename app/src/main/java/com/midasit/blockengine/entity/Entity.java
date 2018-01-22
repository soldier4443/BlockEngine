package com.midasit.blockengine.entity;

import com.midasit.blockengine.models.RawModel;

/**
 * Created by nyh0111 on 2018-01-18.
 */

public class Entity {
    private RawModel model;
    
    public Entity(RawModel model) {
        this.model = model;
    }
    
    public RawModel getModel() {
        return model;
    }
    
    public void setModel(RawModel model) {
        this.model = model;
    }
}
