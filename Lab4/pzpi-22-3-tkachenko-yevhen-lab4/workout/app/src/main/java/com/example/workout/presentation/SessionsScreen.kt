
package com.example.workout.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SessionsScreen(nav: NavController, vm: SessionsVM = viewModel(), authVM: AuthVM = viewModel()) {
    LaunchedEffect(Unit) { vm.load() }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Sessions") },
                actions = {
                    if (authVM.repo.isAdmin) IconButton(onClick = { nav.navigate("admin") }) {
                        Icon(Icons.Default.Settings, contentDescription = null)
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { vm.create() }) { Text("+") }
        }
    ) { padding ->
        LazyColumn(contentPadding = padding) {
            items(vm.list) { s ->
                Card(Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable { }) {
                    Column(Modifier.padding(16.dp)) {
                        Text("Session #${s.sessionId}")
                        Row {
                            TextButton(onClick = { nav.navigate("metrics/${s.sessionId}") }) { Text("Metrics") }
                            TextButton(onClick = { nav.navigate("rec/${s.sessionId}") }) { Text("Recommendations") }
                        }
                        Text("Updated: ${s.updatedAt}", style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
        }
    }
}
