package com.midasit.blockengine.routine;

import com.annimon.stream.Stream;
import com.midasit.blockengine.RenderingView;
import com.midasit.blockengine.core.Camera;
import com.midasit.blockengine.core.EntityRenderer;
import com.midasit.blockengine.core.RenderingContext;
import com.midasit.blockengine.entity.Entity;
import com.midasit.blockengine.loader.ModelLoader;
import com.midasit.blockengine.lwjgl.Vector3f;
import com.midasit.blockengine.models.TexturedModel;
import com.midasit.blockengine.shader.ColorShader;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by tura on 2018-01-17.
 */
public class Routine implements RenderingContext {
    
    protected Map<TexturedModel, List<Entity>> entities = new HashMap<>();
    
    protected ModelLoader modelLoader;
    protected Camera camera;
    
    private RenderingView view;
    
    // This is for reference!
    private List<Entity> entityList = new LinkedList<>();
    
    private ColorShader shader;
    private EntityRenderer renderer;
    
    public void init(int width, int height) {
        modelLoader = new ModelLoader();
        shader = new ColorShader(view.getContext());
        renderer = new EntityRenderer(shader);
        
        camera = new Camera(new Vector3f(0, 0, 0), 0, 0, 0, width, height, 100, -100);
    }
    
    // Update transformation, etc..
    @Override
    public void update(float deltaTime) {
        camera.update(deltaTime);
        Stream.of(entityList)
            .forEach(entity -> entity.update(deltaTime));
    }
    
    public void render() {
        shader.start();
        
        shader.loadViewMatrix(camera.getViewMatrix());
        shader.loadProjectionMatrix(camera.getProjectionMatrix());
        renderer.render(entities);
        
        shader.stop();
    }
    
    /**
     * Should we render this frame?
     */
    public boolean checkForRender() {
        return camera.isChanged() || Stream.of(entityList).anyMatch(Entity::isChanged);
    }
    
    public void addEntity(Entity entity) {
        if (entities.containsKey(entity.getModel())) {
            entities.get(entity.getModel()).add(entity);
        } else {
            List<Entity> batch = new LinkedList<>();
            batch.add(entity);
            
            entities.put(entity.getModel(), batch);
        }
        
        entityList.add(entity);
    }
    
    public Entity findEntity(String name) {
        for (Entity entity : entityList) {
            if (entity.getName().equals(name))
                return entity;
        }
        
        return null;
    }
    
    public void removeEntity(String name) {
        Entity entity = findEntity(name);
        
        if (entity != null) {
            entities.get(entity.getModel()).remove(entity);
            entityList.remove(entity);
        }
    }
    
    public RenderingView getView() {
        return view;
    }
    
    public void setView(RenderingView view) {
        this.view = view;
    }
    
    @Override
    public void cleanUp() {
        entities.clear();
        entityList.clear();
        
        modelLoader.cleanUp();
        shader.cleanUp();
    }
}
