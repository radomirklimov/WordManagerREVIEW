package com.example.wordsnackfix.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.wordsnack.room.WordViewModel
import com.example.wordsnack.ui_components.MainPage


sealed class Screen(val route: String) {
    object MainScreen : Screen("main_screen")
    object DetailScreen : Screen("detail_screen/{word}/{partOfSpeech}/{transcription}/{translation}") {
        fun createRoute(word: String, partOfSpeech: String, transcription: String, translation: String): String {
            return "detail_screen/$word/$partOfSpeech/$transcription/$translation"
        }
    }
}

@Composable
fun Navigation(viewModel: WordViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(route = Screen.MainScreen.route) {
            MainScreen(navController, viewModel)  // Pass the navController
        }
        composable(
            route = Screen.DetailScreen.route,
            arguments = listOf(
                navArgument("word") { type = NavType.StringType },
                navArgument("partOfSpeech") { type = NavType.StringType },
                navArgument("transcription") { type = NavType.StringType },
                navArgument("translation") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val word = backStackEntry.arguments?.getString("word") ?: ""
            val partOfSpeech = backStackEntry.arguments?.getString("partOfSpeech") ?: ""
            val transcription = backStackEntry.arguments?.getString("transcription") ?: ""
            val translation = backStackEntry.arguments?.getString("translation") ?: ""
            DetailScreen(navController, word, partOfSpeech, transcription, translation)
        }
    }
}


@Composable
fun MainScreen(navController: NavController, viewModel: WordViewModel) {
    MainPage.mainPageMix(viewModel, navController)
}

@Composable
fun DetailScreen(
    navController: NavController,
    word: String,
    partOfSpeech: String,
    transcription: String,
    translation: String
) {
//    WordPage.TheWord(
//        navController,
//        word,
//        partOfSpeech,
//        transcription,
//        translation
//    )
}