package com.example.mycook.account.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mycook.R;
import com.example.mycook.account.presenter.AccountPresenter;
import com.example.mycook.account.presenter.AccountPresenterInterface;
import com.example.mycook.db.ConcreteLocalSource;
import com.example.mycook.model.Meal;
import com.example.mycook.model.Repository;
import com.example.mycook.model.UserData;
import com.example.mycook.network.MealsAPI;
import com.example.mycook.startup.view.LoginActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class AccountActivity extends AppCompatActivity implements AccountInterface {

    ImageView iv_account_image;
    TextView tv_account_name;
    Button btn_account_sign_out;
    FirebaseAuth mAuth;
    FirebaseFirestore firebaseDB;
    FirebaseUser currentUser;

    List<Meal> meals;

    AccountPresenterInterface accountPresenterInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        iv_account_image = findViewById(R.id.iv_account_image);
        tv_account_name = findViewById(R.id.tv_account_name);
        btn_account_sign_out = findViewById(R.id.btn_signout);

        mAuth = FirebaseAuth.getInstance();
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        firebaseDB = FirebaseFirestore.getInstance();
        meals = new ArrayList<>();

        accountPresenterInterface = new AccountPresenter(this,Repository.getInstance(getApplication(), MealsAPI.getInstance(getApplication()), ConcreteLocalSource.getInstance(getApplication())));
        accountPresenterInterface.getStoredMeals();

        Uri photoUri = mAuth.getCurrentUser().getPhotoUrl();
        String displayName = mAuth.getCurrentUser().getDisplayName();
        String username = mAuth.getCurrentUser().getEmail();

        updateDisplayName(displayName, username);

        updateProfileImage(photoUri);

        btn_account_sign_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(), LoginActivity.class);
                startActivity(intent);
                mAuth.signOut();
                updateUserDataInFireStore();
                accountPresenterInterface.deleteAllMeals();
                finish();
            }
        });


    }

    private void updateDisplayName(String displayName, String username) {
        if (displayName == null || displayName.equals("")) {
            tv_account_name.setText(username);
        } else {
            tv_account_name.setText(displayName);
        }
    }

    private void updateProfileImage(Uri photoUri) {
        if (photoUri == null) {
            Random random = new Random();
            int randomNumber = random.nextInt(2); // Generates a random number between 0 and 1

            if (randomNumber == 0) {
                iv_account_image.setImageResource(R.drawable.male);
            } else {
                iv_account_image.setImageResource(R.drawable.female);
            }

        } else iv_account_image.setImageURI(photoUri);
    }

    private void updateUserDataInFireStore() {
        UserData updatedUser = new UserData(currentUser.getEmail(), meals);
        Map<String, Object> data = new HashMap<>();
        data.put("users", updatedUser);
        firebaseDB.collection("users").document(currentUser.getUid()).set(data, SetOptions.merge()).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }

    @Override
    public void getAllStoredMeals(List<Meal> allMeals) {
        meals = allMeals;
    }
}
