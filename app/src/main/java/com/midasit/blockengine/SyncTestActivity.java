package com.midasit.blockengine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.midasit.blockengine.core.Core;
import com.midasit.blockengine.core.RenderingSystem;
import com.midasit.blockengine.routine.SimpleRoutine;

public class SyncTestActivity extends AppCompatActivity {
    
    RenderingView renderingView1;
    RenderingView renderingView2;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sync_test);
        
        setTitle("Sync - simple");
        
        renderingView1 = findViewById(R.id.rendering_view_1);
        renderingView2 = findViewById(R.id.rendering_view_2);
        
        renderingView1.setupRenderer(new RenderingSystem());
        renderingView2.setupRenderer(new RenderingSystem());
        
        renderingView1.setupRoutine(new SimpleRoutine(1));
        renderingView2.setupRoutine(new SimpleRoutine(1));
        
        Core.init();
        
        Core.registerContext(renderingView1.getRenderer());
        Core.registerContext(renderingView2.getRenderer());
        
        bindButtons();
    }
    
    private void bindButtons() {
        findViewById(R.id.button_2).setOnClickListener(v -> {
            startActivity(new Intent(this, SyncTest2Activity.class));
            finish();
        });
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Core.unregisterContext(renderingView1.getRenderer());
        Core.unregisterContext(renderingView2.getRenderer());
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
