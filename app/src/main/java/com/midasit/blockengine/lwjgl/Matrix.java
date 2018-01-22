package com.midasit.blockengine.lwjgl;

import java.nio.FloatBuffer;

/**
 * Created by nyh0111 on 2018-01-18.
 */

public abstract class Matrix {
    
    protected Matrix() {
    
    }
    
    public abstract Matrix setIdentity();
    
    public abstract Matrix invert();
    
    public abstract Matrix load(FloatBuffer var1);
    
    public abstract Matrix loadTranspose(FloatBuffer var1);
    
    public abstract Matrix negate();
    
    public abstract Matrix store(FloatBuffer var1);
    
    public abstract Matrix storeTranspose(FloatBuffer var1);
    
    public abstract Matrix transpose();
    
    public abstract Matrix setZero();
    
    public abstract float determinant();
}
