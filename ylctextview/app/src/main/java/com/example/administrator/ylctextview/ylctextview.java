package com.example.administrator.ylctextview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class ylctextview extends View {

    private Paint paint;
    private String text;

    public ylctextview(Context context) {
        super(context);
    }

    private Rect textRect;

    public ylctextview(Context context, AttributeSet attrs) {
        super(context, attrs);

        paint = new Paint();
        paint.setTextSize(50);
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);

        text = "1234";
        textRect = new Rect();
        paint.getTextBounds(text, 0, text.length(), textRect);

        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                text = randomText();
                postInvalidate();
            }
        });
    }

    private String randomText() {
        Random random = new Random();
        Set<Integer> set = new HashSet<Integer>();
        while (set.size() < 4) {
            int randomInt = random.nextInt(10);
            set.add(randomInt);
        }
        StringBuffer sb = new StringBuffer();
        for (Integer i : set) {
            sb.append("" + i);
        }

        return sb.toString();
    }


    public ylctextview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int modew = MeasureSpec.getMode(widthMeasureSpec);
        int modeh = MeasureSpec.getMode(heightMeasureSpec);
        int w = MeasureSpec.getSize(widthMeasureSpec);
        int h = MeasureSpec.getSize(heightMeasureSpec);
        int ylcw = 0;
        int ylch = 0;

        textRect = new Rect();
        paint.getTextBounds(text, 0, text.length(), textRect);

        if (modew == MeasureSpec.EXACTLY) {
            ylcw = w;
        } else {
            ylcw = getPaddingLeft() + getPaddingRight() + textRect.width();
        }

        if (modeh == MeasureSpec.EXACTLY) {
            ylch = h;
        } else {
            ylch = getPaddingTop() + getPaddingBottom() + textRect.height();
        }
        setMeasuredDimension(ylcw, ylch);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int w = getMeasuredWidth();
        int h = getMeasuredHeight();
        canvas.drawText(text, w / 2 - textRect.width() / 2, h / 2 + textRect.height() / 2, paint);
    }


}
