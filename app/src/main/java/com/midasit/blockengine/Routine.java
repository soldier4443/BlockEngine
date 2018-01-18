package com.midasit.blockengine;

import com.midasit.blockengine.loader.Loader;
import com.midasit.blockengine.models.RawModel;

/**
 * Created by nyh0111 on 2018-01-17.
 */

public class Routine {
    
    private RenderingView view;
    
    private Loader loader;
    private RawModel model;
    
    public void init() {
        float[] positions = {
            -0.5f, 0.5f, 0,
            -0.5f, -0.5f, 0,
            0.5f, 0.5f, 0,
            0.5f, 0.5f, 0,
            -0.5f, -0.5f, 0,
            0.5f, -0.5f, 0
        };
        
        loader = new Loader();
        
        model = loader.createModel(positions);
    }
    
    public void render() {
    }
    
    public void setView(RenderingView view) {
        this.view = view;
    }
}
