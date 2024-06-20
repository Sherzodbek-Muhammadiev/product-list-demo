package uz.gita.task.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import uz.gita.task.data.model.ProductData
import uz.gita.task.data.source.ProductDataSource
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val productDataSource: ProductDataSource
) {
    val products: Flow<PagingData<ProductData>> = Pager(PagingConfig(pageSize = 20, initialLoadSize = 20), initialKey = 0) {
        productDataSource
    }.flow
}