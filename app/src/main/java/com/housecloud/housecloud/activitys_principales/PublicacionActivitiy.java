package com.housecloud.housecloud.activitys_principales;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;
import com.housecloud.housecloud.R;
import com.housecloud.housecloud.adapters.CategoriaAdapter;
import com.housecloud.housecloud.model.Categoria;

import java.util.ArrayList;

public class PublicacionActivitiy extends AppCompatActivity {

    private ImageButton img1,img2,img3,img4;

    private EditText etTitulo,etDescripcion;

    private Spinner spCategoria;
    private Categoria c;

    private ArrayList<Categoria> categorias;
    private CategoriaAdapter cAdapter;

    private Button subir;

    private StorageReference mStorage;
    private FirebaseAuth fAuth;

    /*
    private static final int GALLERY_INTENT1 = 1;
    private static final int GALLERY_INTENT2 = 2;
    private static final int GALLERY_INTENT3 = 3;
    private static final int GALLERY_INTENT4 = 4;
    private Uri uriSubida1,uriSubida2,uriSubida3,uriSubida4, uriDescarga;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publicacion);
        /*
        img1 = findViewById(R.id.btnImg1);
        img2 = findViewById(R.id.btnImg2);
        img3 = findViewById(R.id.btnImg3);
        img4 = findViewById(R.id.btnImg4);
        */
        etTitulo = findViewById(R.id.etTitulo);
        etDescripcion = findViewById(R.id.etDescripcion);
        spCategoria = findViewById(R.id.spCategoria);

        iniciarListCategoria();

        fAuth = FirebaseAuth.getInstance();

        cargarCategorias();

        /*
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
        */
    }

    private void cargarCategorias() {
        cAdapter = new CategoriaAdapter(this,categorias);
        spCategoria.setAdapter(cAdapter);

        spCategoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                c = (Categoria) adapterView.getItemAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void iniciarListCategoria() {
        categorias = new ArrayList<Categoria>();
        categorias.add(new Categoria("Herramientas", R.drawable.ic_herramientas));
        categorias.add(new Categoria("Ropa",R.drawable.ic_ropa));
        categorias.add(new Categoria("Jardinería",R.drawable.ic_jardineria));
        categorias.add(new Categoria("Electrodomésticos",R.drawable.ic_electrodomesticos));
        categorias.add(new Categoria("Electrónica",R.drawable.ic_electronica));
    }
    /*
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

    }*/

    public void subirPublicacion(View v) {
        Intent i = getIntent();
        String titulo = etTitulo.getText().toString().trim();
        String descripcion = etDescripcion.getText().toString().trim();
        String categoria = c.getCategoria().toString();
        if(titulo.equals("") || descripcion.equals("") || categoria.equals("")){
            Toast.makeText(this,"Debe rellenar todos los campos",Toast.LENGTH_SHORT).show();
        }else {
            //Subida de informacion del post

            DatabaseReference refRaiz = FirebaseDatabase.getInstance().getReference();
            DatabaseReference refUsers = refRaiz.child("users").child(fAuth.getCurrentUser().getUid());

            DatabaseReference refPost = refUsers.child("posts");
            DatabaseReference refIDPost = refPost.push();

            refIDPost.child("titulo").setValue(titulo);
            refIDPost.child("descripcion").setValue(descripcion);
            refIDPost.child("categoria").setValue(categoria);
            refIDPost.child("id_user").setValue(fAuth.getCurrentUser().getUid());

            finish();
            /*
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
            });*/
        }
    }
}
