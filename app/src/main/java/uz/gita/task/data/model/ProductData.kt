package uz.gita.task.data.model

import java.io.Serializable

data class ProductData(
    val name: String,
    val imageUrl: String,
    val price: String
) : Serializable