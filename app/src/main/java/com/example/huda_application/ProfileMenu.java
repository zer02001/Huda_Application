package com.example.huda_application;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.huda_application.databinding.ActivityProfileMenuBinding;
import com.example.huda_application.databinding.MenuActivityMainApplicationBinding;
import com.example.huda_application.firebase.FirebaseClient;
import com.example.huda_application.user.User;
import com.example.huda_application.user.UserManager;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileMenu extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private MenuActivityMainApplicationBinding binding;
    private TextView name;
    private TextView email;
    private TextView DOB;
    NavigationView profileMenu;
    View headerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = MenuActivityMainApplicationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarProfileMenu.toolbar);
        binding.appBarProfileMenu.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_profile_menu);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        headerView = navigationView.getHeaderView(0);
        name = (TextView) headerView.findViewById(R.id.profile_user_name);
        email = (TextView) headerView.findViewById(R.id.profile_user_email);
        DOB = (TextView) headerView.findViewById(R.id.profile_user_dob);

        FirebaseDatabase.getInstance().getReference("User").child(UserManager.getInstance().getCurrentUser().getUserId())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        User user = FirebaseClient.convertToUser(snapshot);
                        user = snapshot.getValue(User.class);
                        name.setText(String.format("%s", user.getFirstName() + " " + user.getLastName()));
                        email.setText(String.format("%s", user.getEmailAddress()));
                        DOB.setText(String.format("%s", user.getBirthday()));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });

        User user = UserManager.getInstance().getCurrentUser();
        name.setText(String.format("%s", user.getFirstName() + " " + user.getLastName()));
        email.setText(String.format("%s", user.getEmailAddress()));
        DOB.setText(String.format("%s", user.getBirthday()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.profile_menu, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_profile_menu);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}