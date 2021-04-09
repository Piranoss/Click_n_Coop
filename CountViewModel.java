package com.example.clickncoop;

import androidx.lifecycle.ViewModel;

public class CountViewModel extends ViewModel {
    private int counterMash;
    private int counterRythm;
    private int verification;

    public void setCounterMash(int counter) {
        this.counterMash = counter;
    }

    public int getCounterMash() {
        return counterMash;
    }

    public void setCounterRythm(int counter){
        this.counterRythm = counter;
    }

    public int getCounterRythm(){
        return counterRythm;
    }

    public int getVerification(){
        return verification;
    }

    public void setVerification(int verif){
        verification = verif;
    }
}