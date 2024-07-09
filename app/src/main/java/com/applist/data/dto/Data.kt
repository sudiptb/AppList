package sudipta.`in`.newwaytocode.network.dto

import com.google.gson.annotations.SerializedName
import sudipta.`in`.newwaytocode.network.dto.Item

data class Data(
    @SerializedName("app_list")
    val appList: List<Item>? = emptyList()
)
