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
        image_user.setImageResource(R.mipmap.home_page_icon);
        text_user.setTextColor(Color.BLACK);

        image_shopping.setImageResource(R.mipmap.shopping_cart_icon);
        text_shopping.setTextColor(Color.BLACK);

        image_interrogation.setImageResource(R.mipmap.interrogation_icon);
        text_interrogation.setTextColor(Color.BLACK);

        image_home.setImageResource(R.mipmap.my_icon);
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



}
