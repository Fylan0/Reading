package com.fylan.reading.feature.settings

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.documentfile.provider.DocumentFile
import androidx.lifecycle.AndroidViewModel
import com.fylan.reading.core.data.BookRepository
import com.fylan.reading.core.database.model.BookEntity
import com.fylan.reading.feature.settings.utils.LocalPageLoaderUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * @author Create by f.
 * @date 2024/1/4
 * Describe:
 */
@HiltViewModel
class FilePickViewModel @Inject constructor(
    private val bookRepository: BookRepository,
    application: Application,
) : AndroidViewModel(application) {

    // TODO: 使用注解和hilt生成
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO

    suspend fun handleSelectedFile(
        selectedFile: DocumentFile?,
        onClick: (String) -> Unit,
    ) {

        try {
            if (selectedFile != null && selectedFile.isFile) {
                Log.d("FilePicker", "Selected File Name: ${selectedFile.name}")
                Log.d("FilePicker", "Selected File URI: ${selectedFile.uri}")

                val bookEntity = BookEntity(
                    bookName = selectedFile.name,
                    bookContent = selectedFile.uri.toString(),
                )
                //将选择的书籍文件根基拆分章节
                val chapters = LocalPageLoaderUtil.loadChapters(getApplication(), selectedFile,bookEntity.bookId)
                if (chapters == null) {
                    Toast.makeText(getApplication(), "添加失败，解析错误", Toast.LENGTH_SHORT).show()
                } else {

                    withContext(ioDispatcher) {
                        // TODO: 异布[Synchronizer] !!!!!!!
                        bookRepository.insertBook(bookEntity)
                        bookRepository.insertBookChapter(chapters)
                    }
                    Toast.makeText(getApplication(), "添加成功", Toast.LENGTH_SHORT).show()
                }

            } else {
                Log.e("FilePicker", "Invalid selected file")
                Toast.makeText(getApplication(), "添加失败，文件不存在", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(getApplication(), "添加失败，异常", Toast.LENGTH_SHORT).show()
        }

        //关闭当前页面
        onClick("OK")
    }
}