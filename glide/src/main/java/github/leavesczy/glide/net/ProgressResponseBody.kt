package github.leavesczy.glide.net

import android.os.Handler
import android.os.Looper
import android.os.Message
import okhttp3.MediaType
import okhttp3.ResponseBody
import okio.*
import java.io.IOException
import java.lang.ref.WeakReference

/**
 * @Author: leavesCZY
 * @Date: 2020/11/6 21:58
 * @Desc:
 * @Github：https://github.com/leavesCZY
 */
internal class ProgressResponseBody constructor(
    private val imageUrl: String,
    private val responseBody: ResponseBody?
) : ResponseBody() {

    interface ProgressListener {

        fun update(progress: Int)

    }

    companion object {

        private val progressMap = mutableMapOf<String, WeakReference<ProgressListener>>()

        fun addProgressListener(url: String, listener: ProgressListener) {
            progressMap[url] = WeakReference(listener)
        }

        fun removeProgressListener(url: String) {
            progressMap.remove(url)
        }

        private const val CODE_PROGRESS = 100

        private val mainHandler by lazy {
            object : Handler(Looper.getMainLooper()) {
                override fun handleMessage(msg: Message) {
                    if (msg.what == CODE_PROGRESS) {
                        val pair = msg.obj as Pair<String, Int>
                        val progressListener = progressMap[pair.first]?.get()
                        progressListener?.update(pair.second)
                    }
                }
            }
        }

    }

    private var bufferedSource: BufferedSource? = null

    override fun contentType(): MediaType? {
        return responseBody?.contentType()
    }

    override fun contentLength(): Long {
        return responseBody?.contentLength() ?: -1
    }

    override fun source(): BufferedSource {
        if (bufferedSource == null) {
            bufferedSource = source(responseBody!!.source()).buffer()
        }
        return bufferedSource!!
    }

    private fun source(source: Source): Source {
        return object : ForwardingSource(source) {

            var totalBytesRead = 0L

            @Throws(IOException::class)
            override fun read(sink: Buffer, byteCount: Long): Long {
                val bytesRead = super.read(sink, byteCount)
                totalBytesRead += if (bytesRead != -1L) {
                    bytesRead
                } else {
                    0
                }
                val contentLength = contentLength()
                val progress = when {
                    bytesRead == -1L -> {
                        100
                    }
                    contentLength != -1L -> {
                        ((totalBytesRead * 1.0 / contentLength) * 100).toInt()
                    }
                    else -> {
                        0
                    }
                }
                mainHandler.sendMessage(Message().apply {
                    what = CODE_PROGRESS
                    obj = Pair(imageUrl, progress)
                })
                return bytesRead
            }
        }
    }

}