package com.midasit.blockengine.core;

import android.opengl.GLES20;

import com.midasit.blockengine.entity.Entity;
import com.midasit.blockengine.shader.ColorShader;

/**
 * Created by nyh0111 on 2018-01-18.
 */

public class EntityRenderer {
    private ColorShader shader;
    
    public EntityRenderer(ColorShader shader) {
        this.shader = shader;
    }
    
    public void render(Entity entity) {
        shader.start();
        
        bindTexturedModel(entity);
        
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, entity.getModel().getVertexCount());
        
        unbindTexturedModel(entity);
        
        shader.stop();
    }
    
    /**
     * Prepare for each textured model.
     */
    private void bindTexturedModel(Entity entity) {
        // TODO: 2018-01-18 IMPLEMENT
        GLES20.glEnableVertexAttribArray(0);
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, entity.getModel().getVertexVbo());
        GLES20.glVertexAttribPointer(0, 3, GLES20.GL_FLOAT, false, 3 * 4, 0);
    }
    
    /**
     * Unbind textured model for other entities.
     */
    private void unbindTexturedModel(Entity entity) {
        // TODO: 2018-01-18 IMPLEMENT
        GLES20.glDisableVertexAttribArray(0);
    }
    
    private void prepareInstance(Entity entity) {
        // TODO: 2018-01-18 IMPLEMENT
    }
}
