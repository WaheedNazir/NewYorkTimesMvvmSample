package com.waheed.nytimes.data.remote.responses


import com.google.gson.annotations.SerializedName

data class ArticleSearchResponse(
    @SerializedName("copyright")
    val copyright: String = "",
    @SerializedName("response")
    val response: Response = Response(),
    @SerializedName("status")
    val status: String = ""
) {
    data class Response(
        @SerializedName("docs")
        val docs: List<Doc> = emptyList(),
        @SerializedName("meta")
        val meta: Meta = Meta()
    ) {
        data class Doc(
            @SerializedName("abstract")
            val `abstract`: String = "",
            @SerializedName("byline")
            val byline: Byline = Byline(),
            @SerializedName("document_type")
            val documentType: String = "",
            @SerializedName("headline")
            val headline: Headline = Headline(),
            @SerializedName("_id")
            val id: String = "",
            @SerializedName("keywords")
            val keywords: List<Keyword> = emptyList(),
            @SerializedName("lead_paragraph")
            val leadParagraph: String? = "",
            @SerializedName("multimedia")
            val multimedia: List<Multimedia> = emptyList(),
            @SerializedName("news_desk")
            val newsDesk: String = "",
            @SerializedName("print_page")
            val printPage: String = "",
            @SerializedName("print_section")
            val printSection: String = "",
            @SerializedName("pub_date")
            val pubDate: String = "",
            @SerializedName("section_name")
            val sectionName: String = "",
            @SerializedName("snippet")
            val snippet: String = "",
            @SerializedName("source")
            val source: String = "",
            @SerializedName("subsection_name")
            val subsectionName: String = "",
            @SerializedName("type_of_material")
            val typeOfMaterial: String = "",
            @SerializedName("uri")
            val uri: String = "",
            @SerializedName("web_url")
            val webUrl: String = "",
            @SerializedName("word_count")
            val wordCount: Int = 0
        ) {
            data class Byline(
                @SerializedName("organization")
                val organization: String? = "",
                @SerializedName("original")
                val original: String = "",
                @SerializedName("person")
                val person: List<Person> = emptyList()
            ) {
                data class Person(
                    @SerializedName("firstname")
                    val firstname: String = "",
                    @SerializedName("lastname")
                    val lastname: String = "",
                    @SerializedName("middlename")
                    val middlename: Any,
                    @SerializedName("organization")
                    val organization: String = "",
                    @SerializedName("qualifier")
                    val qualifier: Any,
                    @SerializedName("rank")
                    val rank: Int,
                    @SerializedName("role")
                    val role: String = "",
                    @SerializedName("title")
                    val title: Any
                )
            }

            data class Headline(
                @SerializedName("main")
                val main: String = "",
                @SerializedName("print_headline")
                val printHeadline: String = "",
            )

            data class Keyword(
                @SerializedName("major")
                val major: String = "",
                @SerializedName("name")
                val name: String = "",
                @SerializedName("rank")
                val rank: Int,
                @SerializedName("value")
                val value: String
            )

            data class Multimedia(
                @SerializedName("caption")
                val caption: Any,
                @SerializedName("credit")
                val credit: Any,
                @SerializedName("crop_name")
                val cropName: String = "",
                @SerializedName("height")
                val height: Int,
                @SerializedName("legacy")
                val legacy: Legacy,
                @SerializedName("rank")
                val rank: Int,
                @SerializedName("subType")
                val subType: String = "",
                @SerializedName("subtype")
                val subtype: String = "",
                @SerializedName("type")
                val type: String = "",
                @SerializedName("url")
                val url: String = "",
                @SerializedName("width")
                val width: Int
            ) {
                data class Legacy(
                    @SerializedName("thumbnail")
                    val thumbnail: String = "",
                    @SerializedName("thumbnailheight")
                    val thumbnailheight: Int,
                    @SerializedName("thumbnailwidth")
                    val thumbnailwidth: Int,
                    @SerializedName("wide")
                    val wide: String = "",
                    @SerializedName("wideheight")
                    val wideheight: Int,
                    @SerializedName("widewidth")
                    val widewidth: Int,
                    @SerializedName("xlarge")
                    val xlarge: String = "",
                    @SerializedName("xlargeheight")
                    val xlargeheight: Int,
                    @SerializedName("xlargewidth")
                    val xlargewidth: Int
                )
            }
        }

        data class Meta(
            @SerializedName("hits")
            val hits: Int = 0,
            @SerializedName("offset")
            val offset: Int = 0,
            @SerializedName("time")
            val time: Int = 0
        )
    }
}