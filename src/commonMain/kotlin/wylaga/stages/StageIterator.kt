package wylaga.stages

class StageIterator(stageFactory: StageFactory, private val onComplete: () -> Unit) {
    private val stageMakers = arrayOf(stageFactory::stage1, stageFactory::stage2, stageFactory::stage3)
    private var stageCount = 0

    fun next() = stageMakers[stageCount++](onComplete)

    fun hasNext() : Boolean = stageCount < stageMakers.size
}