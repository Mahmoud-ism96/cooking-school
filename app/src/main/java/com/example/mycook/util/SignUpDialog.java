package com.example.mycook.util;

import android.app.Activity;
import android.content.Intent;

import com.example.mycook.R;
import com.example.mycook.startup.view.LoginActivity;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class SignUpDialog {
    public static void showSignupDialog(Activity activity) {
        new MaterialAlertDialogBuilder(activity).setTitle(R.string.dialog_signup_title).setMessage(R.string.dialog_signup_body)
            .setNegativeButton(activity.getResources().getString(R.string.dialog_signup_btn_negative), (dialog, which) -> {})
            .setPositiveButton(activity.getResources().getString(R.string.dialog_signup_btn_positive), (dialog, which) -> {
            Intent intent = new Intent(activity, LoginActivity.class);
            activity.startActivity(intent);
            activity.finish();
        }).show();
    }
}
