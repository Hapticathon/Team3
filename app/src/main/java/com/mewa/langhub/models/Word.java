package com.mewa.langhub.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Maciej on 2015-05-09.
 */
public class Word implements Parcelable{
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

    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(word);
        dest.writeString(translation);
        dest.writeString(pronunciation);
        dest.writeInt(soundId);
        dest.writeFloatArray(coordinates);

    }

    public Word(Parcel in) {
        word = in.readString();
        translation = in.readString();
        pronunciation = in.readString();
        soundId = in.readInt();
        coordinates = in.createFloatArray();
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Word createFromParcel(Parcel in) {
            return new Word(in);
        }

        public Word[] newArray(int size) {
            return new Word[size];
        }
    };

}
