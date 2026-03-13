package br.com.daniella

import com.google.gson.Gson
import java.lang.NullPointerException
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse.BodyHandlers
import java.util.Scanner

fun main() {
    val leitura = Scanner(System.`in`)
    println("Informe o codigo do jogo a ser buscado.")
    val busca = leitura.nextLine()
    val endereco = "https://www.cheapshark.com/api/1.0/games?id=$busca"
    val client: HttpClient = HttpClient.newHttpClient()

    val request = HttpRequest.newBuilder()
        .uri(URI.create(endereco))
        .build()

    val response = client.send(request, BodyHandlers.ofString())

    val json = response.body()
//    println(json)

    val gson = Gson()
    var meuJogo:Jogo? = null

    val resultado = runCatching {
        // Se o JSON for "[]", esta linha vai lançar a exceção que você viu
        val meuInfoJogo = gson.fromJson(json, InfoJogo::class.java)

        meuJogo = Jogo(
            meuInfoJogo.info.title,
            meuInfoJogo.info.thumb
        )
    }

    // Se o runCatching pegar qualquer erro (JsonSyntaxException ou NullPointer), ele cai aqui:
    resultado.onFailure {
        println("Jogo Inexistente: Tente outro ID")
    }

    // Opcional: Você pode usar o onSuccess para algo que deva acontecer apenas no sucesso
    resultado.onSuccess {
        println("Deseja inserir uma descrição personalizada ? S/N")
        val opcao = leitura.nextLine()
        if (opcao.equals("s", ignoreCase = true)){
            println("Insira a descrição personalizada para o jogo.")
            val descricaoPersonalizada = leitura.nextLine()
            meuJogo?.descricao = descricaoPersonalizada
        } else {
             meuJogo?.descricao = meuJogo?.titulo
        }
        println(meuJogo)
    }
    resultado.onSuccess {
        println("Resultado finalizado com Sucesso !")
    }
}