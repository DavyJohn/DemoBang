package com.saint.bang.fargment;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ToxicBakery.viewpager.transforms.ABaseTransformer;
import com.ToxicBakery.viewpager.transforms.AccordionTransformer;
import com.ToxicBakery.viewpager.transforms.BackgroundToForegroundTransformer;
import com.ToxicBakery.viewpager.transforms.CubeInTransformer;
import com.ToxicBakery.viewpager.transforms.CubeOutTransformer;
import com.ToxicBakery.viewpager.transforms.DefaultTransformer;
import com.ToxicBakery.viewpager.transforms.DepthPageTransformer;
import com.ToxicBakery.viewpager.transforms.FlipHorizontalTransformer;
import com.ToxicBakery.viewpager.transforms.FlipVerticalTransformer;
import com.ToxicBakery.viewpager.transforms.ForegroundToBackgroundTransformer;
import com.ToxicBakery.viewpager.transforms.RotateDownTransformer;
import com.ToxicBakery.viewpager.transforms.RotateUpTransformer;
import com.ToxicBakery.viewpager.transforms.StackTransformer;
import com.ToxicBakery.viewpager.transforms.ZoomInTransformer;
import com.ToxicBakery.viewpager.transforms.ZoomOutTranformer;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.saint.bang.R;
import com.saint.bang.utils.Constant;
import com.saint.bang.utils.NetworkImageHolderView;
import com.saint.bang.utils.ZListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zzh on 16-1-9.
 */
public class HomeFragment extends Fragment implements ViewPager.OnPageChangeListener,OnItemClickListener {

    private ZListView zListView;
    private ConvenientBanner convenientBanner;
    private TextView bannertext;
    private List<Map<String,Object>> list=new ArrayList<>();
    private List<String> networkImages;
    private String[] images = {"http://img2.imgtn.bdimg.com/it/u=3093785514,1341050958&fm=21&gp=0.jpg",
            "http://img2.3lian.com/2014/f2/37/d/40.jpg",
            "http://d.3987.com/sqmy_131219/001.jpg",
            "http://img5.imgtn.bdimg.com/it/u=2292555668,1147946895&fm=21&gp=0.jpg",
            "http://img2.3lian.com/2014/f2/37/d/39.jpg",
            "http://www.8kmm.com/UploadFiles/2012/8/201208140920132659.jpg",
            "http://f.hiphotos.baidu.com/image/h%3D200/sign=1478eb74d5a20cf45990f9df460b4b0c/d058ccbf6c81800a5422e5fdb43533fa838b4779.jpg",
            "http://f.hiphotos.baidu.com/image/pic/item/09fa513d269759ee50f1971ab6fb43166c22dfba.jpg"
    };
    private ListView listView;
    private DepthPageTransformer depthPageTransformer;
    private ArrayAdapter transformerArrayAdapter;
    private ArrayList<String> transformerList = new ArrayList<String>();
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //更新主UI

        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.home_layout, container, false);

        zListView= (ZListView) view.findViewById(R.id.zlistview);
        zListView.setPullLoadEnable(true);

        View header=LayoutInflater.from(getActivity()).inflate(R.layout.banner, null);
        convenientBanner= (ConvenientBanner) header.findViewById(R.id.convenientBanner);
        bannertext= (TextView) header.findViewById(R.id.bannertext);
        listView= (ListView) header.findViewById(R.id.listView);
        transformerArrayAdapter = new ArrayAdapter(getActivity(),R.layout.adapter_transformer,transformerList);
        listView.setAdapter(transformerArrayAdapter);
        init();
        zListView.setXListViewListener(new ZListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                    //刷新
                        Log.e("key","下拉刷新");
                        onLoad();
                    }
                },2000);
            }

            @Override
            public void onLoadMore() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                    //加载更多
                        onLoad();
                    }
                },2000);

            }
        });


        for (int i=0;i<20;i++){
            Map<String,Object> map=new HashMap<>();
            map.put("key","第"+i+"行");
            list.add(map);
        }
        ArrayAdapter arrayAdapter=new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,list);
        zListView.addHeaderView(header);
        zListView.setAdapter(arrayAdapter);
        bannertext= (TextView) header.findViewById(R.id.bannertext);


        return view;
    }

    private void init(){
//        initImageLoader();
        loadTestDatas(Constant.NUM);

        //本地图片例子
//        convenientBanner.setPages(
//                new CBViewHolderCreator<LocalImageHolderView>() {
//                    @Override
//                    public LocalImageHolderView createHolder() {
//                        return new LocalImageHolderView();
//                    }
//                }, localImages)
//                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
//                .setPageIndicator(new int[]{R.drawable.ic_page_indicator, R.drawable.ic_page_indicator_focused})
//                        //设置指示器的方向
//                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT)
//                .setOnPageChangeListener(this)//监听翻页事件
//                .setOnItemClickListener(this);
//
//        convenientBanner.setManualPageable(true);//设置不能手动影响

        //网络加载例子
        networkImages= Arrays.asList(images);
        convenientBanner.setPages(new CBViewHolderCreator<NetworkImageHolderView>() {
            @Override
            public NetworkImageHolderView createHolder() {
                return new NetworkImageHolderView();
            }
        }, networkImages)
                .setPageIndicator(new int[]{R.drawable.ic_page_indicator, R.drawable.ic_page_indicator_focused})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT)
                .setOnPageChangeListener(this)
                .setOnItemClickListener(this);
        convenientBanner.setManualPageable(true);



//手动New并且添加到ListView Header的例子
//        ConvenientBanner mConvenientBanner = new ConvenientBanner(this,false);
//        mConvenientBanner.setMinimumHeight(500);
//        mConvenientBanner.setPages(
//                new CBViewHolderCreator<LocalImageHolderView>() {
//                    @Override
//                    public LocalImageHolderView createHolder() {
//                        return new LocalImageHolderView();
//                    }
//                }, localImages)
//                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
//                .setPageIndicator(new int[]{R.drawable.ic_page_indicator, R.drawable.ic_page_indicator_focused})
//                        //设置指示器的方向
//                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT)
//                .setOnItemClickListener(this);
//        listView.addHeaderView(mConvenientBanner);
    }

    private void onLoad(){
        SimpleDateFormat dateFormat=new SimpleDateFormat("HH:mm:ss");
        String time=dateFormat.format(new java.util.Date());
        zListView.stopRefresh();
        zListView.stopLoadMore();
        zListView.setRefreshTime(time);
    }
    /*
加入测试Views
* */
    private void loadTestDatas(int index) {
//        //各种翻页效果
        switch (index){
            case 0:
                transformerList.add(DefaultTransformer.class.getSimpleName());
                break;
            case 1:
                transformerList.add(AccordionTransformer.class.getSimpleName());
                break;
            case 2:
                transformerList.add(BackgroundToForegroundTransformer.class.getSimpleName());
                break;
            case 3:
                transformerList.add(CubeInTransformer.class.getSimpleName());
                break;
            case 4:
                transformerList.add(CubeOutTransformer.class.getSimpleName());
                break;
            case 5:
                transformerList.add(DepthPageTransformer.class.getSimpleName());
                break;
            case 6:
                transformerList.add(FlipHorizontalTransformer.class.getSimpleName());
                break;
            case 7:
                transformerList.add(FlipVerticalTransformer.class.getSimpleName());
                break;
            case 8:
                transformerList.add(ForegroundToBackgroundTransformer.class.getSimpleName());
                break;
            case 9:
                transformerList.add(RotateDownTransformer.class.getSimpleName());
                break;
            case 10:
                transformerList.add(RotateUpTransformer.class.getSimpleName());
                break;
            case 11:
                transformerList.add(StackTransformer.class.getSimpleName());
                break;
            case 12:
                transformerList.add(ZoomInTransformer.class.getSimpleName());
                break;
            case 13:
                transformerList.add(ZoomOutTranformer.class.getSimpleName());
                break;
        }
        transformerArrayAdapter.notifyDataSetChanged();
        System.out.print(transformerList);
        String transforemerName = transformerList.get(0);
//        String transforemerName=StackTransformer.class.getSimpleName();
        try {
            //加载指定的类
            Class cls = Class.forName("com.ToxicBakery.viewpager.transforms." + transforemerName);
            //实例化transforemer
            ABaseTransformer transforemer= (ABaseTransformer)cls.newInstance();
            convenientBanner.getViewPager().setPageTransformer(true, transforemer);

            //部分3D特效需要调整滑动速度
            if(transforemerName.equals("StackTransformer")){
                convenientBanner.setScrollDuration(1200);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

//    public static int getResId(String variableName, Class<?> c) {
//        try {
//            Field idField = c.getDeclaredField(variableName);
//            return idField.getInt(idField);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return -1;
//        }
//
//    }
    // 开始自动翻页

    @Override
    public void onResume() {
        super.onResume();
        //开始自动翻页
        convenientBanner.startTurning(2000);
    }

    // 停止自动翻页
    //暂停时候调用
    @Override
    public void onPause() {
        super.onPause();
        //停止翻页
        convenientBanner.stopTurning();
    }

    @Override
    public void onItemClick(int position) {
        Toast.makeText(getActivity(),"点击第"+position+"页",Toast.LENGTH_SHORT).show();
        System.out.print(networkImages.get(position));
        Log.e("url:=====>","第"+position+"pager"+"===========>"+networkImages.get(position));
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
    bannertext.setText("pager" + position);
//        Toast.makeText(getActivity(),"翻到"+position+"页",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
