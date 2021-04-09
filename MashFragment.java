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

    private Button plus1;
    private Button plus2;
    private boolean verif1 = false;
    private boolean mTimerRunning = false;
    private boolean verif2 = false;
    private boolean verif = false;
    private int compteur1 = 0;
    private int compteur2 = 0;
    private TextView texte;
    private TextView texte2;
    private TextView chrono;
    private TextView chrono2;
    private CountViewModel viewModel;
    public int counter = 10;

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
        chrono = (TextView) view.findViewById(R.id.Chrono1);
        chrono2 = (TextView) view.findViewById(R.id.Chrono2);

        viewModel = new ViewModelProvider(requireActivity()).get(CountViewModel.class);

        compteur1= viewModel.getCounterMash();
        texte.setText(String.valueOf(compteur1));

        compteur2= viewModel.getCounterMash();
        texte2.setText(String.valueOf(compteur2));

        increment();

    }

    public void increment(){
        plus1 = (Button) getView().findViewById(R.id.button_second);
        plus1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mTimerRunning){
                    compteur1 += 1;
                    viewModel.setCounterMash(compteur1);
                    texte.setText(String.valueOf(compteur1));
                } else {
                    verify(1);
                }
            }
        });

        plus2 = (Button) getView().findViewById(R.id.button_second2);
        plus2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mTimerRunning){
                    compteur2 += 1;
                    viewModel.setCounterMash(compteur2);
                    texte2.setText(String.valueOf(compteur2));
                } else {
                    verify(2);
                }
            }
        });
    }
    public void startTimer(){
        new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                chrono.setText(String.format(Locale.getDefault(), "%d:", millisUntilFinished / 1000L));
                chrono2.setText(String.format(Locale.getDefault(), "%d:", millisUntilFinished / 1000L));
            }

            @Override
            public void onFinish() {
                chrono.setText("00:00:00");
                chrono2.setText("00:00:00");
                score();
                NavHostFragment.findNavController(MashFragment.this)
                        .navigate(R.id.action_SecondFragment_to_scoreFragment);
                viewModel.setVerification(1);
            }
        }.start();
        mTimerRunning = true;
    }

    public void verify(int x){
        if(x==1) {
            verif1 = true;
        }
        if(x==2){
            verif2 = true;
        }

        if(verif2 & verif1){
            startTimer();
        }
    }


    public void score(){
        int point;
        point = compteur1+compteur2;
        viewModel.setCounterMash(point);
    }

    public void backStack(){
        ((MainActivity) getActivity()).replaceFragment(new AccueilFragment(), "AccueilFragment");
    }
}