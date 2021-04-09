package com.example.clickncoop;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }

    public void replaceFragment(Fragment fragment, String tag) {
        //Place le fragment actuel dans le container
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.container);

        //Prévient d'ajouter le même fragment à la tête de la pile
        if (currentFragment.getClass() == fragment.getClass()) {
            return;
        }

        //Si le fragment est déjà sur la pile, nous pouvons remettre la pile en place pour éviter une croissance infinie de la pile.
        if (getSupportFragmentManager().findFragmentByTag(tag) != null) {
            getSupportFragmentManager().popBackStack(tag, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }

        //Sinon, juste replacer le fragment.
        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(tag)
                .replace(R.id.container, fragment, tag)
                .commit();
    }

    @Override
    public void onBackPressed() {
        CountViewModel viewModel = new ViewModelProvider(this).get(CountViewModel.class);
        viewModel.setCounterMash(0);
        viewModel.setCounterRythm(0);
        int fragmentsInStack = getSupportFragmentManager().getBackStackEntryCount();
        if (fragmentsInStack > 1) { // Si nous avons plus d'un fragment, remonter la pile.
            getSupportFragmentManager().popBackStack();
        } else if (fragmentsInStack == 1) { // Terminé l'activité, s'il ne reste qu'un seul fragment, pour éviter de laisser un écran vide.
            finish();
        } else {
            super.onBackPressed();
        }
    }
}