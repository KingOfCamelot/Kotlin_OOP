package lab4

import lab4.controller.Controller

fun main() {
    val maze = Model()
    ConsoleUi(maze)
    Controller(maze)
}