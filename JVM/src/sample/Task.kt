package sample


//A constructor class which will be used to store the data from the database
class Task(var taskName: String, var taskCost: Int, vararg dependencies: Task) {
    var criticalPathCost = 0
    //the dependencies for the current task
    var taskDependencies = HashSet<Task>()

    //return variable
    override fun toString(): String {
        return "$taskName: $criticalPathCost"
    }

    fun isDependent(tsk: Task): Boolean {
        //check to see if tsk is a direct dependency to task
        if (taskDependencies.contains(tsk)) {
            return true
        }

        //check to see if dep is a direct dependency to task
        for (dep in taskDependencies) {
            if (dep.isDependent(tsk)) {
                return true
            }
        }
        return false
    }
    init {
        for (t in dependencies) {
            this.taskDependencies.add(t)
        }
    }
}
