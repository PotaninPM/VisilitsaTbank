class Session(
    private val answer: String,
    private val maxAttempts: Int
) {
    private val userAnswer = CharArray(answer.length) { '*' }
    private var attempts = -1

    fun guess(guess: Char): GuessResult {
        if (attempts >= maxAttempts) {
            return GuessResult.Defeat(answer, userAnswer, attempts, maxAttempts)
        }

        var hit = false
        for (i in answer.indices) {
            if (answer[i] == guess && userAnswer[i] == '*') {
                userAnswer[i] = guess
                hit = true
            }
        }

        if (hit) {
            return if (userAnswer.concatToString() == answer) {
                GuessResult.Win(answer, userAnswer, attempts, maxAttempts)
            } else {
                GuessResult.SuccessfulGuess(userAnswer, attempts, maxAttempts)
            }
        } else {
            attempts++
            return if (attempts >= maxAttempts) {
                GuessResult.Defeat(answer, userAnswer, attempts, maxAttempts)
            } else {
                GuessResult.FailedGuess(userAnswer, attempts, maxAttempts)
            }
        }
    }

    fun giveUp(): GuessResult {
        return GuessResult.Defeat(answer, userAnswer, attempts, maxAttempts)
    }
}