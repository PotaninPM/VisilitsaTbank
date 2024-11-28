import dictionary.AllDictionary

class Visilitsa {
    fun run() {
        val dictionary = AllDictionary()
        val word = dictionary.randomWord()
        val session = Session(word, word.length + 5)

        println("Прив! Угадай слово:")

        while (true) {
            println("> Слово: ${session.guess('*').state.concatToString()}")
            print("> Угадай букву (или напиши 'exit' чтобы сдаться): ")
            val input = readln().trim().lowercase()

            if (input == "exit") {
                printState(session.giveUp())
                break
            }

            if (input.length != 1 || input[0] !in 'а'..'я') {
                println("Неверный ввод. Введи только 1 русскую букву.")
                continue
            }

            val guess = input[0]
            val result = session.guess(guess)
            printState(result)

            if (result is GuessResult.Win || result is GuessResult.Defeat) {
                break
            }
        }
    }

    private fun printState(result: GuessResult) {
        println(result.message)
    }
}