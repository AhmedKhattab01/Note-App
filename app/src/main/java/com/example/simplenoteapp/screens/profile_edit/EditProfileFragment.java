package com.example.simplenoteapp.screens.profile_edit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.data.shared_preference.SharedPreference;
import com.example.simplenoteapp.R;
import com.example.simplenoteapp.databinding.FragmentEditProfileBinding;

public class EditProfileFragment extends Fragment {

    private FragmentEditProfileBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentEditProfileBinding.inflate(inflater, container, false);

        binding.etName.setText(SharedPreference.getName(getContext()));
        binding.etMail.setText(SharedPreference.getEmail(getContext()));
        binding.etNumber.setText(SharedPreference.getNumber(getContext()));

        binding.btnSaveEdit.setOnClickListener(view -> {

            String name = binding.etName.getText().toString();
            String email = binding.etMail.getText().toString();
            String number = binding.etNumber.getText().toString();

            if (name.isEmpty() || email.isEmpty() || number.isEmpty()) {
                Toast.makeText(getContext(), getString(R.string.all_fields_required), Toast.LENGTH_SHORT).show();
            }
            else {
                SharedPreference.writeToSharedPref(getContext(),name,email,number);
                Navigation.findNavController(view).popBackStack();
            }
        });

        // Inflate the layout for this fragment
        return binding.getRoot();
    }
}