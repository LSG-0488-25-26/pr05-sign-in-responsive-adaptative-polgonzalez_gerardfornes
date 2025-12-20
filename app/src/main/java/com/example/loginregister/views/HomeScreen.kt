package com.example.loginregister.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.loginregister.R
import com.example.loginregister.ui.theme.*
import com.example.loginregister.viewmodel.StopifyViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    stopifyViewModel: StopifyViewModel = viewModel()
) {
    // 1. Observem el LiveData. Si és null, per defecte serà null
    val userState by stopifyViewModel.currentUser.observeAsState()

    // 2. Gestionem el nom a mostrar
    val displayUsername = userState?.username ?: "Convidat"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(StopifyBlack)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {


        Image(
            painter = painterResource(id = R.drawable.logo_stopify),
            contentDescription = "Logo Stopify",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .width(280.dp)
                .padding(bottom = 40.dp)
        )

        Text(
            text = "Hola, $displayUsername",
            color = StopifyWhite,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Preparat per gaudir del silenci absolut?",
            color = StopifyGrey,
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 20.dp)
        )

        Spacer(modifier = Modifier.height(64.dp))


        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(
                containerColor = StopifyGreen,
                contentColor = Color.Black
            ),
            shape = CircleShape,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Text(
                text = "COMENÇAR A PARAR MÚSICA!",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Pla Premium: 0€/mes per no escoltar res.",
            color = StopifyGrey,
            fontSize = 12.sp
        )
    }
}