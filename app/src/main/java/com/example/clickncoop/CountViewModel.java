package com.example.clickncoop;

import androidx.lifecycle.ViewModel;

public class CountViewModel extends ViewModel {
    private int counter;

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public int getCounter() {
        return counter;
    }
}
