package com.midasit.blockengine.routine;

import com.midasit.blockengine.RenderingView;
import com.midasit.blockengine.core.EntityRenderer;
import com.midasit.blockengine.entity.Entity;
import com.midasit.blockengine.loader.Loader;
import com.midasit.blockengine.shader.ColorShader;

/**
 * Created by nyh0111 on 2018-01-17.
 */

public class Routine {
    
    private RenderingView view;
    
    protected Loader loader;
    protected Entity entity;
    
    private ColorShader shader;
    private EntityRenderer renderer;
    
    public void init() {
        loader = new Loader();
        shader = new ColorShader(view.getContext());
        renderer = new EntityRenderer(shader);
    }
    
    public void update() {
    
    }
    
    public void render() {
        renderer.render(entity);
    }
    
    public void setView(RenderingView view) {
        this.view = view;
    }
}
