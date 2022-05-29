package lab2

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import java.io.File

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