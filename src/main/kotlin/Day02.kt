import java.io.File
import java.math.BigDecimal

private val path = "src/main/kotlin/inputs/day02.txt"
private val file = File(path).absoluteFile

private fun part1(): BigDecimal {
    var total = BigDecimal.ZERO

    file.readText().split(",").forEach {
        it.split("-").apply {
            val range = BigDecimal(first()).rangeTo(BigDecimal(last()))
            for (i in range) {
                val idAsString = i.toString()
                val length = idAsString.length

                if (length % 2 != 0) continue

                if (idAsString.takeLast(length / 2) == idAsString.take(length / 2)) {
                    total = total.plus(i)
                }
            }
        }
    }

    return total
}

private fun part2(): BigDecimal {
    val invalidIds = mutableSetOf<BigDecimal>()

    file.readText().split(",").forEach {
        it.split("-").apply {
            val range = BigDecimal(first()).rangeTo(BigDecimal(last()))
            for (i in range) {
                val idAsString = i.toString()
                val length = idAsString.length

                for (k in 1..length / 2) {
                    val chunked = idAsString.chunked(k)
                    if (chunked.all { it == chunked.first() }) {
                        invalidIds.add(i)
                    }
                }
            }
        }
    }

    return invalidIds.sumOf { it }
}


fun main() {
    println(part1())
    println(part2())
}

operator fun ClosedRange<BigDecimal>.iterator(): Iterator<BigDecimal> =
    object : Iterator<BigDecimal> {
        private var current = start
        override fun hasNext() = current <= endInclusive
        override fun next() = current.also {
            current += BigDecimal.ONE
        }
    }
