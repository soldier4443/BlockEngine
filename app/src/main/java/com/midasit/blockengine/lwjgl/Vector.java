package com.midasit.blockengine.lwjgl;

import java.nio.FloatBuffer;

/**
 * Created by nyh0111 on 2018-01-18.
 */

public abstract class Vector {
    protected Vector() {
    
    }
    
    public final float length() {
        return (float) Math.sqrt((double) this.lengthSquared());
    }
    
    public abstract float lengthSquared();
    
    public abstract Vector load(FloatBuffer buf);
    
    public abstract Vector negate();
    
    public final Vector normalize() {
        float len = this.length();
        if (len != 0.0F) {
            float l = 1.0F / len;
            return this.scale(l);
        } else {
            throw new IllegalStateException("Zero length vector");
        }
    }
    
    public abstract Vector store(FloatBuffer buf);
    
    public abstract Vector scale(float scale);
}
