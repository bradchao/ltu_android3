package com.example.user.myapplication;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by User on 2017/11/29.
 */

public class MyView extends View {
    private Bitmap ballBmp;
    private Resources res;
    private Timer timer;
    private float ballX, ballY, dx, dy, viewW, viewH, ballW, ballH, ball2X, ball2Y, d2x, d2y;

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);

        timer = new Timer();
        res = context.getResources();
        ballBmp = BitmapFactory.decodeResource(res, R.drawable.ball);
        ballW = ballBmp.getWidth(); ballH = ballBmp.getHeight();

        dx = dy = 10;
        d2x = d2y = 4;

        ball2X = 70;
        ball2Y = 40;

        timer.scheduleAtFixedRate(new RefreshView(), 1*1000, 70);
        timer.scheduleAtFixedRate(new BallTask(), 1*1000, 70);
        timer.scheduleAtFixedRate(new Ball2Task(), 1*1000, 70);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        viewW = getWidth(); viewH = getHeight();


        canvas.drawBitmap(ballBmp, ballX, ballY, null);
        canvas.drawBitmap(ballBmp, ball2X, ball2Y, null);

    }

    private class RefreshView extends TimerTask {
        @Override
        public void run() {
            postInvalidate();
        }
    }

    private class BallTask extends TimerTask {
        @Override
        public void run() {
            if (ballX <0 || ballX + ballW > viewW){
                dx *= -1;
            }
            if (ballY <0 || ballY + ballH > viewH){
                dy *= -1;
            }

            ballX += dx;
            ballY += dy;
            //postInvalidate();
        }
    }

    private class Ball2Task extends TimerTask {
        @Override
        public void run() {
            if (ball2X <0 || ball2X + ballW > viewW){
                d2x *= -1;
            }
            if (ball2Y <0 || ball2Y + ballH > viewH){
                d2y *= -1;
            }

            ball2X += d2x;
            ball2Y += d2y;
            //postInvalidate();
        }
    }

}
