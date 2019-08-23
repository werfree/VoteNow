package com.example.votenow;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickClick;

public class Verification extends AppCompatActivity {

    private static final int CAMERA_REQUEST = 1;
    public Button faceButton,idButton;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    private static PickImageDialog pickImageDialog = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);
        faceButton=findViewById(R.id.inputFace);
        idButton=findViewById(R.id.inputId);

        faceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if(cameraIntent.resolveActivity(getPackageManager())!=null){
                    startActivityForResult(cameraIntent,CAMERA_REQUEST);
                }
            }
        });
    }

    private void captureImage() {
    }

    public void pickImage(View view) {
        pickImageDialog = PickImageDialog.build(new PickSetup())
                .setOnClick(new IPickClick() {
                    @Override
                    public void onGalleryClick() {
                        Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(pickPhoto , 753);
                    }

                    @Override
                    public void onCameraClick() {
                        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        if (cameraIntent.resolveActivity(getPackageManager()) != null) {
                            startActivityForResult(cameraIntent, 951);
                        }
                    }
                })
                .show(this);
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            pickImageDialog = null; //.dismiss();
        }catch(Exception e){}

        switch (requestCode) {
            case 753:
                if(resultCode == RESULT_OK) {
                    Uri selectedImage = data.getData();
                    ((ImageView) findViewById(R.id.imageView)).setImageURI(selectedImage);
                }
                break;
            case 951:
                if (resultCode == RESULT_OK && data.hasExtra("data")) {
                    Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                    if (bitmap != null) {
                        ((ImageView) findViewById(R.id.imageView)).setImageBitmap(bitmap);
                    }

                }
                break;
            default:
                super.onActivityResult(requestCode, resultCode, data);
                break;
        }
    }
}
