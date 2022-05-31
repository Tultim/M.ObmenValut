package kg.teamcoders.obmenvalut.dto

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [DataModel::class], version = 2,exportSchema = true)
abstract class AppDataBase : RoomDatabase() {
    abstract fun daoInterface(): DaoInterface
}