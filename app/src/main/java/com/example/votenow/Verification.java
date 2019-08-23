package com.example.votenow;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickClick;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;

public class Verification extends AppCompatActivity {

    private static final int CAMERA_REQUEST = 1;
    public Button faceButton,idButton;
    ImageView imageView;
    Bitmap bitmap;
    CircleImageView circleImageView;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    private static PickImageDialog pickImageDialog = null;
    boolean faceBool=false,idBool=false;
    ImageButton next;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);
        faceButton=findViewById(R.id.inputFace);
        idButton=findViewById(R.id.inputId);
        idButton=findViewById(R.id.inputFace);
        imageView=findViewById(R.id.imageView);
        next=findViewById(R.id.next);
        circleImageView=findViewById(R.id.circleView);
        circleImageView.setVisibility(View.INVISIBLE);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check();
            }
        });

        faceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent,456);
            }
        });


    }

    public void check(){
        if(faceBool == true && idBool == true){
            Toast.makeText(this,"Vote Done",Toast.LENGTH_LONG).show();
        }else if(faceBool==false){
            faceButton.setError("Face Not Uploaded");
        }else{
            idButton.setError("Id Not Uploaded");
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case CAMERA_REQUEST:
                if(permissions.length>0 && permissions[0].equals(PackageManager.PERMISSION_GRANTED)){
                    Toast.makeText(Verification.this,"Permission Granted, Now your application can access CAMERA.", Toast.LENGTH_LONG).show();

                }else {
                    Toast.makeText(Verification.this,"Permission Canceled, Now your application cannot access CAMERA.", Toast.LENGTH_LONG).show();

                }
                break;
        }
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
        super.onActivityResult(requestCode, resultCode, data);
        try {
            pickImageDialog = null; //.dismiss();
        }catch(Exception e){}

        switch (requestCode) {
            case 753:
                if(resultCode == RESULT_OK) {
                    Uri selectedImage = data.getData();
                    //((ImageView) findViewById(R.id.imageView)).setImageURI(selectedImage);
                    try {
                        InputStream iStream =   getContentResolver().openInputStream(selectedImage);
                        byte[] inputData = getBytes(iStream);
                        String image = new String(Base64.encode(inputData, Base64.DEFAULT));
                        //Toast.makeText(this, image, Toast.LENGTH_LONG).show();
                        Log.v("ImageData", image);
                    } catch (Exception e){}
                }
                break;
            case 951:
                if (resultCode == RESULT_OK && data.hasExtra("data")) {
                    Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                    if (bitmap != null) {
                        //((ImageView) findViewById(R.id.imageView)).setImageBitmap(bitmap);
                        try {
                            ByteArrayOutputStream stream = new ByteArrayOutputStream();
                            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                            byte[] imagedata = stream.toByteArray();
                            bitmap.recycle();
                            String image = new String(Base64.encode(imagedata, Base64.DEFAULT));
                            //Toast.makeText(this, image, Toast.LENGTH_LONG).show();
                            Log.v("ImageData", image);
                        } catch (Exception e){}
                    }
                }
                break;
            case 456:
                try {
                    bitmap=(Bitmap)data.getExtras().get("data");
                    circleImageView.setImageBitmap(bitmap);
                    circleImageView.setVisibility(View.VISIBLE);
                    faceButton.setHint("Change Face?");
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    byte[] imagedata = stream.toByteArray();
                    bitmap.recycle();
                    String image = new String(Base64.encode(imagedata, Base64.DEFAULT));
                    //Toast.makeText(this, image, Toast.LENGTH_LONG).show();
                    Log.v("ImageData", image);
                }catch (Exception e){
                    Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            default:
                super.onActivityResult(requestCode, resultCode, data);
                break;
        }
    }

    public byte[] getBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }
        return byteBuffer.toByteArray();
    }
}
