package lab4

import lab4.Move.*
import java.io.File

enum class State(private val textValue: String) {
    WAIT_MOVE("Waiting for move..."),
    FINISH_GAME("Congratulations. Game finish");

    override fun toString(): String = textValue
}

enum class Move(private val textValue: String) {
    Wait("wait"),
    Up("up"),
    Down("down"),
    Left("left"),
    Right("right");

    override fun toString(): String = textValue
}

interface ModelChangeListener {
    fun onModelChanged()
}

class Coordinates(var x: Int, var y: Int)

fun readMaze(): Map<Pair<Int, Int>, Char> {
    val level = File("C:\\Users\\rogin\\Рабочий стол\\kotlin\\src\\main\\kotlin\\lab4\\maze.txt").readLines().withIndex()
        .flatMap { indexedValue ->
            val xCord = indexedValue.index
            indexedValue.value.toCharArray().withIndex().map { indexedChar ->
                val yCord = indexedChar.index
                (xCord to yCord) to indexedChar.value
            }
        }
        .toMap()
    return level
}

class Model {
    private val field = readMaze().toMutableMap()
    var state: State = State.WAIT_MOVE

    private val listeners: MutableSet<ModelChangeListener> = mutableSetOf()

    fun addModelChangeListener(listener: ModelChangeListener) = listeners.add(listener)
    fun removeModelChangeListener(listener: ModelChangeListener) = listeners.remove(listener)

    private var position = Coordinates(field.filter { it.value == 'E' }.keys.first().second,field.filter { it.value == 'E' }.keys.first().first)

    fun doMove(move: Move)
    {
        when (move) {
            Right -> position.x++
            Left -> position.x--
            Up -> position.y--
            Down -> position.y++
        }
        if (position == Coordinates(field.filter { it.value == 'S' }.keys.first().second, field.filter { it.value == 'S' }.keys.first().first))
        {
            state = State.FINISH_GAME
        } else state = State.WAIT_MOVE
        notifyListeners()
    }

    private fun notifyListeners() = listeners.forEach { it.onModelChanged() }

    override fun toString(): String {
        return buildString {
            append(state).appendLine()
            for (i in 0..5) {
                for (j in 0..8) {
                    if (i == position.y && j == position.x) append('S')
                    else append(field[Pair(i, j)])
                }
                appendLine()
            }
        }
    }
}