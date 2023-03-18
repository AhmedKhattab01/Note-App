package com.example.simplenoteapp.screens.profile_fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.data.shared_preference.SharedPreference;
import com.example.simplenoteapp.R;
import com.example.simplenoteapp.databinding.FragmentProfileBinding;
import com.example.simplenoteapp.screens.NotesViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ProfileFragment extends Fragment {
    private FragmentProfileBinding binding;
    private NotesViewModel myViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myViewModel = new ViewModelProvider(this).get(NotesViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentProfileBinding.inflate(inflater, container, false);

        binding.tvName.setText(SharedPreference.getName(getContext()));

        binding.etMail.setText(SharedPreference.getEmail(getContext()));
        binding.etNumber.setText(SharedPreference.getNumber(getContext()));

        binding.btnRemoveAccount.setOnClickListener(view -> {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
            alertDialog.setTitle(R.string.account_deletion);
            alertDialog.setMessage(R.string.account_deletion_msg);
            alertDialog.setNegativeButton(getString(R.string.cancel), (dialogInterface, i) -> dialogInterface.dismiss());
            alertDialog.setPositiveButton(getString(R.string.delete), (dialogInterface, i) -> {
                SharedPreference.deleteUser(getContext());

                new Thread(() -> {
                    myViewModel.deleteAllNotes();
                }).start();

                Navigation.findNavController(view).navigate(R.id.action_profileFragment_to_registerFragment);
            });

            alertDialog.create().show();
        });

        binding.btnEdit.setOnClickListener(view -> {
            Navigation.findNavController(view).navigate(R.id.action_profileFragment_to_editProfileFragment);
        });

        // Inflate the layout for this fragment
        return binding.getRoot();
    }
}