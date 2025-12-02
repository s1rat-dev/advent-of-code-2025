import java.io.File
import kotlin.math.absoluteValue
import kotlin.math.sign

private val path = "src/main/kotlin/inputs/day01.txt"
private val file = File(path).absoluteFile

private fun part1(): Int {
    var score = 50
    var point = 0

    file.forEachLine {
        val direction = it.first()
        val degree = it.takeLastWhile { it.isDigit() }.toInt()

        if (direction == 'L') {
            score -= degree
        } else {
            score += degree
        }

        if (score % 100 == 0) {
            point++
            score = 0
        }
    }

    return point
}

private fun part2(): Int {
    var score = 50
    var point = 0

    file.forEachLine {
        val direction = it.first()
        val degree = it.takeLastWhile { it.isDigit() }.toInt()

        val oldScore = score

        if (direction == 'L') {
            score -= degree
        } else {
            score += degree
        }

        point += (score / 100).absoluteValue

        if (score.sign != oldScore.sign && oldScore != 0) {
            point++
        }

        score %= 100
    }

    return point

}


fun main() {
    println(part1())
    println(part2())
}