package com.example.loginregister.views

import android.graphics.fonts.FontFamily
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.loginregister.R

@Composable
fun LoginLayout(modifier: Modifier) {
    Box(modifier = Modifier.fillMaxSize().background(Color.Black))

    Column (modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally){
        Spacer(modifier = Modifier.size(60.dp))
        Image(painter = painterResource(id = R.drawable.logo_solo_stopify), contentDescription = "LogoStopify", Modifier.size(80.dp))
        Text(text = "¡Hola de nou!", color = Color.White, fontSize = 60.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 10.dp))

        Spacer(modifier = Modifier.size(20.dp))
        Text(text = "Correu electronic o nom d'usuari", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 10.dp))
        /*TextField()*/
    }
}