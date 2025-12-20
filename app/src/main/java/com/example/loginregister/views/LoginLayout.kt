package com.example.loginregister.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.loginregister.R
import com.example.loginregister.ui.theme.*
import com.example.loginregister.viewmodel.StopifyViewModel

@Composable
fun LoginLayout(
    modifier: Modifier = Modifier,
    navController: NavController,
    windowSize: WindowSizeClass,
    viewModel: StopifyViewModel
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(StopifyBlack)
            .padding(16.dp)
    ) {
        when (windowSize.widthSizeClass) {
            WindowWidthSizeClass.Compact -> {
                // Móvil en Vertical
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo_solo_stopify),
                        contentDescription = "LogoStopify",
                        modifier = Modifier.size(80.dp),
                        contentScale = ContentScale.Fit
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "¡Hola de nou!",
                        color = Color.White,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(40.dp))
                    LoginForm(viewModel, navController)
                }
            }
            else -> {
                // Tablet en Horizontal
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier.weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Image(
                                painter = painterResource(id = R.drawable.logo_solo_stopify),
                                contentDescription = "LogoStopify",
                                modifier = Modifier.size(150.dp)
                            )
                            Text(
                                text = "Stopify",
                                color = StopifyWhite,
                                fontSize = 40.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .background(SpotifyDarkGrey, RoundedCornerShape(16.dp))
                            .padding(32.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        LoginForm(viewModel, navController)
                    }
                }
            }
        }

        if (viewModel.cargando) {
            CircularProgressIndicator(
                color = StopifyGreen,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Composable
fun LoginForm(viewModel: StopifyViewModel, navController: NavController) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth().widthIn(max = 400.dp)
    ) {
        Text(
            text = "Inicia sessió per continuar",
            color = StopifyWhite,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(32.dp))

        SpotifyTextField(
            value = viewModel.email,
            onValueChange = { viewModel.emailCargando(it) },
            label = "Correu electronic o nom d'usuari"
        )
        Spacer(modifier = Modifier.height(16.dp))

        SpotifyTextField(
            value = viewModel.contrasenya,
            onValueChange = { viewModel.contrasenyaCargando(it) },
            label = "Contrasenya",
            isPassword = true,
            isPasswordVisible = viewModel.contrasenyaVisible,
            onVisibilityToggle = { viewModel.alternarContrasenyaVisibile() }
        )
        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { viewModel.loginSeleccionat(navController) },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(containerColor = StopifyGreen),
            shape = CircleShape
        ) {
            Text(text = "INICIAR SESSIÓ", color = Color.Black, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(24.dp))

        TextButton(onClick = { /* Navegar a registro */ }) {
            Text(text = "No tens una compte? Registre't", color = SpotifyTextGrey)
        }
    }
}

@Composable
fun SpotifyTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    isPassword: Boolean = false,
    isPasswordVisible: Boolean = false,
    onVisibilityToggle: () -> Unit = {}
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label, color = SpotifyTextGrey) },
        modifier = Modifier.fillMaxWidth(),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = StopifyWhite,
            unfocusedBorderColor = StopifyGrey,
            cursorColor = StopifyGreen,
            focusedTextColor = StopifyWhite,
            unfocusedTextColor = StopifyWhite,
            focusedContainerColor = SpotifyDarkGrey,
            unfocusedContainerColor = SpotifyDarkGrey
        ),
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = if (isPassword) KeyboardType.Password else KeyboardType.Email
        ),
        visualTransformation = if (isPassword && !isPasswordVisible) PasswordVisualTransformation() else VisualTransformation.None,
        trailingIcon = {
            if (isPassword) {
                IconButton(onClick = onVisibilityToggle) {
                    Icon(
                        imageVector = if (isPasswordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                        contentDescription = "Toggle password",
                        tint = SpotifyTextGrey
                    )
                }
            }
        }
    )
}