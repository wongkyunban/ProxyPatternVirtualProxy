package com.wong.proxypatternvirtualproxy;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.wong.proxypatternvirtualproxy.impl.ImageProxy;

import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    ImageProxy imageProxy;
    String url = "https://timgsa.baidu.com/timg?image&amp;quality=80&amp;size=b9999_10000&amp;sec=1552992395516&amp;di=e1c33b639de0dce8598066fcb26f9fcb&amp;imgtype=0&amp;src=http%3A%2F%2Fpic2.16pic.com%2F00%2F07%2F66%2F16pic_766177_b.jpg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView)findViewById(R.id.iv_image);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.zhanweitu);
        try {
            imageProxy = new ImageProxy(new URL(url),bitmap);
            imageProxy.paintIcon(imageView);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
