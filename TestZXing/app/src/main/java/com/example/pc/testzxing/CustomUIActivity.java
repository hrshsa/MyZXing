package com.example.pc.testzxing;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.uuzuche.lib_zxing.activity.CaptureFragment;
import com.uuzuche.lib_zxing.activity.CodeUtils;

/**
 * Created by HuangRuiShu on 2016/12/12.
 *
 * <declare-styleable name="innerrect">
 <attr name="inner_width" format="dimension"/><!-- 控制扫描框的宽度 -->
 <attr name="inner_height" format="dimension"/><!-- 控制扫描框的高度 -->
 <attr name="inner_margintop" format="dimension" /><!-- 控制扫描框距离顶部的距离 -->
 <attr name="inner_corner_color" format="color" /><!-- 控制扫描框四角的颜色 -->
 <attr name="inner_corner_length" format="dimension" /><!-- 控制扫描框四角的长度 -->
 <attr name="inner_corner_width" format="dimension" /><!-- 控制扫描框四角的宽度 -->
 <attr name="inner_scan_bitmap" format="reference" /><!-- 控制扫描图 -->
 <attr name="inner_scan_speed" format="integer" /><!-- 控制扫描速度 -->
 <attr name="inner_scan_iscircle format="false""><!-- 控制小圆点是否展示 -->
 </declare-styleable>
 */
public class CustomUIActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_layout);
        CaptureFragment fragment = new CaptureFragment();
        fragment.setAnalyzeCallback(callback);
        CodeUtils.setFragmentArgs(fragment,R.layout.my_camera);
        getSupportFragmentManager().beginTransaction().
                replace(R.id.container, fragment).commitAllowingStateLoss();

    }

    CodeUtils.AnalyzeCallback callback = new CodeUtils.AnalyzeCallback() {
        @Override
        public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
            Intent resultIntent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putInt(CodeUtils.RESULT_TYPE, CodeUtils.RESULT_SUCCESS);
            bundle.putString(CodeUtils.RESULT_STRING, result);
            resultIntent.putExtras(bundle);
            CustomUIActivity.this.setResult(RESULT_OK, resultIntent);
            CustomUIActivity.this.finish();
        }

        @Override
        public void onAnalyzeFailed() {
            Intent resultIntent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putInt(CodeUtils.RESULT_TYPE, CodeUtils.RESULT_FAILED);
            bundle.putString(CodeUtils.RESULT_STRING, "");
            resultIntent.putExtras(bundle);
            CustomUIActivity.this.setResult(RESULT_OK, resultIntent);
            CustomUIActivity.this.finish();
        }
    };
}
