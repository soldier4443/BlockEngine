package com.midasit.blockengine.shader;

import android.opengl.GLES20;

import com.midasit.blockengine.lwjgl.BufferUtils;
import com.midasit.blockengine.lwjgl.GLES;
import com.midasit.blockengine.lwjgl.Matrix4f;
import com.midasit.blockengine.lwjgl.Vector2f;
import com.midasit.blockengine.lwjgl.Vector3f;

import java.nio.FloatBuffer;

/**
 * Represent general shader program
 */
public abstract class ShaderProgram {
    
    private int programID;
    private int vertexShaderID;
    private int fragmentShaderID;
    
    private static FloatBuffer matrixBuffer = BufferUtils.createFloatBuffer(16);
    
    /**
     * These arguments are file content, not file name.
     */
    public ShaderProgram(String vertexFile, String fragmentFile) {
        vertexShaderID = loadShader(vertexFile, GLES20.GL_VERTEX_SHADER);
        fragmentShaderID = loadShader(fragmentFile, GLES20.GL_FRAGMENT_SHADER);
        
        programID = GLES20.glCreateProgram();
        GLES20.glAttachShader(programID, vertexShaderID);
        GLES20.glAttachShader(programID, fragmentShaderID);
        
        bindAttributes();
    
        GLES20.glLinkProgram(programID);
        GLES20.glValidateProgram(programID);
        
        getAllUniformLocations();
    }
    
    /**
     * Start the shader program
     */
    public void start() {
        GLES20.glUseProgram(programID);
    }
    
    /**
     * Stop the shader program
     */
    public void stop() {
        GLES20.glUseProgram(0);
    }
    
    /**
     * Delete all shader programs
     */
    public void cleanUp() {
        stop();
    
        GLES20.glDetachShader(programID, vertexShaderID);
        GLES20.glDetachShader(programID, fragmentShaderID);
    
        GLES20.glDeleteShader(vertexShaderID);
        GLES20.glDeleteShader(fragmentShaderID);
    
        GLES20.glDeleteProgram(programID);
    }
    
    
    /**
     * Bind attributes of the VAO
     */
    protected abstract void bindAttributes();
    
    /**
     * Get all uniform locations.
     */
    protected abstract void getAllUniformLocations();
    
    /**
     * Encapsulate GL method for child classes.
     */
    protected void bindAttribute(int attribute, String variableName) {
        GLES20.glBindAttribLocation(programID, attribute, variableName);
    }
    
    /**
     * Load int value to the uniform location
     */
    protected void load(int location, int value) {
        GLES20.glUniform1i(location, value);
    }
    
    /**
     * Load float value to the uniform location
     */
    protected void load(int location, float value) {
        GLES20.glUniform1f(location, value);
    }
    
    /**
     * Load boolean value to the uniform location
     */
    protected void load(int location, boolean value) {
        float toLoad = value ? 1f : 0f;
    
        GLES20.glUniform1f(location, toLoad);
    }
    
    /**
     * Load Vector3f to the uniform location
     */
    protected void load(int location, Vector3f value) {
        GLES20.glUniform3f(location, value.x, value.y, value.z);
    }
    
    /**
     * Load Vector2f to the uniform location
     */
    protected void load(int location, Vector2f value) {
        GLES20.glUniform2f(location, value.x, value.y);
    }
    
    /**
     * Load Matrix4f to the uniform location
     */
    protected void load(int location, Matrix4f value) {
        value.store(matrixBuffer);
        matrixBuffer.flip();
    
        GLES.glUniformMatrix4(location, false, matrixBuffer);
    }
    
    
    /**
     * Get uniform location for the name.
     */
    protected int getUniformLocation(String uniformName) {
        return GLES20.glGetUniformLocation(programID, uniformName);
    }
    
    private int loadShader(String file, int type) {
        int shaderID = GLES20.glCreateShader(type);
        GLES20.glShaderSource(shaderID, file);
        GLES20.glCompileShader(shaderID);
        
        if (GLES.glGetShaderi(shaderID, GLES20.GL_COMPILE_STATUS) == GLES20.GL_FALSE) {
            System.out.println(GLES20.glGetShaderInfoLog(shaderID));
            System.err.println("Could not compile shader!");
            System.exit(-1);
        }
        
        return shaderID;
    }
}
