import java.io.File

private val path = "src/main/kotlin/inputs/day04.txt"
private val file = File(path).absoluteFile

private fun part1(): Int {
    val indexOfPapers = getPapersLocation()
    return indexOfPapers.entries.count { entry -> entry.value.mapNotNull { indexOfPapers[it] }.size < 4 }
}

private fun part2(): Int {
    val indexOfPapers = getPapersLocation()

    val initialSize = indexOfPapers.size

    while (true) {
        val toRemove = indexOfPapers
            .filter { (_, value) -> value.count { it in indexOfPapers } < 4 }
            .keys

        if (toRemove.isEmpty()) break

        toRemove.forEach { indexOfPapers.remove(it) }
    }

    return initialSize - indexOfPapers.size
}

private fun getPapersLocation(): MutableMap<Int, List<Int>> {
    val length = file.readLines().first().length
    val indexOfPapers = mutableMapOf<Int, List<Int>>()
    var lineCounter = 0

    file.forEachLine { line ->
        line.forEachIndexed { index, c ->
            if (c == '@') {
                val realIndex = lineCounter * length + index
                indexOfPapers[realIndex] = getNeighbors(realIndex, length)
            }
        }
        lineCounter++
    }
    return indexOfPapers
}


private fun getNeighbors(index: Int, size: Int): List<Int> {
    val row = index / size
    val col = index % size

    val directions = listOf(-1 to -1, -1 to 0, -1 to 1, 0 to -1, 0 to 1, 1 to -1, 1 to 0, 1 to 1)

    val neighbors = mutableListOf<Int>()

    for ((dx, dy) in directions) {
        val nr = row + dx
        val nc = col + dy

        if (nr in 0 until size && nc in 0 until size) {
            neighbors += nr * size + nc
        }
    }

    return neighbors
}

fun main() {
    println(part1())
    println(part2())
}