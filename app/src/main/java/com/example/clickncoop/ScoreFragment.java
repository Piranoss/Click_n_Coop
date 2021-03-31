package com.example.clickncoop;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;


public class ScoreFragment extends Fragment {
    CountViewModel viewModel;
    private Button replay;
    private Button menuP;
    private TextView score;
    private int remiseZero = 0;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_score, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(requireActivity()).get(CountViewModel.class);
        replay = (Button) view.findViewById(R.id.replay);
        menuP = (Button) view.findViewById(R.id.returnM);
        score = (TextView) view.findViewById(R.id.point);
        affichePoint();
        replayAndMenuP();
    }

    public void affichePoint(){
        int scoreFinal = viewModel.getCounter();
        score.setText(String.valueOf(scoreFinal));

    }

    public void replayAndMenuP(){
        replay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(ScoreFragment.this)
                        .navigate(R.id.action_scoreFragment_to_SecondFragment);
                viewModel.setCounter(remiseZero);
            }
        });

        menuP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(ScoreFragment.this)
                        .navigate(R.id.action_scoreFragment_to_FirstFragment);
            }
        });
    }
}