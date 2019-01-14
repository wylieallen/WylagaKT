package wylaga.stages

class StageIterator(stageFactory: StageFactory, private val onComplete: () -> Unit) : Iterator<Stage> {
    private val stageMakers = arrayOf(stageFactory::stage1, stageFactory::stage2, stageFactory::stage3, stageFactory::stage4)
    private var stageCount = 0

    override fun next() = stageMakers[stageCount++](onComplete)

    override fun hasNext() : Boolean = stageCount < stageMakers.size
}