package com.alexparrreira.CharacterSheet

sealed class Destinations(
    val icon: Int,
    val title: String,
    val route: String
){
    object Display1: Destinations(R.drawable.nivel,"Atributos", "display1")
    object Display2: Destinations(R.drawable.busca,"Pericias", "display2")
    object Display3: Destinations(R.drawable.bruxo,"Poderes", "display3")
    object Display4: Destinations(R.drawable.mochila,"Equipamentos", "display4")
    object Display5: Destinations(R.drawable.feiticos,"Magias", "display5")
    object Display6: Destinations(R.drawable.mapa,"Diário de Viagem", "display6")
}
