package com.example.recyclerview

//参照するJSONDataに合わせて形式、変数全て揃える必要がある
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
