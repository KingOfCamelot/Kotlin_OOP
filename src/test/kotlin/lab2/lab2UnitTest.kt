package lab2

import kotlin.test.Test
import kotlin.test.assertEquals

internal class ShapeCollectorTest {
    private val firstColor = ColorOfRGBA(2, 123, 56, 0.31)
    private val secondColor = ColorOfRGBA(3, 4, 56, 0.23)
    private val thirdColor = ColorOfRGBA(45, 234, 12, 1.0)

    @Test
    fun addFigureTests() {
        @Test
        fun addCircleWithException() {
            val collector = ShapeCollector<ColoredShape2d>()
            try {
                collector.addFigures(Circle(-9, firstColor, secondColor))
                assert(false)
            } catch (e: Exception) {
                val expected = "Radius entered incorrectly!!"
                assertEquals(expected, e.message)
            }
        }

        @Test
        fun addSquareWithException() {
            val collector = ShapeCollector<ColoredShape2d>()
            try {
                collector.addFigures(Square(-9.9, -9.9, firstColor, secondColor))
                assert(false)
            } catch (e: Exception) {
                val expected = "Side lengths entered incorrectly!!"
                assertEquals(expected, e.message)
            }
        }

        @Test
        fun addRectangleWithException() {
            val collector = ShapeCollector<ColoredShape2d>()
            try {
                collector.addFigures(Square(-16.9, -9.9, firstColor, secondColor))
                assert(false)
            } catch (e: Exception) {
                val expected = "Side lengths entered incorrectly!!"
                assertEquals(expected, e.message)
            }
        }

        @Test
        fun addTriangleWithException() {
            val collector = ShapeCollector<ColoredShape2d>()
            try {
                collector.addFigures(Triangle(-9.9, 0.0, -9.8, firstColor, secondColor))
                assert(false)
            } catch (e: Exception) {
                val expected = "Side lengths entered incorrectly!!"
                assertEquals(expected, e.message)
            }
            try {
                collector.addFigures(Triangle(2.0, 2.0, 5.0, firstColor, secondColor))
                assert(false)
            } catch (e: Exception) {
                val expected = "You entered an invalid triangle!!!"
                assertEquals(expected, e.message)
            }
        }

        @Test
        fun addFigures() {
            val collector = ShapeCollector<ColoredShape2d>()
            collector.addFigures(Circle(8, firstColor, secondColor))
            collector.addFigures(Circle(10, firstColor, secondColor))
            val list = listOf<ColoredShape2d>(Circle(8, firstColor, secondColor), Circle(10, firstColor, secondColor))
            assertEquals(list, collector.listAllFigures())
        }

        addFigures()
        addTriangleWithException()
        addRectangleWithException()
        addSquareWithException()
        addCircleWithException()
    }

    @Test
    fun minSquareTest() {
        @Test
        fun minSquareNull() {
            val collector = ShapeCollector<ColoredShape2d>()
            val expected = null
            assertEquals(expected, collector.minSquare())
        }

        @Test
        fun minSquare() {
            val collector = ShapeCollector<ColoredShape2d>()
            collector.addFigures(Square(9.0, 9.0, firstColor, secondColor))
            collector.addFigures(Square(16.0, 9.0, firstColor, secondColor))
            val expected = Square(9.0, 9.0, firstColor, secondColor)
            assertEquals(expected, collector.minSquare())
        }

        minSquare()
        minSquareNull()
    }

    @Test
    fun maxSquare() {
        @Test
        fun maxSquareNull() {
            val collector = ShapeCollector<ColoredShape2d>()
            val expected = null
            assertEquals(expected, collector.maxSquare())
        }

        @Test
        fun maxSquare() {
            val collector = ShapeCollector<ColoredShape2d>()
            collector.addFigures(Circle(9, firstColor, secondColor))
            collector.addFigures(Circle(16, firstColor, secondColor))
            val expected = Circle(16, firstColor, secondColor)
            assertEquals(expected, collector.maxSquare())
        }

        maxSquare()
        maxSquareNull()
    }

    @Test
    fun getSumAreaTest() {
        val figure1 = Square(9.0, 9.0, firstColor, secondColor)
        val figure2 = Square(16.0, 9.0, firstColor, secondColor)
        val expected = figure1.calcArea() + figure2.calcArea()
        val collector = ShapeCollector<ColoredShape2d>()
        try {
            collector.sumSquareFigures()
            assert(false)
        } catch (e: Exception) {
            val exception = "You have not added a figure!"
            assertEquals(exception, e.message)
        }
        collector.addFigures(figure1)
        collector.addFigures(figure2)
        assertEquals(expected, collector.sumSquareFigures())
    }

    @Test
    fun searchBorderColorTest() {
        val figure1 = Circle(10, thirdColor, secondColor)
        val figure2 = Circle(9, firstColor, secondColor)
        val expected = listOf<ColoredShape2d>(figure1)
        val collector = ShapeCollector<ColoredShape2d>()
        collector.addFigures(figure1)
        collector.addFigures(figure2)
        assertEquals(expected, collector.searchBorderColor(thirdColor))
    }

    @Test
    fun searchFillColorTest() {
        val figure1 = Circle(10, firstColor, secondColor)
        val figure2 = Circle(9, firstColor, thirdColor)
        val expected = listOf<ColoredShape2d>(figure1)
        val collector = ShapeCollector<ColoredShape2d>()
        collector.addFigures(figure1)
        collector.addFigures(figure2)
        assertEquals(expected, collector.searchFillColor(thirdColor))
    }

    @Test
    fun mapFillColorTest() {
        val figure1 = Circle(10, thirdColor, secondColor)
        val figure2 = Circle(9, firstColor, secondColor)
        val expected = mutableMapOf(thirdColor to listOf<ColoredShape2d>(figure1))
        val collector = ShapeCollector<ColoredShape2d>()
        collector.addFigures(figure1)
        collector.addFigures(figure2)
        assertEquals(expected, collector.mapFillColor())
    }

    @Test
    fun mapBorderColorTest() {
        val figure1 = Circle(10, firstColor, thirdColor)
        val figure2 = Circle(9, firstColor, secondColor)
        val expected = mutableMapOf(thirdColor to listOf<ColoredShape2d>(figure1))
        val collector = ShapeCollector<ColoredShape2d>()
        collector.addFigures(figure1)
        collector.addFigures(figure2)
        assertEquals(expected, collector.mapBorderColor())
    }

}