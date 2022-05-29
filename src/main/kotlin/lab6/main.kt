package lab6

import lab2.*

fun main()
{
    val firstColor = ColorOfRGBA(2, 123, 56, 0.31)
    val secondColor = ColorOfRGBA(3, 4, 56, 0.23)
    val circle = Circle(2, firstColor, secondColor)
    val firstSquare = Square(12.0, 15.0, secondColor, firstColor)
    val triangle = Triangle(6.0, 13.0, 23.0, firstColor, secondColor)
    val figureList = ShapeCollector<ColoredShape2d>()

    figureList.addFigures(circle)
    figureList.addFigures(firstSquare)
    figureList.addFigures(triangle)

    val json = Serialization()
    val format = json.encode(figureList)
    json.output(format, "output.json")

    val input = json.input("input.json")
    val decode = json.decode(input) as ArrayList<*>
}