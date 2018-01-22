package com.midasit.blockengine.routine;

import com.midasit.blockengine.core.Time;
import com.midasit.blockengine.entity.Entity;
import com.midasit.blockengine.lwjgl.MathUtils;
import com.midasit.blockengine.lwjgl.Vector3f;
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
        0.5f, -0.5f, 0
    };
    
    private final int[] indices = {
        0, 1, 2,
        2, 1, 3
    };
    
    @Override
    public void init() {
        super.init();
        
        model = loader.createModel(positions, indices);
        
        entity = new Entity(model, new Vector3f(0, 0, 0), 0, 0, 0, 1);
    }
    
    @Override
    public void update() {
        super.update();
        
        entity.getPosition().x = MathUtils.sin(Time.globalCounter()) * 0.2f;
        entity.rotate(0, 60 * Time.getDeltaTime(), 0);
    }
}
