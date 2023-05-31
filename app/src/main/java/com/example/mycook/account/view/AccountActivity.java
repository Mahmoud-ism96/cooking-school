package com.example.mycook.account.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mycook.R;
import com.example.mycook.account.presenter.AccountPresenter;
import com.example.mycook.account.presenter.AccountPresenterInterface;
import com.example.mycook.db.ConcreteLocalSource;
import com.example.mycook.main.view.MainActivity;
import com.example.mycook.model.Meal;
import com.example.mycook.model.Repository;
import com.example.mycook.network.MealsAPI;
import com.example.mycook.startup.view.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AccountActivity extends AppCompatActivity {

    private String TAG = "AccountActivity";
    ImageView iv_account_image;
    TextView tv_account_name;
    Button btn_account_sign_out;
    ImageButton btn_account_back;
    FirebaseAuth mAuth;
    FirebaseFirestore fireStoreDB;
    FirebaseUser currentFirebaseUser;
    List<Meal> meals;
    AccountPresenterInterface accountPresenterInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        initViews();

        initData();

        showUserData();

    }

    private void initViews() {
        iv_account_image = findViewById(R.id.iv_account_image);
        tv_account_name = findViewById(R.id.tv_account_name);
        btn_account_sign_out = findViewById(R.id.btn_signout);
        btn_account_back = findViewById(R.id.btn_account_back);
    }

    private void initData() {
        accountPresenterInterface = new AccountPresenter(Repository.getInstance(getApplication(), MealsAPI.getInstance(getApplication()), ConcreteLocalSource.getInstance(getApplication())));
        mAuth = FirebaseAuth.getInstance();
        currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        fireStoreDB = FirebaseFirestore.getInstance();
        meals = new ArrayList<>();
        btn_account_sign_out.setOnClickListener(view -> {
            Intent intent = new Intent(getApplication(), LoginActivity.class);
            startActivity(intent);
            accountPresenterInterface.deleteAllMeals();
            mAuth.signOut();
            finish();
        });
        btn_account_back.setOnClickListener(view -> {
            Intent intent = new Intent(getApplication(), MainActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void showUserData() {
        Uri photoUri = mAuth.getCurrentUser().getPhotoUrl();
        String displayName = mAuth.getCurrentUser().getDisplayName();
        String username = mAuth.getCurrentUser().getEmail();

        updateDisplayName(displayName, username);

        updateProfileImage(photoUri);
    }

    private void updateDisplayName(String displayName, String username) {
        if (displayName == null || displayName.equals(""))
            tv_account_name.setText(username);
        else
            tv_account_name.setText(displayName);

    }

    private void updateProfileImage(Uri photoUri) {
        Log.i(TAG, photoUri + "");
        if (photoUri == null || photoUri.equals("")) {
            Random random = new Random();
            int randomNumber = random.nextInt(2); // Generates a random number between 0 and 1

            if (randomNumber == 0)
                iv_account_image.setImageResource(R.drawable.male);
            else
                iv_account_image.setImageResource(R.drawable.female);


        } else {
            Glide.with(this)
                    .load(photoUri).apply(new RequestOptions().override(300, 300))
                    .placeholder(R.drawable.male).error(R.drawable.female)
                    .into(iv_account_image);
        }
    }

}
