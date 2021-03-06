package com.recargo.recargosandbox.data;

import android.content.Context;

import java.io.File;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by jereld on 1/11/17.
 */
@Singleton
@Module
public class NetworkModule {

    @Provides
    File provideCacheFile(@Named("Application Context") Context context) {
        return new File(context.getCacheDir(), "okhttp_cache");
    }

    @Provides
    Cache provideCache(File cacheFile) {
        int cacheSize = 10 * 1024 * 1024;

        return new Cache(cacheFile, cacheSize);
    }
    @Provides
    @Named("plugshare authenticated client")
    OkHttpClient provideOkHttpClient(Cache cache) {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

        return new OkHttpClient.Builder()
                .authenticator((route, response) -> {
                    String credentials = Credentials.basic("PLUGSHARE_CONSUMER", "PLUGSHARE_KEY");
                    return response.request().newBuilder()
                            .addHeader("Authorization", credentials)
                            .build();
                })
                .cache(cache)
                .addInterceptor(loggingInterceptor)
                .build();
    }
}
