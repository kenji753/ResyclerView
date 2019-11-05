package com.example.recyclerview

data class Data(
    val storeID: List<StoreID>
) {
    data class StoreID(
        val post: Post,
        val store: Store
    )

    data class Post(
        var likeCount: Int?,
        var likeState: Boolean?
    )

    data class Store(
        val businessHours: String?,
        val content: String?,
        val name: String?,
        val picture: String?
    )
}
