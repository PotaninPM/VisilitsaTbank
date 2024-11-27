sealed interface GuessResult {
    val state: CharArray
    val attempt: Int
    val maxAttempts: Int
    val message: String

    data class Defeat(
        val answer: String,
        override val state: CharArray,
        override val attempt: Int,
        override val maxAttempts: Int
    ) : GuessResult {
        override val message = "Ты проиграл! Слово было: $answer"
    }

    data class Win(
        val answer: String,
        override val state: CharArray,
        override val attempt: Int,
        override val maxAttempts: Int
    ) : GuessResult {
        override val message = "Ты выиграл! Слово было: $answer"
    }

    data class SuccessfulGuess(
        override val state: CharArray,
        override val attempt: Int,
        override val maxAttempts: Int
    ) : GuessResult {
        override val message = "Есть пробитие!"
    }

    data class FailedGuess(
        override val state: CharArray,
        override val attempt: Int,
        override val maxAttempts: Int
    ) : GuessResult {
        override val message = "Не получилось, ошибка $attempt из $maxAttempts."
    }
}