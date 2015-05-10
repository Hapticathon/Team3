package com.mewa.langhub.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import com.mewa.langhub.R;
import com.mewa.langhub.models.Point;
import com.mewa.langhub.models.Word;

import java.util.ArrayList;

import nxr.tpad.lib.TPad;
import nxr.tpad.lib.TPadImpl;
import nxr.tpad.lib.views.FrictionMapView;

public class WordActivity extends Activity {
    private static final String TAG = "WordActivity";
    ImageView drawingImageView;
    int finalHeight, finalWidth;
    TextView pronunciation;
    TextView word;
    TextView translation;
    public MediaPlayer mediaPlayer;
    private int loadedSoundId = -1;
    private TPad mTpad;
    private FrictionMapView mFrictionMapView;
    boolean play = false;
    Word dataWord;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word);
        final ImageView imageView = (ImageView) findViewById(R.id.image);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        dataWord = bundle.getParcelable("word");
        mediaPlayer = MediaPlayer.create(this, dataWord.getSoundId());
        pronunciation = (TextView) findViewById(R.id.pronunciation);
        word = (TextView) findViewById(R.id.word);
        translation = (TextView) findViewById(R.id.translation);
        pronunciation.setText("");
        word.setText(dataWord.getWord());
        translation.setText(dataWord.getTranslation());

        mFrictionMapView = (FrictionMapView) findViewById(R.id.friction_map_view);
        if (Build.VERSION.SDK_INT <= 21 && mTpad == null) {
            mTpad = new TPadImpl(this);
            mFrictionMapView.setTpad(mTpad);
        }


        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if (!play) {
                            mediaPlayer.start();
                            play = true;
                        }
                        break;
                }
                return false;
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        word.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if (!play) {
                            mediaPlayer.start();
                            play = true;
                        }
                        break;
                }
                return false;
            }
        });

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                play = false;
            }
        });


        ViewTreeObserver vto = imageView.getViewTreeObserver();
        vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            public boolean onPreDraw() {
                imageView.getViewTreeObserver().removeOnPreDrawListener(this);
                finalHeight = imageView.getMeasuredHeight();
                finalWidth = imageView.getMeasuredWidth();
                Bitmap bitmap = createBitmapWithPath(drawingImageView, dataWord.getCoordinates());
                imageView.setImageBitmap(bitmap);
                int black = 0xFF000000;
                int white = 0xFFFFFFFF;
                int colors[] = new int[finalWidth * finalHeight];
                for (int i = 0; i < dataWord.getCoordinates().length; i++) {
                    Log.d(TAG, "coord " + i + ": " + dataWord.getCoordinates()[i]);
                    int dx = finalWidth / (dataWord.getCoordinates().length - 1);
                    int frequency=(Math.abs(((int)dataWord.getCoordinates()[i])-20))/5+2;
                    for (int j = i * dx; j < (i + 1) * dx; j++) {
                        for (int k = 0; k < finalHeight - 1; k++) {
                            if(j%frequency<1 && dataWord.getCoordinates()[i]>10)
                                colors[j+(k*finalWidth)]=black;
                            else
                                colors[j+(k*finalWidth)]=white;

                        }
                    }
                }
                Bitmap bmp = Bitmap.createBitmap(colors, finalWidth, finalHeight, Bitmap.Config.ARGB_8888);
                mFrictionMapView.setDataBitmap(bmp);
                mFrictionMapView.setAlpha(0.0f);
                imageView.setImageBitmap(createBitmapWithPath(imageView, dataWord.getCoordinates()));
                return true;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mediaPlayer.start();
    }

    private Bitmap createBitmapWithPath(final ImageView imageView, float[] coordinates) {

        Bitmap bitmap = Bitmap.createBitmap(finalWidth, finalHeight, Bitmap.Config.ARGB_4444);
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

    private Path createPath(float[] coordinates) {
        ArrayList<Point> points = new ArrayList<>();
        int x = finalWidth / (coordinates.length - 1);
        int y = finalHeight / 20;
        for (int i = 0; i < coordinates.length; i++) {
            Point point = new Point();
            point.x = x * i;
            point.y = Math.abs(y * coordinates[i] - finalHeight);
            points.add(point);
        }

        if (points.size() > 1) {
            for (int i = 0; i < points.size(); i++) {
                if (i >= 0) {
                    Point point = points.get(i);
                    int lol = 12;

                    if (i == 0) {
                        Point next = points.get(i + 1);
                        point.dx = ((next.x - point.x) / lol);
                        point.dy = ((next.y - point.y) / lol);
                    } else if (i == points.size() - 1) {
                        Point prev = points.get(i - 1);
                        point.dx = ((point.x - prev.x) / lol);
                        point.dy = ((point.y - prev.y) / lol);
                    } else {
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
        for (i = 0; i < points.size(); i++) {
            Point point = points.get(i);
            if (first) {
                first = false;
                path.moveTo(point.x, point.y);
            } else {
                Point prev = points.get(i - 1);
                path.cubicTo(prev.x + prev.dx, prev.y + prev.dy, point.x - point.dx, point.y - point.dy, point.x, point.y);
            }
        }

        return path;
    }

    @Override
    protected void onStop() {
        super.onStop();
        mediaPlayer.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.release();
    }
}


