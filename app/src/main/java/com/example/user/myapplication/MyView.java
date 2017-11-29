package com.example.user.myapplication;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by User on 2017/11/29.
 */

public class MyView extends View {
    private Bitmap ballBmp;
    private Resources res;

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);

        res = context.getResources();

        ballBmp = BitmapFactory.decodeResource(res, R.drawable.ball);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(ballBmp, 0, 0, null);

    }
}
