package uz.gita.task.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.task.navigation.AppNavigator
import uz.gita.task.navigation.AppNavigatorDispatcher
import uz.gita.task.navigation.AppNavigatorHandler


@Module
@InstallIn(SingletonComponent::class)
internal interface NavigationModule {

    @Binds
    fun navigator(dispatcher: AppNavigatorDispatcher): AppNavigator

    @Binds
    fun navigatorHandler(dispatcher: AppNavigatorDispatcher): AppNavigatorHandler
}