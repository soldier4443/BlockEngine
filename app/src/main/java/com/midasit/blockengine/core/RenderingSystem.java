package com.midasit.blockengine.core;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.util.Log;

import com.midasit.blockengine.routine.Routine;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import static android.opengl.GLES20.GL_BACK;
import static android.opengl.GLES20.GL_TEXTURE_2D;

/**
 * Created by nyh0111 on 2018-01-17.
 */

public class RenderingSystem implements GLSurfaceView.Renderer, RenderingContext {
    
    private GLSurfaceView view;
    private Routine routine;
    
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES20.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
    
        GLES20.glCullFace(GL_BACK);
        GLES20.glEnable(GLES20.GL_CULL_FACE);
        GLES20.glEnable(GLES20.GL_DEPTH_TEST);
    
        GLES20.glEnable(GL_TEXTURE_2D);
        
        Core.registerContext(this);
        
        Log.e("asdf", "onSurfaceCreated called");
    }
    
    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
        
        if (routine != null)
            routine.init(width, height);
    
        Log.e("asdf", "onSurfaceChanged called");
    }
    
    @Override
    public void onDrawFrame(GL10 gl) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
        
        if (routine != null) {
            routine.update();
            routine.render();
        }
    }
    
    @Override
    public void update() {
        if (view != null)
            view.queueEvent(() -> {
                view.requestRender();
            });
    }
    
    
    public GLSurfaceView getView() {
        return view;
    }
    
    public void setView(GLSurfaceView view) {
        this.view = view;
    }
    
    public Routine getRoutine() {
        return routine;
    }
    
    public void setRoutine(Routine routine) {
        this.routine = routine;
    }
}
