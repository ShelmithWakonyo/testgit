package com.example.dreamwingsiv.data

import com.example.dreamwingsiv.R
import com.example.dreamwingsiv.model.destiny

class datasource {
    fun loadplaces():List<destiny>{
        return listOf<destiny>(
            destiny(R.string.Austalia,R.drawable.australia),
            destiny(R.string.canada,R.drawable.canada),
            destiny(R.string.dubai,R.drawable.dubai),
            destiny(R.string.paris,R.drawable.paris),
            destiny(R.string.SouthK,R.drawable.south_korea),
            destiny(R.string.tokyo,R.drawable.tokyo)
        )
    }
}