package com.midasit.blockengine.core;

/**
 * Created by nyh0111 on 2018-01-17.
 */

public class Time {
    
    public static int FPS_CAP = 60;
    public static float FRAME_TIME = 1000f / FPS_CAP;
    
    private static long currentTime;
    private static long lastFrameTime;
    
    private static float deltaTime;
    private static int globalCounter;
    private static boolean isRunning;
    
    private static Time internal;
    
    private InternalTimer timer;
    
    public static float getDeltaTime() {
        return deltaTime;
    }
    
    public static void resume() {
        isRunning = true;
    }
    
    public static void pause() {
        isRunning = false;
    }
    
    
    static void init() {
        lastFrameTime = System.currentTimeMillis();
        if (internal == null)
            internal = new Time();
        globalCounter = 0;
        internal.initTimer();
    }
    
    private Time() {
    
    }
    
    private void initTimer() {
        pause();
        
        if (timer == null) {
            timer = new InternalTimer();
            timer.start();
        }
        
        resume();
    }
    
    public static float globalCounter() {
        return globalCounter;
    }
    
    private class InternalTimer extends Thread {
        @Override
        public void run() {
            while (true) {
                if (isRunning) {
                    currentTime = System.currentTimeMillis();
                    deltaTime = (currentTime - lastFrameTime) / 1000f;
                    globalCounter++;
                    lastFrameTime = currentTime;
    
                    Core.update();
                    
                    try {
                        Thread.sleep((long) FRAME_TIME);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
