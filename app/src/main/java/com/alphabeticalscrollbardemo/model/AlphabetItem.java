package com.alphabeticalscrollbardemo.model;

/**
 * Created by Lenovo on 21-03-2018.
 */

public class AlphabetItem {
    public int position;
    public String word;
    public boolean isActive;

    public AlphabetItem(int pos, String word, boolean isActive) {
        this.position = pos;
        this.word = word;
        this.isActive = isActive;
    }
}