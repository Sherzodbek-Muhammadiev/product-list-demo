package uz.gita.task.di

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.hilt.ScreenModelKey
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap
import uz.gita.task.ui.home.viewmodel.HomeViewModel

@Module
@InstallIn(SingletonComponent::class)
interface ViewModelModule {

    @Binds
    @IntoMap
    @ScreenModelKey(HomeViewModel::class)
    fun loginViewModel(impl: HomeViewModel): ScreenModel
}