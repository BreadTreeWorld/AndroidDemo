package com.example.myapplication.model

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun ArticleListScreen() {
    var isLoading by remember { mutableStateOf(true) }
    var articles by remember { mutableStateOf<List<Article>>(emptyList()) }
    var isRefreshing by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(1500)  // 模拟网络请求
        articles = getMockArticles()
        isLoading = false
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("RECYCLER LIST") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF2196F3),
                    titleContentColor = Color.White
                )
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when {
                isLoading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                articles.isEmpty() -> {
                    Text(
                        text = "No data",
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                else -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(vertical = 8.dp)
                    ) {
                        items(articles) { article ->
                            ArticleListItem(
                                title = article.title,
                                desc = article.desc,
                                author = article.author,
                                onItemClick = {
                                    println("Click: ${article.title}")
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}


private fun getMockArticles(): List<Article> {
    return List(20) { index ->
        Article(
            title = "Android开发技巧第${index + 1}篇",
            desc = "这是一个非常实用的Android开发技巧文章，介绍了Compose、协程等现代开发技术。",
            author = "开发者${index + 1}"
        )
    }
}


data class Article(
    val title: String,
    val desc: String,
    val author: String,
    val imageUrl: String? = null
)