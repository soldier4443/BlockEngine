package com.midasit.blockengine;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

import com.midasit.blockengine.core.RenderingSystem;
import com.midasit.blockengine.routine.Routine;

/**
 * Created by tura on 2018-01-17.
 */
public class RenderingView extends android.opengl.GLSurfaceView {

    private RenderingSystem renderer;
    private Routine routine;
    
    public RenderingView(Context context) {
        super(context);
    }
    
    public RenderingView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    
    public void setupRenderer(RenderingSystem renderer) {
        configureEGL();
    
        setRenderer(renderer);
        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
        
        renderer.setView(this);
        this.renderer = renderer;
        
        if (routine != null)
            renderer.setRoutine(routine);
    }
    
    public void setupRoutine(Routine routine) {
        if (renderer != null)
            renderer.setRoutine(routine);
        
        routine.setView(this);
        this.routine = routine;
    }
    
    private void configureEGL() {
        setEGLContextClientVersion(2);
        setPreserveEGLContextOnPause(true);
    }
    
    public Routine getRoutine() {
        return routine;
    }
    
    public RenderingSystem getRenderer() {
        return renderer;
    }
}
