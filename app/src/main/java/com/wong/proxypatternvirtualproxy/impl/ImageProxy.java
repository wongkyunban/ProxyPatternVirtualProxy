package com.wong.proxypatternvirtualproxy.impl;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import com.wong.proxypatternvirtualproxy.inter.Icon;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author WongKyunban
 * description
 * created at 2019-03-19 下午3:21
 * @version 1.0
 */
public class ImageProxy implements Icon {

    ImageIcon imageIcon;
    URL imageURL;
    Thread retrievalThread;
    boolean retrieving = false;
    Bitmap bitmap;//占位图
    ImageView imageView;



    private Handler refresh = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            ImageProxy.this.paintIcon(imageView);
        }
    };


    public ImageProxy(URL url,Bitmap bitmap){
        this.imageURL = url;
        this.bitmap = bitmap;

    }


    @Override
    public int getIconWidth() {
        if(imageIcon != null){
            return imageIcon.getIconWidth();
        }else{
            return 800;
        }

    }

    @Override
    public int getIconHeight() {
        if(imageIcon != null){
            return imageIcon.getIconHeight();
        }else{
            return 600;
        }
    }

    @Override
    public void paintIcon(ImageView imageView) {

        this.imageView = imageView;

        if(imageIcon != null){
            imageIcon.paintIcon(imageView);
        }else{

            imageView.setImageBitmap(bitmap);

            if(!retrieving){
                retrieving = true;
                retrievalThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if(imageURL != null){
                            try {
                                HttpURLConnection conn = (HttpURLConnection) imageURL.openConnection();
                                conn.setDoInput(true);
                                conn.connect();
                                InputStream is = conn.getInputStream();
                                Bitmap bitmap1 = BitmapFactory.decodeStream(is);
                                imageIcon = new ImageIcon(bitmap1);
                                is.close();
                                refresh.sendEmptyMessage(1);
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    }
                });
                retrievalThread.start();
            }

        }
    }
}
