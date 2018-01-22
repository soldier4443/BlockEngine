package com.midasit.blockengine;

import com.midasit.blockengine.core.EntityRenderer;
import com.midasit.blockengine.entity.Entity;
import com.midasit.blockengine.loader.Loader;
import com.midasit.blockengine.models.RawModel;
import com.midasit.blockengine.shader.ColorShader;

/**
 * Created by nyh0111 on 2018-01-17.
 */

public class Routine {
    
    private RenderingView view;
    
    private Loader loader;
    private RawModel model;
    
    private ColorShader shader;
    private EntityRenderer renderer;
    
    private Entity entity;
    
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
        entity = new Entity(model);
    
        shader = new ColorShader(view.getContext());
        renderer = new EntityRenderer(shader);
    }
    
    public void render() {
        renderer.render(entity);
    }
    
    public void setView(RenderingView view) {
        this.view = view;
    }
}
