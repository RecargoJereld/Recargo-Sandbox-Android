package com.recargo.recargosandbox.data.api.remote;

import com.google.gson.Gson;
import com.recargo.recargosandbox.BuildConfig;
import com.recargo.recargosandbox.data.NetworkModule;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jereld on 1/11/17.
 */

@Module(includes = NetworkModule.class)
public class PlugShareApiServiceModule {
    @Provides
    @Singleton
    Gson provideGson() {
        return new Gson();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson,
                             @Named("plugshare authenticated client") OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.PLUGSHARE_BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    @Provides
    @Singleton
    PlugShareApiService providePlugShareApiService(Retrofit retrofit) {
        return retrofit.create(PlugShareApiService.class);
    }
}
