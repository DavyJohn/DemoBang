package com.saint.bang.main;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.saint.bang.BaseActivity;
import com.saint.bang.R;
import com.saint.bang.fargment.HomeFragment;
import com.saint.bang.fargment.InterrogationFargment;
import com.saint.bang.fargment.ShoppingFragment;
import com.saint.bang.fargment.UserFragment;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private HomeFragment homeFragment;
    private InterrogationFargment interrogationFargment;
    private ShoppingFragment shoppingFragment;
    private UserFragment userFragment;
    private LinearLayout home,user,shopping,interrogation;
    private TextView text_home,text_user,text_shopping,text_interrogation;
    private ImageView image_home,image_user,image_shopping,image_interrogation;
    private FragmentManager fragmentManager=getFragmentManager();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        setTabSelection(0);

        if (savedInstanceState!=null){
            homeFragment= (HomeFragment) fragmentManager.findFragmentByTag("home");
            interrogationFargment= (InterrogationFargment) fragmentManager.findFragmentByTag("interrogation");
            shoppingFragment= (ShoppingFragment) fragmentManager.findFragmentByTag("shop");
            userFragment= (UserFragment) fragmentManager.findFragmentByTag("user");
        }
    }

    private void initData(){
        home= (LinearLayout) findViewById(R.id.home);
        user= (LinearLayout) findViewById(R.id.user);
        shopping= (LinearLayout) findViewById(R.id.shopping);
        interrogation= (LinearLayout) findViewById(R.id.interrogation);

        text_home= (TextView) findViewById(R.id.text_home);
        text_interrogation= (TextView) findViewById(R.id.text_interrogation);
        text_shopping= (TextView) findViewById(R.id.text_shopping);
        text_user= (TextView) findViewById(R.id.text_user);

        image_home= (ImageView) findViewById(R.id.image_home);
        image_interrogation= (ImageView) findViewById(R.id.image_interrogation);
        image_shopping= (ImageView) findViewById(R.id.image_shopping);
        image_user= (ImageView) findViewById(R.id.image_user);


        home.setOnClickListener(this);
        user.setOnClickListener(this);
        shopping.setOnClickListener(this);
        interrogation.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.home:
                setTabSelection(0);
                break;
            case R.id.interrogation:
                setTabSelection(1);
                break;
            case R.id.shopping:
                setTabSelection(2);
                break;
            case R.id.user:
                setTabSelection(3);
                break;
        }
    }
    private void setTabSelection(int index){
        clean();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        hideFragment(transaction);
        switch (index){
            case 0:
                image_home.setImageResource(R.mipmap.grab_treasure_icon_not_selected);
                text_home.setTextColor(Color.RED);
                if (homeFragment==null){
                    Log.e("fragment===================>", "home");
                    homeFragment=new HomeFragment();
                    transaction.add(R.id.framelayout, homeFragment, "home");
                }else
                    transaction.show(homeFragment);
                break;
            case 1:
                image_interrogation.setImageResource(R.mipmap.find_the_icon_not_selected);
                text_interrogation.setTextColor(Color.RED);
                if (interrogationFargment==null){
                    Log.e("fragment===================>","home");
                    interrogationFargment=new InterrogationFargment();
                    transaction.add(R.id.framelayout,interrogationFargment,"interrogation");
                }else {
                    transaction.show(interrogationFargment);
                }
                break;
            case 2:
                image_shopping.setImageResource(R.mipmap.list_icon_not_selected);
                text_shopping.setTextColor(Color.RED);
                if (shoppingFragment==null){
                    Log.e("fragment===================>","home");
                    shoppingFragment=new ShoppingFragment();
                    transaction.add(R.id.framelayout,shoppingFragment,"shop");

                }else {
                    transaction.show(shoppingFragment);
                }

                break;
            case 3:
                image_user.setImageResource(R.mipmap.grab_treasure_icon_not_selected);
                text_user.setTextColor(Color.RED);
                if (userFragment==null){
                    Log.e("fragment===================>","home");
                    userFragment=new UserFragment();
                    transaction.add(R.id.framelayout,userFragment,"user");
                }else {
                    transaction.show(userFragment);
                }
                break;
        }
        transaction.commit();
    }
    private void clean(){
        image_user.setImageResource(R.mipmap.my_icon);
        text_user.setTextColor(Color.BLACK);

        image_shopping.setImageResource(R.mipmap.shopping_cart_icon);
        text_shopping.setTextColor(Color.BLACK);

        image_interrogation.setImageResource(R.mipmap.interrogation_icon);
        text_interrogation.setTextColor(Color.BLACK);

        image_home.setImageResource(R.mipmap.home_page_icon);
        text_home.setTextColor(Color.BLACK);
    }
    private void hideFragment(FragmentTransaction transaction){
        if (homeFragment!=null){
            transaction.hide(homeFragment);
        }
        if (interrogationFargment!=null){
            transaction.hide(interrogationFargment);
        }
        if (shoppingFragment!=null){
            transaction.hide(shoppingFragment);
        }
        if (userFragment!=null){
            transaction.hide(userFragment);
        }
    }
    @Override
    protected void setUpActionBar() {

    }


//    private ConvenientBanner convenientBanner;
//    private ZListView zListView;
//    private TextView bannertext;
//    private ArrayList<Integer> localImages = new ArrayList<Integer>();//本地image
//    private List<String> networkImages;
//    private BannerData bannerData;
//    private List<Map<String,Object>> list=new ArrayList<>();
//    private String[] images = {"http://img2.imgtn.bdimg.com/it/u=3093785514,1341050958&fm=21&gp=0.jpg",
//            "http://img2.3lian.com/2014/f2/37/d/40.jpg",
//            "http://d.3987.com/sqmy_131219/001.jpg",
//            "http://img2.3lian.com/2014/f2/37/d/39.jpg",
//            "http://www.8kmm.com/UploadFiles/2012/8/201208140920132659.jpg",
//            "http://f.hiphotos.baidu.com/image/h%3D200/sign=1478eb74d5a20cf45990f9df460b4b0c/d058ccbf6c81800a5422e5fdb43533fa838b4779.jpg",
//            "http://f.hiphotos.baidu.com/image/pic/item/09fa513d269759ee50f1971ab6fb43166c22dfba.jpg"
//    };
//
//    private ListView listView;
//    private DepthPageTransformer depthPageTransformer;
//    private ArrayAdapter transformerArrayAdapter;
//    private ArrayList<String> transformerList = new ArrayList<String>();
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        initViews();
//        init();
//
//
//
//    }
//    private void initViews() {
//        convenientBanner = (ConvenientBanner) findViewById(R.id.convenientBanner);
//        convenientBanner.setPageTransformer(depthPageTransformer);
//        bannertext= (TextView) findViewById(R.id.bannertext);
//        listView = (ListView) findViewById(R.id.listView);
//        transformerArrayAdapter = new ArrayAdapter(this,R.layout.adapter_transformer,transformerList);
//        listView.setAdapter(transformerArrayAdapter);
//        listView.setOnItemClickListener(this);
//        zListView= (ZListView) findViewById(R.id.zlistview);
//        zListView.setPullLoadEnable(true);
//        zListView.setPullRefreshEnable(true);
//        zListView.setXListViewListener(new ZListView.IXListViewListener() {
//            @Override
//            public void onRefresh() {
//            onLoad();
//            }
//
//            @Override
//            public void onLoadMore() {
//                onLoad();
//            }
//        });
////        zListView.addHeaderView(convenientBanner);
//
//        for (int i=0;i<20;i++){
//            Map<String,Object> map=new HashMap<>();
//            map.put("key","第"+i+"行");
//            list.add(map);
//        }
//        ArrayAdapter arrayAdapter=new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1,list);
//        zListView.setAdapter(arrayAdapter);
//
//
//
//    }
//
//    private void init(){
//        initImageLoader();
//        loadTestDatas(Constant.NUM);
//
//        //本地图片例子
////        convenientBanner.setPages(
////                new CBViewHolderCreator<LocalImageHolderView>() {
////                    @Override
////                    public LocalImageHolderView createHolder() {
////                        return new LocalImageHolderView();
////                    }
////                }, localImages)
////                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
////                .setPageIndicator(new int[]{R.drawable.ic_page_indicator, R.drawable.ic_page_indicator_focused})
////                        //设置指示器的方向
////                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT)
////                .setOnPageChangeListener(this)//监听翻页事件
////                .setOnItemClickListener(this);
////
////        convenientBanner.setManualPageable(true);//设置不能手动影响
//
//        //网络加载例子
//        networkImages= Arrays.asList(images);
//        convenientBanner.setPages(new CBViewHolderCreator<NetworkImageHolderView>() {
//            @Override
//            public NetworkImageHolderView createHolder() {
//                return new NetworkImageHolderView();
//            }
//        },networkImages)
//                .setPageIndicator(new int[]{R.drawable.ic_page_indicator,R.drawable.ic_page_indicator_focused})
//                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT)
//                .setOnPageChangeListener(this)
//                .setOnItemClickListener(this);
//        convenientBanner.setManualPageable(true);
//
//
//
////手动New并且添加到ListView Header的例子
////        ConvenientBanner mConvenientBanner = new ConvenientBanner(this,false);
////        mConvenientBanner.setMinimumHeight(500);
////        mConvenientBanner.setPages(
////                new CBViewHolderCreator<LocalImageHolderView>() {
////                    @Override
////                    public LocalImageHolderView createHolder() {
////                        return new LocalImageHolderView();
////                    }
////                }, localImages)
////                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
////                .setPageIndicator(new int[]{R.drawable.ic_page_indicator, R.drawable.ic_page_indicator_focused})
////                        //设置指示器的方向
////                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT)
////                .setOnItemClickListener(this);
////        listView.addHeaderView(mConvenientBanner);
//    }
//    //初始化网络图片缓存库
//    private void initImageLoader(){
//        //网络图片例子,结合常用的图片缓存库UIL,你可以根据自己需求自己换其他网络图片库
//        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder().
//                showImageForEmptyUri(R.drawable.ic_default_adimage)
//                .cacheInMemory(true).cacheOnDisk(true).build();
//
//        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
//                getApplicationContext()).defaultDisplayImageOptions(defaultOptions)
//                .threadPriority(Thread.NORM_PRIORITY - 2)
//                .denyCacheImageMultipleSizesInMemory()
//                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
//                .tasksProcessingOrder(QueueProcessingType.LIFO).build();
//        ImageLoader.getInstance().init(config);
//    }
//
//    /*
// 加入测试Views
// * */
//    private void loadTestDatas(int index) {
//        //本地图片集合
//        for (int position = 0; position < 7; position++)
//            localImages.add(getResId("ic_test_" + position, R.drawable.class));
//
//
////        //各种翻页效果
//        switch (index){
//            case 0:
//                transformerList.add(DefaultTransformer.class.getSimpleName());
//                break;
//            case 1:
//                transformerList.add(AccordionTransformer.class.getSimpleName());
//                break;
//            case 2:
//                transformerList.add(BackgroundToForegroundTransformer.class.getSimpleName());
//                break;
//            case 3:
//                transformerList.add(CubeInTransformer.class.getSimpleName());
//                break;
//            case 4:
//                transformerList.add(CubeOutTransformer.class.getSimpleName());
//                break;
//            case 5:
//                transformerList.add(DepthPageTransformer.class.getSimpleName());
//                break;
//            case 6:
//                transformerList.add(FlipHorizontalTransformer.class.getSimpleName());
//                break;
//            case 7:
//                transformerList.add(FlipVerticalTransformer.class.getSimpleName());
//                break;
//            case 8:
//                transformerList.add(ForegroundToBackgroundTransformer.class.getSimpleName());
//                break;
//            case 9:
//                transformerList.add(RotateDownTransformer.class.getSimpleName());
//                break;
//            case 10:
//                transformerList.add(RotateUpTransformer.class.getSimpleName());
//                break;
//            case 11:
//                transformerList.add(StackTransformer.class.getSimpleName());
//                break;
//            case 12:
//                transformerList.add(ZoomInTransformer.class.getSimpleName());
//                break;
//            case 13:
//                transformerList.add(ZoomOutTranformer.class.getSimpleName());
//                break;
//        }
//        transformerArrayAdapter.notifyDataSetChanged();
//        System.out.print(transformerList);
//        String transforemerName = transformerList.get(0);
////        String transforemerName=StackTransformer.class.getSimpleName();
//        try {
//            //加载指定的类
//            Class cls = Class.forName("com.ToxicBakery.viewpager.transforms." + transforemerName);
//            //实例化transforemer
//            ABaseTransformer transforemer= (ABaseTransformer)cls.newInstance();
//            convenientBanner.getViewPager().setPageTransformer(true, transforemer);
//
//            //部分3D特效需要调整滑动速度
//            if(transforemerName.equals("StackTransformer")){
//                convenientBanner.setScrollDuration(1200);
//            }
//
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//
//
//
//
////
////        transformerList.add(DefaultTransformer.class.getSimpleName());
////        transformerList.add(AccordionTransformer.class.getSimpleName());
////        transformerList.add(BackgroundToForegroundTransformer.class.getSimpleName());
////        transformerList.add(CubeInTransformer.class.getSimpleName());
////        transformerList.add(CubeOutTransformer.class.getSimpleName());
////        transformerList.add(DepthPageTransformer.class.getSimpleName());
////        transformerList.add(FlipHorizontalTransformer.class.getSimpleName());
////        transformerList.add(FlipVerticalTransformer.class.getSimpleName());
////        transformerList.add(ForegroundToBackgroundTransformer.class.getSimpleName());
////        transformerList.add(RotateDownTransformer.class.getSimpleName());
////        transformerList.add(RotateUpTransformer.class.getSimpleName());
////        transformerList.add(StackTransformer.class.getSimpleName());
////        transformerList.add(ZoomInTransformer.class.getSimpleName());
////        transformerList.add(ZoomOutTranformer.class.getSimpleName());
////        transformerArrayAdapter.notifyDataSetChanged();
//
//
//
//    }
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
//
//    // 开始自动翻页
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        //开始自动翻页
//        convenientBanner.startTurning(2000);
//    }
//
//    // 停止自动翻页
//    //暂停时候调用
//    @Override
//    protected void onPause() {
//        super.onPause();
//        //停止翻页
//        convenientBanner.stopTurning();
//    }
//
//    @Override
//    public void onItemClick(int position) {
//            Toast.makeText(this, "点击了第" + position + "个", Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
////        Log.e("page滚动参数", "当前页面" + position + "偏移百分比" + positionOffset + "偏移的像素位置" + positionOffsetPixels);
//    }
//
//    @Override
//    public void onPageSelected(int position) {
//        bannertext.setText("监听到翻到第" + position + "了");
////        Toast.makeText(this, "监听到翻到第" + position + "了", Toast.LENGTH_SHORT).show();
//
//    }
//
//    @Override
//    public void onPageScrollStateChanged(int state) {
//
//    }
//    //LISTVIEW
//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        Toast.makeText(this,"点了第"+position+"行",Toast.LENGTH_SHORT).show();
////        点击后加入两个内容
////        localImages.clear();
////        localImages.add(R.drawable.ic_test_2);
////        localImages.add(R.drawable.ic_test_4);
////        convenientBanner.notifyDataSetChanged();
////
//////        控制是否循环
////        convenientBanner.setCanLoop(!convenientBanner.isCanLoop());
//
//
////        String transforemerName = transformerList.get(position);
////        try {
////            Class cls = Class.forName("com.ToxicBakery.viewpager.transforms." + transforemerName);
////            ABaseTransformer transforemer= (ABaseTransformer)cls.newInstance();
////            convenientBanner.getViewPager().setPageTransformer(true, transforemer);
////
////            //部分3D特效需要调整滑动速度
////            if(transforemerName.equals("StackTransformer")){
////                convenientBanner.setScrollDuration(1200);
////            }
////
////        } catch (ClassNotFoundException e) {
////            e.printStackTrace();
////        } catch (InstantiationException e) {
////            e.printStackTrace();
////        } catch (IllegalAccessException e) {
////            e.printStackTrace();
////        }
//
//    }
//    @Override
//    protected void setUpActionBar() {
//
//    }
//    private void onLoad(){
//        SimpleDateFormat dateFormat=new SimpleDateFormat("HH:mm:ss");
//        String time=dateFormat.format(new java.util.Date());
//        zListView.stopRefresh();
//        zListView.stopLoadMore();
//        zListView.setRefreshTime(time);
//    }


}
