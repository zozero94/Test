package zero.data.source.remote

import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import timber.log.Timber
import java.lang.reflect.Type

class ZeroConverterFactory(private val serializationConverterFactory: Converter.Factory) : Converter.Factory() {
    override fun responseBodyConverter(type: Type, annotations: Array<out Annotation>, retrofit: Retrofit): Converter<ResponseBody, *> {
        val converter = serializationConverterFactory.responseBodyConverter(type, annotations, retrofit)
        return Converter {
            val response = converter?.convert(it)
            Timber.tag("zero:converter").e("response call $response")
            response
        }
    }

    override fun requestBodyConverter(
        type: Type,
        parameterAnnotations: Array<out Annotation>,
        methodAnnotations: Array<out Annotation>,
        retrofit: Retrofit
    ): Converter<*, RequestBody>? {
        val converter = serializationConverterFactory.requestBodyConverter(type, parameterAnnotations, methodAnnotations, retrofit)
        Timber.tag("zero:converter").e("request $converter")
        return converter
    }

    override fun stringConverter(type: Type, annotations: Array<out Annotation>, retrofit: Retrofit): Converter<*, String>? {
        return super.stringConverter(type, annotations, retrofit)
    }
}