package lab1

fun findTheYoungestBooks(bookList: List<Book>): Int {
    var maxYearOfIssue = Int.MIN_VALUE
    for (i in bookList.indices) {
        val year: Int = bookList[i].yearOfIssue.toInt()
        if (maxYearOfIssue <= year) maxYearOfIssue = year
    }
    return maxYearOfIssue
}

fun findTheOldestBooks(bookList: List<Book>): Int {
    var minYearOfIssue = Int.MAX_VALUE
    for (i in bookList.indices) {
        val year: Int = bookList[i].yearOfIssue.toInt()
        if (minYearOfIssue >= year) minYearOfIssue = year
    }
    return minYearOfIssue
}

fun findBookLongName(bookList: List<Book>): String {
    var longNameBook = bookList[0].bookName
    for (i in bookList.indices) {
        val length: Int = bookList[i].bookName.length
        if (longNameBook.length <= length) longNameBook = bookList[i].bookName
    }
    return longNameBook
}

fun findBookShortName(bookList: List<Book>): String {
    var shortNameBook = bookList[0].bookName
    val longNameBook = bookList[0].bookName
    for (i in bookList.indices) {
        val length: Int = bookList[i].bookName.length
        if (longNameBook.length >= length) shortNameBook = bookList[i].bookName
    }
    return shortNameBook
}

fun main() {
    val text = """
            1.War and peace// L.N.Tolstoy//1867 
            2.The Witcher// A.Sapkovskiy//1986
            3.The Great Gatsby// F.ScottFitzgerald// 1925
            4.The Invisible Man//H.Wells// 1897
            """
    val bookList: List<Book> = parseBook(text)
    println(
        """${bookList[0].bookName} ${bookList[0].author} ${bookList[0].yearOfIssue}
    ${bookList[1].bookName} ${bookList[1].author} ${bookList[1].yearOfIssue}
    ${bookList[2].bookName} ${bookList[2].author} ${bookList[2].yearOfIssue}
    ${bookList[3].bookName} ${bookList[3].author} ${bookList[3].yearOfIssue}"""
    )
    println("The oldest book: ${findTheOldestBooks(bookList)}, the newest book: ${findTheYoungestBooks(bookList)}")
    println("The shortest book name: ${findBookShortName(bookList)}, the longest book name ${findBookLongName(bookList)}")
}