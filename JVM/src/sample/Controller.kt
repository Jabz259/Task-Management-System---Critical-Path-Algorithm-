package sample

import java.util.*
import kotlin.collections.HashSet

fun criticalMessage (): String {
    //formatting the output to textarea using java gui
    var criticalData = "\n CRITICAL PATH BACKWARD PASS  FROM LAST EARLY START TO ANY LATE FINISHES; CHECK PROGRESS WITHIN THE PROJECT. \n \n" +
            Arrays.toString(criticalPathAlgorithm(DbConnection.testingData()))
    var criticalInfo =  "\n \n"  +DbConnection.fetchDetails() + criticalData
    var criticalDelay = criticalInfo + "\n \n" + "CURRENT PROGRESS = "+ "PROJECT WILL NOT BE DELAYED!"
    return criticalDelay
}

fun criticalPathAlgorithm (tasks: Set<Task>?): Array<Task> {
   //tasks which have been completed will be stored here
    val completedTasks = HashSet<Task>()
    //Remaining tasks will be store for calculation
    val remainingTasks = HashSet(tasks)
    //if there are still tasks stored, then run the backward pass algorithm
    while (!remainingTasks.isEmpty()) {
        var taskProgress = false
        //check for a new task to calculate
        val newTask = remainingTasks.iterator()
        while (newTask.hasNext()) {
            val task = newTask.next()
            if (completedTasks.containsAll(task.taskDependencies)) {
                //calculate all dependencies, and add the critical cost with the stored amount
                var criticalData = 0
                for (t in task.taskDependencies) {
                    if (t.criticalPathCost > criticalData) {
                        criticalData = t.criticalPathCost
                    }
                }
                task.criticalPathCost = criticalData + task.taskCost
                //flag the task as finished calculated and remove the current iteration
                completedTasks.add(task)
                newTask.remove()
                //if this is successful then flag it as true
                taskProgress = true
            }
        }
        //if there is no progress within the algorithm, then throw a exception and stop algorithm
      if (!taskProgress) throw RuntimeException("Algorithm has stopped")
    }

    //retrieve the tasks
    val ret = completedTasks.toTypedArray()
    //priority list will be created
    Arrays.sort(ret, Comparator { t1, t2 -> //sort by cost
        val count = t2.criticalPathCost - t1.criticalPathCost
        if (count != 0) return@Comparator count
        //for the tie breaker, the dependency information will be used to differentiate
        if (t1.isDependent(t2)) return@Comparator -1
        if (t2.isDependent(t1)) 1 else 0
    })
    return ret
}

// Store data Constructor
class StoreData {
    var projectNameData : String = ""
    var projectDepartmentData : String = ""
    var mainTeamLocationData : String = ""
    var supportTeamLocationData : String = ""
    var mainTaskData : String = ""
    var additionalTaskData : String = ""
    var dependencyData : String = ""
    var costData : String = ""
    var location : String = ""
}

fun updateData () {
    var store = StoreData()
    //calling constructor and defining parameters using lambda 'with' keyword
    //part of the Kotlin library
    //allows cleaner code and repeated code, also with keyword allows multiple functions in a single method body
    with(store) {
        //converting all data from textfield to string then lowercase for better management
        projectNameData = ProjectInformation().projectNameField.getText().toString().toLowerCase()
        projectDepartmentData = ProjectInformation().teamNameField.getText().toString().toLowerCase()
        mainTeamLocationData = ProjectInformation().mainData("").toString().toLowerCase()
        supportTeamLocationData = ProjectInformation().supportData("").toString().toLowerCase()
        mainTaskData = ProjectInformation().mainTaskNameField.getText().toString().toLowerCase()
        additionalTaskData = ProjectInformation().additionalTaskField.getText().toString().toLowerCase()
        dependencyData = ProjectInformation().dependencyData("").toString().toLowerCase()
        costData = ProjectInformation().countField.getText().toString().toLowerCase()
        location = ProjectInformation().retypeProjectField.getText().toString().toLowerCase()
        println(location)

       DbConnection.updateDatabase(projectNameData, projectDepartmentData, mainTeamLocationData, supportTeamLocationData, mainTaskData, additionalTaskData, dependencyData, costData, location) //)
       DbConnection.updateDependencyName(projectNameData, location)
       DbConnection.updateDependencyCost(costData, projectNameData)

    }
}

 fun insertData () {
     var store = StoreData()
     //calling constructor and defining parameters using lambda 'with' keyword
     //part of the Kotlin library
     //allows cleaner code and repeated code, also with keyword allows multiple functions in a single method body
     with(store) {
         //converting all data from textfield to string then lowercase for better management
         projectNameData = ProjectInformation().projectNameField.getText().toString().toLowerCase()
         projectDepartmentData = ProjectInformation().teamNameField.getText().toString().toLowerCase()
         mainTeamLocationData = ProjectInformation().mainData("").toString().toLowerCase()
         supportTeamLocationData = ProjectInformation().supportData("").toString().toLowerCase()
         mainTaskData = ProjectInformation().mainTaskNameField.getText().toString().toLowerCase()
         additionalTaskData = ProjectInformation().additionalTaskField.getText().toString().toLowerCase()
         dependencyData = ProjectInformation().dependencyData("").toString().toLowerCase()
         costData = ProjectInformation().countField.getText().toString().toLowerCase()
         var fetchDependencyCost = DbConnection.fetchDependencyCost(dependencyData)

         DbConnection.insert(projectNameData, projectDepartmentData, mainTeamLocationData, supportTeamLocationData, mainTaskData, additionalTaskData, dependencyData, costData, fetchDependencyCost) //)
     }
 }

fun filter (): List<String> {
    //retrieving data from the supplied textfield
    val searchField = ProjectInformation().searchField.getText().toString()
    //Using lambda expressions to filter data from the database function
    var arrayDat = DbConnection.readAllData().filter { it.startsWith(searchField) }
  return arrayDat
}

fun ManageData() : List<String> {
    //storing data alphabetically within listeview
    var data = DbConnection.readAllData().sorted()
   return data
}








