package com.midasit.blockengine.models;

/**
 * 메모리 상의 3D 모델을 나타냄.
 */
public class RawModel {
    private int vertexVbo;
    private int vertexCount;
    
    public RawModel(int vertexVbo, int vertexCount) {
        this.vertexVbo = vertexVbo;
        this.vertexCount = vertexCount;
    }
    
    public int getVertexVbo() {
        return vertexVbo;
    }
    
    public void setVertexVbo(int vertexVbo) {
        this.vertexVbo = vertexVbo;
    }
    
    public int getVertexCount() {
        return vertexCount;
    }
    
    public void setVertexCount(int vertexCount) {
        this.vertexCount = vertexCount;
    }
}
