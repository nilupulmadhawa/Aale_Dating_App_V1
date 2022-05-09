package com.example.aale;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;


import com.example.aale.databinding.ActivityUserBinding;
import com.example.aale.ui.chat_bubble.ChatBubbleFragment;
import com.example.aale.ui.find_lover.FindLoverFragment;
import com.example.aale.ui.user_profile.UserProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class UserActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener{

    private ActivityUserBinding binding;

    NavigationBarView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =  ActivityUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();
        bottomNavigationView = binding.bottomNavigationView;

       bottomNavigationView.setOnItemSelectedListener(this::onNavigationItemSelected);
        bottomNavigationView.setSelectedItemId(R.id.bottom_find_lover);
    }
    UserProfileFragment firstFragment = new UserProfileFragment();
    FindLoverFragment secondFragment = new FindLoverFragment();
    ChatBubbleFragment thirdFragment = new ChatBubbleFragment();

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.bottom_user_profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, firstFragment).commit();
                return true;

            case R.id.bottom_find_lover:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, secondFragment).commit();
                return true;

            case R.id.bottom_message_bubble:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, thirdFragment).commit();
                return true;
        }
        return false;
    }
}