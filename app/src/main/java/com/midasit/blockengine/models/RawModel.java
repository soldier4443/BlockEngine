package com.midasit.blockengine.models;

/**
 * 메모리 상의 3D 모델을 나타냄.
 */
public class RawModel {
    private int vertexVbo;
    private int ibo;
    private int vertexCount;
    
    public RawModel(int vertexVbo, int ibo, int vertexCount) {
        this.vertexVbo = vertexVbo;
        this.ibo = ibo;
        this.vertexCount = vertexCount;
    }
    
    public int getVertexVbo() {
        return vertexVbo;
    }
    
    public void setVertexVbo(int vertexVbo) {
        this.vertexVbo = vertexVbo;
    }
    
    public int getIbo() {
        return ibo;
    }
    
    public void setIbo(int ibo) {
        this.ibo = ibo;
    }
    
    public int getVertexCount() {
        return vertexCount;
    }
    
    public void setVertexCount(int vertexCount) {
        this.vertexCount = vertexCount;
    }
}
