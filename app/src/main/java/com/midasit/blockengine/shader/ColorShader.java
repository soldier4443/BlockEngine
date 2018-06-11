package com.midasit.blockengine.shader;

import android.content.Context;

import com.midasit.blockengine.R;
import com.midasit.blockengine.loader.ModelLoader;
import com.midasit.blockengine.loader.RawResourceReader;
import com.midasit.blockengine.lwjgl.Matrix4f;

/**
 * Created by tura on 2018-01-18.
 */
public class ColorShader extends ShaderProgram {
    
    private int location_transformationMatrix;
    private int location_viewMatrix;
    private int location_projectionMatrix;
    
    public ColorShader(Context context) {
        super(RawResourceReader.readTextFileFromRawResources(context, R.raw.color_vertex_shader),
              RawResourceReader.readTextFileFromRawResources(context, R.raw.color_fragment_shader));
    }
    
    @Override
    protected void bindAttributes() {
        bindAttribute(ModelLoader.ATTRIB_POSITION, "position");
        bindAttribute(ModelLoader.ATTRIB_TEXTURE_COORDS, "textureCoords");
    }
    
    @Override
    protected void getAllUniformLocations() {
        location_transformationMatrix = getUniformLocation("transformationMatrix");
        location_viewMatrix = getUniformLocation("viewMatrix");
        location_projectionMatrix = getUniformLocation("projectionMatrix");
    }
    
    public void loadTransformationMatrix(Matrix4f matrix) {
        load(location_transformationMatrix, matrix);
    }
    
    public void loadViewMatrix(Matrix4f matrix) {
        load(location_viewMatrix, matrix);
    }
    
    public void loadProjectionMatrix(Matrix4f projectionMatrix) {
        load(location_projectionMatrix, projectionMatrix);
    }
}
