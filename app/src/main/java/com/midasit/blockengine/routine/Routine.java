package com.midasit.blockengine.routine;

import com.midasit.blockengine.RenderingView;
import com.midasit.blockengine.core.Camera;
import com.midasit.blockengine.core.EntityRenderer;
import com.midasit.blockengine.entity.Entity;
import com.midasit.blockengine.loader.Loader;
import com.midasit.blockengine.lwjgl.Vector3f;
import com.midasit.blockengine.shader.ColorShader;

/**
 * Created by nyh0111 on 2018-01-17.
 */

public class Routine {
    
    private RenderingView view;
    
    protected Loader loader;
    protected Entity entity;
    protected Camera camera;
    
    private ColorShader shader;
    private EntityRenderer renderer;
    
    public void init(int width, int height) {
        loader = new Loader();
        shader = new ColorShader(view.getContext());
        renderer = new EntityRenderer(shader);
        
        camera = new Camera(new Vector3f(0, 0, 0), 0, 0, 0, width, height, 100, -100);
    }
    
    // Update transformation, etc..
    public void update() {
    
    }
    
    public void render() {
        camera.update();
        entity.update();
        
        shader.start();
        
        shader.loadViewMatrix(camera.getViewMatrix());
        renderer.render(entity, camera.getProjectionMatrix());
        
        shader.stop();
    }
    
    public void setView(RenderingView view) {
        this.view = view;
    }
    
    public RenderingView getView() {
        return view;
    }
}
