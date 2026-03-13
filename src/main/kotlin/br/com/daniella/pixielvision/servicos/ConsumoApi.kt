package br.com.daniella.br.com.daniella.pixielvision.servicos

import br.com.daniella.br.com.daniella.pixielvision.modelo.InfoJogo
import br.com.daniella.br.com.daniella.pixielvision.modelo.Jogo
import com.google.gson.Gson
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse.BodyHandlers

class ConsumoApi {
    fun buscaJogo(id: String): InfoJogo {
        val endereco = "https://www.cheapshark.com/api/1.0/games?id=$id"
        val client: HttpClient = HttpClient.newHttpClient()

        val request = HttpRequest.newBuilder()
            .uri(URI.create(endereco))
            .build()

        val response = client.send(request, BodyHandlers.ofString())

        val json = response.body()
        val gson = Gson()

// Proteção: Se a API retornar vazio, não tentamos formatar nem fazer o parse
        if (json.isNullOrBlank() || json == "[]") {
            // Retornamos um objeto vazio ou lançamos erro para o runCatching pegar
            throw IllegalArgumentException("ID inválido ou não encontrado")
        }

        val jsonFormatado = if (json.startsWith("[")) {
            json.substring(1, json.length - 1)
        } else {
            json
        }

// Se o jsonFormatado for vazio (ex: "[]" virou ""), o fromJson daria erro.
        return gson.fromJson(jsonFormatado, InfoJogo::class.java)

    }
}
