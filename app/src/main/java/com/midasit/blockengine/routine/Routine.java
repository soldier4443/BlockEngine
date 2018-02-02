package com.midasit.blockengine.routine;

import com.midasit.blockengine.RenderingView;
import com.midasit.blockengine.core.Camera;
import com.midasit.blockengine.core.EntityRenderer;
import com.midasit.blockengine.entity.Entity;
import com.midasit.blockengine.loader.Loader;
import com.midasit.blockengine.lwjgl.MathUtils;
import com.midasit.blockengine.lwjgl.Matrix4f;
import com.midasit.blockengine.shader.ColorShader;

/**
 * Created by nyh0111 on 2018-01-17.
 */

public class Routine {
    
    private RenderingView view;
    
    // We want to keep this matrix just one for one routine.
    // TODO: 2018-01-22 WRAP WITH CAMERA
    protected Matrix4f projectionMatrix = new Matrix4f();
    
    protected Loader loader;
    protected Entity entity;
    protected Camera camera;
    
    private ColorShader shader;
    private EntityRenderer renderer;
    
    public void init(int width, int height) {
        setProjectionMatrix(5, width, height, 100, -100);
        
        loader = new Loader();
        shader = new ColorShader(view.getContext());
        renderer = new EntityRenderer(shader);
    }
    
    public void update() {
    
    }
    
    public void render() {
        shader.start();
        
        shader.loadViewMatrix(MathUtils.createViewMatrix(camera));
        renderer.render(entity, projectionMatrix);
        
        shader.stop();
    }
    
    public void setView(RenderingView view) {
        this.view = view;
    }
    
    
    protected void setProjectionMatrix(float size, float width, float height, float near, float far) {
        float aspectRatio = width / height;
        
        // 식이 이거 맞나..
        projectionMatrix.setIdentity();
        projectionMatrix.m00 = 2 / (aspectRatio * size);
        projectionMatrix.m11 = 2 / size;
        projectionMatrix.m22 = -2 / (far - near);
        projectionMatrix.m32 = -(far + near) / (far - near);
    }
    
    protected void setSize(float size) {
        float mul = (2 / projectionMatrix.m11) / size;
        
        projectionMatrix.m00 *= mul;
        projectionMatrix.m11 *= mul;
    }
}
