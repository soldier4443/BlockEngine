package com.midasit.blockengine.core;

import com.midasit.blockengine.entity.Entity;
import com.midasit.blockengine.lwjgl.Vector3f;

/**
 * Created by nyh0111 on 2018-02-02.
 */

public class Camera extends Entity {
    
    public Camera(Vector3f position, float rx, float ry, float rz) {
        super(null, position, rx, ry, rz, 1);
    }
}
