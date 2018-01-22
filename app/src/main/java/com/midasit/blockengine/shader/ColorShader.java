package com.midasit.blockengine.shader;

import android.content.Context;

import com.midasit.blockengine.R;
import com.midasit.blockengine.loader.RawResourceReader;

/**
 * Created by nyh0111 on 2018-01-18.
 */

public class ColorShader extends ShaderProgram {
    
    public ColorShader(Context context) {
        super(RawResourceReader.readTextFileFromRawResources(context, R.raw.color_vertex_shader),
              RawResourceReader.readTextFileFromRawResources(context, R.raw.color_fragment_shader));
    }
    
    @Override
    protected void bindAttributes() {
    
    }
    
    @Override
    protected void getAllUniformLocations() {
    
    }
}
