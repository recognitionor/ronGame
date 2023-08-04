package com.jhlee.quiz_libs.domain.model


import android.os.Parcel
import android.os.Parcelable

data class Quiz(
    var id: Int,
    val category: String,
    val level: Int,
    val imageUrl: String,
    val answer: Int,
    val question: String,
    val choiceList: List<String>,
    val time: Long,
    val chance: Int,
    val reward: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.createStringArrayList() ?: emptyList(),
        parcel.readLong(),
        parcel.readInt(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(category)
        parcel.writeInt(level)
        parcel.writeString(imageUrl)
        parcel.writeInt(answer)
        parcel.writeString(question)
        parcel.writeStringList(choiceList)
        parcel.writeLong(time)
        parcel.writeInt(chance)
        parcel.writeInt(reward)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Quiz> {
        override fun createFromParcel(parcel: Parcel): Quiz {
            return Quiz(parcel)
        }

        override fun newArray(size: Int): Array<Quiz?> {
            return arrayOfNulls(size)
        }
    }
}
