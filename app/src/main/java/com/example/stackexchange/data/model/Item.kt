package com.example.stackexchange.data.model

import android.os.Parcel
import android.os.Parcelable

data class Item(
    val account_id: Int = 0,
    val badge_counts: BadgeCounts? = null,
    val creation_date: Int = 0,
    val display_name: String = "",
    val is_employee: Boolean = false,
    val last_access_date: Int = 0,
    val last_modified_date: Int = 0,
    val link: String = "",
    val location: String = "",
    val profile_image: String = "",
    val reputation: Int = 0,
    val reputation_change_day: Int = 0,
    val reputation_change_month: Int = 0,
    val reputation_change_quarter: Int = 0,
    val reputation_change_week: Int = 0,
    val reputation_change_year: Int = 0,
    val user_id: Int = 0,
    val user_type: String = "",
    val website_url: String = ""): Parcelable, Comparable<Item> {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readParcelable(BadgeCounts::class.java.classLoader)!!,
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readByte() != 0.toByte(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(account_id)
        parcel.writeParcelable(badge_counts, flags)
        parcel.writeInt(creation_date)
        parcel.writeString(display_name)
        parcel.writeByte(if (is_employee) 1 else 0)
        parcel.writeInt(last_access_date)
        parcel.writeInt(last_modified_date)
        parcel.writeString(link)
        parcel.writeString(location)
        parcel.writeString(profile_image)
        parcel.writeInt(reputation)
        parcel.writeInt(reputation_change_day)
        parcel.writeInt(reputation_change_month)
        parcel.writeInt(reputation_change_quarter)
        parcel.writeInt(reputation_change_week)
        parcel.writeInt(reputation_change_year)
        parcel.writeInt(user_id)
        parcel.writeString(user_type)
        parcel.writeString(website_url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Item> {
        override fun createFromParcel(parcel: Parcel): Item {
            return Item(parcel)
        }

        override fun newArray(size: Int): Array<Item?> {
            return arrayOfNulls(size)
        }
    }

    override fun compareTo(other: Item): Int {
        return compareValues(other.display_name, other.display_name)
    }
}
