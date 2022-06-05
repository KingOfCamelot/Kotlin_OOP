package lab1

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Test {

    @Test
    fun `test one correct string parse`() {
        val input = "1.War and peace//L.N.Tolstoy//1867"
        assertEquals(
            listOf(Book("War and peace", "L.N.Tolstoy", "1867")),
            parseBook(input)
        )
    }

    @Test
    fun `test empty string parse`() {
        val input = ""
        assertEquals(
            true,
            parseBook(input).isEmpty()
        )
    }

    @Test
    fun `test algorithms of finding the oldest, the latest, the longest and the shortest books from correct string`() {
        val input = """
            1.War and peace// L.N.Tolstoy//1867
            2.The Witcher// A.Sapkovskiy//1986
            3.The Great Gatsby// F.ScottFitzgerald//1925
            4.The Invisible Man//H.Wells//1897
            """
        assertEquals(
            listOf(Book("War and peace", "L.N.Tolstoy", "1867")),
            findTheOldestBooks(parseBook(input))
        )
        assertEquals(
            listOf(
                Book("The Witcher", "A.Sapkovskiy", "1986"),
            ),
            findTheYoungestBooks(parseBook(input))
        )
        assertEquals(
            listOf(Book("The Invisible Man", "H.Wells", "1897")),
            findBookLongName(parseBook(input))
        )
        assertEquals(
            listOf(Book("The Witcher", "A.Sapkovskiy", "1986")),
            findBookShortName(parseBook(input))
        )
    }

    @Test
    fun `test algorithms of finding the oldest, the latest, the longest and the shortest books from empty string`() {
        val input = ""
        assertEquals(
            true,
            findTheOldestBooks(parseBook(input))
        )
        assertEquals(
            true,
            findTheYoungestBooks(parseBook(input))
        )
        assertEquals(
            true,
            findBookLongName(parseBook(input)).isEmpty()
        )
        assertEquals(
            true,
            findBookShortName(parseBook(input)).isEmpty()
        )
    }

}
