sealed interface Result {
    val state: CharArray
    val attempt: Int
    val maxAttempts: Int
    val message: String

    data class Defeat(
        val answer: String,
        override val state: CharArray,
        override val attempt: Int,
        override val maxAttempts: Int
    ) : Result {
        override val message = "Ты проиграл! Слово было: $answer"
    }

    data class Win(
        val answer: String,
        override val state: CharArray,
        override val attempt: Int,
        override val maxAttempts: Int
    ) : Result {
        override val message = "Ты выиграл! Слово было: $answer"
    }

    data class SuccessfulGuess(
        override val state: CharArray,
        override val attempt: Int,
        override val maxAttempts: Int
    ) : Result {
        override val message = "Есть пробитие!"
    }

    data class FailedGuess(
        override val state: CharArray,
        override val attempt: Int,
        override val maxAttempts: Int
    ) : Result {
        override val message = "Не получилось, попытка $attempt из $maxAttempts."
    }

    data class LetterWasUsed(
        override val state: CharArray,
        override val attempt: Int,
        override val maxAttempts: Int,
        val letter: Char
    ) : Result {
        override val message = "Буква '$letter' уже была использована."
    }

    data class LettersInUse(
        override val state: CharArray,
        override val attempt: Int,
        override val maxAttempts: Int,
        val letters: Set<Char>
    ) : Result {
        override val message = "Использованные буквы: ${letters.joinToString(", ")}"
    }
}