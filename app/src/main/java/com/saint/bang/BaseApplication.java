package com.saint.bang;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.saint.bang.utils.Constant;
import com.tencent.bugly.crashreport.CrashReport;

import java.util.ArrayList;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by yyx on 16/1/5.
 */
public class BaseApplication extends Application {

    private Context context;
    private ImageLoader imageLoader=ImageLoader.getInstance();
    /**
     * Activity集合
     */
    private static ArrayList<BaseActivity> activitys = new ArrayList<BaseActivity>();

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        JPushInterface.setDebugMode(Constant.SystemConstant.ISDEBUG);
        JPushInterface.init(context);
        CrashReport.initCrashReport(context, "900018342", false);
        initImageLoader();
        getDisplayOptions();
    }

    public Context getAppContext(){
        return context;
    }

    /**
     * 添加Activity到ArrayList<Activity>管理集合
     * @param activity
     */
    public void addActivity(BaseActivity activity) {
        String className = activity.getClass().getName();
        for (Activity at : activitys) {
            if (className.equals(at.getClass().getName())) {
                activitys.remove(at);
                break;
            }
        }
        activitys.add(activity);
    }

    /**
     * 退出应用程序的时候，手动调用
     */
    @Override
    public void onTerminate() {
        super.onTerminate();
        for (BaseActivity activity : activitys) {
            activity.defaultFinish();
        }
    }
    public Handler handler=null;

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    private void initImageLoader(){
//        File cacheDir = StorageUtils.getCacheDirectory(context);
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .memoryCacheExtraOptions(480, 800) // default = device screen dimensions保存的每个缓存文件的最大长宽
                .diskCacheExtraOptions(480, 800, null)//保存硬盘的最大长宽
                .taskExecutor(null)
                .taskExecutorForCachedImages(null)
                .threadPoolSize(3) // default 限制数量
                .threadPriority(Thread.NORM_PRIORITY - 2) // default
                .tasksProcessingOrder(QueueProcessingType.FIFO) // default
                .denyCacheImageMultipleSizesInMemory()
//                .memoryCache(new LruMemoryCache(2 * 1024 * 1024))
                .memoryCacheSize(2 * 1024 * 1024)//内存的最大大小
                .memoryCacheSizePercentage(13) // default 硬盘最大的缓存数
//                .diskCache(new UnlimitedDiskCache(cacheDir)) // default
                .diskCacheSize(50 * 1024 * 1024)
//                .diskCacheFileCount(100)//缓存文件的数量
//                .diskCacheFileNameGenerator(new HashCodeFileNameGenerator()) // default
                .imageDownloader(new BaseImageDownloader(context)) // default
//                .imageDecoder(new BaseImageDecoder()) // default
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple()) // default
                .writeDebugLogs()
                .build();
        imageLoader.getInstance().init(config);

    }
    public DisplayImageOptions getDisplayOptions(){

        DisplayImageOptions options;
        options = new DisplayImageOptions.Builder()
//                .showImageOnLoading(R.drawable.ic_launcher) //设置图片在下载期间显示的图片
//                .showImageForEmptyUri(R.drawable.ic_launcher)//设置图片Uri为空或是错误的时候显示的图片
//                .showImageOnFail(R.drawable.ic_launcher)  //设置图片加载/解码过程中错误时候显示的图片
                .cacheInMemory(true)//设置下载的图片是否缓存在内存中
                .cacheOnDisc(true)//设置下载的图片是否缓存在SD卡中
                .considerExifParams(true)  //是否考虑JPEG图像EXIF参数（旋转，翻转）
                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)//设置图片以如何的编码方式显示
                .bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型//
                        // .decodingOptions(android.graphics.BitmapFactory.Options decodingOptions)//设置图片的解码配置
                        //.delayBeforeLoading(int delayInMillis)//int delayInMillis为你设置的下载前的延迟时间
                        //设置图片加入缓存前，对bitmap进行设置
                        //.preProcessor(BitmapProcessor preProcessor)
                .resetViewBeforeLoading(true)//设置图片在下载前是否重置，复位
                .displayer(new RoundedBitmapDisplayer(20))//是否设置为圆角，弧度为多少
                .displayer(new FadeInBitmapDisplayer(100))//是否图片加载好后渐入的动画时间
                .build();//构建完成
        return options;

    }

}
