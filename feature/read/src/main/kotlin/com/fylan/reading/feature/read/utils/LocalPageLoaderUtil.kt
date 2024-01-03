package com.fylan.reading.feature.read.utils

import android.content.Context
import com.fylan.reading.core.database.model.BookChapter
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.RandomAccessFile
import java.util.UUID
import java.util.regex.Pattern

/**
 * @author Create by f.
 * @date 2023/12/30
 * Describe: 将书籍根据章节分页
 */
object LocalPageLoaderUtil {

    //正则表达式章节匹配模式
    // "(第)([0-9零一二两三四五六七八九十百千万壹贰叁肆伍陆柒捌玖拾佰仟]{1,10})([章节回集卷])(.*)"
    private val CHAPTER_PATTERNS = arrayOf(
        "^(.{0,8})(\u7b2c)([0-9\u96f6\u4e00\u4e8c\u4e24\u4e09\u56db\u4e94\u516d\u4e03\u516b\u4e5d\u5341\u767e\u5343\u4e07\u58f9\u8d30\u53c1\u8086\u4f0d\u9646\u67d2\u634c\u7396\u62fe\u4f70\u4edf]{1,10})([\u7ae0\u8282\u56de\u96c6\u5377])(.{0,30})$",
        "^(\\s{0,4})([\\(\u3010\u300a]?(\u5377)?)([0-9\u96f6\u4e00\u4e8c\u4e24\u4e09\u56db\u4e94\u516d\u4e03\u516b\u4e5d\u5341\u767e\u5343\u4e07\u58f9\u8d30\u53c1\u8086\u4f0d\u9646\u67d2\u634c\u7396\u62fe\u4f70\u4edf]{1,10})([\\.:\uff1a\u0020\u000c\t])(.{0,30})$",
        "^(\\s{0,4})([\\(\uff08\u3010\u300a])(.{0,30})([\\)\uff09\u3011\u300b])(\\s{0,2})$",
        "^(\\s{0,4})(\u6b63\u6587)(.{0,20})$",
        "^(.{0,4})(Chapter|chapter)(\\s{0,4})([0-9]{1,4})(.{0,30})$"
    )

    private const val BUFFER_SIZE = 512 * 1024

    fun Context.assetToFile(assetName: String): File? {
        val file = File(filesDir, assetName)
        if (file.exists()) return file
        try {
            assets.open(assetName).use { input ->
                FileOutputStream(file).use { output ->
                    input.copyTo(output)
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
        return file
    }


    fun loadChapters(file: File): List<BookChapter> {
        val bookId = UUID.randomUUID().toString()
        //章节列表
        val chapter = arrayListOf<BookChapter>()

        val randomAccessFile = RandomAccessFile(file, "r")

        //获取到当前数据章节的正则
        val chapterPattern = checkChapterPattern(randomAccessFile)

        val buffer = ByteArray(BUFFER_SIZE)

        var readLength = 0
        var curOffset = 0

        while ((randomAccessFile.read(buffer, 0, buffer.size).also { readLength = it }) > 0) {
            //将读取到的字节数组转成字符串
            val blockContent = String(buffer)

            //当前查找位置
            var seekPos = 0
            val matcher = chapterPattern.matcher(blockContent)

            while (matcher.find()) {
                val chapterStart = matcher.start()
                if (chapterStart > 0) {
                    //获取章节标题前面的文案
                    val content = blockContent.substring(seekPos, chapterStart)

                    seekPos += content.length

                    if (chapter.isEmpty()) {
                        //序章
                        val prologue = BookChapter(bookId, "序章", 0, content)
                        chapter.add(prologue)
                    } else {
                        val lastChapter = chapter.last()
                        lastChapter.chapterContent = lastChapter.chapterContent + content
                    }

                    //创建当前章节
                    val newChapter = BookChapter(bookId, matcher.group(), chapter.size)
                    chapter.add(newChapter)
                } else {
                    val content = blockContent.substring(seekPos, matcher.start())
                    if (chapter.isEmpty()) {
                        val lastChapter = chapter.last()
                        lastChapter.chapterContent = lastChapter.chapterContent + content
                    } else {
                        val newChapter = BookChapter(bookId, matcher.group(), chapter.size)
                        newChapter.chapterContent = content
                        chapter.add(newChapter)
                    }
                }
            }
        }

        return chapter
    }


    fun checkChapterPattern(bookStream: RandomAccessFile): Pattern {
        var tagPattern: Pattern? = null
        val buffer = ByteArray(BUFFER_SIZE / 4)
        val length = bookStream.read(buffer, 0, buffer.size)
        val blockStr = String(buffer)

        CHAPTER_PATTERNS.forEach { str ->
            val pattern = Pattern.compile(str, Pattern.MULTILINE)
            val matcher = pattern.matcher(blockStr)
            if (matcher.find()) {
                //这个书记章节使用的这个正则
                tagPattern = pattern
                return@forEach
            }
        }
        //重置指针位置
        bookStream.seek(0)
        // TODO: 这块测试先认定不为空
        return tagPattern!!
    }

}