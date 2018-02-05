package com.midasit.blockengine.core;

import android.opengl.GLES20;

import com.midasit.blockengine.entity.Entity;
import com.midasit.blockengine.loader.Loader;
import com.midasit.blockengine.lwjgl.MathUtils;
import com.midasit.blockengine.lwjgl.Matrix4f;
import com.midasit.blockengine.models.RawModel;
import com.midasit.blockengine.models.TexturedModel;
import com.midasit.blockengine.shader.ColorShader;

/**
 * Created by nyh0111 on 2018-01-18.
 */

public class EntityRenderer {
    private ColorShader shader;
    
    public EntityRenderer(ColorShader shader) {
        this.shader = shader;
    }
    
    public void render(Entity entity, Matrix4f projectionMatrix) {
        shader.loadProjectionMatrix(projectionMatrix);
        
        bindTexturedModel(entity.getModel());
        
        prepareInstance(entity);
        GLES20.glDrawElements(GLES20.GL_TRIANGLES, entity.getModel().getRawModel().getVertexCount(), GLES20.GL_UNSIGNED_INT, 0);
        
        unbindTexturedModel();
    }
    
    /**
     * Prepare for each textured model.
     */
    private void bindTexturedModel(TexturedModel model) {
        RawModel rawModel = model.getRawModel();
        
        GLES20.glEnableVertexAttribArray(Loader.ATTRIB_POSITION);
        GLES20.glEnableVertexAttribArray(Loader.ATTRIB_TEXTURE_COORDS);
        
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, rawModel.getPositionVbo());
        GLES20.glVertexAttribPointer(0, 3, GLES20.GL_FLOAT, false, 3 * 4, 0);
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, rawModel.getTextureCoordsVbo());
        GLES20.glVertexAttribPointer(1, 2, GLES20.GL_FLOAT, false, 2 * 4, 0);
        
        GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, model.getTexture().getTextureId());
    }
    
    /**
     * Unbind textured model for other entities.
     */
    private void unbindTexturedModel() {
        GLES20.glDisableVertexAttribArray(Loader.ATTRIB_POSITION);
        GLES20.glDisableVertexAttribArray(Loader.ATTRIB_TEXTURE_COORDS);
    }
    
    private void prepareInstance(Entity entity) {
        Matrix4f transformationMatrix = MathUtils.createTransformationMatrix(
            entity.getPosition(), entity.getRx(), entity.getRy(), entity.getRz(), entity.getScale());

        shader.loadTransformationMatrix(transformationMatrix);
    }
}
