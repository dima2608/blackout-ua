package com.august.ua.blackout.data.dto

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.august.ua.blackout.R
import com.google.gson.annotations.SerializedName

enum class OblastType(
    val id: String,
    @StringRes
    val oblastName: Int,
    @DrawableRes
    val icon: Int
){
    @SerializedName("cherkasy")
    Cherkasy(
        id = "cherkasy",
        oblastName = R.string.cherkasy,
        icon = R.drawable.kyiv_oblast
    ),

    @SerializedName("kyiv_oblast")
    Kyiv(
        id = "kyiv_oblast",
        oblastName = R.string.kyiv_oblast,
        icon = R.drawable.kyiv_oblast
    ),

    @SerializedName("luhansk_oblast")
    Luhansk(
        id = "luhansk_oblast",
        oblastName = R.string.luhansk_oblast,
        icon = R.drawable.luhansk_oblast
    ),

    @SerializedName("lviv_oblast")
    Lviv(
        id = "lviv_oblast",
        oblastName = R.string.lviv_oblast,
        icon = R.drawable.lviv_oblast
    ),
    @SerializedName("mykolaiv_oblast")
    Mykolaiv(
        id = "mykolaiv_oblast",
        oblastName = R.string.mykolaiv_oblast,
        icon = R.drawable.odesa_oblast
    ),

    @SerializedName("odesa_oblast")
    Odesa(
        id = "odesa_oblast",
        oblastName = R.string.odesa_oblast,
        icon = R.drawable.odesa_oblast
    ),

    @SerializedName("poltava_oblast")
    Poltava(
        id = "poltava_oblast",
        oblastName = R.string.poltava_oblast,
        icon = R.drawable.poltava_oblast
    ),
    @SerializedName("rivne_oblast")
    Rivne(
        id = "rivne_oblast",
        oblastName = R.string.rivne_oblast,
        icon = R.drawable.rivne_oblast
    ),

    @SerializedName("sumy_oblast")
    Sumy(
        id = "sumy_oblast",
        oblastName = R.string.sumy_oblast,
        icon = R.drawable.sumy_oblast
    ),

    @SerializedName("ternopil_oblast")
    Ternopil(
        id = "sumy_oblast",
        oblastName = R.string.ternopil_oblast,
        icon = R.drawable.ternopil_oblast
    ),

    @SerializedName("vinnytsia_oblast")
    Vinnytsia(
        id = "vinnytsia_oblast",
        oblastName = R.string.vinnytsia_oblast,
        icon = R.drawable.vinnitsa_oblast
    ),

    @SerializedName("volyn_oblast")
    Volyn(
        id = "vinnytsia_oblast",
        oblastName = R.string.volyn_oblast,
        icon = R.drawable.volyn_oblast
    ),

    @SerializedName("zakarpattia_oblast")
    Zakarpattia(
        id = "zakarpattia_oblast",
        oblastName = R.string.zakarpattia_oblast,
        icon = R.drawable.zakarpattia_oblast
    ),

    @SerializedName("zaporizhia_oblast")
    Zaporizhia(
        id = "zaporizhia_oblast",
        oblastName = R.string.zaporizhia_oblast,
        icon = R.drawable.zaporizhia_oblast
    ),

    @SerializedName("zhytomyr_oblast")
    Zhytomyr(
        id = "zhytomyr_oblast",
        oblastName = R.string.zhytomyr_oblast,
        icon = R.drawable.zhytomyr_oblast
    ),

    @SerializedName("chernivtsi")
    Chernivtsi(
        id = "chernivtsi_oblast",
        oblastName = R.string.chernivtsi_oblast,
        icon = R.drawable.zhytomyr_oblast
    ),

    @SerializedName("unknown")
    Unknown(
        id = "-1",
        oblastName = R.string.unknown,
        R.drawable.kyiv_oblast
    )
}