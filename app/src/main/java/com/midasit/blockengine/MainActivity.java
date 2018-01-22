package com.midasit.blockengine;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.midasit.blockengine.core.Core;
import com.midasit.blockengine.core.RenderingContext;
import com.midasit.blockengine.core.RenderingSystem;
import com.midasit.blockengine.routine.SimpleRoutine;

public class MainActivity extends AppCompatActivity {
    
    static class SimpleTester implements RenderingContext {
        @Override
        public void update() {
            Log.e("asdf", "SimpleTester - id: " + Thread.currentThread().getId());
        }
    }
    
    RenderingView renderingView1;
    RenderingView renderingView2;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        renderingView1 = findViewById(R.id.rendering_view_1);
        renderingView2 = findViewById(R.id.rendering_view_2);
        
        renderingView1.setupRenderer(new RenderingSystem());
        renderingView2.setupRenderer(new RenderingSystem());
        
        renderingView1.setupRoutine(new SimpleRoutine());
        renderingView2.setupRoutine(new SimpleRoutine());
        
//        for (int i = 0; i < 10; i++) {
//            SimpleTester simpleTester = new SimpleTester();
//
//            Core.registerContext(simpleTester);
//        }
        
        Core.init();
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        
        if (renderingView1 != null) {
            renderingView1.onResume();
        }
        
        if (renderingView2 != null) {
            renderingView2.onResume();
        }
    }
    
    @Override
    protected void onPause() {
        super.onPause();
        
        if (renderingView1 != null) {
            renderingView1.onPause();
        }
    
        if (renderingView2 != null) {
            renderingView2.onPause();
        }
    }
}
