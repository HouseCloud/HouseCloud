package com.housecloud.housecloud;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private EditText etNombre, etEmail, etPhone, etPassword, etCP;
    private Button btnRegistrar;
    private ProgressDialog pd;

    private FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etNombre = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        etPassword = findViewById(R.id.etPassword);
        etCP = findViewById(R.id.etCPostal);
        btnRegistrar = findViewById(R.id.btnRegister);

        pd = new ProgressDialog(this);



        fAuth = FirebaseAuth.getInstance();
    }

    public void RegistroUser(View v){
        final String name, email, phone, password, cp;
        final DatabaseReference refRaiz = FirebaseDatabase.getInstance().getReference();
        final DatabaseReference refUsers = refRaiz.child("users");

        name = etNombre.getText().toString().trim();
        email = etEmail.getText().toString().trim();
        phone = etPhone.getText().toString().trim();
        password = etPassword.getText().toString().trim();
        cp = etCP.getText().toString().trim();

        if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)){
            pd.setMessage("Registrando, por favor espere...");
            pd.show();
            fAuth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            DatabaseReference refIdUser = refUsers.child(fAuth.getCurrentUser().getUid());
                            refIdUser.child("name").setValue(name);
                            refIdUser.child("email").setValue(email);
                            refIdUser.child("phone").setValue(phone);
                            refIdUser.child("CP").setValue(cp);
                            pd.dismiss();
                            Toast.makeText(RegisterActivity.this,"Registro realizado con exito",Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }
}
