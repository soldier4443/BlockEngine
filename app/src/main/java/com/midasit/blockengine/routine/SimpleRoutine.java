package com.midasit.blockengine.routine;

import com.midasit.blockengine.entity.Entity;
import com.midasit.blockengine.models.RawModel;

/**
 * Created by nyh0111 on 2018-01-22.
 */

public class SimpleRoutine extends Routine {
    
    private RawModel model;
    
    private final float[] positions = {
        -0.5f, 0.5f, 0,
        -0.5f, -0.5f, 0,
        0.5f, 0.5f, 0,
        0.5f, 0.5f, 0,
        -0.5f, -0.5f, 0,
        0.5f, -0.5f, 0
    };
    
    @Override
    public void init() {
        super.init();
        
        model = loader.createModel(positions);
        
        super.entity = new Entity(model);
    }
}
