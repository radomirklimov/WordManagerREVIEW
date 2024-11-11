package com.example.wordsnackfix.generate_text

import okhttp3.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface OpenAiApiService {
    @POST("generate-text")
    suspend fun generateText(@Body request: TextGenerationRequest): TextGenerationResponse
}

data class TextGenerationRequest(val word: String)
data class TextGenerationResponse(val generatedText: String)
