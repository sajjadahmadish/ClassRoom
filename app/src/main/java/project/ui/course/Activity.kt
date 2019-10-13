package project.ui.course

import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.fragment.app.commit
import ir.sinapp.classroom.BR
import ir.sinapp.classroom.R
import ir.sinapp.classroom.databinding.ActivityCourseBinding
import project.ui.base.BaseActivity
import project.ui.course.classwork.ClassworkFragment
import project.ui.course.people.PeopleFragment
import project.ui.course.stream.StreamFragment
import project.utils.Bungee
import javax.inject.Inject


class CourseActivity :
    BaseActivity<ActivityCourseBinding, CourseViewModel>(ActivityCourseBinding::class.java),
    CourseNavigator {


    lateinit var classId: String
    override val bindingVariable: Int get() = BR.viewModel
    @Inject
    override lateinit var viewModel: CourseViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.navigator = this
        classId = intent.getStringExtra("classId")!!

        viewModel.getClazz(classId) {
            binding.name.text = it.name
        }

        viewModel.select(1)
    }


    override fun showFragmentStream() {
        val fragment = StreamFragment.newInstance(classId)
        supportFragmentManager.commit {
            setCustomAnimations(R.anim.fly_in, R.anim.fly_out)
            replace(R.id.fragment, fragment)
        }
    }

    override fun showFragmentPeople() {
        val fragment = PeopleFragment.newInstance(classId, viewModel.clazz.userType.toInt(), viewModel.clazz.permission)
        supportFragmentManager.commit {
            setCustomAnimations(R.anim.fly_in, R.anim.fly_out)
            replace(R.id.fragment, fragment)
        }
    }

    override fun showFragmentClasswork() {
        val fragment = ClassworkFragment.newInstance(classId)
        supportFragmentManager.commit {
            setCustomAnimations(R.anim.fly_in, R.anim.fly_out)
            replace(R.id.fragment, fragment)
        }
    }


    override fun onBackPressed() {
        super.onBackPressed()
        Bungee.fade(this)
    }

    override fun goBack() {
        super.onBackPressed()
    }

    override fun openInvite(classId: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}




