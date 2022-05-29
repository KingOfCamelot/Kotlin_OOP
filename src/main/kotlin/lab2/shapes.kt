package lab2

import kotlin.math.sqrt

@kotlinx.serialization.Serializable
data class ColorOfRGBA(val red: Int, val green: Int, val blue: Int, val alpha: Double) {
    override fun toString(): String {
        return "Color figure of RGBA: $red, $green, $blue, $alpha"
    }
}

interface Shape2d {
    fun calcArea(): Double
}

interface ColoredShape2d : Shape2d {
    val borderColor: ColorOfRGBA
    val fillColor: ColorOfRGBA
}

@kotlinx.serialization.Serializable
class Circle(private val radius: Int, override val borderColor: ColorOfRGBA, override val fillColor: ColorOfRGBA) : ColoredShape2d
{
    override fun calcArea(): Double {
        return 3.14 * radius * radius
    }
    override fun toString(): String {
        return "circle"
    }
}

@kotlinx.serialization.Serializable
class Square(private val firstSide: Double, private val secondSide: Double, override val borderColor: ColorOfRGBA, override val fillColor: ColorOfRGBA) : ColoredShape2d
{
    override fun calcArea(): Double {
        return firstSide * secondSide
    }
    override fun toString(): String {
        return "square"
    }
}

@kotlinx.serialization.Serializable
class Triangle(
    private val triangleFirstSide: Double,
    private val triangleSecondSide: Double,
    private val triangleThirdSide: Double,
    override val borderColor: ColorOfRGBA,
    override val fillColor: ColorOfRGBA
) : ColoredShape2d
{
    override fun calcArea(): Double {
        val perimeter = (triangleFirstSide + triangleSecondSide + triangleThirdSide) / 2
        return sqrt(
            perimeter * (perimeter - triangleFirstSide) * (perimeter - triangleSecondSide) * (perimeter - triangleThirdSide)
        )
    }
    override fun toString(): String {
        return "triangle"
    }
}