package com.saint.bang;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;



import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by yyx on 16/1/5.
 */
public abstract class BaseActivity extends AppCompatActivity{

    /**
     * LOG打印标签
     */
    private static final String TAG = BaseActivity.class.getSimpleName();

    /**
     * 全局的Context
     */
    protected Context mContext;

    protected BaseApplication baseApplication;

    private SweetAlertDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        baseApplication = (BaseApplication)this.getApplication();
        mContext = baseApplication.getAppContext();
        baseApplication.addActivity(this);
        progressDialog = new SweetAlertDialog(this,SweetAlertDialog.PROGRESS_TYPE);
        initActionBar();
    }

    /**
     * 初始action bar
     */
    protected void initActionBar(){
        setUpActionBar();
    }

    /**
     * 初始化action bar组件
     */
    protected abstract void setUpActionBar();

    /**
     * 获取全局的Context
     */
    public Context getContext() {
        return mContext;
    }

    /**
     * Debug输出Log信息
     * @param msg
     */
    protected void debugLog(String msg) {
        Log.d(TAG, msg);
    }

    /**
     * Error输出Log信息
     * @param msg
     */
    protected void errorLog(String msg) {
        Log.e(TAG, msg);
    }

    /**
     * Info输出Log信息
     * @param msg
     */
    protected void showLog(String msg) {
        Log.i(TAG, msg);
    }

    /**
     * 长时间显示Toast提示(来自String)
     * @param message
     */
    protected void showToast(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();
    }

    /**
     * 长时间显示Toast提示(来自res)
     * @param resId
     */
    protected void showToast(int resId) {
        Toast.makeText(mContext, getString(resId), Toast.LENGTH_LONG).show();
    }

    /**
     * 短暂显示Toast提示(来自res)
     * @param resId
     */
    protected void showShortToast(int resId) {
        Toast.makeText(mContext, getString(resId), Toast.LENGTH_SHORT).show();
    }

    /**
     * 短暂显示Toast提示(来自String)
     * @param text
     */
    protected void showShortToast(String text) {
        Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show();
    }


    /**
     * 消息提示对话框
     * @param title
     * @param msg
     */
    protected void showInfoDialog(String title,String msg){
        new SweetAlertDialog(mContext)
                .setTitleText(title)
                .setContentText(msg)
                .show();
    }

    /**
     * 显示警告对话框
     * @param title
     * @param msg
     */
    protected void showWarnDialog(String title,String msg){
        new SweetAlertDialog(mContext, SweetAlertDialog.WARNING_TYPE)
                .setTitleText(title)
                .setContentText(msg)
                .setConfirmText(getResources().getString(R.string.warn_dialog_btn_tex))
                .show();
    }

    /**
     * 显示出错对话框
     * @param title
     * @param msg
     */
    protected void showErrorDialog(String title,String msg){
        new SweetAlertDialog(mContext, SweetAlertDialog.ERROR_TYPE)
                .setTitleText(title)
                .setContentText(msg)
                .show();
    }

    /**
     * 显示成功对话框
     * @param title
     * @param msg
     */
    protected void showSuccessDialog(String title,String msg){
        new SweetAlertDialog(mContext, SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText(title)
                .setContentText(msg)
                .show();
    }

    /**
     * 对话框进度条显示
     * @param msg
     */
    public void showProgressMsg(String msg){
        progressDialog.getProgressHelper().setBarColor(R.color.progressBarColor);
        progressDialog.setTitleText(msg);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    /**
     * 隐藏进度条对话框
     */
    public void hideProgressMsg(){
        if(progressDialog != null){
            progressDialog.hide();
        }
    }

    /**
     * 通过Class跳转界面
     **/
    public void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 通过Action跳转界面
     **/
    public void startActivity(String action) {
        startActivity(action, null);
    }

    /**
     * 含有Bundle通过Action跳转界面
     **/
    public void startActivity(String action, Bundle bundle) {
        Intent intent = new Intent();
        intent.setAction(action);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 含有Bundle通过Class打开编辑界面
     **/
    public void startActivityForResult(Class<?> cls, Bundle bundle, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }




    /**
     * 带有右进右出动画的退出
     */
    @Override
    public void finish() {
        super.finish();
    }

    /**
     * 默认退出
     */
    public void defaultFinish() {
        super.finish();
    }
}
