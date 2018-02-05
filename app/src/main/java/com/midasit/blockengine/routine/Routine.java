package com.midasit.blockengine.routine;

import com.midasit.blockengine.RenderingView;
import com.midasit.blockengine.core.Camera;
import com.midasit.blockengine.core.EntityRenderer;
import com.midasit.blockengine.entity.Entity;
import com.midasit.blockengine.loader.Loader;
import com.midasit.blockengine.lwjgl.Vector3f;
import com.midasit.blockengine.models.TexturedModel;
import com.midasit.blockengine.shader.ColorShader;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by nyh0111 on 2018-01-17.
 */

public class Routine {
    
    private RenderingView view;
    
    protected Loader loader;
    protected Map<TexturedModel, List<Entity>> entities = new HashMap<>();
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
        
        shader.start();
        
        shader.loadViewMatrix(camera.getViewMatrix());
        shader.loadProjectionMatrix(camera.getProjectionMatrix());
        renderer.render(entities);
        
        shader.stop();
    }
    
    public void addEntity(Entity entity) {
        if (entities.containsKey(entity.getModel())) {
            entities.get(entity.getModel()).add(entity);
        } else {
            List<Entity> batch = new LinkedList<>();
            batch.add(entity);
        
            entities.put(entity.getModel(), batch);
        }
    }
    
    public Entity findEntity(String name) {
        for (TexturedModel model : entities.keySet()) {
            List<Entity> batch = entities.get(model);
            for (Entity entity : batch) {
                if ( name.equals(entity.getName()) ) {
                    return entity;
                }
            }
        }
        
        return null;
    }
    
    public void removeEntity(String name) {
        Entity entity = findEntity(name);
        
        if (entity != null) {
            entities.get(entity.getModel()).remove(entity);
        }
    }
    
    public void setView(RenderingView view) {
        this.view = view;
    }
    
    public RenderingView getView() {
        return view;
    }
}
