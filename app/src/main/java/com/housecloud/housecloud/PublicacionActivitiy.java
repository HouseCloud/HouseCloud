package com.housecloud.housecloud;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class PublicacionActivitiy extends AppCompatActivity {

    private ImageButton img1,img2,img3,img4;

    private EditText etTitulo,etDescripcion,etCategoria;

    private Button subir;

    private StorageReference mStorage;
    private FirebaseAuth fAuth;

    private static final int GALLERY_INTENT1 = 1;
    private static final int GALLERY_INTENT2 = 2;
    private static final int GALLERY_INTENT3 = 3;
    private static final int GALLERY_INTENT4 = 4;
    private Uri uriSubida1,uriSubida2,uriSubida3,uriSubida4, uriDescarga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publicacion_activitiy);

        img1 = findViewById(R.id.btnImg1);
        img2 = findViewById(R.id.btnImg2);
        img3 = findViewById(R.id.btnImg3);
        img4 = findViewById(R.id.btnImg4);

        etTitulo = findViewById(R.id.etTitulo);
        etDescripcion = findViewById(R.id.etDescripcion);
        etCategoria = findViewById(R.id.etCategoria);

        fAuth = FirebaseAuth.getInstance();

        mStorage = FirebaseStorage.getInstance().getReference();

        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_PICK);
                i.setType("image/*");
                startActivityForResult(i,GALLERY_INTENT1);
            }
        });

        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_PICK);
                i.setType("image/*");
                startActivityForResult(i,GALLERY_INTENT2);
            }
        });

        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_PICK);
                i.setType("image/*");
                startActivityForResult(i,GALLERY_INTENT3);
            }
        });

        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_PICK);
                i.setType("image/*");
                startActivityForResult(i,GALLERY_INTENT4);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK && requestCode == GALLERY_INTENT1){
            uriSubida1 = data.getData();
            Glide.with(PublicacionActivitiy.this)
                    .load(uriSubida1)
                    .into(img1);
        }
        if(resultCode == RESULT_OK && requestCode == GALLERY_INTENT2){
            uriSubida2 = data.getData();
            Glide.with(PublicacionActivitiy.this)
                    .load(uriSubida2)
                    .into(img2);
        }
        if(resultCode == RESULT_OK && requestCode == GALLERY_INTENT3){
            uriSubida3 = data.getData();
            Glide.with(PublicacionActivitiy.this)
                    .load(uriSubida3)
                    .into(img3);
        }
        if(resultCode == RESULT_OK && requestCode == GALLERY_INTENT4){
            uriSubida4 = data.getData();
            Glide.with(PublicacionActivitiy.this)
                    .load(uriSubida4)
                    .into(img4);
        }

    }

    public void subirPublicacion(View v) {
        String titulo = etTitulo.getText().toString().trim();
        String descripcion = etDescripcion.getText().toString().trim();
        String categoria = etCategoria.getText().toString().trim();
        if(titulo.equals("") || descripcion.equals("") || categoria.equals("")){
            Toast.makeText(this,"Debe rellenar todos los campos",Toast.LENGTH_SHORT).show();
        }else {
            //Subida de informacion del post

            final DatabaseReference refRaiz = FirebaseDatabase.getInstance().getReference();
            final DatabaseReference refTablon = refRaiz.child("posts");
            /*
            String titulo = etTitulo.getText().toString().trim();
            String descripcion = etDescripcion.getText().toString().trim();
            String categoria = etCategoria.getText().toString().trim();
            */
            DatabaseReference refIdUser = refTablon.child(fAuth.getCurrentUser().getUid());
            DatabaseReference refPost = refIdUser.push();

            refPost.child("titulo").setValue(titulo);
            refPost.child("descripcion").setValue(descripcion);
            refPost.child("categoria").setValue(categoria);
            // Subida de imagenes del post

            StorageReference filePath = mStorage.child("fotos").child(fAuth.getCurrentUser().getUid());
            StorageReference filePost = filePath.child(refPost.getKey());
            StorageReference fileImg = filePost.child(uriSubida1.getLastPathSegment());

            fileImg.putFile(uriSubida1).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    uriDescarga = taskSnapshot.getDownloadUrl();
                    Toast.makeText(PublicacionActivitiy.this, "Foto subida", Toast.LENGTH_SHORT).show();
                }
            });

            fileImg = filePost.child(uriSubida2.getLastPathSegment());

            fileImg.putFile(uriSubida2).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    uriDescarga = taskSnapshot.getDownloadUrl();
                    Toast.makeText(PublicacionActivitiy.this, "Foto subida", Toast.LENGTH_SHORT).show();
                }
            });

            fileImg = filePost.child(uriSubida3.getLastPathSegment());

            fileImg.putFile(uriSubida3).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    uriDescarga = taskSnapshot.getDownloadUrl();
                    Toast.makeText(PublicacionActivitiy.this, "Foto subida", Toast.LENGTH_SHORT).show();
                }
            });

            fileImg = filePost.child(uriSubida4.getLastPathSegment());

            fileImg.putFile(uriSubida4).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    uriDescarga = taskSnapshot.getDownloadUrl();
                    Toast.makeText(PublicacionActivitiy.this, "Foto subida", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
