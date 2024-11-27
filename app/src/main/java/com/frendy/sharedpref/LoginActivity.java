package com.frendy.sharedpref;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.frendy.sharedpref.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inisialisasi binding
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Inisialisasi PrefManager
        prefManager = PrefManager.getInstance(this);

        // Tombol untuk pindah ke halaman Register
        binding.txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        // Tombol Login
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = binding.edtUsername.getText().toString();
                String password = binding.edtPassword.getText().toString();

                // Validasi input
                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Mohon isi semua data", Toast.LENGTH_SHORT).show();
                } else {
                    if (isValidUsernamePassword(username, password)) {
                        prefManager.setLoggedIn(true);
                        checkLoginStatus();
                    } else {
                        Toast.makeText(LoginActivity.this, "Username atau Password salah", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    // Metode untuk memvalidasi username dan password
    private boolean isValidUsernamePassword(String inputUsername, String inputPassword) {
        String username = prefManager.getUsername();
        String password = prefManager.getPassword();
        return username.equals(inputUsername) && password.equals(inputPassword);
    }

    // Metode untuk mengecek status login
    private void checkLoginStatus() {
        boolean isLoggedIn = prefManager.isLoggedIn();
        if (isLoggedIn) {
            Toast.makeText(LoginActivity.this, "Login Berhasil", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        } else {
            Toast.makeText(LoginActivity.this, "Login Gagal", Toast.LENGTH_SHORT).show();
        }
    }
}
