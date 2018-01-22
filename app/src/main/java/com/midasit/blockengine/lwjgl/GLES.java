package com.midasit.blockengine.lwjgl;

import android.opengl.GLES20;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

/**
 * Simple Wrapper for ease of use.
 */
public class GLES {
    
    private static final IntBuffer ints = BufferUtils.createIntBuffer(32);
    
    public static int glGenBuffers() {
        GLES20.glGenBuffers(1, ints);
        return ints.get(0);
    }
    
    public static void glBufferData(int target, IntBuffer data, int usage) {
        GLES20.glBufferData(target, data.remaining() << 2, data, usage);   // GL_STATIC_DRAW: We won't change the value of VBO once we've stored it.
    }
    
    public static void glBufferData(int target, FloatBuffer data, int usage) {
        GLES20.glBufferData(target, data.remaining() << 2, data, usage);   // GL_STATIC_DRAW: We won't change the value of VBO once we've stored it.
    }
    
    public static void glDeleteBuffers(int buffer) {
        ints.put(0, buffer);
        GLES20.glDeleteBuffers(1, ints);
    }
    
    public static int glGetShaderi(int shader, int pname) {
        GLES20.glGetShaderiv(shader, pname, ints);
        return ints.get(0);
    }
    
    public static void glUniformMatrix4(int location, boolean transpose, FloatBuffer matrices) {
        GLES20.glUniformMatrix4fv(location, matrices.remaining() >> 4, transpose, matrices);
    }
}
