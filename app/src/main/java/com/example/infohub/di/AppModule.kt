package com.example.infohub.di
import android.app.Application
import androidx.room.Room
import com.example.infohub.data.local.NewsDao
import com.example.infohub.data.local.NewsDatabase
import com.example.infohub.data.local.NewsTypeConvertor
import com.example.infohub.data.manager.LocaluserManagerImpl
import com.example.infohub.data.remote.dto.NewsApi
import com.example.infohub.data.repository.NewsRepositoryImpl
import com.example.infohub.domain.manager.localusermanager
import com.example.infohub.domain.repository.NewsRepository
import com.example.infohub.domain.usecases.app_entry.AppEntryUsecases
import com.example.infohub.domain.usecases.app_entry.ReadAppEntry
import com.example.infohub.domain.usecases.app_entry.SaveAppEntry
import com.example.infohub.domain.usecases.news.DeleteArticle
import com.example.infohub.domain.usecases.news.GetNews
import com.example.infohub.domain.usecases.news.NewsUseCases
import com.example.infohub.domain.usecases.news.SearchNews
import com.example.infohub.domain.usecases.news.SelectArticle
import com.example.infohub.domain.usecases.news.SelectArticles
import com.example.infohub.domain.usecases.news.UpsertArticle
import com.example.infohub.util.Constant.BASE_URL
import com.example.infohub.util.Constant.NEWS_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideLocalUserManager(
        application:Application
    ):localusermanager=LocaluserManagerImpl(application)

    @Provides
    @Singleton
    fun provideAppEntryUsecases(
        localusermanager: localusermanager
    ) = AppEntryUsecases(
        readAppEntry = ReadAppEntry(localusermanager),
        saveAppEntry = SaveAppEntry(localusermanager)
    )

    @Provides
    @Singleton
    fun provideNewsApi(): NewsApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }
    @Provides
    @Singleton
    fun provideNewsRepository(
        newsApi: NewsApi,
        newsDao: NewsDao
    ):NewsRepository=NewsRepositoryImpl(newsApi,newsDao)
    @Provides
    @Singleton
    fun provideNewsUseCases(
        newsRepository: NewsRepository,
        newsDao: NewsDao
    ):NewsUseCases{
        return NewsUseCases(
            getNews = GetNews(newsRepository),
            searchNews = SearchNews(newsRepository),
            upsertArticle= UpsertArticle(newsRepository),
            deleteArticle = DeleteArticle(newsRepository),
            selectArticles = SelectArticles(newsRepository),
            selectArticle = SelectArticle(newsRepository)



        )
    }
    @Provides
    @Singleton
    fun provideNewsDatabase(
        application:Application
    ):NewsDatabase{
        return Room.databaseBuilder(
            context = application,
            klass = NewsDatabase::class.java,
            name=NEWS_DATABASE_NAME
        ).addTypeConverter(NewsTypeConvertor())
            .fallbackToDestructiveMigration()
            .build()
    }
    @Provides
    @Singleton
    fun provideNewsDao(
        newsDatabase: NewsDatabase
    ):NewsDao=newsDatabase.newsDao

}


