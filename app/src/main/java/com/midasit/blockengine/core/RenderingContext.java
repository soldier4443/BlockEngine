package com.midasit.blockengine.core;

/**
 * Created by tura on 2018-01-17.
 */
public interface RenderingContext {
    void update(float deltaTime);
    void cleanUp();
}
