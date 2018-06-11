package com.midasit.blockengine.core;

import android.util.Log;

import com.annimon.stream.Stream;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Rendering Core.
 */
public class Core {
    
    private static List<RenderingContext> contextList = new CopyOnWriteArrayList<>();
    
    /**
     * Initialize/Reset the system.
     */
    public static void init() {
        Time.init();
    }
    
    static void update(float deltaTime) {
        Stream.of(contextList).forEach(it -> it.update(deltaTime));
    }
    
    public static void registerContext(RenderingContext renderingContext) {
        if (!(renderingContext instanceof RenderingSystem)) {
            throw new IllegalArgumentException("Should register RenderingSystem!!");
        }
        
        if (contextList.contains(renderingContext)) {
            Log.d("Core", "Rendering Context already registered.");
            return;
        }
        
        contextList.add(renderingContext);
    }
    
    // TODO: 2018-01-22 When I should call this..?
    public static void unregisterContext(RenderingContext renderingContext) {
        if (!contextList.contains(renderingContext)) {
            Log.d("Core", "Rendering Context is not registered.");
            return;
        }
        
        renderingContext.cleanUp();
        contextList.remove(renderingContext);
    }
}
