package com.joke.media_camera.camera;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.joke.media_camera.BuildConfig;
import com.joke.media_camera.Important;
import com.joke.media_camera.MainActivity;
import com.joke.media_camera.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CameraActivity extends AppCompatActivity {

    @BindView(R.id.button1)
    Button mButton1;
    @BindView(R.id.button2)
    Button mButton2;
    @BindView(R.id.suface)
    ImageView mSuface;
    @BindView(R.id.activity_camera)
    RelativeLayout mActivityCamera;
    @BindView(R.id.button3)
    Button mButton3;
    @BindView(R.id.text)
    TextView mText;
    private ImageReceiver mReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= 23)
            checkPermission();
        mReceiver = new ImageReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        registerReceiver(mReceiver, filter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }

    private static final int WRITE_EXTERNAL_STORAGE = 99;

    @RequiresApi(23)
    private void checkPermission() {
        if ((checkSelfPermission(Manifest.permission.CAMERA) &
                checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE))
                != PackageManager.PERMISSION_GRANTED
                ) {
            requestPermissions(new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.CAMERA
            }, WRITE_EXTERNAL_STORAGE);
        }
    }

    //从拍照APP进行拍照
    static final int REQUEST_IMAGE_CAPTURE = 100;

    //将拍摄的照片保存到默认的位置
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    //从拍照App拍照并进行保存
    static final int REQUEST_SAVE_PHOTO = 101;

    private void dispatchTakePictureIntent2() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.joke.media_camera.fileprovider",
                        photoFile);
                takePictureIntent.putExtra("return-data", true);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_SAVE_PHOTO);
            }
        }
    }

    String mCurrentPhotoPath;

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
//        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    @OnClick(R.id.button1)
    public void onButton1Click() {
        dispatchTakePictureIntent2();
    }

    @OnClick(R.id.button2)
    public void onButton2Click() {
        pickImage();
    }

    @Important (notice = "{" +
            "Uri photoURI = Uri.fromFile(file); 与 FileProvider的不同之处" +
            "使用Uri.fromFile(file) 可以保证任何app都可以访问相应的内容，因此其安全性非常低" +
            "FileProvider的获取到的Uri可以保证只有私有内容，只允许自己访问,因此安全性相对较高" +
            "}"
    )
    @OnClick(R.id.button3)
    public void onButton3Click(){
        File file = new File(mCurrentPhotoPath);
        Uri photoURI = Uri.fromFile(file);
        Uri uri = FileProvider.getUriForFile(this,
                "com.joke.media_camera.fileprovider",
                file);
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("uri",uri);
        startActivity(intent);
//        startPhotoZoom(uri);
    }

    private static final int INTENT_PICK_IMAGE = 102;

    private void pickImage() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, INTENT_PICK_IMAGE);
    }

    @SuppressLint("NewApi")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            mSuface.setImageURI(data.getData());
            return;
        }

        if (requestCode == REQUEST_SAVE_PHOTO && resultCode == RESULT_OK) {
            Uri  uri = null;
            if (data != null && data.getData() != null) {
                uri = data.getData();
            } else {
                uri = Uri.fromFile(new File(mCurrentPhotoPath));
            }
            if (uri != null) {
                mSuface.setImageURI(uri);
                mText.setText(mCurrentPhotoPath);
                startPhotoZoom(uri);
            }
            return;
        }
        if (requestCode == INTENT_PICK_IMAGE && resultCode == RESULT_OK) {
            mSuface.setImageURI(data.getData());
        }
        if (requestCode == PHOTO_REQUEST_CUT && resultCode == RESULT_OK) {
            if (data != null) {
                if (data.getData() != null) { //判断是否存在相应的Uri
                  mSuface.setImageURI(data.getData());
                } else { //否则加载相应的数据
                    Bundle extra = data.getExtras();
                    Bitmap bit = extra.getParcelable("data");
                    mSuface.setImageBitmap(bit);
                }
            }
        }
    }

    //只能是getExternalStoragePublicDirectory()获取的公有目录，
    // 如果是getExternalFilesDir，这个则认为是app的私有的公有目录
    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(mCurrentPhotoPath);
        Uri uri = Uri.fromFile(f);
//        Uri uri = FileProvider.getUriForFile(this, "com.joke.media_camera.fileprovider", f);
        mediaScanIntent.setData(uri);
        this.sendBroadcast(mediaScanIntent);
    }

    private static final int PHOTO_REQUEST_CUT = 103;


    //裁剪相应的图片
    private void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // crop为true是设置在开启的intent中设置显示的view可以剪裁
        intent.putExtra("crop", "true");

        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);

        // outputX,outputY 是剪裁图片的宽高
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        intent.putExtra("return-data", true);
        intent.putExtra("noFaceDetection", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,uri);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());

        startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }

    class ImageReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)) {
                Uri data = intent.getData();
                if (data != null) {
                    mText.setText(data.toString());
                }
            }
        }
    }
}
