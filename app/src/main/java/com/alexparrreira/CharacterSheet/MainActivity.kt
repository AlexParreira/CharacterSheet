package com.alexparrreira.CharacterSheet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.alexparrreira.CharacterSheet.Destinations.*
import com.alexparrreira.CharacterSheet.ui.theme.CharacterSheetTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CharacterSheetTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    PrincipalDisplay()
                }
            }
        }
    }
}

@Composable
fun PrincipalDisplay() {

    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val navigationItens = listOf(
        Display1,
        Display2,
        Display3,
        Display4,
        Display5,
        Display6
    )
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { TopBar(scope, scaffoldState) },
        drawerContent = {
            Drawer(
                scope,
                scaffoldState,
                navController,
                menuItems = navigationItens
            )
        }

    ) {
        NavigationHost(navController)
    }
}

@Composable
fun TopBar(
    scope: CoroutineScope,
    scaffoldState: ScaffoldState
) {
    TopAppBar(
        backgroundColor = Color.Gray,
        title = {
            Text(
                text = "Texto Aqui!",
                fontSize = 30.sp,
                color = Color.White,
                fontFamily = fontTorm
            )
        },
        navigationIcon = {
            IconButton(onClick = {
                scope.launch {
                    scaffoldState.drawerState.open()
                }
            }) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    tint = Color.White,
                    contentDescription = "Icon Menu"

                )
            }
        }
    )
}

@Composable
fun Drawer(
    scope: CoroutineScope,
    scaffoldState: ScaffoldState,
    navController: NavHostController,
    menuItems: List<Destinations>,


    ) {

    Column(
        Modifier
            .background(Color.Gray)
            .fillMaxHeight()
    ) {
        Image(
            painterResource(id = R.drawable.ic_no_image_foreground),
            contentDescription = "Options Menu",
            modifier = Modifier
                .height(160.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )
        Spacer(
            modifier = Modifier
                .height(15.dp)
                .fillMaxWidth()

        )
        val currentRoute = currentRoute(navController)
        menuItems.forEach { item ->
            DrawerItem(
                item = item,
                selected = currentRoute == item.route
            ) {
                navController.navigate(item.route) {
                    launchSingleTop = true
                }

                scope.launch {
                    scaffoldState.drawerState.close()
                }

            }

        }
    }
}

@Composable
fun DrawerItem(
    item: Destinations,
    selected: Boolean,
    onItemClick: (Destinations) -> Unit
) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(75.dp)
            .padding(8.dp)
            .clip(RoundedCornerShape(12))
            .background(if (selected) MaterialTheme.colors.primaryVariant.copy(alpha = 025f) else Color.Transparent)
            .padding(8.dp)
            .clickable { onItemClick(item) }


    ) {
        Image(
            painterResource(id = item.icon),
            contentDescription = item.title,
            Modifier.width(75.dp)
        )

        Spacer(modifier = Modifier.width(25.dp))
        Text(
            textAlign = TextAlign.Center,
            text = item.title,
            color = Color.White,
            fontSize = 18.sp,
            fontFamily = fontTorm,
            style = MaterialTheme.typography.body1
        )

    }
}

@Composable
fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}

@Preview(showBackground = true)
@Composable
fun PrincipalDisplayPreview() {
    CharacterSheetTheme {
        PrincipalDisplay()
    }
}