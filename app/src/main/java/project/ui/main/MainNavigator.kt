package project.ui.main

interface MainNavigator {


    fun openMapActivity()
    fun openJoinActivity()
    fun openCreateActivity()

    fun toggleFabMode()

    fun openCourseActivity(classId: String)
}