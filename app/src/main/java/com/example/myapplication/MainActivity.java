package com.example.myapplication;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ImageView dogImageView;
    private float[] targetXArray = new float[15]; // X 좌표 배열
    private float[] targetYArray = new float[15]; // Y 좌표 배열
    private int currentPosition = 0; // 현재 좌표 인덱스
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dogImageView = findViewById(R.id.dogImageView);

        // 미리 정의된 좌표 설정
        setTargetCoordinates();

        handler.postDelayed(runnable, 3000); // 3초 딜레이
    }

    private void setTargetCoordinates() {
        // 강아지 움직이는 포인트
        targetXArray[0] = 50;
        targetYArray[0] = 50;
        targetXArray[1] = 800;
        targetYArray[1] = 100;
        targetXArray[2] = 400;
        targetYArray[2] = 200;
        targetXArray[3] = 600;
        targetYArray[3] = 500;
        targetXArray[4] = 100;
        targetYArray[4] = 800;
        targetXArray[5] = 900;
        targetYArray[5] = 900;
        targetXArray[6] = 700;
        targetYArray[6] = 200;
        targetXArray[7] = 300;
        targetYArray[7] = 700;
        targetXArray[8] = 1000;
        targetYArray[8] = 300;
        targetXArray[9] = 200;
        targetYArray[9] = 400;
        targetXArray[10] = 1100;
        targetYArray[10] = 600;
        targetXArray[11] = 500;
        targetYArray[11] = 1000;
        targetXArray[12] = 1200;
        targetYArray[12] = 200;
        targetXArray[13] = 400;
        targetYArray[13] = 900;
        targetXArray[14] = 800;
        targetYArray[14] = 700;
    }


    // 강아지 이동 메서드
    private void moveDog(float x, float y) {
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(dogImageView, "x", dogImageView.getX(), x - dogImageView.getWidth() / 2);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(dogImageView, "y", dogImageView.getY(), y - dogImageView.getHeight() / 2);

        animatorX.setDuration(3000); //  강아지 이동 속도 조절(x)
        animatorY.setDuration(3000); //  애강아지 이동 속도 조절(y)

        animatorX.start();
        animatorY.start();
    }

    // 루틴 실행 메서드
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            // 다음 좌표로 이동
            if (currentPosition < 15) {
                moveDog(targetXArray[currentPosition], targetYArray[currentPosition]);
                currentPosition++;
            } else {
                currentPosition = 0;
            }
            handler.postDelayed(this, 5000); // 루틴간 강아지 딜레이
        }
    };

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            // 클릭 시 해당 좌표로 이동
            moveDog(event.getX(), event.getY());
            handler.removeCallbacks(runnable);
            handler.postDelayed(runnable, 5000); // 터치이벤트 발생후 루틴 복기하기전까지 딜레이
        }
        return super.onTouchEvent(event);
    }
}
