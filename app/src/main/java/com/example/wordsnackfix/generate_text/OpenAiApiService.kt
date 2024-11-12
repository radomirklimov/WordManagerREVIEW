package com.example.wordsnackfix.generate_text

import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.Response

interface OpenAiApiService {
    @POST("generate-text")
    suspend fun generateText(@Body request: TextGenerationRequest): Response<TextGenerationResponse>
}

data class TextGenerationRequest(val word: String)
data class TextGenerationResponse(val generatedText: String)

