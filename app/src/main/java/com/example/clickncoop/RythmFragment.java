package com.example.clickncoop;

import androidx.lifecycle.ViewModelProvider;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class RythmFragment extends Fragment {

    private CountViewModel mViewModel;

    public static RythmFragment newInstance() {
        return new RythmFragment();
    }

    private int compteur = 0;
    private TextView texte;
    private TextView texte2;
    private CountViewModel viewModel;
    private CountDownTimer timer;
    private TextView chrono1;
    private TextView chrono2;
    private Button plus1;
    private Button plus2;
    private Button plus3;
    private Button plus4;
    private boolean mTimerRunning = false;
    private boolean verif = false;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.rythm_fragment, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        texte = (TextView) view.findViewById(R.id.Compteur);
        texte2 = (TextView) view.findViewById(R.id.Compteur2nd);
        chrono1 = (TextView) view.findViewById(R.id.Chrono);
        chrono2 = (TextView) view.findViewById(R.id.Chrono2nd);

        viewModel = new ViewModelProvider(requireActivity()).get(CountViewModel.class);

        compteur= viewModel.getCounter();
        texte.setText(String.valueOf(compteur));
        texte2.setText(String.valueOf(compteur));

        plus1 = (Button) getView().findViewById(R.id.button1);
        plus2 = (Button) getView().findViewById(R.id.button2);
        plus3 = (Button) getView().findViewById(R.id.button3);
        plus4 = (Button) getView().findViewById(R.id.button4);

        plus2.setEnabled(false);
        plus2.setBackgroundColor(getResources().getColor(R.color.rouge2));
        plus3.setEnabled(false);
        plus3.setBackgroundColor(getResources().getColor(R.color.rouge2));
        plus4.setEnabled(false);
        plus4.setBackgroundColor(getResources().getColor(R.color.rouge2));

        increment();
    }

    public void increment(){
        plus1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mTimerRunning) {
                    compteur += 1;
                    viewModel.setCounter(compteur);
                    texte.setText(String.valueOf(compteur));
                    texte2.setText(String.valueOf(compteur));
                    changeButton(1);
                }else{
                    verify(true);
                    compteur += 1;
                    viewModel.setCounter(compteur);
                    texte.setText(String.valueOf(compteur));
                    texte2.setText(String.valueOf(compteur));
                    changeButton(1);
                }
            }
        });

        plus2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mTimerRunning) {
                    compteur += 1;
                    viewModel.setCounter(compteur);
                    texte.setText(String.valueOf(compteur));
                    texte2.setText(String.valueOf(compteur));
                    changeButton(2);
                }
            }
        });
        plus3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mTimerRunning) {
                    compteur += 1;
                    viewModel.setCounter(compteur);
                    texte.setText(String.valueOf(compteur));
                    texte2.setText(String.valueOf(compteur));
                    changeButton(3);
                }
            }
        });

        plus4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mTimerRunning) {
                    compteur += 1;
                    viewModel.setCounter(compteur);
                    texte.setText(String.valueOf(compteur));
                    texte2.setText(String.valueOf(compteur));
                    changeButton(4);
                }
            }
        });
    }

    public void countDownTimer(){
        timer = new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                chrono1.setText("00:"+ String.format(Locale.getDefault(), "%d:", millisUntilFinished / 1000L));
                chrono2.setText("00:"+ String.format(Locale.getDefault(), "%d:", millisUntilFinished / 1000L));
            }

            @Override
            public void onFinish() {
                chrono1.setText("00:00:00");
                chrono2.setText("00:00:00");
                score();
                NavHostFragment.findNavController(RythmFragment.this)
                        .navigate(R.id.action_rythmFragment_to_scoreFragment);
            }
        }.start();
        mTimerRunning = true;
    }

    public void score(){
        int point;
        point = compteur;
        viewModel.setCounter(point);
    }

    public void verify(boolean x){
        if(x) {
            verif = true;
        }
        if(verif){
            countDownTimer();
        }
    }

    public void changeButton(int x){
        switch (x){
            case 1:
                plus1.setEnabled(false);
                plus1.setBackgroundColor(getResources().getColor(R.color.rouge2));
                plus2.setEnabled(true);
                plus2.setBackgroundColor(getResources().getColor(R.color.rouge));
                break;
            case 2:
                plus2.setEnabled(false);
                plus2.setBackgroundColor(getResources().getColor(R.color.rouge2));
                plus3.setEnabled(true);
                plus3.setBackgroundColor(getResources().getColor(R.color.rouge));
                break;
            case 3:
                plus3.setEnabled(false);
                plus3.setBackgroundColor(getResources().getColor(R.color.rouge2));
                plus4.setEnabled(true);
                plus4.setBackgroundColor(getResources().getColor(R.color.rouge));
                break;
            case 4:
                plus4.setEnabled(false);
                plus4.setBackgroundColor(getResources().getColor(R.color.rouge2));
                plus1.setEnabled(true);
                plus1.setBackgroundColor(getResources().getColor(R.color.rouge));
                break;
        }
    }
    /*
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CountViewModel.class);
    }
     */
}