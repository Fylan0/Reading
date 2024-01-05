package com.fylan.reading.feature.settings

import android.content.Intent
import android.net.Uri
import android.provider.DocumentsContract
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.documentfile.provider.DocumentFile
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

/**
 * @author Create by f.
 * @date 2024/1/4
 * Describe:
 */

@Composable
fun FilePickPage(
    viewModel: FilePickViewModel = hiltViewModel(),
    onClick: (String) -> Unit,
) {
    val context = LocalContext.current

//    var selectedFile by remember { mutableStateOf<Uri?>(null) }

    val pickFileLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == ComponentActivity.RESULT_OK) {
            val data: Intent? = result.data
            data?.data?.let { uri ->
                val selectedFile = DocumentFile.fromSingleUri(context, uri)
                viewModel.viewModelScope.launch {
                    viewModel.handleSelectedFile(selectedFile = selectedFile, onClick = onClick)
                }
            }
        }
    }
    //!!!在Compose中，启动ActivityResultLauncher通常需要在LaunchedEffect中进行，
    // 以确保在Compose的生命周期内适当地初始化。如果在Composable函数中直接调用launch方法而不在LaunchedEffect或DisposableEffect中，
    // 可能会导致启动时机不正确，从而引发IllegalStateException: Launcher has not been initialized异常。
    LaunchedEffect(pickFileLauncher) {
        val intent = createFilePickerIntent()
        pickFileLauncher.launch(intent)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent) // 设置透明背景
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "文件选择")
    }
}


private fun createFilePickerIntent() = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
    addCategory(Intent.CATEGORY_OPENABLE)
//    type = "*/*" // Set the MIME type to allow any file type
    type = "text/plain" // Set the MIME type to allow any file type
    putExtra(DocumentsContract.EXTRA_INITIAL_URI, getDownloadsDirectoryUri())
}

private fun getDownloadsDirectoryUri(): Uri {
    return Uri.parse("content://com.android.providers.downloads.documents/root/downloads")
}