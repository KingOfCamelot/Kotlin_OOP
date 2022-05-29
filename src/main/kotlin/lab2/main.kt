package lab2

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
    //list size
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