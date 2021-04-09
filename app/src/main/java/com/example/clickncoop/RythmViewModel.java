package com.example.clickncoop;

import androidx.lifecycle.ViewModel;

public class RythmViewModel extends ViewModel {
    private int counterRythm;

    public void setCounter(int counter) {
        this.counterRythm = counter;
    }

    public int getCounterRythm() {
        return counterRythm;
    }
}