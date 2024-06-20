package uz.gita.task.data.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import kotlinx.coroutines.delay
import uz.gita.task.data.model.ProductData
import javax.inject.Inject

class ProductDataSource @Inject constructor() : PagingSource<Int, ProductData>() {
    private val urls = listOf(
        "https://firebasestorage.googleapis.com/v0/b/x-soft-1707.appspot.com/o/img%2F0000000004011.webp?alt=media&token=33097998-0a52-4095-8436-fad20f9ea384",
        "https://firebasestorage.googleapis.com/v0/b/x-soft-1707.appspot.com/o/img%2F21543-fru-g1-65-fbbe-3d-f_1589569104.png?alt=media&token=a51bec83-7ae6-4e14-b046-bcccbb8cfead",
        "https://firebasestorage.googleapis.com/v0/b/x-soft-1707.appspot.com/o/img%2F21543-fru-g1-65-peme-3d-f_1589569250.png?alt=media&token=5bf1455c-4839-46c9-a9e8-bb1d57f3e257",
        "https://firebasestorage.googleapis.com/v0/b/x-soft-1707.appspot.com/o/img%2F25-fruite-apple-candy-fruite-10-original-imag5zgcc8hk4tek.webp?alt=media&token=2948598f-8d74-40b1-ab66-767f1f9a8577",
        "https://firebasestorage.googleapis.com/v0/b/x-soft-1707.appspot.com/o/img%2FAbest-Mixed-Fruite-Juice-Slix.jpg?alt=media&token=698070f2-a21f-4601-a924-08defe665699",
        "https://firebasestorage.googleapis.com/v0/b/x-soft-1707.appspot.com/o/img%2FCherry_season_(48216568227).jpg?alt=media&token=c00b46ef-ee2c-490b-a55f-6c2d05e1f71d",
        "https://firebasestorage.googleapis.com/v0/b/x-soft-1707.appspot.com/o/img%2FEN_067311350026_6484.png?alt=media&token=ef5aa980-73f0-4531-b839-f15df6bcf1fe",
        "https://firebasestorage.googleapis.com/v0/b/x-soft-1707.appspot.com/o/img%2FEN_067311570028_6487.png?alt=media&token=f17d49c5-4c9d-4a09-8cf4-8b6b7e8059f0",
        "https://firebasestorage.googleapis.com/v0/b/x-soft-1707.appspot.com/o/img%2FEmile-Noel-Produit-huile-olive-Fruite-Mur-1L-1296-1.png?alt=media&token=c76a32a3-3f6d-4649-a112-2fd7cfdb5565",
        "https://firebasestorage.googleapis.com/v0/b/x-soft-1707.appspot.com/o/img%2Ffftjbw03bqt3kt7tqje6xxbc2eplq4j8b7s4tnicylsns0vf0wod2esyc71s-w750-q85.jpg?alt=media&token=bce4cd69-b431-40f7-8d59-14fb497045b0",
        "https://firebasestorage.googleapis.com/v0/b/x-soft-1707.appspot.com/o/img%2Foranges-fruit-vitamins-healthy-eating-51958.webp?alt=media&token=7431ca1e-8822-472e-ba44-18bae7930bf1",
        "https://firebasestorage.googleapis.com/v0/b/x-soft-1707.appspot.com/o/img%2Fred-apple-isolated-clipping-path-19130134.webp?alt=media&token=e03bd83e-1273-48df-adb3-cba48ad8f420",
    )


    override fun getRefreshKey(state: PagingState<Int, ProductData>): Int? {
        return state.anchorPosition?.inc() ?: 0
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ProductData> {
        return LoadResult.Page(
            data = loadItem(params.key, params.loadSize),
            prevKey = if (params.key == 0 || params.key == null) null else params.key!! - 1,
            nextKey = params.key!! + 1
        )
    }

    private suspend fun loadItem(key: Int?, size: Int): List<ProductData> {
        var position = (key ?: 0) * size
        val ls = ArrayList<ProductData>(size)
        repeat(size) {
            position++
            ls.add(ProductData("Product #$position", urls[position % urls.size], "12$"))
        }
        delay(2000)
        return ls
    }

}