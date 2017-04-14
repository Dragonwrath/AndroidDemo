package android.marshon.approotdemo.dialog;

import android.content.DialogInterface;
import android.marshon.approotdemo.R;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class DialogActivity extends AppCompatActivity {

    private static final String TAG = "DialogActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
    }

    public void pop(View view) {
        AlertDialog dialog = new AlertDialog.Builder(this)
            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(DialogActivity.this, "确定", Toast.LENGTH_SHORT).show();
                }
            })
            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(DialogActivity.this, "取消", Toast.LENGTH_SHORT).show();
                }
            })
            .setTitle("输出对话框")
            .setView(R.layout.activity_main)
            .create();
        dialog.show();

        View currentFocus = dialog.getCurrentFocus();
        if (currentFocus !=null) {
            int width = currentFocus.getMeasuredWidth();
            int height = currentFocus.getMeasuredHeight();
            Log.e(TAG, "pop: ------>" + width + "-------------" + height);
        }
    }
}
