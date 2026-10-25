// Create a network interceptor
// ruleid: android-tls-certificate-transparency
val builder = CTInterceptorBuilder()
    .setFailOnError(isFailOnError)
    .setLogger(defaultLogger)
    .setDiskCache(AndroidDiskCache(application))

for (host in hosts) {
    builder.includeHost(host)
}

val networkInterceptor: Interceptor = builder.build()

// Set the interceptor when creating the OkHttp client
return OkHttpClient.Builder()
    .addNetworkInterceptor(networkInterceptor)
    .build()


val httpsConnection = connection as HttpsURLConnection

// Create a hostname verifier wrapping the original
// ruleid: android-tls-certificate-transparency
val builder = CTHostnameVerifierBuilder(httpsConnection.hostnameVerifier)
    .setFailOnError(isFailOnError)
    .setLogger(defaultLogger)
    .setDiskCache(AndroidDiskCache(application))

for (host in hosts) {
    builder.includeHost(host)
}

httpsConnection.hostnameVerifier = builder.build()


// Create a hostname verifier wrapping the original
// ruleid: android-tls-certificate-transparency
val builder = CTHostnameVerifierBuilder(httpsConnection.hostnameVerifier)
    .setFailOnError(isFailOnError)
    .setLogger(defaultLogger)
    .setDiskCache(AndroidDiskCache(application))

for (host in hosts) {
    builder.includeHost(host)
}

httpsConnection.hostnameVerifier = builder.build()