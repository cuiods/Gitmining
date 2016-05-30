package edu.nju.common.json;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * Created by Harry on 2016/5/30.
 */
public class GithubStatsInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        Response response = chain.proceed(request);
        while (response.code() == 202){ //github is computing, retry again later!!!!!!
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            response = chain.proceed(request);
        }

        return response;
    }
}
