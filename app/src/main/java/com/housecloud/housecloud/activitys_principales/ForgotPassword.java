package com.housecloud.housecloud.activitys_principales;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.housecloud.housecloud.R;

public class ForgotPassword extends AppCompatActivity {

    private EditText emailUser;
    private TextView titulo;
    private SpannableString tituloSub;
    private FirebaseAuth firebaseAuth;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        titulo = findViewById(R.id.tvTitulo);

        tituloSub = new SpannableString("RECUPERACION DE LA CUENTA");
        tituloSub.setSpan(new UnderlineSpan(), 0, tituloSub.length(), 0);
        titulo.setText(tituloSub);

        email = getIntent().getStringExtra("email");
        emailUser = findViewById(R.id.emailUser);
        emailUser.setText(email);

    }

    public void sendEmail(View v) {
        Log.e("Mostrando Toast", "Ejecuta esta línea");

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Ventana de diálogo de ejemplo");
        builder.create().show();
    }
}
