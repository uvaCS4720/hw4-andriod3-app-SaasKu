package edu.nd.pmcburne.hello.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import edu.nd.pmcburne.hello.viewmodel.PlmViewModel
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerInfoWindowContent
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import edu.nd.pmcburne.hello.model.Placemark

/***************************************************************************************
 * REFERENCES
 * Title:  Maps Compose Library
 * Author: Google Maps Platform
 * URL:https://developers.google.com/maps/documentation/android-sdk/maps-compose
 * Software License: Apache 2 License
 * Usage: I used this as reference to implement the Google map implementation in my code
 ***************************************************************************************/

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GroundsMapScreen(vm: PlmViewModel){
    val selectedTag by vm.st.collectAsState()
    val tags by vm.tags.collectAsState()
    val plms by vm.plms.collectAsState()

    var expanded by remember { mutableStateOf(false)}

/*
    I chose to make the rotunda the center of the map because its the central
    location of the lawn.
 */

    val rotunda_center = LatLng(38.03567, -78.50365)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(rotunda_center, 15f)
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Around Grounds",
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ){
        padding ->
        Column(modifier = Modifier.padding(padding).padding(5.dp)){
            Column(
                modifier = Modifier.fillMaxWidth().padding(5.dp)
            ){
//            Text("Selected tag: $selectedTag")
//            Text("Unique tag count: ${tags.size}")
//            Text("Placemark count: ${plms.size}")

                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = {
                        expanded = !expanded
                    },
                    modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
                ) {
                    OutlinedTextField(
                        value = selectedTag,
                        onValueChange = {},
                        readOnly = true,
                        label = {Text("Tag")},
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded=expanded)
                        },
                        modifier = Modifier.fillMaxWidth().menuAnchor(MenuAnchorType.PrimaryNotEditable)
                    )

                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = {expanded = false},
                        modifier = Modifier.verticalScroll(rememberScrollState())
                    ){
                        tags.forEach{
                                tag ->
                            DropdownMenuItem(
                                text = {Text(tag)},
                                onClick = {
                                    vm.setTag(tag)
                                    expanded = false
                                }
                            )
                        }
                    }
                }
                Text(
                    text = "Click on each description to see the full description!",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth().padding(top = 8.dp, start = 4.dp)
                )

            }
            var selectedPlm by remember { mutableStateOf<Placemark?>(null) }
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState
            ){
                plms.forEach{
                        plm ->
                    val markerState = remember(plm.id){
                        MarkerState(position = LatLng(plm.latitude, plm.longitude))
                    }
                    Marker(
                        state = markerState,
                        title = plm.name,
                        snippet = plm.description,
                        onInfoWindowClick = {
                            selectedPlm = plm
                        }
                    )

                }
            }

            selectedPlm?.let { plm ->
                AlertDialog(
                    onDismissRequest = { selectedPlm = null },
                    confirmButton = {
                        TextButton(onClick = { selectedPlm = null }) {
                            Text("Close")
                        }
                    },
                    title = {
                        Text(plm.name)
                    },
                    text = {
                        Text(plm.description)
                    }
                )
            }



        }

    }
}