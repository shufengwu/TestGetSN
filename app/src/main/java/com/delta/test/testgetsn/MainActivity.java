package com.delta.test.testgetsn;

import android.content.Context;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    TextView tv1;
    TextView tv2;
    TextView tv3;
    TextView tv4;
    TextView tv5;
    TextView tv6;
    TextView tv7;
    TextView tv8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("获取唯一标识码常用方法");
        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);
        tv4 = (TextView) findViewById(R.id.tv4);
        tv5 = (TextView) findViewById(R.id.tv5);
        tv6 = (TextView) findViewById(R.id.tv6);
        tv7 = (TextView) findViewById(R.id.tv7);
        tv8 = (TextView) findViewById(R.id.tv8);
        String[] propertys = {"ro.boot.serialno", "ro.serialno"};
        tv1.setText("1: " + getAndroidOsSystemProperties(propertys[0]));
        tv2.setText("2: " + getAndroidOsSystemProperties(propertys[1]));
        tv3.setText("3: " + android.os.Build.SERIAL);
        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String imei = tm.getDeviceId();
        if (!TextUtils.isEmpty(imei)) {
            tv4.setText("4: " + imei);
        }
        tv5.setText("5: " + "35"
                + Build.BOARD.length()%10
                + Build.BRAND.length()%10
                + Build.CPU_ABI.length()%10
                + Build.DEVICE.length()%10
                + Build.DISPLAY.length()%10
                + Build.HOST.length()%10
                + Build.ID.length()%10
                + Build.MANUFACTURER.length()%10
                + Build.MODEL.length()%10
                + Build.PRODUCT.length()%10
                + Build.TAGS.length()%10
                + Build.TYPE.length()%10
                + Build.USER.length()%10);

    }

    static Method systemProperties_get = null;

    static String getAndroidOsSystemProperties(String key) {
        String ret;
        try {
            systemProperties_get = Class.forName("android.os.SystemProperties").getMethod("get", String.class);
            if ((ret = (String) systemProperties_get.invoke(null, key)) != null)
                return ret;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return "";
    }


}
