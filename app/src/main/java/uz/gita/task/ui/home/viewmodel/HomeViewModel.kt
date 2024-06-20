package uz.gita.task.ui.home.viewmodel

import androidx.paging.cachedIn
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import uz.gita.task.data.repository.ProductRepository
import uz.gita.task.navigation.AppNavigator
import uz.gita.task.ui.details.DetailsScreen
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    private val navigator: AppNavigator
) : ScreenModel, HomeContract.ViewModel {

    override val uiState = MutableStateFlow(HomeContract.UiState(productRepository.products.cachedIn(screenModelScope)))

    override fun onEventDispatcher(intent: HomeContract.Intent) {
        when (intent) {
            is HomeContract.Intent.OpenDetails -> {
                screenModelScope.launch { navigator.navigateTo(DetailsScreen(intent.productData)) }
            }
        }
    }
}