package com.example.mycook.main.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.example.mycook.R;
import com.example.mycook.account.view.AccountActivity;
import com.example.mycook.db.ConcreteLocalSource;
import com.example.mycook.main.presenter.MainPresenter;
import com.example.mycook.main.presenter.MainPresenterInterface;
import com.example.mycook.model.Repository;
import com.example.mycook.model.UserData;
import com.example.mycook.network.MealsAPI;
import com.example.mycook.util.SignUpDialog;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ImageButton btn_account;
    NavController navController;
    BottomNavigationView navView;
    FirebaseFirestore firebaseDB;
    Boolean isExist;
    FirebaseUser currentUser;
    MainPresenterInterface mainPresenterInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainPresenterInterface = new MainPresenter(Repository.getInstance(getApplication(), MealsAPI.getInstance(getApplication()), ConcreteLocalSource.getInstance(getApplication())));

        firebaseDB = FirebaseFirestore.getInstance();
        isExist = false;
        currentUser = FirebaseAuth.getInstance().getCurrentUser();

        if (currentUser != null) checkDataInFireStore();

        navView = findViewById(R.id.bottom_navigation);
        navController = Navigation.findNavController(this, R.id.main_nav_host_fragment);
        NavigationUI.setupWithNavController(navView, navController);

        btn_account = findViewById(R.id.btn_account);

        btn_account.setOnClickListener(view -> {
            if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                Intent intent = new Intent(getApplication(), AccountActivity.class);
                startActivity(intent);
            } else SignUpDialog.showSignupDialog(MainActivity.this);
        });

        navController.addOnDestinationChangedListener((navController, destination, bundle) -> {
            if (FirebaseAuth.getInstance().getCurrentUser() == null) {
                if (destination.getId() == R.id.navigation_plan || destination.getId() == R.id.navigation_favourites) {
                    SignUpDialog.showSignupDialog(MainActivity.this);
                }
            }
        });
    }

    private void checkDataInFireStore() {

        firebaseDB.collection("users").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        if (document.getId().equals(currentUser.getUid())) {
                            //her you need to contain data from FireStore to your object
                            Map<String, Object> data = document.getData();

//                                    UserData loggedInUser = document.toObject(UserData.class);

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
            }
        });
    }

    private void createNewUserInFireStore() {
        Map<String, Object> user = new HashMap<>();
        UserData newUser = new UserData(currentUser.getEmail());
        user.put("users", newUser);

        firebaseDB.collection("users").document(currentUser.getUid()).set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
            }
        });
    }
}
