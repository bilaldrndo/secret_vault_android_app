package com.example.secretvault.di

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import com.example.secretvault.data.RoomAppDatabase
import com.example.secretvault.data.repositories.DataStoreRepository
import com.example.secretvault.util.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        RoomAppDatabase::class.java,
        DATABASE_NAME,
    ).build()

    @Singleton
    @Provides
    fun provideNotesDao(database: RoomAppDatabase) = database.notesDao();

}