package com.example.lab5contactdatabase;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private int selectedAvatarResId;
    private ImageView selectedAvatarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        selectedAvatarView = findViewById(R.id.selectedAvatar);
        TypedArray avatars = getResources().obtainTypedArray(R.array.avatar_images);
        selectedAvatarResId = avatars.getResourceId(0, R.drawable.avatar1);
        avatars.recycle();

        Button chooseAvatarBtn = findViewById(R.id.chooseAvatarBtn);
        chooseAvatarBtn.setOnClickListener(v -> showAvatarDialog());

        Button savebtn = findViewById(R.id.button);
        savebtn.setOnClickListener(v -> saveDetails());

        Button viewDetailsBtn = findViewById(R.id.viewDetailsBtn);
        viewDetailsBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
            startActivity(intent);
        });
    }

    private void showAvatarDialog() {
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_avatar_selector, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose Avatar")
                .setView(dialogView)
                .setNegativeButton("Cancel", null);

        AlertDialog dialog = builder.create();

        int[] avatarIds = { R.id.avatar1, R.id.avatar2, R.id.avatar3, R.id.avatar4, R.id.avatar5 };
        TypedArray avatars = getResources().obtainTypedArray(R.array.avatar_images);

        for (int i = 0; i < avatarIds.length; i++) {
            ImageView avatarView = dialogView.findViewById(avatarIds[i]);
            int avatarResId = avatars.getResourceId(i, 0);
            avatarView.setOnClickListener(v -> {
                selectedAvatarResId = avatarResId;
                selectedAvatarView.setImageResource(avatarResId);
                dialog.dismiss();
            });
        }

        avatars.recycle();
        dialog.show();
    }

    private void saveDetails() {
        DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext());
        EditText nameTxt = findViewById(R.id.name);
        EditText dobTxt = findViewById(R.id.dob);
        EditText emailTxt = findViewById(R.id.Email);

        String name = nameTxt.getText().toString();
        String dob = dobTxt.getText().toString();
        String email = emailTxt.getText().toString();

        long personId = dbHelper.insertDetails(name, dob, email, selectedAvatarResId);
        Toast.makeText(this, "Person has been created with id: " + personId,
                Toast.LENGTH_LONG).show();

        Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
        startActivity(intent);
    }
}