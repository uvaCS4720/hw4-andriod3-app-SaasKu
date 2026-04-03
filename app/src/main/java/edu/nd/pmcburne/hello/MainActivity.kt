package edu.nd.pmcburne.hello

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.nd.pmcburne.hello.ui.theme.MyApplicationTheme


import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.CameraPosition

import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberUpdatedMarkerState
import edu.nd.pmcburne.hello.viewmodel.PlmViewModel
import edu.nd.pmcburne.hello.view.GroundsMapScreen

//class MainActivity : ComponentActivity() {
//    private val viewModel by viewModels<MainViewModel>()
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContent {
//            MyApplicationTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
////                    MainScreen(viewModel, modifier = Modifier.padding(innerPadding))
//                    MapTest(modifier = Modifier.padding(innerPadding))
//                }
//            }
//        }
//    }
//}


// TODO: DONT FORGET TO CITE YOUR SOURCES!!!!
class MainActivity: ComponentActivity(){
    private val vm: PlmViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GroundsMapScreen(vm = vm)
        }
    }
}

//@Composable
//fun MainScreen(
//    viewModel: MainViewModel,
//    modifier: Modifier = Modifier
//) {
//    Column(modifier = modifier) {
//        Text(
//            "Welcome to the Counter App!"
//        )
//        Spacer(modifier = modifier.height(16.dp))
//        Counter(viewModel)
//    }
//}
//
//@Composable
//@Preview(showBackground = true)
//fun PreviewMainScreen() {
//    MyApplicationTheme {
//        MainScreen(viewModel = MainViewModel())
//    }
//}
//
//@Composable
//fun Counter(
//    viewModel: MainViewModel,
//    modifier: Modifier = Modifier
//) {
//    val uiState by viewModel.uiState.collectAsState()
//    val counterValue = uiState.counterValue
//    Row {
//        Text("Value: $counterValue")
//        Button( // increment button
//            onClick = { viewModel.incrementCounter() },
//            modifier = modifier
//        ) { Text("+") }
//        Button( //decrement button
//            onClick = { viewModel.decrementCounter() },
//            enabled = viewModel.isDecrementEnabled,
//            modifier = modifier
//        ) {
//            Text("-")
//        }
//        Button( // reset button
//            onClick = { viewModel.incrementCounter() },
//            enabled = viewModel.isResetEnabled,
//            modifier = modifier
//        ) {
//            Text("Reset")
//        }
//
//    }
//}


//@Preview(name = "Light Mode Counter", showBackground = true)
//@Preview(name = "Dark Mode Counter", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
//@Composable
//fun CounterPreview() {
//    MyApplicationTheme {
//        Counter(viewModel = MainViewModel(0))
//    }
//}


// Test code from documentation to check if my api stuff is working correctly
//https://developers.google.com/maps/documentation/android-sdk/maps-compose

//@Composable
//fun MapTest(modifier: Modifier = Modifier) {
//    val singapore = LatLng(1.35, 103.87)
//    val singaporeMarkerState = rememberUpdatedMarkerState(position = singapore)
//    val cameraPositionState = rememberCameraPositionState {
//        position = CameraPosition.fromLatLngZoom(singapore, 10f)
//    }
//
//    GoogleMap(
//        modifier = modifier.fillMaxSize(),
//        cameraPositionState = cameraPositionState
//    ) {
//        Marker(
//            state = singaporeMarkerState,
//            title = "Singapore",
//            snippet = "Marker in Singapore"
//        )
//    }
//}