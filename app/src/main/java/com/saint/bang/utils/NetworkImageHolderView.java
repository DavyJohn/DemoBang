package com.saint.bang.utils;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.saint.bang.R;

/**
 * Created by Sai on 15/8/4.
 * 网络图片加载例子
 */
public class NetworkImageHolderView implements Holder<String> {
    private ImageView imageView;
    @Override
    public View createView(Context context) {
        //你可以通过layout文件来创建，也可以像我一样用代码创建，不一定是Image，任何控件都可以进行翻页
        imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return imageView;
    }
    /**
     * @param data 为图片的imageurl*/

    @Override
    public void UpdateUI(Context context,int position, String data) {
        //不能正常显示的图片
        imageView.setImageResource(R.drawable.ic_default_adimage);

//        Log.e("NetworkImageHolderView", "UpdateUI中" + "postion==============>" + position + "===========data=================>" + data);
        ImageLoader.getInstance().displayImage(data,imageView);
    }
}
