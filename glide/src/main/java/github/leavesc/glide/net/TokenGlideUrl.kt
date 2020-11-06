package github.leavesc.glide.net

import com.bumptech.glide.load.model.GlideUrl
import java.net.URI

/**
 * @Author: leavesC
 * @Date: 2020/11/6 15:13
 * @Desc:
 * GitHub：https://github.com/leavesC
 */
class TokenGlideUrl(private val selfUrl: String) : GlideUrl(selfUrl) {

    override fun getCacheKey(): String {
        val uri = URI(selfUrl)
        val querySplit = uri.query.split("&".toRegex())
        querySplit.forEach {
            val kv = it.split("=".toRegex())
            if (kv.size == 2 && kv[0] == "token") {
                //将包含 token 的键值对移除
                return selfUrl.replace(it, "")
            }
        }
        return selfUrl
    }

}