package com.example.lab_07

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun DriverScreen(navController: NavController) {
    var driver by remember { mutableStateOf("") }

    val driverNames = remember { mutableStateListOf<String>() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        // back button
        Button(
            onClick = {
                navController.popBackStack()
            },
            modifier = Modifier.testTag("backButton") // for testing
        ) {
            Text("Back")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // title text
        Text(
            text = "Race Drivers",
            fontSize = 30.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        // text field and button to add race drivers
        //there's also a button to delete all race drivers added
        Row (
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            TextField(
                value = driver,
                onValueChange = {
                    driver = it // update driver to new value
                },
                label = {
                    Text("Driver name")
                },
                modifier = Modifier
                    .weight(.1f)
                    .testTag("driverInput") // for testing
            )

            Spacer(modifier = Modifier.height(8.dp))

            Spacer(modifier = Modifier.width(8.dp))

            Column (modifier = Modifier.width(IntrinsicSize.Max)) {
                Button(
                    onClick = {
                        if (driver.isNotBlank()) {
                            driverNames.add(driver)
                            driver = ""
                        }
                    },
                    modifier = Modifier.testTag("addDriverButton") // for testing
                ) {
                    Text("Add")
                }

                Button(
                    onClick = {
                        if (driverNames.isNotEmpty()) {
                            driverNames.clear() //clear all drivers in the list
                        }
                    },
                    modifier = Modifier.testTag("ClearDriversButton") // for testing
                ) {
                    Text("Clear")
                }
            }
        }

        // list to display drivers
        LazyColumn {
            items(driverNames) { driver ->
                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = driver,
                    modifier = Modifier.testTag("eachDriver"), //for testing
                    fontSize = 24.sp
                )
            }
        }
    }
}