package com.midasit.blockengine.lwjgl;

import com.midasit.blockengine.core.Camera;

/**
 * Created by nyh0111 on 2018-01-22.
 */

public class MathUtils {
    
    /**
     * Create transformation matrix using translation, rotation, and scale.
     *
     * @param translation position.
     * @param rx          x amount of rotation, in degrees.
     * @param ry          y amount of rotation, in degrees.
     * @param rz          z amount of rotation, in degrees.
     * @param scale       scale factor.
     * @return
     */
    public static Matrix4f createTransformationMatrix(Vector3f translation, float rx, float ry, float rz, float scale) {
        Matrix4f matrix = new Matrix4f();
        matrix.setIdentity();
        
        matrix.translate(translation);
        Matrix4f.rotate((float) Math.toRadians(rx), new Vector3f(1, 0, 0), matrix, matrix);
        Matrix4f.rotate((float) Math.toRadians(ry), new Vector3f(0, 1, 0), matrix, matrix);
        Matrix4f.rotate((float) Math.toRadians(rz), new Vector3f(0, 0, 1), matrix, matrix);
        Matrix4f.scale(new Vector3f(scale, scale, scale), matrix, matrix);
        
        return matrix;
    }
    
    /**
     * Create view matrix for this camera.
     * Note that the order of operation is opposite to #createTransformationMatrix()
     */
    public static Matrix4f createViewMatrix(Camera camera) {
        Matrix4f viewMatrix = new Matrix4f();
        viewMatrix.setIdentity();
        
        Matrix4f.rotate((float) Math.toRadians(camera.getRx()), new Vector3f(1, 0, 0), viewMatrix, viewMatrix);
        Matrix4f.rotate((float) Math.toRadians(camera.getRy()), new Vector3f(0, 1, 0), viewMatrix, viewMatrix);
        Matrix4f.rotate((float) Math.toRadians(camera.getRz()), new Vector3f(0, 0, 1), viewMatrix, viewMatrix);
        
        Vector3f cameraPosition = camera.getPosition();
        Vector3f negativePosition = new Vector3f(-cameraPosition.x, -cameraPosition.y, -cameraPosition.z);
        Matrix4f.translate(negativePosition, viewMatrix, viewMatrix);
        
        return viewMatrix;
    }
    
    
    // Handy functions
    
    public static float sin(float angleInDegree) {
        return (float) Math.sin(Math.toRadians(angleInDegree));
    }
    
    public static float cos(float angleInDegree) {
        return (float) Math.cos(Math.toRadians(angleInDegree));
    }
}
