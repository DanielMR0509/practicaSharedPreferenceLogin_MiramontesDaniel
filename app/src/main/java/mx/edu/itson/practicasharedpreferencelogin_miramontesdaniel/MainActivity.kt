package mx.edu.itson.practicasharedpreferencelogin_miramontesdaniel

import HomeScreen
import LoginScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import mx.edu.itson.practicasharedpreferencelogin_miramontesdaniel.ui.theme.PracticaSharedPreferenceLogin_MiramontesDanielTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val preferenceManager = PreferenceManager(this)

        setContent {
            var loggedIn by remember {
                mutableStateOf(preferenceManager.isLoggedIn())
            }

            if (loggedIn) {
                HomeScreen(onLogout = {
                    preferenceManager.logout()
                    loggedIn = false
                })
            } else {
                LoginScreen(onLoginSuccess = {
                    preferenceManager.saveLoginStatus(true)
                    loggedIn = true
                })
            }
        }
    }
}