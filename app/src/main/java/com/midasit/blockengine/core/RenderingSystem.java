package com.midasit.blockengine.core;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.util.Log;

import com.midasit.blockengine.routine.Routine;
import com.midasit.blockengine.util.Profiler;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import static android.opengl.GLES20.GL_BACK;
import static android.opengl.GLES20.GL_TEXTURE_2D;

/**
 * Created by tura on 2018-01-17.
 */
public class RenderingSystem implements GLSurfaceView.Renderer, RenderingContext {
    
    private GLSurfaceView view;
    private Routine routine;
    
    // Flags
    private boolean ready;
    private boolean cleanUp;
    
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES20.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
    
        GLES20.glCullFace(GL_BACK);
        GLES20.glEnable(GLES20.GL_CULL_FACE);
        GLES20.glEnable(GLES20.GL_DEPTH_TEST);
    
        GLES20.glEnable(GL_TEXTURE_2D);
        
        ready = false;
        
        Log.e("RenderingSystem", "onSurfaceCreated called");
    }
    
    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
        
        if (routine != null)
            routine.init(width, height);
    
        Log.e("RenderingSystem", "onSurfaceChanged called");
    }
    
    @Override
    public void onDrawFrame(GL10 gl) {
        if (!ready) {
            Log.e("RenderingSystem", "RenderingSystem is ready to render!");
            ready = true;
        }
        
        if (cleanUp) {
            cleanUp();
        } else {
            GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
    
            if (routine != null) {
                routine.render();
            }
        }
        
        if (Time.globalCounter() % 60 == 0) {
            Profiler.onCurrentThread()
                .printOut()
                .clearDrawCall();
        }
    }
    
    @Override
    public void update(float deltaTime) {
        if (ready && routine != null) {
            routine.update(deltaTime);
            
            if (routine.checkForRender()) {
                if (view != null)
                    view.queueEvent(() -> view.requestRender());
            }
        }
    }
    
    @Override
    public void cleanUp() {
        cleanUp = true;
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
