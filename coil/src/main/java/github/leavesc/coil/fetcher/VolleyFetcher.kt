package github.leavesc.coil.fetcher

import android.app.Application
import android.net.Uri
import coil.bitmap.BitmapPool
import coil.decode.DataSource
import coil.decode.Options
import coil.fetch.FetchResult
import coil.fetch.Fetcher
import coil.fetch.SourceResult
import coil.size.Size
import com.android.volley.NetworkResponse
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.HttpHeaderParser
import com.android.volley.toolbox.RequestFuture
import com.android.volley.toolbox.Volley
import okio.Buffer
import okio.BufferedSource


/**
 * @Author: leavesC
 * @Date: 2020/11/22 13:52
 * @GitHub：https://github.com/leavesC
 * @Desc:
 */
class VolleyFetcher(private val application: Application) : Fetcher<Uri> {

    override fun handles(data: Uri) = data.scheme == "http" || data.scheme == "https"

    override fun key(data: Uri): String? {
        return data.toString()
    }

    private class ImageRequest(url: String, private val listener: RequestFuture<BufferedSource>) :
        Request<BufferedSource>(Method.GET, url, listener) {
        override fun parseNetworkResponse(response: NetworkResponse): Response<BufferedSource> {
            return Response.success(
                Buffer().write(response.data),
                HttpHeaderParser.parseCacheHeaders(response)
            )
        }

        override fun deliverResponse(response: BufferedSource) {
            listener.onResponse(response)
        }
    }

    override suspend fun fetch(
        pool: BitmapPool,
        data: Uri,
        size: Size,
        options: Options
    ): FetchResult {
        val url = data.toString()
        val newFuture = RequestFuture.newFuture<BufferedSource>()
        val request = ImageRequest(url, newFuture)
        newFuture.setRequest(request)
        Volley.newRequestQueue(application).apply {
            add(request)
            start()
        }
        val get = newFuture.get()
        return SourceResult(
            source = get,
            mimeType = "",
            dataSource = DataSource.NETWORK
        )
    }

}
