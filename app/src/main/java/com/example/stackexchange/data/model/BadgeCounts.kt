package com.example.stackexchange.data.model

import android.os.Parcel
import android.os.Parcelable

data class BadgeCounts(
    val bronze: Int = 0,
    val gold: Int = 0,
    val silver: Int = 0): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(bronze)
        parcel.writeInt(gold)
        parcel.writeInt(silver)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BadgeCounts> {
        override fun createFromParcel(parcel: Parcel): BadgeCounts {
            return BadgeCounts(parcel)
        }

        override fun newArray(size: Int): Array<BadgeCounts?> {
            return arrayOfNulls(size)
        }
    }
}