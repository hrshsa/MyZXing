package com.example.pc.testzxing;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.uuzuche.lib_zxing.camera.BitmapLuminanceSource;
import com.uuzuche.lib_zxing.decoding.DecodeFormatManager;

import java.util.Hashtable;
import java.util.Vector;

/**
 * Created by HuangRuiShu on 2016/12/12.
 */
public class SecActivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qr_container);
        imageView = (ImageView) findViewById(R.id.qr_image);
        imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Bitmap bitmap = ((BitmapDrawable) (imageView).getDrawable()).getBitmap();
                MultiFormatReader multiFormatReader = new MultiFormatReader();

                // 解码的参数
                Hashtable<DecodeHintType, Object> hints = new Hashtable<DecodeHintType, Object>(2);
                // 可以解析的编码类型
                Vector<BarcodeFormat> decodeFormats = new Vector<BarcodeFormat>();
                if (decodeFormats == null || decodeFormats.isEmpty()) {
                    decodeFormats = new Vector<BarcodeFormat>();

                    // 这里设置可扫描的类型，我这里选择了都支持
                    decodeFormats.addAll(DecodeFormatManager.ONE_D_FORMATS);
                    decodeFormats.addAll(DecodeFormatManager.QR_CODE_FORMATS);
                    decodeFormats.addAll(DecodeFormatManager.DATA_MATRIX_FORMATS);
                }
                hints.put(DecodeHintType.POSSIBLE_FORMATS, decodeFormats);
                // 设置继续的字符编码格式为UTF8
                // hints.put(DecodeHintType.CHARACTER_SET, "UTF8");
                // 设置解析配置参数
                multiFormatReader.setHints(hints);

                // 开始对图像资源解码
                Result rawResult = null;
                try {
                    rawResult = multiFormatReader.decodeWithState(new BinaryBitmap(new HybridBinarizer(new BitmapLuminanceSource(bitmap))));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (rawResult != null) {
                    Toast.makeText(SecActivity.this, "result is " + rawResult.getText(), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(SecActivity.this, "result is null", Toast.LENGTH_LONG).show();
                }
                return false;
            }
        });
    }
}
