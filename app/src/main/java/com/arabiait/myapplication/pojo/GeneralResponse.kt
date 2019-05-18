package com.arabiait.myapplication.pojo

import com.google.gson.annotations.SerializedName
import javax.annotation.Generated

@Generated("com.robohorse.robopojogenerator")
data class GeneralResponse(

        @field:SerializedName("dates")
        val dates: Dates? = null,

        @field:SerializedName("page")
        val page: Int? = null,

        @field:SerializedName("total_pages")
        val totalPages: Int? = null,

        @field:SerializedName("results")
        val results: List<ResultsItem?>? = null,

        @field:SerializedName("total_results")
        val totalResults: Int? = null
)