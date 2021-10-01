package com.example.imc

import android.os.Parcel
import android.os.Parcelable
import java.text.DecimalFormat

class Imc(var nomeUser: String?, var pesoUser: Double, var alturaUser: Double, var sexoUser: String?): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readString()
    )

    fun calculaImc():Double{
        val formataValor = DecimalFormat("#,00")
        return formataValor.format(pesoUser/(alturaUser*alturaUser)).toDouble()

    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nomeUser)
        parcel.writeDouble(pesoUser)
        parcel.writeDouble(alturaUser)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Imc> {
        override fun createFromParcel(parcel: Parcel): Imc {
            return Imc(parcel)
        }

        override fun newArray(size: Int): Array<Imc?> {
            return arrayOfNulls(size)
        }
    }
}