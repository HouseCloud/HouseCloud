package com.housecloud.housecloud;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;

import de.hdodenhof.circleimageview.CircleImageView;

public class PerfilUsuario extends AppCompatActivity {



    private StorageReference mStorage;

    private FirebaseUser mCurrentUser;
    private ProgressDialog mProgresDialog;
    private DatabaseReference mUserDatabase;

    private CircleImageView mDisplayImage;

    private static final int GALLERY_INTENT1 = 1;
    private FirebaseAuth fAuth;

    private Button btmodificar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuario);



        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
        mDisplayImage = findViewById(R.id.imageUser);
        btmodificar = findViewById(R.id.btmodificar);
        mDisplayImage = findViewById(R.id.imageUser);
        if(mCurrentUser!=null){
            String current_uid = mCurrentUser.getUid();
            mUserDatabase = FirebaseDatabase.getInstance().getReference().child("users").child(current_uid);
            mUserDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String image = dataSnapshot.child("image").getValue().toString();

                    Glide.with(PerfilUsuario.this).load(image).into(mDisplayImage);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });



            fAuth = FirebaseAuth.getInstance();


            mStorage = FirebaseStorage.getInstance().getReference();

            btmodificar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(Intent.ACTION_PICK);
                    i.setType("image/*");
                    startActivityForResult(Intent.createChooser(i, "SELECT IMAGE"), GALLERY_INTENT1);

                /*CropImage.activity()
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .start(RegisterActivity.this);*/
                }
            });



        }




    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK && requestCode == GALLERY_INTENT1){
            Uri uriSubidaUser =  data.getData();
            CropImage.activity(uriSubidaUser).setAspectRatio(1,1).start(this);
        }

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {

                mProgresDialog = new ProgressDialog(PerfilUsuario.this);
                mProgresDialog.setTitle("Subiendo imagen...");
                mProgresDialog.setMessage("Por favor espere mientras subimos la imagen");
                mProgresDialog.setCanceledOnTouchOutside(false);
                mProgresDialog.show();


                Uri resultUri = result.getUri();
                String current_user_id = mCurrentUser.getUid();

                StorageReference filapath = mStorage.child("profile_images").child(current_user_id+".jpg");

                filapath.putFile(resultUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(PerfilUsuario.this, "working", Toast.LENGTH_LONG).show();

                            String download_url = task.getResult().getDownloadUrl().toString();
                            mUserDatabase.child("image").setValue(download_url).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        mProgresDialog.dismiss();
                                        Toast.makeText(PerfilUsuario.this,"Foto subida correctamente", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });

                        }else{
                            Toast.makeText(PerfilUsuario.this, "error", Toast.LENGTH_LONG).show();
                        }
                    }
                });


            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }

    }



}
