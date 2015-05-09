package com.mewa.langhub;

import java.util.ArrayList;

/**
 * Created by Maciej on 2015-05-09.
 */
public class Word {
    String word;
    String translation;
    String pronunciation;
    ArrayList<Integer> coordinates;

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

    public ArrayList<Integer> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(ArrayList<Integer> coordinates) {
        this.coordinates = coordinates;
    }

    public Word(String word,  String pronunciation, ArrayList<Integer> coordinates, String translation) {
        this.coordinates = coordinates;
        this.pronunciation = pronunciation;
        this.word = word;
        this.translation = translation;
    }
}
