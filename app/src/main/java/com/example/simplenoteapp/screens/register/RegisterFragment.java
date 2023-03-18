package com.example.simplenoteapp.screens.register;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.data.shared_preference.SharedPreference;
import com.example.simplenoteapp.R;
import com.example.simplenoteapp.databinding.FragmentRegisterBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class RegisterFragment extends Fragment {

    private FragmentRegisterBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentRegisterBinding.inflate(inflater, container, false);

        BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.bottomNavigationView);
        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);

        if (toolbar.getVisibility() == View.VISIBLE) {
            toolbar.setVisibility(View.GONE);
        }

        if (bottomNavigationView.getVisibility() == View.VISIBLE) {
            bottomNavigationView.setVisibility(View.GONE);
        }

        binding.button.setOnClickListener(view -> {
            createAccount();
        });

        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    private void createAccount() {
        String name = binding.etName.getText().toString();
        String email = binding.etEmail.getText().toString();
        String number = binding.etNumber.getText().toString();

        if (name.isEmpty() || email.isEmpty() || number.isEmpty()) {
            Toast.makeText(requireContext(), getString(R.string.all_fields_required), Toast.LENGTH_LONG).show();
            return;
        }

        SharedPreference.writeToSharedPref(
                requireContext(),
                binding.etName.getText().toString(),
                binding.etEmail.getText().toString(),
                binding.etNumber.getText().toString()
        );

        if (SharedPreference.isUserLogin(requireContext())) {
            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_registerFragment_to_homeFragment);
        }
    }
}