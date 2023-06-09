package com.example.mycook.startup.view.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mycook.R;
import com.example.mycook.main.view.MainActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpFragment extends Fragment {

    private static final String TAG = "EmailPassword";
    // [START declare_auth]
    private FirebaseAuth mAuth;
    // [END declare_auth]

    private static final int RC_SIGN_IN = 9001;

    GoogleSignInClient mGoogleSignInClient;

    EditText et_email;
    EditText et_password;
    EditText et_confPassword;
    Button btn_submit;

    public SignUpFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build();

        mGoogleSignInClient = GoogleSignIn.getClient(getContext(), gso);

        mAuth = FirebaseAuth.getInstance();


        et_email = view.findViewById(R.id.et_signup_email);
        et_password = view.findViewById(R.id.et_signup_password);
        et_confPassword = view.findViewById(R.id.et_signup_confirm_password);
        btn_submit = view.findViewById(R.id.btn_signup);

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_password.getText().toString().equals(et_confPassword.getText().toString()))
                    createAccount(et_email.getText().toString(), et_password.getText().toString());
                else
                    Toast.makeText(getContext(), "Re-enter your password.", Toast.LENGTH_SHORT).show();

                FirebaseUser currentUser = mAuth.getCurrentUser();

            }
        });
    }

    // [START on_start_check_user]
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);

    }

    private void createAccount(String email, String password) {
        // [START create_user_with_email]
        if (checkConnectivity()) {
            if ((email.equals(null)  || !email.isEmpty()) || (password.equals(null)|| !password.isEmpty())) {
                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(getContext(), "Authentication failed.", Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
            } else Toast.makeText(getContext(), "Invalid Data.", Toast.LENGTH_SHORT).show();
        } else Toast.makeText(getContext(), "Connection failed.", Toast.LENGTH_SHORT).show();
        // [END create_user_with_email]
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            Intent intent = new Intent(getContext(), MainActivity.class);
            startActivity(intent);
            getActivity().finish();
        }
    }

    private boolean checkConnectivity() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
        boolean isConnected = networkCapabilities != null && networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET);
        return isConnected;
    }
}