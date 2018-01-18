package com.midasit.blockengine.core;

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
    
    static void update() {
        Stream.of(contextList).forEach(RenderingContext::update);
    }
    
    public static void registerContext(RenderingContext renderingContext) {
        contextList.add(renderingContext);
    }
    
    public static void upregisteContext(RenderingContext renderingContext) {
        contextList.remove(renderingContext);
    }
}
