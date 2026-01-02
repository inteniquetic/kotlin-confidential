package com.inteniquetic.kotlinobfuscation.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.inteniquetic.kotlinobfuscation.example.config.ObfuscationConfiguration
import com.inteniquetic.kotlinobfuscation.example.config.ObfuscationConfiguration2
import com.inteniquetic.kotlinobfuscation.example.ui.theme.KotlinobfuscationTheme
import com.inteniquetic.kotlinobfuscation.runtime.KotlinObfuscation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        KotlinObfuscation.initialize()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KotlinobfuscationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column {
                        Greeting(
                            name = ObfuscationConfiguration.apiKey(),
                            modifier = Modifier.padding(innerPadding)
                        )
                        Greeting(
                            name = ObfuscationConfiguration.suspiciousDynamicLibraries().toString(),
                            modifier = Modifier.padding(innerPadding)
                        )
                        Greeting(
                            name = ObfuscationConfiguration2.apiKey(),
                            modifier = Modifier.padding(innerPadding)
                        )
                        Greeting(
                            name = ObfuscationConfiguration2.suspiciousDynamicLibraries().toString(),
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    KotlinobfuscationTheme {
        Greeting("Android")
    }
}