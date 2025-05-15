package com.example.myapplication.domain

import android.os.Parcel
import android.os.Parcelable
import androidx.versionedparcelable.VersionedParcelize

//import android.os.Parcel
//import android.os.Parcelable
//import androidx.versionedparcelable.VersionedParcelize

@VersionedParcelize
data class Film(
    val title: String,
    val poster: String,
    val description: String,
    val rating: Double = 0.0,
    var isInFavorites:Boolean = false) : Parcelable


{
        constructor(parcel: Parcel) : this(parcel.readString().toString(),
            parcel.readInt().toString(),
            parcel.readString().toString())

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(p0: Parcel, p1: Int) {
        p0.writeString(title)
        p0.writeString(description)
    }

    companion object CREATOR : Parcelable.Creator<Film> {
        override fun createFromParcel(parcel: Parcel): Film {
            return Film(parcel)
        }

        override fun newArray(size: Int): Array<Film?> {
            return arrayOfNulls(size)
        }
    }
}

