package project.ui.course

interface CourseNavigator {

    fun goBack()

    fun showFragmentStream()
    fun showFragmentPeople()
    fun showFragmentClasswork()

    fun openInvite(classId: String)
}