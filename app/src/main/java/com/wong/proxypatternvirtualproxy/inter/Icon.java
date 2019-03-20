package com.wong.proxypatternvirtualproxy.inter;

import android.widget.ImageView;

/**
 * @author WongKyunban
 * description
 * created at 2019-03-19 下午3:19
 * @version 1.0
 */
public interface Icon {
    int getIconWidth();
    int getIconHeight();
    void paintIcon(ImageView imageView);
}
