import java.io.File
import java.math.BigDecimal

private val path = "src/main/kotlin/inputs/day03.txt"
private val file = File(path).absoluteFile

private fun part1(): Int {
    var total = 0

    file.forEachLine { line ->
        val first = line.substring(0, line.length - 1).withIndex().maxBy { it.value.digitToInt() }
        val second = line.substring(first.index + 1).maxOf { it.digitToInt() }
        val targetValue = "${first.value}$second".toInt()

        total += targetValue
    }

    return total
}

private fun part2(): BigDecimal {
    val comparator = compareByDescending<IndexedValue<Char>> { it.value.digitToInt() }.thenBy { it.index }
    val total = mutableListOf<BigDecimal>()

    file.forEachLine { line ->
        var result = ""
        var limit = 11
        var currentIndex = 0
        while (result.length != 12) {
            val target = line.substring(currentIndex, line.length - limit)
                .mapIndexed { index, c -> IndexedValue(currentIndex + index, c) }
                .sortedBy { it.value.digitToInt() }
                .sortedWith(comparator)

            limit--
            result += target.first().value
            currentIndex = target.first().index + 1
        }

        total.add(BigDecimal(result))
       }

    return total.sumOf { it }
}

fun main() {
    println(part1())
    println(part2())
}