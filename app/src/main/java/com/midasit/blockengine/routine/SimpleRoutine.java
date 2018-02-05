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
    public void init(int width, int height) {
        super.init(width, height);
        
        model = loader.createModel(positions, indices);
        
        entity = new Entity(model, new Vector3f(0, 0, -2), 0, 0, 0, 1);
    }
    
    @Override
    public void update() {
        super.update();
        
        camera.setPosition(MathUtils.sin(Time.globalCounter())  * 0.5f, camera.getPosition().getY(), camera.getPosition().getZ());
        camera.setSize(5 + 3 * MathUtils.sin(Time.globalCounter() * 2));
    }
}
