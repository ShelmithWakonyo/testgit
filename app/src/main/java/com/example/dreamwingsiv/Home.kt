package com.example.dreamwingsiv

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dreamwingsiv.data.datasource
import com.example.dreamwingsiv.model.destiny
import com.example.dreamwingsiv.ui.theme.DreamWingsIVTheme

class Home : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DreamWingsIVTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    PlacePortrait()
                }
            }
        }
    }
}


@Composable
fun Placelist(recklist: List<destiny>, modifier: Modifier=Modifier) {
    LazyColumn (modifier = modifier){
        items(recklist){
                tour ->
            ListCard(
                recklist = tour,
                modifier = Modifier.padding(10.dp)
            )
        }
    }

}

@Composable
fun ListCard(recklist: destiny, modifier: Modifier=Modifier ) {
    Card(modifier = modifier){
        Column {
            Image(
                painter = painterResource(id = (recklist.imageResourceId)),
                contentDescription = stringResource(id = (recklist.stringResourceId)),
                modifier = Modifier
                    .fillMaxSize()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = LocalContext.current.getString(recklist.stringResourceId),
                modifier = Modifier.padding(20.dp),
                style = MaterialTheme.typography.headlineMedium
            )

        }
    }

}

@Composable
fun PlaneNavigation(modifier: Modifier = Modifier){
    val home = LocalContext.current
    NavigationBar (modifier = modifier,
        containerColor = MaterialTheme.colorScheme.surfaceVariant
    ){
        NavigationBarItem(
            selected = true,
            onClick = {  home.startActivity(Intent(home,Home::class.java)) },
            label = {
                Text(text = stringResource(id = R.string.home))
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = null
                ) }
        )
        NavigationBarItem(selected = false,
            onClick = {  home.startActivity(Intent(home,DropDownList::class.java)) },
            label={
                Text(stringResource(id = R.string.book))
            },

            icon = {
                Icon(
                    imageVector = Icons.Default.Done,
                    contentDescription = null,
                )
            })
        NavigationBarItem(selected = false,
            onClick = {
                home.startActivity(Intent(home,ContactUs::class.java))
            },
            label = {
                Text(stringResource(id = R.string.call))
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.Call,
                    contentDescription = null,
                )
            })
//        NavigationBarItem(selected = false,
//            onClick = {
//                home.startActivity(Intent(home,PaypalPage::class.java))
//            },
//            label = {
//                Text(stringResource(id = R.string.money))
//            },
//            icon = {
//                Icon(
//                    imageVector = Icons.Default.Send,
//                    contentDescription = null,
//                )
//            })


    }
}
@Composable
fun PlaceScreen(modifier: Modifier=Modifier){
    Column (modifier){
        Spacer(Modifier.padding(horizontal = 20.dp))
        Placelist(recklist = datasource().loadplaces())
    }
}
@Composable
fun PlacePortrait(){
    DreamWingsIVTheme {
        Scaffold(bottomBar = { PlaneNavigation()}) {
                padding -> PlaceScreen(Modifier.padding(padding))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DreamWingsIVTheme {
        PlacePortrait()
    }
}