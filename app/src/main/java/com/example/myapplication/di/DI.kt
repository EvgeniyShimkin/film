package com.example.myapplication.di


/*object DI {
    val mainModule = module {
        single {MainRepository()}
        single<TmdbApi> {
            val okHttpClient = OkHttpClient.Builder()
                .callTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(HttpLoggingInterceptor().apply {
                    if(BuildConfig.DEBUG) {
                        level = HttpLoggingInterceptor.Level.BASIC
                    }
                })
                .build()
            val retrofit = Retrofit.Builder()
                .baseUrl(ApiConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
            retrofit.create(TmdbApi::class.java)
        }
        single {Interactor(get()), get()}
    }
}*/