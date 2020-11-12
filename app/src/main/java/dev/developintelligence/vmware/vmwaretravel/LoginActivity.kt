package dev.developintelligence.vmware.vmwaretravel

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.edit
import dev.developintelligence.vmware.vmwaretravel.providers.LoginAPI

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // We load login data in case we have it
        val preferences = getSharedPreferences("login", MODE_PRIVATE)
        findViewById<EditText>(R.id.editUsername).setText(preferences.getString("username", ""))
        findViewById<EditText>(R.id.editPassword).setText(preferences.getString("password", ""))


        // button login click
        findViewById<Button>(R.id.buttonLogin).setOnClickListener {
            val username = findViewById<EditText>(R.id.editUsername).text.toString()
            val password = findViewById<EditText>(R.id.editPassword).text.toString()
            val api = LoginAPI()

            api.login(username, password) { isValid ->
                    if (isValid) {
                        // We go to Next Activity
                        val intent = Intent(this, HomeActivity::class.java)
                        startActivity(intent)

                        // We save the data for next login
                        preferences.edit {
                            putString("username", username)
                            putString("password", password)
                        }

                    } else {
                        AlertDialog.Builder(this)
                                .setTitle("Log In Error")
                                .setMessage("Call Customer Support to reset your credentials")
                                .setNeutralButton("Ok") { _, _ ->

                                }
                                .show()
                    }
            }
        }

        findViewById<Button>(R.id.buttonForgot).setOnClickListener {
            Toast.makeText(this,
                "Call Customer Support to reset your credentials",
                Toast.LENGTH_LONG).show()
        }
    }

}