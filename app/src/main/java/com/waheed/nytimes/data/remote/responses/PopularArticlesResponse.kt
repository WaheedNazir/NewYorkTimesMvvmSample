package com.waheed.nytimes.data.remote.responses


import com.google.gson.annotations.SerializedName

data class PopularArticlesResponse(
    @SerializedName("copyright")
    val copyright: String = "",
    @SerializedName("num_results")
    val numResults: Int = 0,
    @SerializedName("results")
    val articles: List<Article> = emptyList(),
    @SerializedName("status")
    val status: String = ""
) {
    data class Article(
        @SerializedName("abstract")
        val `abstract`: String = "",
        @SerializedName("adx_keywords")
        val adxKeywords: String = "",
        @SerializedName("byline")
        val byline: String = "",
        @SerializedName("des_facet")
        val desFacet: List<String> = emptyList(),
        @SerializedName("geo_facet")
        val geoFacet: List<String> = emptyList(),
        @SerializedName("id")
        val id: Long = 0,
        @SerializedName("media")
        val media: List<Media>? = emptyList(),
        @SerializedName("nytdsection")
        val nytdsection: String = "",
        @SerializedName("org_facet")
        val orgFacet: List<String> = emptyList(),
        @SerializedName("per_facet")
        val perFacet: List<String> = emptyList(),
        @SerializedName("published_date")
        val publishedDate: String = "",
        @SerializedName("section")
        val section: String = "",
        @SerializedName("source")
        val source: String = "",
        @SerializedName("subsection")
        val subsection: String = "",
        @SerializedName("title")
        val title: String = "",
        @SerializedName("type")
        val type: String = "",
        @SerializedName("updated")
        val updated: String = "",
        @SerializedName("uri")
        val uri: String = "",
        @SerializedName("url")
        val url: String = ""
    ) {
        data class Media(
            @SerializedName("approved_for_syndication")
            val approvedForSyndication: Int = 0,
            @SerializedName("caption")
            val caption: String = "",
            @SerializedName("copyright")
            val copyright: String = "",
            @SerializedName("media-metadata")
            val mediaMetadata: List<MediaMetadata>? = emptyList(),
            @SerializedName("subtype")
            val subtype: String = "",
            @SerializedName("type")
            val type: String = ""
        ) {
            data class MediaMetadata(
                @SerializedName("format")
                val format: String = "",
                @SerializedName("height")
                val height: Int = 0,
                @SerializedName("url")
                val url: String = "",
                @SerializedName("width")
                val width: Int = 0
            )
        }
    }
}