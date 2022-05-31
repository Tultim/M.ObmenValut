package kg.teamcoders.obmenvalut.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "moneyTable")
data class DataModel(
    @PrimaryKey(autoGenerate = true)
    var moneyID: Int = 0,
    @ColumnInfo(name = "valuta")
    val moneyValuta: String,
    @ColumnInfo(name = "nalichiye")
    val moneyNalichiye: Boolean,
    @ColumnInfo(name = "num")
    val moneyNum: Int,
    @ColumnInfo(name = "pokupka")
    val moneyPokupka: Int,
    @ColumnInfo(name = "prodazha")
    val moneyProdazha: Int,
    @ColumnInfo(name = "otvetstveniy")
    val moneyOtvetstveniy: String,
    @ColumnInfo(name = "login")
    val login : String,
    @ColumnInfo(name = "pass")
    val pass : String,
    @ColumnInfo(name = "adres")
    val moneyAdres: String,
    @ColumnInfo(name = "gorod")
    val moneyGorod: String,
    @ColumnInfo(name = "telefon")
    val moneyTelefon: String
    )
