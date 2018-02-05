package com.midasit.blockengine.core;

import com.midasit.blockengine.entity.Entity;
import com.midasit.blockengine.lwjgl.Matrix4f;
import com.midasit.blockengine.lwjgl.Vector3f;

/**
 * Created by nyh0111 on 2018-02-02.
 */

public class Camera extends Entity {
    
    private Vector3f tempVector = new Vector3f();
    
    private Matrix4f viewMatrix = new Matrix4f();
    private Matrix4f projectionMatrix = new Matrix4f();
    
    private float size = 1f;
    
    public Camera(Vector3f position, float rx, float ry, float rz, float width, float height, float near, float far) {
        super(null, position, rx, ry, rz, 1);
        
        float aspectRatio = width / height;
        
        // 식이 이거 맞나..
        projectionMatrix.m00 = 2 / (aspectRatio * size);
        projectionMatrix.m11 = 2 / size;
        projectionMatrix.m22 = -2 / (far - near);
        projectionMatrix.m32 = -(far + near) / (far - near);
    }
    
    @Override
    public void onUpdate() {
        super.onUpdate();
    
        createViewMatrix();
        createProjectionMatrix();
    }
    
    private void createProjectionMatrix() {
        float mul = (2 / projectionMatrix.m11) / size;
    
        projectionMatrix.m00 *= mul;
        projectionMatrix.m11 *= mul;
    }
    
    /**
     * Create view matrix for this camera.
     * Note that the order of operation is opposite to #createTransformationMatrix()
     */
    private void createViewMatrix() {
        viewMatrix.setIdentity();
        
        Matrix4f.rotate((float) Math.toRadians(getRx()), new Vector3f(1, 0, 0), viewMatrix, viewMatrix);
        Matrix4f.rotate((float) Math.toRadians(getRy()), new Vector3f(0, 1, 0), viewMatrix, viewMatrix);
        Matrix4f.rotate((float) Math.toRadians(getRz()), new Vector3f(0, 0, 1), viewMatrix, viewMatrix);
        
        getPosition().negate(tempVector);
        Matrix4f.translate(tempVector, viewMatrix, viewMatrix);
    }
    
    public void setSize(float size) {
        this.size = size;
        setChanged(true);
    }
    
    public Matrix4f getProjectionMatrix() {
        return projectionMatrix;
    }
    
    public Matrix4f getViewMatrix() {
        return viewMatrix;
    }
}
