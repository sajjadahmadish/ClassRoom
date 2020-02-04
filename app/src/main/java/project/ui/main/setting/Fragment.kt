package project.ui.main.setting


import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import com.jakewharton.rxbinding3.view.clicks
import com.jakewharton.rxbinding3.view.layoutChangeEvents
import com.jakewharton.rxbinding3.widget.checkedChanges
import ir.sinapp.classroom.BR
import ir.sinapp.classroom.R
import ir.sinapp.classroom.databinding.FragmentSettingBinding
import project.data.DataManager
import project.ui.base.BaseFragment
import javax.inject.Inject



class SettingFragment : BaseFragment<FragmentSettingBinding, SettingViewModel>(FragmentSettingBinding::class.java) {

    override val bindingVariable: Int get() = BR.viewModel

    @Inject
    override lateinit var viewModel: SettingViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        initTheme()

//        viewModel += binding.themeGroup.checkedChanges().subscribe {
//            when (it) {
//                R.id.themeLight -> viewModel.setTheme(AppCompatDelegate.MODE_NIGHT_NO, DataManager.Theme.THEME_LIGHT)
//                R.id.themeDark -> viewModel.setTheme(AppCompatDelegate.MODE_NIGHT_YES, DataManager.Theme.THEME_DARK)
//                R.id.themeBattery -> viewModel.setTheme(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY, DataManager.Theme.THEME_BATTERY)
//                R.id.themeSystem -> viewModel.setTheme(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM, DataManager.Theme.THEME_SYSTEM)
//            }
//        }

        viewModel += binding.txt.clicks().subscribe {
            viewModel.logout()
        }

    }


    companion object {

        fun newInstance(): SettingFragment {
            val args = Bundle()
            val fragment = SettingFragment()
            fragment.arguments = args
            return fragment
        }

    }



//    private fun initTheme() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P){
//            binding.themeSystem.visibility = View.VISIBLE
//        } else {
//            binding.themeSystem.visibility = View.GONE
//        }
//        when (viewModel.savedTheme) {
//            DataManager.Theme.THEME_LIGHT -> binding.themeLight.isChecked = true
//            DataManager.Theme.THEME_DARK -> binding.themeDark.isChecked = true
//            DataManager.Theme.THEME_SYSTEM -> binding.themeSystem.isChecked = true
//            DataManager.Theme.THEME_BATTERY -> binding.themeBattery.isChecked = true
//            DataManager.Theme.THEME_UNDEFINED -> {
//                when (resources.configuration.uiMode.and(Configuration.UI_MODE_NIGHT_MASK)) {
//                    Configuration.UI_MODE_NIGHT_NO -> binding.themeLight.isChecked = true
//                    Configuration.UI_MODE_NIGHT_YES -> binding.themeDark.isChecked = true
//                    Configuration.UI_MODE_NIGHT_UNDEFINED -> binding.themeLight.isChecked = true
//                }
//            }
//        }
//    }



}






