
package com.example.workout.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun RegisterScreen(nav: NavController, vm: AuthVM = viewModel()) {
    var u by remember { mutableStateOf("") }
    var p by remember { mutableStateOf("") }
    var e by remember { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(u, { u = it }, label = { Text("Username") })
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(p, { p = it }, label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation())
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(e, { e = it }, label = { Text("Email") })
        Spacer(Modifier.height(16.dp))
        Button(onClick = { vm.register(u, p, e, nav) }) { Text("Create account") }
        vm.error?.let { Text(it, color = MaterialTheme.colorScheme.error) }
    }
}
