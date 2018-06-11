package com.midasit.blockengine.util;

import android.util.Log;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by nyh0111 on 2018-06-11.
 * <p>
 * 조사 클래스
 */
public class Profiler {
    private static Map<String, Profiler> profilerMap = new ConcurrentHashMap<>();
    private static Profiler dummy = new Profiler();
    private static AtomicBoolean enabled = new AtomicBoolean(true);
    
    private int drawCall;
    
    public static Profiler onCurrentThread() {
        if (enabled.get()) {
            String threadName = Thread.currentThread().getName();
            Profiler profiler = profilerMap.get(threadName);
            if (profiler == null) {
                profiler = new Profiler();
                profilerMap.put(threadName, profiler);
            }
    
            return profiler;
        } else {
            return dummy;
        }
    }
    
    public Profiler addDrawCall() {
        if (enabled.get())
            drawCall++;
        return this;
    }
    
    public Profiler clearDrawCall() {
        if (enabled.get())
            drawCall = 0;
        return this;
    }
    
    public Profiler printOut() {
        if (enabled.get())
            Log.e("Profiler:" + Thread.currentThread().getName(), "DrawCall - " + drawCall);
        return this;
    }
}
