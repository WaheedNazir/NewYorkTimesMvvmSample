package com.waheed.nytimes.di

import android.content.Context
import com.waheed.nytimes.BuildConfig
import com.waheed.nytimes.R
import com.waheed.nytimes.data.remote.NytAPI
import com.waheed.nytimes.repositories.DefaultArticlesRepository
import com.waheed.nytimes.repositories.ArticlesRepository
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDefaultShoppingRepository(api: NytAPI) =
        DefaultArticlesRepository(api) as ArticlesRepository

    @Singleton
    @Provides
    fun provideGlideInstance(
        @ApplicationContext context: Context
    ) = Glide.with(context).setDefaultRequestOptions(
        RequestOptions()
            .placeholder(R.drawable.ic_image)
            .error(R.drawable.ic_image)
    )

    @Singleton
    @Provides
    fun provideNytApi(): NytAPI {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .build()
            .create(NytAPI::class.java)
    }
}

















