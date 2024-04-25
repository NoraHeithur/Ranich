package com.example.ranich.common

import androidx.annotation.Keep
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.ResponseBody
import retrofit2.Response

fun <T : Any> Response<T>.toResult(): ApiResult<T> {
    try {
        if (this.isSuccessful) {
            this.body()?.let {
                return ApiResult.Success(it)
            } ?: run {
                return ApiResult.Error(code = this.code(), exception = null)
            }
        } else {
            return when (this.code()) {
                404, 422, 500, 503 -> {
                    val errorMessage = errorBody().getErrorMessage()
                    ApiResult.Error(
                        code = this.code(),
                        error = errorMessage?.error,
                        exception = null,
                        message = errorMessage?.message?.first()
                    )
                }

                else -> {
                    ApiResult.Error(code = this.code(), exception = null)
                }
            }
        }
    } catch (e: Exception) {
        return ApiResult.Error(code = this.code(), exception = e)
    }
}

@OptIn(ExperimentalStdlibApi::class)
fun ResponseBody?.getErrorMessage(): ResponseErrorBody? {
    try {
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        val errorBody = this
        errorBody?.charStream()?.readText()?.apply {
            return moshi.adapter<ResponseErrorBody>().fromJson(this)
        }
    } catch (e: Exception) {
        return null
    }
    return null
}

@Keep
@JsonClass(generateAdapter = true)
data class ResponseErrorBody(
    var message: List<String>? = null,
    var error: String? = null,
    var statusCode: Int? = null,
)

sealed class ApiResult<out T : Any> {
    data class Success<out T : Any>(val data: T) : ApiResult<T>()
    data class Error(
        val code: Int? = null,
        val error: String? = null,
        val exception: Exception?,
        val message: String? = null
    ) : ApiResult<Nothing>()
}