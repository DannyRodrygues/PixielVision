package br.com.daniella

import com.sun.management.GcInfo

data class InfoJogo(val info: InfoApiShark) {
    override fun toString(): String {
        return info.toString()
    }
}