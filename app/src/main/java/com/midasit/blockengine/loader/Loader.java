package com.midasit.blockengine.loader;

import android.opengl.GLES20;

import com.annimon.stream.Stream;
import com.midasit.blockengine.lwjgl.BufferUtils;
import com.midasit.blockengine.lwjgl.GLES;
import com.midasit.blockengine.models.RawModel;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.opengles.GL11;

/**
 * Created by nyh0111 on 2018-01-17.
 */

public class Loader {
    
    public static final int ATTRIB_POSITION = 0;
    
    private List<Integer> vbos = new ArrayList<>();
    
    /**
     * Load data to VAO
     */
    public RawModel createModel(float[] positions, int[] indices) {
        int vertexVbo = storeDataInAttributeList(ATTRIB_POSITION, 3, positions);
        int ibo = bindIndexBuffer(indices);
        
        return new RawModel(vertexVbo, ibo, indices.length);
    }
    
    private int storeDataInAttributeList(int attributeNumber, int dimension, float[] data) {
        int vboID = GLES.glGenBuffers();
        vbos.add(vboID);
        
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, vboID);
        
        // Store data in VBO
        FloatBuffer buffer = storeDataInFloatBuffer(data);
        GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, buffer.remaining() << 2, buffer, GLES20.GL_STATIC_DRAW);   // GL_STATIC_DRAW: We won't change the value of VBO once we've stored it.
        
        // Bind VBO in VAO (at attributeNumber)
        GLES20.glVertexAttribPointer(attributeNumber, dimension, GL11.GL_FLOAT, false, 0, 0);
    
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, 0);
        
        return vboID;
    }
    
    /**
     * Bind index buffer using given indices.
     */
    private int bindIndexBuffer(int[] indices) {
        // Create empty IBO
        int ibo = GLES.glGenBuffers();
        vbos.add(ibo);
        
        GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, ibo);
        
        // Store data in IBO
        IntBuffer buffer = storeDataInIntBuffer(indices);
        GLES20.glBufferData(GLES20.GL_ELEMENT_ARRAY_BUFFER, buffer.remaining() << 2, buffer, GLES20.GL_STATIC_DRAW);
        
        // We don't unbind index buffer anywhere.
        // Each VAO has one special slot for an index buffer,
        // and unbinding the index buffer will remove it from that slot.
        
        return ibo;
    }
    
    /**
     * Convert given data into FloatBuffer.
     */
    private FloatBuffer storeDataInFloatBuffer(float[] data) {
        FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
        
        buffer.put(data);
        buffer.flip(); // Finish writing to it, and prepare to read.
        
        return buffer;
    }
    
    /**
     * Convert given data into FloatBuffer.
     */
    private IntBuffer storeDataInIntBuffer(int[] data) {
        IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
        
        buffer.put(data);
        buffer.flip(); // Finish writing to it, and prepare to read.
    
        return buffer;
    }
    
    /**
     * Delete all of the buffers in the lists.
     */
    public void cleanUp() {
        Stream.of(vbos).forEach(GLES::glDeleteBuffers);
    }
}
