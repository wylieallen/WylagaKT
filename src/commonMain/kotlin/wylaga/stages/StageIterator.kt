package wylaga.stages

class StageIterator(private val stageFactory: StageFactory, private val onComplete: () -> Unit) {
    private var stageCount = 0

    fun next() : Stage  {
        ++stageCount
        return stageFactory.nextStage(onComplete)
    }

    fun hasNext() : Boolean = stageCount <= 3
}