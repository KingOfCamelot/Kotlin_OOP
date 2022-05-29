package lab2

import kotlin.math.sqrt

import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import java.io.File

fun main() {
    val firstColor = ColorOfRGBA(2, 123, 56, 0.31)
    val secondColor = ColorOfRGBA(3, 4, 56, 0.23)
    val thirdColor = ColorOfRGBA(45, 234, 12, 1.0)
    val fourthColor = ColorOfRGBA(5, 103, 78, 0.78)
    val circle = Circle(2, firstColor, secondColor)
    val firstSquare = Square(12.0, 15.0, thirdColor, firstColor)
    val secondSquare = Square(6.0, 13.0, fourthColor, secondColor)
    val triangle = Triangle(6.0, 13.0, 23.0, fourthColor, secondColor)
    val figureList = ShapeCollector<ColoredShape2d>()

    figureList.addFigures(circle)
    figureList.addFigures(firstSquare)
    figureList.addFigures(secondSquare)
    figureList.addFigures(triangle)

    //sum of all figure
    figureList.sumSquareFigures()
    //list of all stored figure
    figureList.listAllFigures()
    //ашпгку list size
    figureList.sizeFigureList()
    //Returning a figure with a min area
    figureList.minSquare()
    //Returning a figure with a max area
    figureList.maxSquare()
    //return shapes grouped by fill color
    figureList.mapFillColor()
    //return shapes grouped by border color
    figureList.mapBorderColor()
    //find all shapes by border color
    figureList.searchBorderColor(thirdColor)
    //find all shapes by fill color
    figureList.searchFillColor(secondColor)
    //finding shapes by a specific type
    figureList.searchType()
}

val json = Json {
    prettyPrint = true
    serializersModule = SerializersModule {
        polymorphic(ColoredShape2d::class) {
            subclass(Circle::class)
            subclass(Square::class)
            subclass(Triangle::class)
        }
    }
}

class Serialization
{
    fun encode(input: ShapeCollector<ColoredShape2d>): String {
        return json.encodeToString(input)
    }

    fun input(line: String): String{
        return File(line).readText()
    }

    fun decode(output: String): ShapeCollector<ColoredShape2d> {
        return json.decodeFromString(output)
    }

    fun output(text: String, line: String){
        File(line).writeText(text)
    }
}

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

@kotlinx.serialization.Serializable
class ShapeCollector<T : ColoredShape2d> {
    private var figureList: ArrayList<T> = arrayListOf()
    private var sumSquare: Double = 0.0

    fun addFigures(figure: T) {
        figureList.add(figure)
    }

    fun listAllFigures(): ArrayList<T> {
        return figureList
    }

    fun sumSquareFigures(): Double {
        val leftBorder = figureList.size - 1
        for (i in 0..leftBorder) {
            sumSquare += figureList[i].calcArea()
        }
        println(sumSquare)
        return sumSquare
    }

    fun sizeFigureList(): Int {
        return figureList.size
    }

    fun minSquare(): ColoredShape2d {
        val size = figureList.size - 1
        var minSquare = figureList[0].calcArea()
        var minFigure: ColoredShape2d = figureList[0]
        for (i in 0..size) {
            if (figureList[i].calcArea() <= minSquare) {
                minSquare = figureList[i].calcArea()
                minFigure = figureList[i]
            }
        }
        println("min: $minSquare")
        return minFigure
    }

    fun maxSquare(): ColoredShape2d {
        val size = figureList.size - 1
        var maxSquare = figureList[0].calcArea()
        var maxFigure: ColoredShape2d = figureList[0]
        for (i in 0..size) {
            if (figureList[i].calcArea() >= maxSquare) {
                maxSquare = figureList[i].calcArea()
                maxFigure = figureList[i]
            }
        }
        println("min: $maxSquare")
        return maxFigure
    }

    fun searchBorderColor(key: ColorOfRGBA): ArrayList<T> {
        val listKeepFiguries: ArrayList<T> = arrayListOf()
        val size = figureList.size - 1
        for (i in 0..size) {
            if (figureList[i].borderColor == key) {
                listKeepFiguries.add(figureList[i])
            }
        }
        return listKeepFiguries
    }

    fun searchFillColor(key: ColorOfRGBA): ArrayList<T> {
        val listKeepFiguries: ArrayList<T> = arrayListOf()
        val size = figureList.size - 1
        for (i in 0..size) {
            if (figureList[i].fillColor == key) {
                listKeepFiguries.add(figureList[i])
            }
        }
        return listKeepFiguries
    }

    fun mapFillColor(): Map<ColorOfRGBA, List<T>> {
        return figureList.groupBy { it.fillColor }
    }

    fun mapBorderColor(): Map<ColorOfRGBA, List<T>> {
        return figureList.groupBy { it.borderColor }
    }

    fun searchType(): Map<Class<Any>, List<T>> = figureList.groupBy { it.javaClass }

    fun addAll(shapeList: List<T>) {
        figureList.forEach()
        {
            figureList.add(it)
        }
    }

    fun getSorted(comparator: Comparator<T>): List<T>
    {
        val sortList = figureList
        sortList.sortWith(comparator)
        return sortList
    }
}