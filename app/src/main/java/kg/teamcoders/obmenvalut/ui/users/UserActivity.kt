package kg.teamcoders.obmenvalut.ui.users

import android.annotation.SuppressLint
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import kg.teamcoders.obmenvalut.R
import kg.teamcoders.obmenvalut.databinding.ActivityUserBinding
import kg.teamcoders.obmenvalut.dto.AppDataBase
import kg.teamcoders.obmenvalut.dto.DataModel
import java.lang.Exception

class UserActivity : AppCompatActivity() {

    private lateinit var binding : ActivityUserBinding
    private lateinit var db : AppDataBase
    private lateinit var adapter : UsersAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = Room.databaseBuilder(
            applicationContext,
            AppDataBase::class.java, "database"
        )
            .allowMainThreadQueries()
            .build()
        adapter = UsersAdapter()
        binding.recyclerViewUsers.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewUsers.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
        getUsers()
    }

    override fun onResume() {
        super.onResume()
        binding.nameButton.setOnClickListener {
            if(binding.nameEditTextUserInfo.text.isNotEmpty()){
                try{
                    val id = db.daoInterface().getIdByName(binding.nameEditTextUserInfo.text.toString())
                    showDialog("Имя",id)
                }catch (e : Exception){
                    Toast.makeText(applicationContext,"Не правильное имя",Toast.LENGTH_SHORT).show()
                }
            }
        }
        binding.adresButton.setOnClickListener {
            if(binding.nameEditTextUserInfo.text.isNotEmpty()){
                try{
                    val id = db.daoInterface().getIdByName(binding.nameEditTextUserInfo.text.toString())
                    showDialog("Адрес",id)
                }catch (e : Exception){
                    Toast.makeText(applicationContext,"Не правильное имя",Toast.LENGTH_SHORT).show()
                }
            }
        }
        binding.gorodButton.setOnClickListener {
            if(binding.nameEditTextUserInfo.text.isNotEmpty()){
                try{
                    val id = db.daoInterface().getIdByName(binding.nameEditTextUserInfo.text.toString())
                    showDialog("Город",id)
                }catch (e : Exception){
                    Toast.makeText(applicationContext,"Не правильное имя",Toast.LENGTH_SHORT).show()
                }
            }
        }
        binding.telefonButton.setOnClickListener {
            if(binding.nameEditTextUserInfo.text.isNotEmpty()){
                try{
                    val id = db.daoInterface().getIdByName(binding.nameEditTextUserInfo.text.toString())
                    showDialog("Телефон",id)
                }catch (e : Exception){
                    Toast.makeText(applicationContext,"Не правильное имя",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    @SuppressLint("NotifyDataSetChanged")
    private fun getUsers(){
        val users = db.daoInterface().getAllMoney()
        val usersAR = arrayListOf<DataModel>()
        users.forEach {
            usersAR.add(it)
        }
        adapter.stList(usersAR)
        adapter.notifyDataSetChanged()
    }

    private fun showDialog(type : String, id : Int){
        val builder: android.app.AlertDialog.Builder = android.app.AlertDialog.Builder(this)
        builder.setTitle(type)

        val input = EditText(this)
        input.setHint("Введите новое значение")
        input.inputType = InputType.TYPE_CLASS_TEXT
        builder.setView(input)

        builder.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->
            try{
                if(input.text.isNotEmpty()){
                    when(type){
                        "Имя" -> db.daoInterface().updateUserName(input.text.toString(),id)
                        "Адрес" -> db.daoInterface().updateUserAdres(input.text.toString(),id)
                        "Город" -> db.daoInterface().updateUserGorod(input.text.toString(),id)
                        "Телефон" -> db.daoInterface().updateTelefon(input.text.toString(),id)
                    }
                    Toast.makeText(applicationContext,"Успешно",Toast.LENGTH_SHORT).show()
                    dialog.cancel()
                }else{
                    Toast.makeText(applicationContext,"Поле пустое",Toast.LENGTH_SHORT).show()
                }
            }catch (e : Exception){
                Toast.makeText(applicationContext,"Упс... Ошибка",Toast.LENGTH_SHORT).show()
            }
        })
        builder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which -> dialog.cancel() })
        builder.show()
    }
}