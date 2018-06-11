package com.midasit.blockengine;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.midasit.blockengine.core.Core;
import com.midasit.blockengine.core.RenderingSystem;
import com.midasit.blockengine.routine.SimpleRoutine;

public class SyncTest2Activity extends AppCompatActivity {
    
    RenderingView renderingView1;
    RenderingView renderingView2;
    RenderingView renderingView3;
    RenderingView renderingView4;
    
    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sync_test2);
        
        setTitle("Sync - various");
    
        renderingView1 = findViewById(R.id.rendering_view_1);
        renderingView2 = findViewById(R.id.rendering_view_2);
        renderingView3 = findViewById(R.id.rendering_view_3);
        renderingView4 = findViewById(R.id.rendering_view_4);
        
        renderingView1.setupRenderer(new RenderingSystem());
        renderingView2.setupRenderer(new RenderingSystem());
        renderingView3.setupRenderer(new RenderingSystem());
        renderingView4.setupRenderer(new RenderingSystem());
        
        renderingView1.setupRoutine(new SimpleRoutine(10));
        renderingView2.setupRoutine(new SimpleRoutine(100));
        renderingView3.setupRoutine(new SimpleRoutine(1000));
        renderingView4.setupRoutine(new SimpleRoutine(5000));
        
        ((TextView)findViewById(R.id.text_view_1)).setText(String.format("%dx", 10));
        ((TextView)findViewById(R.id.text_view_2)).setText(String.format("%dx", 100));
        ((TextView)findViewById(R.id.text_view_3)).setText(String.format("%dx", 1000));
        ((TextView)findViewById(R.id.text_view_4)).setText(String.format("%dx", 5000));
        
        Core.init();
        
        Core.registerContext(renderingView1.getRenderer());
        Core.registerContext(renderingView2.getRenderer());
        Core.registerContext(renderingView3.getRenderer());
        Core.registerContext(renderingView4.getRenderer());
        
        bindButtons();
    }
    
    private void bindButtons() {
        findViewById(R.id.button_1).setOnClickListener(v -> {
            startActivity(new Intent(this, SyncTestActivity.class));
            finish();
        });
    
        findViewById(R.id.button_2).setOnClickListener(v -> {
        
        });
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Core.unregisterContext(renderingView1.getRenderer());
        Core.unregisterContext(renderingView2.getRenderer());
        Core.unregisterContext(renderingView3.getRenderer());
        Core.unregisterContext(renderingView4.getRenderer());
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
    
        if (renderingView3 != null) {
            renderingView3.onResume();
        }
    
        if (renderingView4 != null) {
            renderingView4.onResume();
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
    
        if (renderingView3 != null) {
            renderingView3.onPause();
        }
    
        if (renderingView4 != null) {
            renderingView4.onPause();
        }
    }
}
