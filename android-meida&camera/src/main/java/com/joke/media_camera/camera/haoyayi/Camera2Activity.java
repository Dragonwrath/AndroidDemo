package com.joke.media_camera.camera.haoyayi;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.hardware.camera2.CameraCaptureSession;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.TextureView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.joke.media_camera.R;

import java.io.File;

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class Camera2Activity extends AppCompatActivity implements View.OnClickListener, Camera2.OnCameraListener {
    public static final int REQUEST_CODE_LOCAL = 19;

    private View scalePic, send, cancel, takepicture;
    private String resultPath;
    private ImageView preview;
    private Camera2 camera2;
    private TextureView mTextureView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera2_haoyayi);
        initView(savedInstanceState);
    }

    protected void initView(Bundle savedInstanceState) {
        String defaultFile = getIntent().getStringExtra("outFile");
        String cameraTip = getIntent().getStringExtra("cameraTip");
        scalePic = findViewById(R.id.camera_scalePic);
        scalePic.setOnClickListener(this);
        send = findViewById(R.id.camera_send);
        send.setOnClickListener(this);
        cancel = findViewById(R.id.camera_cancel);
        cancel.setOnClickListener(this);
        takepicture = findViewById(R.id.camera_takepicture);
        takepicture.setOnClickListener(this);
        ((TextView) (findViewById(R.id.camera_tip))).setText(cameraTip);

        preview = (ImageView) this.findViewById(R.id.preview_img);
        mTextureView = (TextureView) findViewById(R.id.autoFitTextureView);
        camera2 = new Camera2(this,mTextureView,defaultFile);
        camera2.setOnCameraListener(this);
    }




    @Override
    public void onResume() {
        super.onResume();
        try {
            camera2.onResume();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        try {
            camera2.onPause();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.camera_scalePic: {
                Intent intent;
                if (Build.VERSION.SDK_INT < 19) {
                    intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("image/*");
                } else {
                    intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                }
                startActivityForResult(intent, REQUEST_CODE_LOCAL);
                break;
            }
            case R.id.camera_cancel:
                if (send.getVisibility() == View.VISIBLE) {
                    scalePic.setVisibility(View.VISIBLE);
                    takepicture.setVisibility(View.VISIBLE);
                    send.setVisibility(View.GONE);
                    mTextureView.setVisibility(View.VISIBLE);
                    preview.setVisibility(View.GONE);

                    try {
                        camera2.reOpenReview();
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                    return;
                }
                finish();
                break;
            case R.id.camera_takepicture:
                try {
                    camera2.takePicture();
                }catch (Exception e){
                    e.printStackTrace();
                }

                break;
            case R.id.camera_send: {
                Intent intent = new Intent();
                intent.putExtra("file", resultPath);
                setResult(RESULT_OK, intent);
                finish();
                break;
            }
        }

    }

    /**
     * onActivityResult
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE_LOCAL) {
                if (data != null) {
                    Uri selectedImage = data.getData();
                    display(preview, selectedImage.toString());
                    preview.setVisibility(View.VISIBLE);
                    mTextureView.setVisibility(View.GONE);
                    scalePic.setVisibility(View.GONE);
                    takepicture.setVisibility(View.INVISIBLE);
                    send.setVisibility(View.VISIBLE);
                    resultPath = handlePath(selectedImage);

                }
            }
        }
    }

    /**
     * 根据图库图片uri发送图片
     *
     * @param selectedImage
     */
    private String handlePath(Uri selectedImage) {
        Cursor cursor = getContentResolver().query(selectedImage, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex("_data");
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            return picturePath;
        } else {
            File file = new File(selectedImage.getPath());
            return file.getAbsolutePath();
        }
    }

    public static void startActivityForResult(Activity context, String outFile, String cameraTip, int requestCode) {
        Intent intent = new Intent();
        intent.putExtra("outFile", outFile);
        intent.putExtra("cameraTip", cameraTip);
        intent.setClass(context, Camera2Activity.class);
        context.startActivityForResult(intent, requestCode);
    }

    @Override
    public void onTakePictureSuccess(final String reqFile) {
        resultPath = reqFile;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                resultPath = getScaledImage(Camera2Activity.this, resultPath);
                preview.setImageDrawable(Drawable.createFromPath(resultPath));
                preview.setVisibility(View.VISIBLE);
                mTextureView.setVisibility(View.GONE);
                scalePic.setVisibility(View.GONE);
                takepicture.setVisibility(View.INVISIBLE);
                send.setVisibility(View.VISIBLE);
            }
        });

    }

    private void display(ImageView view ,String path) {
        view.setImageURI(Uri.parse(path));
    }

    private String getScaledImage(Activity activity, String resultPath) {
        return "";
    }


    @Override
    public void onConfigureFailed(CameraCaptureSession cameraCaptureSession) {
    }

    @Override
    public void onSetUpCameraOutputsFailed(Exception e) {
    }
}
