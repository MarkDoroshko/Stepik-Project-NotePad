package com.example.stepik_project_notepad.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.stepik_project_notepad.presentation.navigation.NavGraph
import com.example.stepik_project_notepad.presentation.screens.notes.NotesScreen
import com.example.stepik_project_notepad.presentation.ui.theme.StepikProjectNotePadTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StepikProjectNotePadTheme {
                NavGraph()
            }
        }
    }
}