package kg.teamcoders.obmenvalut.dto

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface DaoInterface {

    @Query("select * from moneyTable")
    fun getAllMoney(): List<DataModel>


    @Query("select * from moneyTable WHERE valuta=:valuta")
    fun getMoneyByName(valuta : String): List<DataModel>

    @Query("select moneyId FROM moneyTable WHERE otvetstveniy=:otvetstveniy")
    fun getIdByName(otvetstveniy : String): Int

    @Insert
    fun addValuta(valuta: DataModel)

    @Query("UPDATE moneyTable SET num =:num where valuta=:valuta")
    fun updateMoney(valuta: String, num: Int)

    @Query("DELETE FROM moneyTable WHERE moneyId = :id")
    fun deleteValuta(id : Int)

    @Query("select * from moneyTable WHERE otvetstveniy=:otvetstveniy")
    fun getUserInfo(otvetstveniy : String): List<DataModel>

    @Query("UPDATE moneyTable SET otvetstveniy =:otvetstveniy WHERE moneyId =:moneyId")
    fun updateUserName(otvetstveniy: String,moneyId: Int)

    @Query("UPDATE moneyTable SET adres =:adres WHERE moneyId =:moneyId")
    fun updateUserAdres(adres: String,moneyId: Int)

    @Query("UPDATE moneyTable SET gorod =:gorod WHERE moneyId =:moneyId")
    fun updateUserGorod(gorod: String,moneyId: Int)

    @Query("UPDATE moneyTable SET telefon =:telefon WHERE moneyId =:moneyId")
    fun updateTelefon(telefon: String,moneyId: Int)
}