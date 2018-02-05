package com.midasit.blockengine.routine;

import com.midasit.blockengine.R;
import com.midasit.blockengine.core.Time;
import com.midasit.blockengine.entity.Entity;
import com.midasit.blockengine.lwjgl.MathUtils;
import com.midasit.blockengine.lwjgl.Vector3f;
import com.midasit.blockengine.models.RawModel;
import com.midasit.blockengine.models.TexturedModel;
import com.midasit.blockengine.texture.ModelTexture;

/**
 * Created by nyh0111 on 2018-01-22.
 */

public class SimpleRoutine extends Routine {
    
    private TexturedModel model;
    
    private final float[] positions = {
        -0.5f, 0.5f, 0,
        -0.5f, -0.5f, 0,
        0.5f, 0.5f, 0,
        0.5f, -0.5f, 0
    };
    
    /**
     * 왼쪽 아래가 0,0
     */
    private final float[] textureCoords = {
        0, 1,
        0, 0,
        1, 1,
        1, 0
    };
    
    private final int[] indices = {
        0, 1, 2,
        2, 1, 3
    };
    
    @Override
    public void init(int width, int height) {
        super.init(width, height);
        
        RawModel rawModel = loader.createModel(positions, textureCoords, indices);
        ModelTexture modelTexture = new ModelTexture(loader.loadTexture(getView().getContext(), R.drawable.pattern_2));
        model = new TexturedModel(rawModel, modelTexture);
        
        entity = new Entity(model, new Vector3f(0, 0, -2), 0, 0, 0, 1);
    }
    
    @Override
    public void update() {
        super.update();
        
        camera.setPosition(MathUtils.sin(Time.globalCounter())  * 0.5f, camera.getPosition().getY(), camera.getPosition().getZ());
        camera.setSize(5 + 3 * MathUtils.sin(Time.globalCounter() * 2));
    }
}
