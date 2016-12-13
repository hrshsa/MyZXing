package com.example.pc.testzxing;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.uuzuche.lib_zxing.activity.CodeUtils;

/**
 * Created by HuangRuiShu on 2016/12/12.
 */
public class CreateQrActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button2;
    private Button button;
    private ImageView mQrImageView;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_code_layout);
        button = (Button) findViewById(R.id.button2);
        button2 = (Button) findViewById(R.id.button3);
        mQrImageView = (ImageView) findViewById(R.id.qr_iv);
        editText = (EditText) findViewById(R.id.edittext);
        button.setOnClickListener(this);
        button2.setOnClickListener(this);
        mQrImageView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button3:
                String content = editText.getText().toString();
                if (TextUtils.isEmpty(content))
                    return;
                editText.setText("");
                Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.aa);
                Bitmap bitmap = CodeUtils.createImage(content, mQrImageView.getWidth(), mQrImageView.getHeight(), bitmap1);
                mQrImageView.setImageBitmap(bitmap);
                break;

            case R.id.button2:
                String content1 = editText.getText().toString();
                if (TextUtils.isEmpty(content1))
                    return;
                editText.setText("");
                Bitmap bitmap2 = CodeUtils.createImage(content1, mQrImageView.getWidth(), mQrImageView.getHeight(), null);
                mQrImageView.setImageBitmap(bitmap2);
                break;

            case R.id.qr_iv:
                break;
        }
    }
}
