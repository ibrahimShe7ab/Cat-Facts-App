                package com.shehab.applestore

                import android.os.Bundle
                import androidx.activity.ComponentActivity
                import androidx.activity.compose.setContent
                import androidx.compose.foundation.clickable
                import androidx.compose.foundation.layout.Arrangement
                import androidx.compose.foundation.layout.Column
                import androidx.compose.foundation.layout.fillMaxSize
                import androidx.compose.foundation.layout.padding
                import androidx.compose.material3.Surface
                import androidx.compose.material3.Text
                import androidx.compose.runtime.Composable
                import androidx.compose.runtime.MutableState
                import androidx.compose.runtime.mutableStateOf
                import androidx.compose.ui.Alignment
                import androidx.compose.ui.Modifier
                import androidx.compose.ui.unit.dp
                import androidx.compose.ui.unit.sp
                import com.shehab.applestore.Models.CatFact
                import com.shehab.applestore.RetrofitInstance.Instance
                import com.shehab.applestore.ui.theme.AppleStoreTheme
                import kotlinx.coroutines.DelicateCoroutinesApi
                import kotlinx.coroutines.Dispatchers
                import kotlinx.coroutines.GlobalScope
                import kotlinx.coroutines.launch
                import kotlinx.coroutines.withContext

                class MainActivity : ComponentActivity() {
                    private var fact = mutableStateOf(CatFact())
                    override fun onCreate(savedInstanceState: Bundle?) {
                        super.onCreate(savedInstanceState)
                         setContent {
                            AppleStoreTheme {
                                Surface(Modifier.clickable { sendRequest()}) {
                                    sendRequest()
                                    MyUi(modifier =Modifier,fact)
                                }
                            }
                        }
                    }

                    @OptIn(DelicateCoroutinesApi::class)
                    private fun sendRequest() {
                GlobalScope.launch(Dispatchers.IO){
                    val response=Instance.api.GetRoudomFact()
                    if (response.isSuccessful ){
                        withContext(Dispatchers.Main){fact.value= response.body()!! }

                    } }

                }


                @Composable
                fun MyUi(modifier: Modifier = Modifier, fact: MutableState<CatFact>) {

                    Column(
                        modifier
                            .padding(20.dp)
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(text = "Cat Facts : ",modifier.padding(10.dp), fontSize = 25.sp)
                    Text(text = fact.value.fact,modifier.padding(10.dp), fontSize = 22.sp )

                    }

                }}
