package com.joke.media_camera;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.joke.media_camera.camera.haoyayi.CameraActivity;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView mText;
    private TextView mText2;
    private ImageView mImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mText = (TextView) findViewById(R.id.text);
        mText2 = (TextView) findViewById(R.id.text2);
        mImage = (ImageView)findViewById(R.id.image);
//
//        Intent intent = new Intent(this, CameraActivity.class);
//        File file = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "haoyayi");
//        file.mkdirs();
//        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault()); // 格式化时间
//        String filename = format.format(new Date()) + ".jpg";
//        File outFile = new File(file, filename);
//        outFile.mkdirs();
//        intent.putExtra("outFile",outFile.getAbsolutePath());
//        intent.putExtra("cameraTip","Tips:Capture A New Photo");
//        startActivityForResult(intent,1);


//        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//        intent.setType("image/*");
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(intent,1);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Uri uri = data.getData();
            File file = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
            if (file != null)
                mText.setText(file.toString());
            else
                mText.setText(uri.toString());
            mText2.setText(handlePath(uri));
            mImage.setImageURI(uri);
        }
    }

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
}
