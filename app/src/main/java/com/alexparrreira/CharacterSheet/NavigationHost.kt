package com.alexparrreira.CharacterSheet

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.alexparrreira.CharacterSheet.Destinations.*


@Composable
fun NavigationHost(navController: NavHostController){
    NavHost(navController = navController, startDestination = Display1.route){
        composable(Display1.route){
            Atributos()
        }
        composable(Display2.route){
            Pericias()
        }
        composable(Display3.route){
            Poderes()
        }
        composable(Display4.route){
            Equipamentos()
        }
        composable(Display5.route){
            Magias()
        }
        composable(Display6.route){
            DiarioDeViajem()
        }
    }
}