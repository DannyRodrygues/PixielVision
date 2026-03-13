import br.com.daniella.br.com.daniella.pixielvision.modelo.Gamer

fun main(){
    val gamer1 = Gamer("Daniella", "dani@email.com")
    println(gamer1)

    val gamer2 = Gamer(
        "Marcelo",
        "marcelo@email.com",
        "27/03/1975",
        "celogame" )
    println(gamer2)

    gamer1.let {
        it.dataNascimento = "17/01/82"
        it.usuario = "danivader"
    }.also {
        println(gamer1.idInterno)
    }

    println(gamer1)
    gamer1.usuario = "DaniVader"
    println(gamer1)
}