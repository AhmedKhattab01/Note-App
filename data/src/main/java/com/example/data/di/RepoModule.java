package com.example.data.di;

import com.example.data.repo.LocalRepoImpl;
import com.example.data.room.CategoryDao;
import com.example.data.room.NoteDao;
import com.example.domain.repo.LocalRepository;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class RepoModule {
    @Provides
    LocalRepository localRepository(NoteDao noteDao, CategoryDao categoryDao) {
        return new LocalRepoImpl(noteDao,categoryDao);
    }
}
