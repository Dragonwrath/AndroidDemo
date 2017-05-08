package com.joke.media_camera.camera.haoyayi;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.joke.media_camera.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.ButterKnife;


public class CameraActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int DEFAULT_IMAGE_WIDTH = 150;
    private static final int DEFAULT_IMAGE_HEIGHT = 150;
    public static final int REQUEST_CODE_LOCAL = 19;

    private int maxPictureSize = 5000000;
    private Camera camera;
    private String outFile;

    private View scalePic, send, cancel, takepicture;
    private SurfaceView surfaceView;
    private ImageView preview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_haoyayi);
        ButterKnife.bind(this);
        initView(savedInstanceState);
    }

    protected void initView(Bundle savedInstanceState) {
        outFile = getIntent().getStringExtra("outFile");
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

        surfaceView = (SurfaceView) this.findViewById(R.id.surfaceView);
        surfaceView.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
//        surfaceView.getHolder().setFixedSize(DEFAULT_IMAGE_WIDTH, DEFAULT_IMAGE_HEIGHT); //设置Surface分辨率
        surfaceView.getHolder().setKeepScreenOn(true);// 屏幕常亮
        surfaceView.getHolder().addCallback(new SurfaceCallback());//为SurfaceView的句柄添加一个回调函数
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
                    surfaceView.setVisibility(View.VISIBLE);
                    preview.setVisibility(View.GONE);
                    if (camera == null) {
                        showToast("摄像头启动失败!");
                        return;
                    }
                    camera.startPreview();
                    return;
                }
                finish();
                break;
            case R.id.camera_takepicture:
                if (camera != null) {
                    // 拍照
                    camera.takePicture(null, null, new MyPictureCallback());

                    takepicture.setVisibility(View.GONE);
                    send.setVisibility(View.VISIBLE);
                    scalePic.setVisibility(View.GONE);
                    break;
                }
                break;
            case R.id.camera_send: {
                Intent intent = new Intent();
                intent.putExtra("file", outFile);
                setResult(RESULT_OK, intent);
                finish();
                break;
            }
        }

    }

    private final class MyPictureCallback implements Camera.PictureCallback {

        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            camera.stopPreview();
            saveToSDCard(data);


        }
    }

    /**
     * 将拍下来的照片存放在SD卡中
     *
     * @param data
     * @throws IOException
     */
    private void saveToSDCard(final byte[] data) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                File jpgFile;
                if (outFile == null | outFile.equals("")) {
                    Date date = new Date();
                    SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()); // 格式化时间
                    String filename = format.format(date) + ".jpg";
                    File fileFolder = new File(Environment.getExternalStorageDirectory() + "/finger/");
                    if (!fileFolder.exists()) { // 如果目录不存在，则创建一个名为"finger"的目录
                        fileFolder.mkdir();
                    }
                    jpgFile = new File(fileFolder, filename);
                } else {
                    jpgFile = new File(outFile);
                }

                FileOutputStream output = null;
                try {
                    output = new FileOutputStream(jpgFile);
                    output.write(data);
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (null != output) {
                        try {
                            output.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }


//                CameraActivity.this.runOnUiThread(new Runnable() {
//                    @Override
//                    public void execute() {
//                        Bitmap bitmap = ImageUtils.decodeScaleImage(outFile, DeviceUtils.deviceWidth(getActivity()), DeviceUtils.deviceHeight(getActivity()));
//                        preview.setImageBitmap(bitmap);
//                        enableLoading(false);
//                        preview.setVisibility(View.VISIBLE);
//                        surfaceView.setVisibility(View.GONE);
//                        scalePic.setVisibility(View.GONE);
//                        takepicture.setVisibility(View.INVISIBLE);
//                        send.setVisibility(View.VISIBLE);
//                    }
//                });
            }
        }).start();

    }

    private final class SurfaceCallback implements SurfaceHolder.Callback {

        // 拍照状态变化时调用该方法
        @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, final int width, final int height) {
            if (camera == null) {
                showToast("摄像头启动失败!");
                return;
            }

            try {
                Camera.Parameters myParam = camera.getParameters();
                myParam.setJpegQuality(100);
                myParam.setRotation(getPreviewDegree(CameraActivity.this));
                setPictureSize(myParam);
                camera.setParameters(myParam);

                //实现自动对焦
                camera.autoFocus(new Camera.AutoFocusCallback() {
                    @Override
                    public void onAutoFocus(boolean success, Camera camera) {
                        if (success) {
                            try {
                                camera.cancelAutoFocus();//只有加上了这一句，才会自动对焦。
                                camera.startPreview();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }

                });
            } catch (Exception e) {
//                FIR.sendCrashManually(e);
            }

        }

        // 开始拍照时调用该方法
        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            try {
                camera = Camera.open(); // 打开摄像头
                camera.setPreviewDisplay(holder); // 设置用于显示拍照影像的SurfaceHolder对象
                camera.setDisplayOrientation(getPreviewDegree(CameraActivity.this));
                camera.startPreview(); // 开始预览
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // 停止拍照时调用该方法
        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            if (camera != null) {
                camera.release(); // 释放照相机
                camera = null;
            }
        }
    }


    // 设置图片大小
    private void setPictureSize(Camera.Parameters parameters) {
        List<Camera.Size> sizes = parameters.getSupportedPictureSizes();
        if (sizes == null) {
            return;
        }
        int maxSize = 0;
        int width = 0;
        int height = 0;
        for (int i = 0; i < sizes.size(); i++) {
            Camera.Size size = sizes.get(i);
            int pix = size.width * size.height;
            if (pix > maxSize) {
                maxSize = pix;
                width = size.width;
                height = size.height;
            }
        }
        parameters.setPictureSize(width, height);
    }


    /**
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (camera != null) {
                    try {
                        camera.autoFocus(null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_CAMERA: // 按下拍照按钮
                if (camera != null && event.getRepeatCount() == 0) {
                    // 拍照
                    camera.takePicture(null, null, new MyPictureCallback());
                }
            case KeyEvent.KEYCODE_BACK:
                if (send.getVisibility() == View.VISIBLE) {
                    finish();
                } else {
                    cancel.performClick();
                    return false;
                }

                break;

        }

        return super.onKeyDown(keyCode, event);
    }


    // 提供一个静态方法，用于根据手机方向获得相机预览画面旋转的角度
    public static int getPreviewDegree(Activity activity) {
        // 获得手机的方向
        int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();
        int degree = 0;
        // 根据手机的方向计算相机预览画面应该选择的角度
        switch (rotation) {
            case Surface.ROTATION_0:
                degree = 90;
                break;
            case Surface.ROTATION_90:
                degree = 0;
                break;
            case Surface.ROTATION_180:
                degree = 270;
                break;
            case Surface.ROTATION_270:
                degree = 180;
                break;
        }
        return degree;
    }


    /**
     * onActivityResult
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE_LOCAL) {
                if (data != null) {
                    if (camera != null) {
                        camera.stopPreview();
                    }
                    Uri selectedImage = data.getData();
                    display(preview, selectedImage.toString());
                    preview.setVisibility(View.VISIBLE);
                    surfaceView.setVisibility(View.GONE);
                    scalePic.setVisibility(View.GONE);
                    takepicture.setVisibility(View.INVISIBLE);
                    send.setVisibility(View.VISIBLE);
                    outFile = handlePath(selectedImage);
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
        intent.setClass(context, CameraActivity.class);
        context.startActivityForResult(intent, requestCode);
    }

    private void display(ImageView view ,String path) {
        view.setImageURI(Uri.parse(path));
    }

    private void showToast(String string) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
    }
}
