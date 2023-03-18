package com.example.simplenoteapp.screens.splash;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.data.shared_preference.SharedPreference;
import com.example.simplenoteapp.R;
import com.example.simplenoteapp.databinding.FragmentSplashBinding;

import java.util.Timer;
import java.util.TimerTask;

public class SplashFragment extends Fragment {
    private FragmentSplashBinding binding;
    private int delay = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentSplashBinding.inflate(inflater, container, false);

        Timer timer = new Timer();

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                delay++;

                if (delay == 3) {
                    timer.cancel();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (SharedPreference.isUserLogin(requireContext())) {
                                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_splashFragment_to_homeFragment);
                            } else {
                                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_splashFragment_to_registerFragment);
                            }
                        }
                    });
                }
            }
        };

        timer.scheduleAtFixedRate(timerTask, 0, 1000);

        // Inflate the layout for this fragment
        return binding.getRoot();
    }
}