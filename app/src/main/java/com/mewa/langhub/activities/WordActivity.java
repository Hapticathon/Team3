package com.mewa.langhub.activities;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import com.mewa.langhub.models.Point;
import com.mewa.langhub.R;

import java.util.ArrayList;

public class WordActivity extends Activity {
    ImageView drawingImageView;
    int finalHeight, finalWidth;
    TextView pronunciation;
    TextView word;
    TextView translation;
    SoundPool soundPool;
    int soundID;
    float[] coordinates = {6,5,5,7,8,9,10,11,11,10,8,7,6,4,3,3,4,5,5,7,8,13,16,18,17,16,16,14};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word);
        final ImageView imageView = (ImageView)findViewById(R.id.image);
        pronunciation = (TextView) findViewById(R.id.pronunciation);
        word = (TextView) findViewById(R.id.word);
        translation = (TextView) findViewById(R.id.translation);
        soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        soundID = soundPool.load(this, R.raw.beat, 1);
        pronunciation.setText("pɹəˌnʌnsiˈeɪʃən");
        word.setText("pronunciation");
        translation.setText("wymowa");
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                soundPool.play(soundID,1.0f,1.0f,1,0,1.0f);
            }
        });

        word.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                soundPool.play(soundID,1.0f,1.0f,1,0,1.0f);
                return false;
            }
        });


        ViewTreeObserver vto = imageView.getViewTreeObserver();
        vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            public boolean onPreDraw() {
                imageView.getViewTreeObserver().removeOnPreDrawListener(this);
                finalHeight = imageView.getMeasuredHeight();
                finalWidth = imageView.getMeasuredWidth();
                Bitmap bitmap =createBitmapWithPath(drawingImageView,coordinates);
                imageView.setImageBitmap(bitmap);
                return true;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    private Bitmap createBitmapWithPath(final ImageView imageView, float[] coordinates){

        Bitmap bitmap = Bitmap.createBitmap(finalWidth, finalHeight , Bitmap.Config.ARGB_4444);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.WHITE);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(9);
        Path p = createPath(coordinates);
        canvas.drawPath(p, paint);
        return bitmap;
    }

    private Path createPath(float[] coordinates){
        ArrayList<Point> points = new ArrayList<>();
        int x= finalWidth/coordinates.length;
        int y = finalHeight/20;
        for(int i=0;i<coordinates.length;i++) {
            Point point = new Point();
            point.x=x*i;
            point.y=Math.abs(y*coordinates[i]-finalHeight);
            points.add(point);
        }

        if(points.size() > 1){
            for(int i = 0; i < points.size(); i++){
                if(i >= 0){
                    Point point = points.get(i);
                    int lol = 12;

                    if(i == 0){
                        Point next = points.get(i + 1);
                        point.dx = ((next.x - point.x) / lol);
                        point.dy = ((next.y - point.y) / lol);
                    }
                    else if(i == points.size() - 1){
                        Point prev = points.get(i - 1);
                        point.dx = ((point.x - prev.x) / lol);
                        point.dy = ((point.y - prev.y) / lol);
                    }
                    else{
                        Point next = points.get(i + 1);
                        Point prev = points.get(i - 1);
                        point.dx = ((next.x - prev.x) / lol);
                        point.dy = ((next.y - prev.y) / lol);
                    }
                }
            }
        }
        Path path = new Path();
        int i;
        boolean first = true;
        for(i = 0; i < points.size(); i++){
            Point point = points.get(i);
            if(first){
                first = false;
                path.moveTo(point.x, point.y);
            }
            else{
                Point prev = points.get(i - 1);
                path.cubicTo(prev.x + prev.dx, prev.y + prev.dy, point.x - point.dx, point.y - point.dy, point.x, point.y);
            }
        }

        return path;
    }
}


