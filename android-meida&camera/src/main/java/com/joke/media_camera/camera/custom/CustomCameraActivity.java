package com.joke.media_camera.camera.custom;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.joke.media_camera.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

//this following step is using the Camera API,for newer version you should use Camera2
public class CustomCameraActivity extends AppCompatActivity {

    private static final String TAG = "CustomCameraActivity";

    private Camera mCamera;
    private CameraPreview mPreview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_camera);
        if (!checkCameraHardware(this)) {
            Toast.makeText(this, "不存在摄像头，请确认", Toast.LENGTH_SHORT).show();
        } else  {
            requestCameraPermission(this);
            addCamera();
            createPictureCallback();
            addPictureCallback();
        }
        listFile();
    }

    public void listFile() {
        File mediaStorageDir = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "CustomCamera");
        if (mediaStorageDir.isDirectory()) {
            File[] files = mediaStorageDir.listFiles();
            for (File file : files) {
                Log.e(TAG, "listFile: "+file.getAbsolutePath() );
            }
        }
    }

    //Step A- check if this device has a camera
    private boolean checkCameraHardware(Context context) {
        return context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA);
    }

    //step B - check permission
    private void requestCameraPermission(Context context) {
        if(Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE},2);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 2) {

            for (int i = 0; i < permissions.length; i++) {
                if (permissions[i].equals(Manifest.permission.CAMERA)) {
                    if (grantResults[i] == PackageManager.PERMISSION_GRANTED ){
                        Toast.makeText(this, "获取权限成功", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(this, "获取权限失败", Toast.LENGTH_SHORT).show();
                        if (Build.VERSION.SDK_INT >= 23)
                            shouldShowRequestPermissionRationale(Manifest.permission.CAMERA);
                    }
                }
            }
            for (String permission : permissions) {
                if (permission.equals(Manifest.permission.CAMERA)) {
                    
                }
            }
        }
    }

    //Step C- a safe way to get an instance of the Camera Object
    private static Camera getCameraInstance() {
        Camera c = null;
        try {
            c = Camera.open(); // attempt to get a Camera instance
        }
        catch (Exception e){
            // Camera is not available (in use or does not exist)
        }
        return c; // returns null if camera is unavailable
    }

    //Step D- custom a  new class which extends SurfaceView
    //Note：If you wang to change size of camera preview ,you shoulde set
    // size in the method of surfaceChanged,
    // Do not set arbitrary values in the setPreviewSize() method.
    //Note: With the introduction of the Multi-Window feature in
    // Android 7.0 (API level 24) and higher, you can no longer assume
    // the aspect ratio of the preview is the same as your activity
    // even after calling setDisplayOrientation(). Depending on the window size
    // and aspect ratio, you may may have to fit a wide camera preview
    // into a portrait-orientated layout, or vice versa, using a letterbox layout


    //Step E - create your layout
    public void addCamera() {
        // Create an instance of Camera
        mCamera = getCameraInstance();

        // Create our Preview view and set it as the content of our activity.
        mPreview = new CameraPreview(this, mCamera);
        FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
        preview.addView(mPreview);
    }

    //Step F - initial your picture callback
    private Camera.PictureCallback mPicture;
    private void createPictureCallback() {
        mPicture = new Camera.PictureCallback() {

            @Override
            public void onPictureTaken(byte[] data, Camera camera) {

                File pictureFile = getOutputMediaFile(MEDIA_TYPE_IMAGE);
                if (pictureFile == null){
                    Log.d(TAG, "Error creating media file, check storage permissions: ");
                    return;
                }

                try {
                    FileOutputStream fos = new FileOutputStream(pictureFile);
                    fos.write(data);
                    fos.close();
                } catch (FileNotFoundException e) {
                    Log.d(TAG, "File not found: " + e.getMessage());
                } catch (IOException e) {
                    Log.d(TAG, "Error accessing file: " + e.getMessage());
                } finally {
                    Log.d(TAG, "Error accessing file: " + pictureFile.getAbsolutePath());
                    Log.d(TAG, "Error accessing file size: " + pictureFile.length());
                }
            }
        };
    }

    //Step G - add PictureCallback to button
    private void addPictureCallback() {
        Button captureButton = (Button) findViewById(R.id.button_capture);
        captureButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // get an image from the camera
                        mCamera.takePicture(null, null, mPicture);
                    }
                }
        );
    }





    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;

    /** Create a file Uri for saving an image or video */
    private Uri getOutputMediaFileUri(int type){
        return Uri.fromFile(getOutputMediaFile(type));
    }

    /** Create a File for saving an image or video */
    private  File getOutputMediaFile(int type){
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "CustomCamera");
        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                Log.d("CustomCamera", "failed to create directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",Locale.getDefault()).format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE){
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "IMG_"+ timeStamp + ".jpg");
        } else if(type == MEDIA_TYPE_VIDEO) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "VID_"+ timeStamp + ".mp4");
        } else {
            return null;
        }

        return mediaFile;
    }
}
