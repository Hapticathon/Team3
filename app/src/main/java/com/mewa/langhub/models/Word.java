package com.mewa.langhub.models;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Maciej on 2015-05-09.
 */
public class Word {
    private String word;
    private String translation;
    private String pronunciation;
    private float []coordinates;
    private int soundId;

    public int getSoundId() {
        return soundId;
    }

    public void setSoundId(int soundId) {
        this.soundId = soundId;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }



    public String getPronunciation() {
        return pronunciation;
    }

    public void setPronunciation(String pronunciation) {
        this.pronunciation = pronunciation;
    }

    public float[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(float[] coordinates) {
        this.coordinates = coordinates;
    }

    public Word(String word, String translation, String pronunciation, float[] coordinates) {
        this.word = word;
        this.translation = translation;
        this.pronunciation = pronunciation;
        this.coordinates = coordinates;
    }
}
