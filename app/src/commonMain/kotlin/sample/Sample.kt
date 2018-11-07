package sample

import org.rekotlin.*

expect class Sample() {
    fun checkMe(): Int
}

expect object Platform {
    val name: String
}

fun hello(): String = "Hello from ${Platform.name}"

class Proxy {
    fun proxyHello() = hello()
}

data class GameState(val ct: Int) : StateType {
    companion object {
        val INITIAL_STATE = GameState(1)
    }
}

fun reducer(action: Action, state: GameState?): GameState {
    return when (action) {
        is ButtonClickAction -> state!!.copy(ct = state!!.ct + 1)
        else -> throw IllegalArgumentException("Unhandled Action")
    }
}

val gameStore = Store<GameState>(
    reducer = ::reducer,
    state = GameState.INITIAL_STATE,
    middleware = listOf()
)

fun main(args: Array<String>) {
    println(hello())
}