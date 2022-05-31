package kg.teamcoders.obmenvalut.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.room.Room
import kg.teamcoders.obmenvalut.R
import kg.teamcoders.obmenvalut.databinding.ActivityLoginBinding
import kg.teamcoders.obmenvalut.dto.AppDataBase
import kg.teamcoders.obmenvalut.dto.DataModel
import kg.teamcoders.obmenvalut.ui.main.MainActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding
    private lateinit var db : AppDataBase


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = Room.databaseBuilder(
            applicationContext,
            AppDataBase::class.java, "database"
        )
            .allowMainThreadQueries()
            .build()

       binding.loginButton.setOnClickListener {
           try {
               val a = db.daoInterface().getAllMoney()
               val username = binding.usernameEditText.text.toString()
               val pass = binding.passwordEditText.text.toString()
               a.forEach {
                   if(it.login == username && it.pass == pass){
                       startActivity(Intent(this,MainActivity::class.java))
                   }
               }
           }catch (e:Exception){
               Toast.makeText(applicationContext,"Неверный логин или пароль!",Toast.LENGTH_SHORT).show()
           }
       }
    }
}