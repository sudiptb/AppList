package com.applist.state

import sudipta.`in`.newwaytocode.network.dto.Item


data class ItemState(
    val itemList: List<Item> = emptyList()
)
