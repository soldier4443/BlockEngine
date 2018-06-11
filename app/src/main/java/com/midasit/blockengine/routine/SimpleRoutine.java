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
 * Created by tura on 2018-01-22.
 */
public class SimpleRoutine extends Routine {
    
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
    private final int dupCount;
    private TexturedModel model;
    
    public SimpleRoutine(int dupCount) {
        this.dupCount = dupCount;
    }
    
    @Override
    public void init(int width, int height) {
        super.init(width, height);
        
        RawModel rawModel = modelLoader.createModel(positions, textureCoords, indices);
        ModelTexture modelTexture = new ModelTexture(modelLoader.loadTexture(getView().getContext(), R.drawable.pattern_2));
        model = new TexturedModel(rawModel, modelTexture);
        
        for (int i = 0; i < dupCount; i++) {
            addEntity(new Entity("entity1", model, new Vector3f(-1f, 0, -2), 0, 0, 0, 1));
            addEntity(new Entity("entity2", model, new Vector3f(1f, 0, -2), 0, 0, 0, 1));
        }
    }
    
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        
        camera.setPosition(MathUtils.sin(Time.globalCounter() * 2) * 0.5f,
            MathUtils.cos(Time.globalCounter() * 2) * 0.5f, camera.getPosition().getZ());
        camera.setSize(3);
    }
}
