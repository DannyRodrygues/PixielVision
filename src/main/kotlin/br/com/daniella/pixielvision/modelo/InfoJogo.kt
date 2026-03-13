package br.com.daniella.br.com.daniella.pixielvision.modelo

data class InfoJogo(val info: InfoApiShark) {
    override fun toString(): String {
        return info.toString()
    }
}