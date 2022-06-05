package lab5

import lab2.*
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class ShapeCollectorTest {
    private val firstColor = ColorOfRGBA(2, 123, 56, 0.31)
    private val secondColor = ColorOfRGBA(3, 4, 56, 0.23)

    @Test
    fun addAllTest() {
        val figures = ShapeCollector<ColoredShape2d>()
        figures.addFigures(Circle(2, firstColor, secondColor))
        figures.addFigures(Square(2.0, 2.0,  firstColor, secondColor))
        figures.addFigures(Square(2.0, 3.0, firstColor, secondColor))
        figures.addFigures(Triangle(2.0, 3.0, 2.0, firstColor, secondColor))
        val newList = listOf(Circle(1, firstColor, secondColor), Circle(2, firstColor, secondColor))
        figures.addAll(newList)
        val expected = listOf(
            Circle(2, firstColor, secondColor),
            Square(2.0, 2.0, firstColor, secondColor),
            Square(2.0, 3.0, firstColor, secondColor),
            Triangle(2.0, 3.0, 2.0, firstColor,secondColor)
        ) + newList
        assertEquals(expected, figures.listAllFigures())
    }

    @Test
    fun getSortedTest() {
        val figures = ShapeCollector<ColoredShape2d>()
        figures.addFigures(Circle(2, firstColor, secondColor))
        figures.addFigures(Square(2.0, 2.0, firstColor, secondColor))
        figures.addFigures(Square(2.0, 3.0, firstColor, secondColor))
        figures.addFigures(Triangle(2.0, 3.0, 2.0, firstColor, secondColor))
        val actual = figures.getSorted()
        val expected = listOf(
            Triangle(2.0, 3.0, 2.0, firstColor, secondColor),
            Square(2.0, 2.0, firstColor, secondColor),
            Square(2.0, 3.0, firstColor, secondColor),
            Circle(2, firstColor, secondColor)
        )
        assertEquals(expected, actual)
    }
}