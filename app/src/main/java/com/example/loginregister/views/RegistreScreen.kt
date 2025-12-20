package com.example.loginregister.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
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
fun RegistreScreen(
    navController: NavController,
    windowSize: WindowSizeClass,
    viewModel: StopifyViewModel,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(StopifyBlack)
            .padding(16.dp)
    ) {
        when (windowSize.widthSizeClass) {
            WindowWidthSizeClass.Compact -> {
                // Versió Móvil
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.text_logo_stopifty),
                        contentDescription = "LogoStopify",
                        modifier = Modifier.size(160.dp),
                        contentScale = ContentScale.Fit
                    )
                    RegisterForm(viewModel, navController)
                }
            }
            else -> {
                // Versió Tablet
                Row(modifier = Modifier.fillMaxSize()) {
                    Box(
                        modifier = Modifier.weight(0.4f).fillMaxHeight(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Image(
                                painter = painterResource(id = R.drawable.text_logo_stopifty),
                                contentDescription = "LogoStopify",
                                modifier = Modifier.size(80.dp),
                                contentScale = ContentScale.Fit
                            )
                            Text("Join Stopify", color = StopifyWhite, fontSize = 30.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                    Box(
                        modifier = Modifier
                            .weight(0.6f)
                            .fillMaxHeight()
                            .background(SpotifyDarkGrey, RoundedCornerShape(16.dp))
                            .padding(24.dp)
                    ) {
                        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                            RegisterForm(viewModel, navController)
                        }
                    }
                }
            }
        }

        // Loading Overlay
        if (viewModel.cargando) {
            CircularProgressIndicator(color = StopifyGreen, modifier = Modifier.align(Alignment.Center))
        }
    }
}

@Composable
fun RegisterForm(viewModel: StopifyViewModel, navController: NavController) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Registrat Gratis", color = StopifyWhite, fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(24.dp))

        // Missatge d'Error
        viewModel.missatgeError?.let {
            Text(text = it, color = Color.Red, fontSize = 14.sp)
            Spacer(modifier = Modifier.height(8.dp))
        }

        // FIELDS
        RegisterTextField(value = viewModel.nomRegistre, onChange = { viewModel.nomRegistreChange(it) }, label = "Nom Complet")
        Spacer(modifier = Modifier.height(8.dp))

        RegisterTextField(value = viewModel.cumpleRegistre, onChange = { viewModel.cumpleRegistreChange(it) }, label = "Aniversari (DD/MM/YYYY)", keyboardType = KeyboardType.Number)
        Spacer(modifier = Modifier.height(8.dp))

        RegisterTextField(value = viewModel.emailRegistre, onChange = { viewModel.emailRegistreChange(it) }, label = "Correu Electronic", keyboardType = KeyboardType.Email)
        Spacer(modifier = Modifier.height(8.dp))

        RegisterTextField(value = viewModel.telefonRegistre, onChange = { viewModel.telefonRegistreChange(it) }, label = "Telefon", keyboardType = KeyboardType.Phone)
        Spacer(modifier = Modifier.height(8.dp))

        RegisterTextField(value = viewModel.usuariRegistre, onChange = { viewModel.usuariRegistreChange(it) }, label = "Usuari")
        Spacer(modifier = Modifier.height(8.dp))

        RegisterTextField(
            value = viewModel.contrasenyaRegistre,
            onChange = { viewModel.contrasenyaRegistreChange(it) },
            label = "Contrasenya",
            isPassword = true,
            isPasswordVisible = viewModel.contrasenyaVisible,
            onVisibilityToggle = { viewModel.alternarContrasenyaVisibile() }
        )
        Spacer(modifier = Modifier.height(8.dp))

        RegisterTextField(
            value = viewModel.confirmarContrasenyaRegistre,
            onChange = { viewModel.confirmarContrasenyaRegistreChange(it) },
            label = "Confirmar Contrasenya",
            isPassword = true,
            isPasswordVisible = viewModel.contrasenyaVisible,
            onVisibilityToggle = { viewModel.alternarContrasenyaVisibile() }
        )
        Spacer(modifier = Modifier.height(16.dp))

        // TERMS CHECKBOX
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = viewModel.acceptarTermesRegistre,
                onCheckedChange = { viewModel.termesRegistreChange(it) },
                colors = CheckboxDefaults.colors(checkedColor = StopifyGreen, uncheckedColor = StopifyGrey)
            )
            Text("Accepto els Termes i Condicions", color = StopifyWhite, fontSize = 12.sp)
        }
        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { viewModel.registerSeleccionat(navController) },
            modifier = Modifier.fillMaxWidth().height(48.dp),
            colors = ButtonDefaults.buttonColors(containerColor = StopifyGreen),
            shape = CircleShape
        ) {
            Text("REGISTRAR-SE", color = Color.Black, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(16.dp))
        TextButton(onClick = { navController.popBackStack() }) {
            Text("Ja tens una compta? Inicia Sessió", color = SpotifyTextGrey)
        }
    }
}

@Composable
fun RegisterTextField(
    value: String,
    onChange: (String) -> Unit,
    label: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    isPassword: Boolean = false,
    isPasswordVisible: Boolean = false,
    onVisibilityToggle: () -> Unit = {}
) {
    OutlinedTextField(
        value = value,
        onValueChange = onChange,
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
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        visualTransformation = if (isPassword && !isPasswordVisible) PasswordVisualTransformation() else VisualTransformation.None,
        trailingIcon = {
            if (isPassword) {
                IconButton(onClick = onVisibilityToggle) {
                    Icon(
                        imageVector = if (isPasswordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                        contentDescription = "Toggle",
                        tint = SpotifyTextGrey
                    )
                }
            }
        }
    )
}