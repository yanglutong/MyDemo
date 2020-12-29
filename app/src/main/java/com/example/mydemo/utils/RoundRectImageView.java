package com.example.mydemo.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import java.util.ArrayList;

/*
    圆角图标
    左上，右上为圆角
 */
@SuppressLint("AppCompatCustomView")
public class RoundRectImageView extends ImageView {
    private Float radius = Float.parseFloat(6+"");
    //设置圆角为左上和右上
    private float [] radiusArray={radius,radius,radius,radius,0.0f,0.0f,0.0f,0.0f};
    public RoundRectImageView(Context context) {
        this(context,null);
    }

    public RoundRectImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RoundRectImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

       // 绘制圆角

    private void drawRoundAngle(Canvas paramCanvas) {
        Paint paint = new  Paint();
        paint.setAntiAlias(true);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        Path path = new Path();
        path.addRoundRect(new RectF(0.0f, 0.0f, Float.parseFloat(getWidth()+""),  Float.parseFloat(getHeight()+"")), radiusArray, Path.Direction.CW);
        path.setFillType(Path.FillType.INVERSE_WINDING);
        paramCanvas.drawPath(path, paint);
    }
    /*
        重新绘制
     */

    @Override
    public void draw(Canvas paramCanvas) {
        Bitmap bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        Canvas localCanvas = new Canvas(bitmap);
        if (bitmap.isRecycled()) {
            bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
            localCanvas = new Canvas(bitmap);
        }
        super.draw(localCanvas);
        drawRoundAngle(localCanvas);
        Paint paint = new Paint();
        paint.setXfermode(null);
        paramCanvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
        bitmap.recycle();
    }
}
