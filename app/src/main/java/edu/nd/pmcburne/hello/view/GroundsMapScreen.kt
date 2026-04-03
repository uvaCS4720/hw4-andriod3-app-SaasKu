package edu.nd.pmcburne.hello.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import edu.nd.pmcburne.hello.viewmodel.PlmViewModel
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// TODO: DONT FORGET TO CITE YOUR SOURCES!!!!


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GroundsMapScreen(vm: PlmViewModel){
    val selectedTag by vm.st.collectAsState()
    val tags by vm.tags.collectAsState()
    val plms by vm.plms.collectAsState()

    var expanded by remember { mutableStateOf(false)}

    Column(modifier = Modifier.padding(16.dp)){
        Text("Selected tag: $selectedTag")
        Text("Unique tag count: ${tags.size}")
        Text("Placemark count: ${plms.size}")

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
    }
}