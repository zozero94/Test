package zero.app.data.source.remote

import retrofit2.*
import timber.log.Timber
import java.lang.reflect.Type

object ZeroCallAdapterFactory : CallAdapter.Factory() {
    override fun get(returnType: Type, annotations: Array<out Annotation>, retrofit: Retrofit): CallAdapter<*, *>? {
        val nextCallAdapter =
            retrofit.nextCallAdapter(this@ZeroCallAdapterFactory, returnType, annotations) as CallAdapter<Any, Any>? ?: return null

        return object : CallAdapter<Any, Any> by nextCallAdapter {
            override fun adapt(call: Call<Any>): Any {
                val adapted = nextCallAdapter.adapt(call)
                return if (adapted is Call<*>) {
                    ZeroCall(adapted)
                } else {
                    adapted
                }
            }
        }
    }
}

private class ZeroCall<T> constructor(private val call: Call<T>) : Call<T> by call {
    override fun execute(): Response<T> {
        Timber.tag("zero:call").w("execute")
        // request body converter
        //ok http intercept
        val res = call.execute()
        //ok http intercept
        // response body converter
        Timber.tag("zero:call").w("execute $res")
        return res
    }

    override fun enqueue(callback: Callback<T>) {
        Timber.tag("zero:call").w("enqueue")
        call.enqueue(ZeroCallback(callback))
    }
}

private class ZeroCallback<T> constructor(private val callback: Callback<T>) : Callback<T> by callback {
    override fun onResponse(call: Call<T>, response: Response<T>) {
        Timber.tag("zero:call").w("enqueue $response")
        callback.onResponse(call, response)
    }
}