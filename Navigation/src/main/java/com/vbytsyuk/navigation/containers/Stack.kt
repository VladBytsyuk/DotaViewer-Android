package containers

interface Stack<T> {
    val size: Int

    fun isEmpty(): Boolean
    fun isNotEmpty(): Boolean

    fun push(item: T): Boolean
    fun pop(): T
    fun peek(): T
}

internal class AriadneStack<T>: Stack<T> {
    private val list: MutableList<T> = mutableListOf()


    override val size: Int get() = list.size


    override fun isEmpty() = list.isEmpty()

    override fun isNotEmpty() = list.isNotEmpty()


    override fun push(item: T) = list.add(item)

    override fun pop(): T = if (!list.isEmpty())
        list.last().apply { list.remove(this) }
    else throw EmptyStackException("Can't pop on empty stack.")

    override fun peek(): T = if (!list.isEmpty())
        list.last()
    else throw EmptyStackException("Can't pop on empty stack.")


    class EmptyStackException(message: String) : Throwable(message)
}