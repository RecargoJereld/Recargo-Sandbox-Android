package com.recargo.recargosandbox.data.api;

import com.google.gson.Gson;
import com.recargo.recargosandbox.BuildConfig;
import com.recargo.recargosandbox.data.NetworkModule;
import com.recargo.recargosandbox.util.ApplicationScope;

import javax.inject.Named;

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
    @ApplicationScope
    Gson provideGson() {
        return new Gson();
    }

    @Provides
    @ApplicationScope
    Retrofit provideRetrofit(Gson gson,
                             @Named("plugshare authenticated client") OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.PLUGSHARE_BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    @Provides
    @ApplicationScope
    PlugShareApiService providePlugShareApiService(Retrofit retrofit) {
        return retrofit.create(PlugShareApiService.class);
    }
}
