package com.deshine.huishu.app.http;


import android.content.Context;
import android.text.TextUtils;

import com.deshine.huishu.app.app.AppApplication;
import com.deshine.huishu.app.app.AppConstant;
import com.deshine.huishu.app.cameralib.util.LogUtil;
import com.deshine.huishu.app.http.cookie.SimpleCookieJar;
import com.deshine.huishu.app.http.https.HttpsUtils;
import com.deshine.huishu.app.http.interceptor.HttpCommonInterceptor;
import com.deshine.huishu.app.utils.CollectionUtils;
import com.deshine.huishu.app.utils.GesonUtil;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retofit网络请求工具类
 * Created by HDL on 2016/7/29.
 */
public class CommonRetrofitClientUtil {
    private static final int READ_TIMEOUT = 1000;//读取超时时间,单位  秒
    private static final int WRITE_TIMEOUT = 1000;//读取超时时间,单位  秒
    private static final int CONN_TIMEOUT = 1000;//连接超时时间,单位  秒
    private static HttpLoggingInterceptor loggingInterceptor;
    private static OkHttpClient okHttpClient;
    private static Retrofit mRetrofit;

    //静态块,类加载时初始化OkHttpClient对象
    static {
        initOkHttpClient();
    }

    private CommonRetrofitClientUtil() {

    }
    public static OkHttpClient getOkHttpClient(){
        return okHttpClient;
    }
    public static Retrofit newInstence(String url) {
        mRetrofit = null;
        mRetrofit = new Retrofit.Builder()
                .client(okHttpClient)//添加一个client,不然retrofit会自己默认添加一个
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(GesonUtil.getGson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return mRetrofit;
    }
    /**
     *  获取单例OkHttpClient
     *
     * @return
     */
    @SuppressWarnings("deprecated")
    private static OkHttpClient initOkHttpClient() {
        //添加公共参数拦截器
        HttpCommonInterceptor interceptor = new HttpCommonInterceptor.Builder()
                .build();

        //日志
        loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                //打印retrofit日志
                LogUtil.i("retrofitBack = " + message);
            }
        });
        //日志等级
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        if (okHttpClient == null) {
            synchronized (OkHttpClient.class) {
                if (okHttpClient == null) {
                    okHttpClient = new OkHttpClient.Builder()
                    .hostnameVerifier(new HostnameVerifier() {
                        @Override
                        public boolean verify(String hostname, SSLSession session) {
                            return true;
                        }
                    })
                    //为所有请求添加请求头
                    .addInterceptor(interceptor)
                    //打印拦截器日志
                    .addNetworkInterceptor(loggingInterceptor)
                    .cookieJar(new SimpleCookieJar())
                    //设置连接超时时间
                    .connectTimeout(CONN_TIMEOUT, TimeUnit.SECONDS)
                    //设置读取超时时间
                    .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                    // 设置写入超时时间
                    .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                    .followRedirects(true)
                    /**
                     * trust all the https point
                     */
                    .sslSocketFactory(HttpsUtils.initSSLSocketFactory(),
                            HttpsUtils.initTrustManager())
                    .build();
                }
            }
        }
        return okHttpClient;
    }

}
