package com.example.clickncoop;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import java.util.Locale;

public class MashFragment extends Fragment {

    private int compteur1 = 0;
    private int compteur2 = 0;
    private TextView texte;
    private TextView texte2;
    private CountViewModel viewModel;
    private CountDownTimer timer;
    private TextView chrono1;
    private Button plus1;
    private Button plus2;
    private boolean isClickedP1;
    private boolean isClickedP2;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.mash_fragment, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        texte = (TextView) view.findViewById(R.id.Compteur1);
        texte2 = (TextView) view.findViewById(R.id.Compteur2);
        chrono1 = (TextView) view.findViewById(R.id.Chrono1);

        viewModel = new ViewModelProvider(requireActivity()).get(CountViewModel.class);

        compteur1= viewModel.getCounter();
        texte.setText(String.valueOf(compteur1));

        compteur2= viewModel.getCounter();
        texte2.setText(String.valueOf(compteur2));

        plus1 = (Button) getView().findViewById(R.id.button_second);
        plus2 = (Button) getView().findViewById(R.id.button_second2);

        countDownTimer();
        increment();
    }

    public void increment(){
        plus1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                compteur1+=1;
                viewModel.setCounter(compteur1);
                texte.setText(String.valueOf(compteur1));
            }
        });

        plus2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                compteur2+=1;
                viewModel.setCounter(compteur2);
                texte2.setText(String.valueOf(compteur2));
            }
        });
    }

    public void countDownTimer(){
            timer = new CountDownTimer(10000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    chrono1.setText("00:"+ String.format(Locale.getDefault(), "%d:", millisUntilFinished / 1000L));
                }

                @Override
                public void onFinish() {
                    chrono1.setText("00:00:00");
                    score();
                    NavHostFragment.findNavController(MashFragment.this)
                            .navigate(R.id.action_SecondFragment_to_scoreFragment);
                }
            }.start();
    }

    public CountDownTimer getTimer() {
        return timer;
    }

    public void score(){
        int point;
        point = compteur1+compteur2;
        viewModel.setCounter(point);
    }
}