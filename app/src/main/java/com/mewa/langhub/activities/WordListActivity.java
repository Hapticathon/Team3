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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



    @Override
    public void onClick(Word word) {
        Intent intent = new Intent(getBaseContext(), WordActivity.class);
        intent.putExtra("word", word);
        startActivity(intent);
    }
}
