package uz.gita.task.ui.home.viewmodel

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import uz.gita.task.data.model.ProductData

interface HomeContract {
    interface ViewModel {
        val uiState: StateFlow<UiState>
        fun onEventDispatcher(intent: Intent)
    }

    data class UiState(
        val products: Flow<PagingData<ProductData>>
    )

    sealed interface Intent {
        data class OpenDetails(val productData: ProductData) : Intent
    }
}