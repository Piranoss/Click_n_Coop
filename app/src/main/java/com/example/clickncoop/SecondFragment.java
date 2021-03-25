package com.example.clickncoop;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

public class SecondFragment extends Fragment {

    private int compteur1 = 0;
    private int compteur2 = 0;
    private TextView texte;
    private TextView texte2;
    private CountViewModel viewModel;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        texte = (TextView) view.findViewById(R.id.Compteur1);
        texte2 = (TextView) view.findViewById(R.id.Compteur2);

        viewModel = new ViewModelProvider(requireActivity()).get(CountViewModel.class);

        compteur1= viewModel.getCounter();
        texte.setText(String.valueOf(compteur1));

        compteur2= viewModel.getCounter();
        texte2.setText(String.valueOf(compteur2));

        increment();
    }

    public void increment(){
        Button plus1 = (Button) getView().findViewById(R.id.button_second);
        plus1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                compteur1+=1;
                viewModel.setCounter(compteur1);
                texte.setText(String.valueOf(compteur1));
            }
        });

        Button plus2 = (Button) getView().findViewById(R.id.button_second2);
        plus2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                compteur2+=1;
                viewModel.setCounter(compteur2);
                texte2.setText(String.valueOf(compteur2));
            }
        });
    }
}