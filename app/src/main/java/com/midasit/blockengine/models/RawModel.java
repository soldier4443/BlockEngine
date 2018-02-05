package com.midasit.blockengine.models;

/**
 * 메모리 상의 3D 모델을 나타냄.
 */
public class RawModel {
    private int positionVbo;
    private int textureCoordsVbo;
    private int ibo;
    private int vertexCount;
    
    public RawModel(int positionVbo, int textureCoordsVbo, int ibo, int vertexCount) {
        this.positionVbo = positionVbo;
        this.textureCoordsVbo = textureCoordsVbo;
        this.ibo = ibo;
        this.vertexCount = vertexCount;
    }
    
    public int getPositionVbo() {
        return positionVbo;
    }
    
    public void setPositionVbo(int positionVbo) {
        this.positionVbo = positionVbo;
    }
    
    public int getTextureCoordsVbo() {
        return textureCoordsVbo;
    }
    
    public void setTextureCoordsVbo(int textureCoordsVbo) {
        this.textureCoordsVbo = textureCoordsVbo;
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
