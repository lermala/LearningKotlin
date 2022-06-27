package other

class BeautifulOutput {
    private var taskNumber = 0

    fun getConditionalAndResult(condition: String, task: Any): String{
        taskNumber++
        return "\nTask $taskNumber: $condition\nSolution: $task"
    }
}