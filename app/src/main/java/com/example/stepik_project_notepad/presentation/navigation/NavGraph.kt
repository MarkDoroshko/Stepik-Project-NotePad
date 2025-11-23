package com.example.stepik_project_notepad.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.stepik_project_notepad.presentation.screens.creation.CreateNoteScreen
import com.example.stepik_project_notepad.presentation.screens.editing.EditNoteScreen
import com.example.stepik_project_notepad.presentation.screens.notes.NotesScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.Notes.route
    ) {
        composable(Screen.Notes.route) {
            NotesScreen(
                onNoteClick = { navController.popBackStack() },
                onAddNoteClick = { navController.popBackStack() }
            )
        }
        composable(Screen.CreateNote.route) {
            CreateNoteScreen(
                onFinished = { navController.popBackStack() }
            )
        }
        composable(Screen.EditNote.route) {
            EditNoteScreen(
                noteId = 5,
                onFinished = { navController.popBackStack() }
            )
        }
    }
}

sealed class Screen(val route: String) {
    data object Notes : Screen("notes")

    data object CreateNote : Screen("create_note")

    data object EditNote : Screen("edit_note")
}