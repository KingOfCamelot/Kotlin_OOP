package lab2

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