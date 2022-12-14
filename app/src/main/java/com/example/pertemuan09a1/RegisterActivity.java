package com.example.pertemuan09a1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

public class RegisterActivity extends AppCompatActivity {
    EditText inputUserName, inputPassword, inputEmail, inputNama, inputAsal, inputAlamat;
    Button btnSimpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        this.setTitle("Register");

        inputUserName = findViewById(R.id.etUserR);
        inputPassword = findViewById(R.id.etPasswordR);
        inputEmail = findViewById(R.id.etEmail);
        inputNama = findViewById(R.id.etNama);
        inputAsal = findViewById(R.id.etAsal);
        inputAlamat = findViewById(R.id.etAlamat);

        btnSimpan = findViewById(R.id.btnSimpan);
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isValidation()){
                    simpanFileData();
                }else{
                    Toast.makeText(RegisterActivity.this,
                            "Mohon Lengkapi Seluruh Data",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        //menampilkan up button
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    public boolean isValidation(){
        if(inputUserName.getText().toString().equals("")||
                inputPassword.getText().toString().equals("")||
                inputEmail.getText().toString().equals("")||
                inputNama.getText().toString().equals("")||
                inputAsal.getText().toString().equals("")||
                inputAlamat.getText().toString().equals("")){
            return false;
        }else{
            return true;
        }
    }

    void simpanFileData(){
        String isiFile = inputUserName.getText().toString() + ";"
                + inputPassword.getText().toString() + ";"
                + inputEmail.getText().toString() + ";"
                + inputNama.getText().toString() + ";"
                + inputAsal.getText().toString() + ";"
                + inputAlamat.getText().toString();

        File file = new File(getFilesDir(), inputUserName.getText().toString());
        FileOutputStream outputStream = null;

        try{
            file.createNewFile();//buat file baru
            outputStream = new FileOutputStream(file, false);
            outputStream.write(isiFile.getBytes());
            outputStream.flush();
            outputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        Toast.makeText(this, "Register berhasil", Toast.LENGTH_SHORT).show();

    }

    //cara yang seharusnya lebih elegan, tapi entah kenapa tidak berhasil
//    @Override
//    public boolean onSupportNavigateUp() {
//        onBackPressed();
//        finish();
//        return true;
//    }

    //cara yang kurang elegan untuk action up
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}