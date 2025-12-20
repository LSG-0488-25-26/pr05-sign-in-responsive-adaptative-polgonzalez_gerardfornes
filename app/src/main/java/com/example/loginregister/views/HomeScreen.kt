package com.example.loginregister.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
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
import androidx.navigation.NavController
import com.example.loginregister.R
import com.example.loginregister.ui.theme.*
import com.example.loginregister.viewmodel.StopifyViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    stopifyViewModel: StopifyViewModel = viewModel(),
    windowSizeClass: WindowSizeClass,
    navController: NavController
) {
    val userState by stopifyViewModel.currentUser.observeAsState()
    val displayUsername = userState?.username ?: "Convidat"

    when (windowSizeClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(StopifyBlack)
                    .padding(24.dp)
                    .verticalScroll(rememberScrollState()), // Scroll per si la pantalla és petita en alçada
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                HomeContent(displayUsername)
            }
        }
        else -> {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .background(StopifyBlack)
                    .padding(24.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo_stopify),
                        contentDescription = "Logo Stopify",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier.width(280.dp)
                    )
                }

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .verticalScroll(rememberScrollState()), // Scroll independent
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    // Passem només la part de text i botons, sense el logo repetit
                    HomeTextAndButtons(displayUsername)
                }
            }
        }
    }
}


@Composable
fun HomeContent(username: String) {
    Image(
        painter = painterResource(id = R.drawable.logo_stopify),
        contentDescription = "Logo Stopify",
        contentScale = ContentScale.Fit,
        modifier = Modifier
            .width(280.dp)
            .padding(bottom = 40.dp)
    )

    HomeTextAndButtons(username)
}

@Composable
fun HomeTextAndButtons(username: String) {
    Text(
        text = "Hola, $username",
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