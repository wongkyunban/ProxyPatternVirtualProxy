package com.wong.proxypatternvirtualproxy.impl;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.wong.proxypatternvirtualproxy.inter.Icon;

/**
 * @author WongKyunban
 * description
 * created at 2019-03-19 下午3:20
 * @version 1.0
 */
public class ImageIcon implements Icon {
    Bitmap bitmap;

    public ImageIcon(Bitmap bitmap) {
        this.bitmap = bitmap;
    }


    @Override
    public int getIconWidth() {
        if (bitmap != null) {
            return bitmap.getWidth();
        }
        return 0;
    }

    @Override
    public int getIconHeight() {
        if (bitmap != null) {
            return bitmap.getHeight();
        }
        return 0;
    }

    @Override
    public void paintIcon(ImageView imageView) {

        if (imageView != null && bitmap != null) {
            imageView.setImageBitmap(bitmap);
        }
    }
}
