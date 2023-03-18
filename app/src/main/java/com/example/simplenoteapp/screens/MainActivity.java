package com.example.simplenoteapp.screens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.View;

import com.example.simplenoteapp.R;
import com.example.simplenoteapp.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Set;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.bottomNavigationView.setVisibility(View.GONE);
        binding.toolbar.setVisibility(View.GONE);
        setSupportActionBar(binding.toolbar);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
        NavController navController = navHostFragment.getNavController();

        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.homeFragment,
                R.id.profileFragment,
                R.id.favouriteFragment
        ).build();

        NavigationUI.setupWithNavController(binding.toolbar, navController, appBarConfiguration);

        navController.addOnDestinationChangedListener((controller, destination, args) -> {
            if (destination.getId() == R.id.homeFragment) {
                if (binding.toolbar.getVisibility() == View.GONE) {
                    binding.toolbar.setVisibility(View.VISIBLE);
                }
                if (binding.bottomNavigationView.getVisibility() == View.GONE) {
                    binding.bottomNavigationView.setVisibility(View.VISIBLE);
                }
                return;
            }
            if (destination.getId() == R.id.addNoteFragment) {
                binding.bottomNavigationView.setVisibility(View.GONE);
                return;
            }
        });
    }
}