package com.midasit.blockengine.models;

import com.midasit.blockengine.texture.ModelTexture;

/**
 * Created by nyh0111 on 2018-02-05.
 */

public class TexturedModel {
    
    private RawModel rawModel;
    private ModelTexture texture;
    
    public TexturedModel(RawModel rawModel, ModelTexture texture) {
        this.rawModel = rawModel;
        this.texture = texture;
    }
    
    public RawModel getRawModel() {
        return rawModel;
    }
    
    public void setRawModel(RawModel rawModel) {
        this.rawModel = rawModel;
    }
    
    public ModelTexture getTexture() {
        return texture;
    }
    
    public void setTexture(ModelTexture texture) {
        this.texture = texture;
    }
}