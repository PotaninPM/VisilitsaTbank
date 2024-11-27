package dictionary

class AllDictionary : Dictionary {
    private val words = listOf("собака", "домашняя", "супергерой", "ира", "данила")

    override fun randomWord(): String {
        return words.random()
    }
}