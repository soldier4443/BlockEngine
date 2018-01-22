package com.midasit.blockengine.shader;

import android.content.Context;

import com.midasit.blockengine.R;
import com.midasit.blockengine.loader.Loader;
import com.midasit.blockengine.loader.RawResourceReader;
import com.midasit.blockengine.lwjgl.Matrix4f;

/**
 * Created by nyh0111 on 2018-01-18.
 */

public class ColorShader extends ShaderProgram {
    
    private int location_transformationMatrix;
    
    public ColorShader(Context context) {
        super(RawResourceReader.readTextFileFromRawResources(context, R.raw.color_vertex_shader),
              RawResourceReader.readTextFileFromRawResources(context, R.raw.color_fragment_shader));
    }
    
    @Override
    protected void bindAttributes() {
        bindAttribute(Loader.ATTRIB_POSITION, "position");
    }
    
    @Override
    protected void getAllUniformLocations() {
        location_transformationMatrix = getUniformLocation("transformationMatrix");
    }
    
    public void loadTransformationMatrix(Matrix4f matrix) {
        load(location_transformationMatrix, matrix);
    }
}
