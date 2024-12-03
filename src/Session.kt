class Session(
    private val answer: String,
    private val maxAttempts: Int
) {
    private val userAnswer = CharArray(answer.length) { '*' }
    private val usedLetters: MutableSet<Char> = mutableSetOf()
    private var attempts = -1

    fun guess(guess: Char): Result {
        if(usedLetters.contains(guess)) {
            return Result.LetterWasUsed(userAnswer, attempts, maxAttempts, guess)
        }

        if (attempts >= maxAttempts) {
            return Result.Defeat(answer, userAnswer, attempts, maxAttempts)
        }

        if(guess != '*')
            usedLetters.add(guess)

        var hit = false
        for (i in answer.indices) {
            if (answer[i] == guess && userAnswer[i] == '*') {
                userAnswer[i] = guess
                hit = true
            }
        }

        if (hit) {
            return if (userAnswer.concatToString() == answer) {
                Result.Win(answer, userAnswer, attempts, maxAttempts)
            } else {
                Result.SuccessfulGuess(userAnswer, attempts, maxAttempts)
            }
        } else {
            attempts++
            return if (attempts >= maxAttempts) {
                Result.Defeat(answer, userAnswer, attempts, maxAttempts)
            } else {
                Result.FailedGuess(userAnswer, attempts, maxAttempts)
            }
        }
    }

    fun getUserLetters(): Result {
        return Result.LettersInUse(userAnswer, attempts, maxAttempts, usedLetters)
    }

    fun giveUp(): Result {
        return Result.Defeat(answer, userAnswer, attempts, maxAttempts)
    }
}