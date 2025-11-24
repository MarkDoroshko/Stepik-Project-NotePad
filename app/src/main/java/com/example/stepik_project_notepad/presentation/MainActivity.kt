package com.example.stepik_project_notepad.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.stepik_project_notepad.presentation.navigation.NavGraph
import com.example.stepik_project_notepad.presentation.ui.theme.StepikProjectNotePadTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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