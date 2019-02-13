package com.example.qd.cloud.service;

import android.app.ProgressDialog;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import android.content.Context;

import com.example.qd.cloud.bean.BaseResultBean;
import com.example.qd.cloud.interfaces.ICallBack;
import com.example.qd.cloud.utils.NetUtil;

/**
 * Created by DIY on 2017/3/31. 16:25
 * author:free宇
 * email:freexiaoyu@foxmail.com
 * describe:
 */

public class RequestCallBack<T> {
    private final static int RESULT_SUCESS_CODE=200;
    private BaseResultBean<T> baseResultBean;
    private ProgressDialog pd;

    public void RXReqeust(Context context, Observable<BaseResultBean<T>> mObservable, final ICallBack<T> callBack) {
        mObservable.subscribeOn(Schedulers.io())//请求数据的事件发生在io线程
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new io.reactivex.Observer<BaseResultBean<T>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResultBean<T> t) {
                        baseResultBean = t;
                    }

                    @Override
                    public void onError(Throwable e) {
                        //数据错误
                        callBack.onFailure("", e.getMessage(), "数据错误");
                    }

                    @Override
                    public void onComplete() {
                        if (baseResultBean != null) {
                            if (baseResultBean.getCode() == RESULT_SUCESS_CODE) {
                                //数据正确，把数据返回
                                callBack.onSuccess("", "", baseResultBean.getResult());
                            } else {
                                callBack.onFailure(String.valueOf(baseResultBean.getCode()), "", baseResultBean.getMsg());
                            }
                        } else {
                            //数据错误
                            callBack.onFailure("", "", "数据错误");
                        }

                    }
                });
    }

    public void RXReqeust(Context mContext, Observable<BaseResultBean<T>> mObservable, final ICallBack<T> callBack, Boolean boolShow, String... title) {
        if (!NetUtil.isNetworkAvailable(mContext)) {
            callBack.onFailure("", "404", "请检查网络!");
            return;
        }
        if (boolShow) {
            showDialog(mContext, title);
        }
        mObservable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new io.reactivex.Observer<BaseResultBean<T>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResultBean<T> t) {
                        baseResultBean = t;
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (pd != null) {
                            pd.dismiss();
                        }
                        if(e instanceof ResultException){
                            callBack.onFailure("", e.getMessage(), ((ResultException) e).getErrMsg());
                        }else {
                            //数据错误
                            callBack.onFailure("", e.getMessage(), "数据错误");
                        }
                    }

                    @Override
                    public void onComplete() {
                        if (pd != null) {
                            pd.dismiss();
                        }
                        if (baseResultBean != null) {
                            if (baseResultBean.getCode()== RESULT_SUCESS_CODE) {
                                //数据正确，把数据返回/
                                callBack.onSuccess(baseResultBean.getCode()+""
                                        , baseResultBean.getMsg(), baseResultBean.getResult());
                            } else {
                                callBack.onFailure(baseResultBean.getCode()+""
                                        ,  baseResultBean.getMsg(), baseResultBean.getMsg());
                            }
                        } else {
                            //数据错误
                            callBack.onFailure("", "", "数据错误");
                        }

                    }
                });
    }


    private void showDialog(Context mContext, String... title) {
       try {
           if (pd == null) {
               pd = new ProgressDialog(mContext);
           }
           if (title.length == 0) {
               pd.setMessage("正在加载请稍后...");
           } else {
               pd.setMessage(title[0] + "请稍后...");
           }
           if (!pd.isShowing()) {
               pd.show();
           }
       }catch (Exception ex){

       }
    }

}
