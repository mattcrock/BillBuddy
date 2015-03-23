package com.crock.matt.billbuddy;

import com.crock.matt.billbuddy.util.SystemUiHider;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


public class Capture extends Activity {

    private static final int CAMERA_PIC_REQUEST = 1337;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_capture);

        Button btnContinue = (Button) findViewById(R.id.btn_continue);
        Button btnCapture = (Button) findViewById(R.id.btn_capture);

        btnCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CaptureImage();
            }
        });

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Capture.this, "Continue Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        //start the camera activity and capture the image
        CaptureImage();
    }

    public void CaptureImage(){
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST);
    }

    //this is fired when the image has been captured
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == CAMERA_PIC_REQUEST){
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");

            ImageView image = (ImageView) findViewById(R.id.img_captured);
            image.setImageBitmap(thumbnail);
        }
    }
}
