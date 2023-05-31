package com.example.mycook.main.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.example.mycook.R;
import com.example.mycook.account.view.AccountActivity;
import com.example.mycook.db.ConcreteLocalSource;
import com.example.mycook.main.presenter.MainPresenter;
import com.example.mycook.main.presenter.MainPresenterInterface;
import com.example.mycook.model.Meal;
import com.example.mycook.model.Repository;
import com.example.mycook.model.UserData;
import com.example.mycook.network.MealsAPI;
import com.example.mycook.util.SignUpDialog;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements MainInterface {

    private String TAG = "MainActivity";
    ImageButton btn_account;
    public ImageButton btn_back;
    NavController navController;
    public BottomNavigationView navView;
    FirebaseFirestore fireStoreDB;
    Boolean isExist;
    FirebaseUser currentFirebaseUser;
    MainPresenterInterface mainPresenterInterface;

    static List<Meal> meals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        initData();
    }

    private void initViews() {
        navView = findViewById(R.id.bottom_navigation);
        btn_account = findViewById(R.id.btn_account);
        btn_back = findViewById(R.id.btn_back);
    }

    private void initData() {
        mainPresenterInterface = new MainPresenter(this, Repository.getInstance(getApplication(), MealsAPI.getInstance(getApplication()), ConcreteLocalSource.getInstance(getApplication())));
        isExist = false;
        meals = new ArrayList<>();

        initFirebaseDB();

        initNavigation();

        btn_account.setOnClickListener(view -> {
            if (currentFirebaseUser != null) {
                Intent intent = new Intent(getApplication(), AccountActivity.class);
                startActivity(intent);
            } else SignUpDialog.showSignupDialog(MainActivity.this);
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.popBackStack();
            }
        });

        navController.addOnDestinationChangedListener((navController, destination, bundle) -> {
            if (currentFirebaseUser == null) {
                if (destination.getId() == R.id.navigation_plan || destination.getId() == R.id.navigation_favourites) {
                    SignUpDialog.showSignupDialog(MainActivity.this);
                }
            }
        });
    }

    private void initFirebaseDB() {
        fireStoreDB = FirebaseFirestore.getInstance();
        currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentFirebaseUser != null) checkDataInFireStore();
    }

    private void initNavigation() {
        navController = Navigation.findNavController(this, R.id.main_nav_host_fragment);
        NavigationUI.setupWithNavController(navView, navController);
    }

    private void checkDataInFireStore() {

        fireStoreDB.collection("users").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (QueryDocumentSnapshot document : task.getResult()) {
                    if (document.getId().equals(currentFirebaseUser.getUid())) {
                        Map<String, Object> data = document.getData();
                        UserData userData = new UserData((Map<String, Object>) data.get("users"));
                        if (userData.getStoredMeals() != null)
                            mainPresenterInterface.insertAllMeals(userData.getStoredMeals());
                        isExist = true;
                    }
                }
                if (!isExist) {
                    createNewUserInFireStore();
                }
            }
        });
    }

    private void createNewUserInFireStore() {
        Map<String, Object> user = new HashMap<>();
        UserData newUser = new UserData(currentFirebaseUser.getEmail());
        user.put("users", newUser);

        fireStoreDB.collection("users").document(currentFirebaseUser.getUid()).set(user)
                .addOnSuccessListener(unused -> Log.i(TAG, "Success"))
                .addOnFailureListener(e -> Log.i(TAG, "Failure: " + e));
    }

    @Override
    protected void onPause() {
        super.onPause();
        mainPresenterInterface.getStoredMeals();
    }

    @Override
    public void getAllStoredMeals(List<Meal> allMeals) {
        meals = allMeals;
        updateDatabase();
    }

    public void updateDatabase() {
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            updateUserDataInFireStoreDB();

        }
    }

    private void updateUserDataInFireStoreDB() {
        UserData updatedUserData = new UserData(currentFirebaseUser.getEmail(), meals);
        Map<String, Object> data = new HashMap<>();
        data.put("users", updatedUserData);
        fireStoreDB.collection("users").document(currentFirebaseUser.getUid()).set(data, SetOptions.merge())
                .addOnSuccessListener(unused -> Log.i(TAG, "Success"))
                .addOnFailureListener(e -> Log.i(TAG, "Failure: " + e));
    }
}
