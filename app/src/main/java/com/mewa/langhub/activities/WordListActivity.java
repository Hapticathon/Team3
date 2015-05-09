package com.mewa.langhub.activities;

import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.mewa.langhub.Data;
import com.mewa.langhub.R;
import com.mewa.langhub.interfaces.WordClickHandler;
import com.mewa.langhub.models.Word;

import java.util.ArrayList;



public class WordListActivity extends AppCompatActivity implements WordClickHandler {

    public static SoundPool soundPool;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadMusic();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void loadMusic(){
        soundPool =  new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        Data.word1.setSoundId(soundPool.load(this, R.raw.health, 1));
        Data.word2.setSoundId(soundPool.load(this, R.raw.shoe, 1));
        Data.word3.setSoundId(soundPool.load(this, R.raw.paper, 1));
        Data.word4.setSoundId(soundPool.load(this, R.raw.student, 1));
        Data.word5.setSoundId(soundPool.load(this, R.raw.hospital, 1));
        Data.word6.setSoundId(soundPool.load(this, R.raw.bird, 1));
        Data.word7.setSoundId(soundPool.load(this, R.raw.history, 1));
        Data.word8.setSoundId(soundPool.load(this, R.raw.work, 1));
        Data.word9.setSoundId(soundPool.load(this, R.raw.family, 1));
        Data.word10.setSoundId(soundPool.load(this, R.raw.spring, 1));
        Data.word11.setSoundId(soundPool.load(this, R.raw.book, 1));
    }

    @Override
    public void onClick(Word word) {
        Intent intent = new Intent(getBaseContext(),WordActivity.class);
        intent.putExtra("word",word);
        startActivity(intent);
    }
}
