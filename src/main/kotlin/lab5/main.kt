package lab5

import lab2.*

fun main()
{
    val firstColor = ColorOfRGBA(2, 123, 56, 0.31)
    val secondColor = ColorOfRGBA(3, 4, 56, 0.23)
    val circle = Circle(2, firstColor, secondColor)
    val square = Square(6.0, 13.0, firstColor, secondColor)
    val triangle = Triangle(6.0, 13.0, 23.0, firstColor, secondColor)
    val figureList = ShapeCollector<ColoredShape2d>()

    figureList.addFigures(circle)
    figureList.addFigures(square)
    figureList.addFigures(triangle)

    val figurelist2 = ShapeCollector<ColoredShape2d>()
    figurelist2.addAll(figureList.listAllFigures())
    figurelist2.getSorted(compareBy{it.calcArea()})
}