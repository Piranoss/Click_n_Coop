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
        replayAndMenuP();
        verificationTypeJeu();
        //backStack();
    }

    public void affichePointMash(){
        int scoreFinal = viewModel.getCounterMash();
        score.setText(String.valueOf(scoreFinal));

    }

    public void affichePointRythm(){
        int scoreFinal = viewModel.getCounterRythm();
        score.setText(String.valueOf(scoreFinal));

    }

    public void verificationTypeJeu(){
        if (viewModel.getVerification() == 1){
            affichePointMash();
        }
        if (viewModel.getVerification() == 2){
            affichePointRythm();
        }
    }

    public void replayAndMenuP(){
        
        replay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(viewModel.getVerification() == 1) {
                    NavHostFragment.findNavController(ScoreFragment.this)
                            .navigate(R.id.action_scoreFragment_to_SecondFragment);
                    viewModel.setCounterMash(remiseZero);
                }
                if(viewModel.getVerification() == 2) {
                    NavHostFragment.findNavController(ScoreFragment.this)
                            .navigate(R.id.action_scoreFragment_to_rythmFragment);
                    viewModel.setCounterRythm(remiseZero);
                }
            }
        });

        menuP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(ScoreFragment.this)
                        .navigate(R.id.action_scoreFragment_to_FirstFragment);
                viewModel.setCounterMash(remiseZero);
                viewModel.setCounterRythm(remiseZero);
            }
        });
    }

    public void backStack(){
        if (viewModel.getVerification() == 1) {
            ((MainActivity) getActivity()).replaceFragment(new MashFragment(), "MashFragment");
        }
        if (viewModel.getVerification() == 2) {
            ((MainActivity) getActivity()).replaceFragment(new RythmFragment(), "RythmFragment");
        }
    }
}