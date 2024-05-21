package com.example.myapplication;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ImageView dogImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dogImageView = findViewById(R.id.dogImageView);

        // 화면 터치 리스너 설정
        findViewById(R.id.main).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        moveDog(event.getX(), event.getY());
                        break;
                }
                return true;
            }
        });

        // 강아지 터치 리스너 설정
        dogImageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    v.setVisibility(View.GONE);  // 강아지 숨기기
                }
                return true;
            }
        });
    }

    private void moveDog(float x, float y) {
        float targetX = x - dogImageView.getWidth() / 2;
        float targetY = y - dogImageView.getHeight() / 2;

        ObjectAnimator animatorX = ObjectAnimator.ofFloat(dogImageView, "x", dogImageView.getX(), targetX);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(dogImageView, "y", dogImageView.getY(), targetY);

        animatorX.setDuration(1000); // 1초 동안 애니메이션 실행
        animatorY.setDuration(1000); // 1초 동안 애니메이션 실행

        animatorX.start();
        animatorY.start();
    }
}
