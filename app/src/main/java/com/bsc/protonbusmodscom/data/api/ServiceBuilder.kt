package com.bsc.protonbusmodscom.data.api

import com.bsc.protonbusmodscom.settings.settingsURL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.conscrypt.Conscrypt
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception
import java.lang.RuntimeException
import java.security.SecureRandom
import java.security.Security
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.*
import javax.security.cert.CertificateException

object ServiceBuilder {

    private fun getClient(): OkHttpClient {

        Security.insertProviderAt(Conscrypt.newProvider(), 1);

        val clientBuilder = OkHttpClient().newBuilder().apply {

           ignoreAllSSLErrors()
        }

        clientBuilder.readTimeout(60, TimeUnit.SECONDS)
        clientBuilder.connectTimeout(60, TimeUnit.SECONDS)

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        clientBuilder.addInterceptor(interceptor)
        return clientBuilder.build()
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl(settingsURL.api_urlbase) // change this IP for testing by your actual machine IP
        .addConverterFactory(GsonConverterFactory.create())
        .client(getClient())
        .build()

    fun getRetrofitInstance(): RestApi {
        return retrofit.create(RestApi::class.java)
    }

    private fun OkHttpClient.Builder.ignoreAllSSLErrors(): OkHttpClient.Builder {
        val naiveTrustManager = object : X509TrustManager {
            override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
            override fun checkClientTrusted(certs: Array<X509Certificate>, authType: String) = Unit
            override fun checkServerTrusted(certs: Array<X509Certificate>, authType: String) = Unit
        }

        val insecureSocketFactory = SSLContext.getInstance("TLSv1.3").apply {
            val trustAllCerts = arrayOf<TrustManager>(naiveTrustManager)
            init(null, trustAllCerts, SecureRandom())
        }.socketFactory

        sslSocketFactory(insecureSocketFactory, naiveTrustManager)
        hostnameVerifier(HostnameVerifier { _, _ -> true })
        return this
    }
}