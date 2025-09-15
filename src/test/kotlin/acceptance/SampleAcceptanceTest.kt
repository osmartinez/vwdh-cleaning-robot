package acceptance
import application.RunInstructionsToRobot
import infrastructure.adapters.`in`.cli.CliParser
import infrastructure.adapters.out.InMemoryRobotStateRepository
import infrastructure.adapters.out.StdoutLogger
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test


class SampleAcceptanceTest {
    @Test
    fun `parses, executes and verifies end-to-end sample`() {
        val input = """
            5 5
            1 2 N
            LMLMLMLMM
            3 3 E
            MMRMMRMRRM
        """.trimIndent().lines()

        val (floor, pairs) = CliParser.parse(input)
        val repo = InMemoryRobotStateRepository()
        val useCase = RunInstructionsToRobot(repo, StdoutLogger())

        val robots = pairs.map { (robot, commands) -> useCase.runRobot(floor, robot, commands) }
        val results = robots.map { it.toString() }

        assertAll(
            { assertEquals(5, floor.width) },
            { assertEquals(5, floor.height) },
            { assertEquals(2, pairs.size) },
            { assertEquals(listOf("1 3 N", "5 1 E"), results) },
            { assertEquals(listOf("1 3 N", "5 1 E"), repo.history().map { it.toString() }) },
        )
    }
}