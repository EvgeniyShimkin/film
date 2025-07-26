package com.example.myapplication.data.Enity

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.versionedparcelable.VersionedParcelize

//import android.os.Parcel
//import android.os.Parcelable
//import androidx.versionedparcelable.VersionedParcelize
@VersionedParcelize
@Entity(tableName = "cached_films", [Index(value = ["title"], unique = true)])


data class Film(
    @PrimaryKey(autoGenerate = true) val id: Int=0,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "poster_path") val poster: String?,
    @ColumnInfo(name = "overview") val description: String,
    @ColumnInfo(name = "vote_average") val rating: Double = 0.0,
    var isInFavorites:Boolean = false) : Parcelable


{
        constructor(parcel: Parcel) : this(
            id = parcel.readInt(),
            title = parcel.readString() ?: "",
            poster = parcel.readString() ?: "",
            description = parcel.readString() ?: "",
            rating = parcel.readDouble(),
        )

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeString(poster)
        parcel.writeString(description)
        parcel.writeDouble(rating)
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

