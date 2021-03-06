package project.ui.main

import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.commitNow
import com.crashlytics.android.Crashlytics
import com.google.android.material.tabs.TabLayout
import com.google.firebase.iid.FirebaseInstanceId
import com.jakewharton.rxbinding3.view.clicks
import com.jakewharton.rxbinding3.view.visibility
import com.mikepenz.materialdrawer.AccountHeader
import com.mikepenz.materialdrawer.AccountHeaderBuilder
import com.mikepenz.materialdrawer.Drawer
import com.mikepenz.materialdrawer.DrawerBuilder
import com.mikepenz.materialdrawer.holder.StringHolder
import com.mikepenz.materialdrawer.model.DividerDrawerItem
import com.mikepenz.materialdrawer.model.ProfileDrawerItem
import com.mikepenz.materialdrawer.model.SwitchDrawerItem
import io.github.inflationx.calligraphy3.CalligraphyUtils
import io.reactivex.Observable
import io.reactivex.rxkotlin.toObservable
import ir.sinapp.classroom.BR
import ir.sinapp.classroom.R
import ir.sinapp.classroom.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.runBlocking
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import project.adapter.ClassesAdapter
import project.data.DataManager
import project.ui.base.BaseActivity
import project.ui.course.CourseActivity
import project.ui.create.CreateActivity
import project.ui.join.JoinActivity
import project.ui.main.archive.ArchiveFragment
import project.ui.main.calender.CalenderFragment
import project.ui.main.classes.ClassesFragment
import project.ui.main.help.HelpFragment
import project.ui.main.hide.HideFragment
import project.ui.main.notification.NotificationFragment
import project.ui.main.setting.SettingFragment
import project.ui.osm.OsmActivity
import project.utils.*
import project.utils.navDrawer.MyDrawerItem
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class MainActivity :
    BaseActivity<ActivityMainBinding, MainViewModel>(ActivityMainBinding::class.java),
    MainNavigator {


    override val bindingVariable: Int get() = BR.viewModel

    @Inject
    override lateinit var viewModel: MainViewModel


    @Inject
    lateinit var drawerBuilder: DrawerBuilder


    @Inject
    lateinit var mPagerAdapter: MainPagerAdapter


    private lateinit var drawer: Drawer
    private lateinit var notificationItem: MyDrawerItem
    private lateinit var headerResult: AccountHeader
    private lateinit var itemProfile: ProfileDrawerItem


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.navigator = this

        viewModel.eventNotify()


        showFragment(getString(R.string.classes), 1)


        AppLogger.i(session.token)
        AppLogger.i(session.username)


        runBlocking {
            setUpText()
            setUpDrawer()
        }

        setUp()

        initNotify()

        changeTabsFont(binding.tabs, "fonts/IRANYekanMobileRegular.ttf")

    }


    private fun initNotify() {

        FirebaseInstanceId.getInstance().instanceId.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                viewModel.registerNotify(task.result!!.token)
            }
        }


    }

    private fun setUpText() {

        binding.textSwitcher.setFactory {
            val t = TextView(this@MainActivity)
            t.gravity = Gravity.CENTER
            t.setTextColor(resources.getColor(R.color.colorAccent))
            t.textSize = 21f
            t.typeface = CommonUtils.typefaceFromAsset("fonts/IRANYekanMobileRegular.ttf", this)
            t
        }

        val `in` = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left)
        val out = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right)

        binding.textSwitcher.inAnimation = `in`
        binding.textSwitcher.outAnimation = out

        binding.textSwitcher.setCurrentText(getString(R.string.app_name))
    }


    private fun setUpDrawer() {

        itemProfile = ProfileDrawerItem()
            .withIdentifier(1)
            .withName("profile")
            .withIcon(R.drawable.ic_user_man)


        headerResult = AccountHeaderBuilder()
            .withActivity(this)
            .withTextColorRes(R.color.white)
            .withAccountHeader(R.layout.drawer_header)
            .withHeaderBackground(R.drawable.back)
            .withSelectionListEnabledForSingleProfile(false)
            .withCloseDrawerOnProfileListClick(false)
            .addProfiles(itemProfile)
            .build()


        viewModel.updateHeader { firstName, lastName, username, gender ->
            Crashlytics.setUserIdentifier(username)
            Crashlytics.setUserName("$firstName $lastName")

            Crashlytics.setUserIdentifier(username)
            itemProfile.withName("$firstName $lastName")
            itemProfile.withEmail(username)
            itemProfile.withIcon(if (gender) R.drawable.ic_user_man else R.drawable.ic_user_woman)
            headerResult.updateProfile(itemProfile)

        }.subscribe()


        val font = CommonUtils.typefaceFromAsset("fonts/IRANYekanMobileBold.ttf", this)


        val itemClasses = MyDrawerItem()
            .withSelected(true)
            .withIconTintingEnabled(true)
            .withName(R.string.classes)
            .withIcon(R.drawable.ic_classmatelogo)
            .withTypeface(font)


        val itemCalender = MyDrawerItem()
            .withSelected(false)
            .withIconTintingEnabled(true)
            .withName(R.string.calender)
            .withIcon(R.drawable.ic_calendar)
            .withTypeface(font)

//
//        notificationItem = MyDrawerItem()
//            .withName(R.string.notifications)
//            .withSelected(false)
//            .withIconTintingEnabled(true)
//            .withIcon(R.drawable.ic_notification)
//            .withTypeface(font)

        val itemArchived = MyDrawerItem()
            .withSelected(false)
            .withIconTintingEnabled(true)
            .withName(R.string.archived_class)
            .withIcon(R.drawable.ic_archive)
            .withTypeface(font)

        val itemHided = MyDrawerItem()
            .withSelected(false)
            .withIconTintingEnabled(true)
            .withName(R.string.hided_class)
            .withIcon(R.drawable.ic_hide)
            .withTypeface(font)


        val itemSetting = MyDrawerItem()
            .withSelected(false)
            .withName(R.string.settings)
            .withIconTintingEnabled(true)
            .withIcon(R.drawable.ic_settings)
            .withTypeface(font)

        val itemDayNight = SwitchDrawerItem()
            .withSelected(false)
            .withName(R.string.night_mode)
            .withIconTintingEnabled(true)
            .withIcon(R.drawable.ic_moon)
            .withTypeface(font)
            .withChecked(isNightMode())
            .withSelectable(false)
            .withOnCheckedChangeListener { _, _, isChecked ->
                drawer.closeDrawer()
                if (!isChecked)
                    viewModel.setTheme(
                        AppCompatDelegate.MODE_NIGHT_NO,
                        DataManager.Theme.THEME_LIGHT
                    )
                else viewModel.setTheme(
                    AppCompatDelegate.MODE_NIGHT_YES,
                    DataManager.Theme.THEME_DARK
                )
            }



        val itemHelp = MyDrawerItem()
            .withSelected(false)
            .withIconTintingEnabled(true)
            .withName(R.string.help)
            .withIcon(R.drawable.ic_help)
            .withTypeface(font)

        drawer = drawerBuilder
            .withOnDrawerListener(object : Drawer.OnDrawerListener {
                override fun onDrawerSlide(drawerView: View?, slideOffset: Float) {
                    binding.content.translationX =
                        slideOffset * drawerView!!.width * if (isRtl()) -1 else 1
                }

                override fun onDrawerClosed(drawerView: View?) {
                }

                override fun onDrawerOpened(drawerView: View?) {
                    EventBus.getDefault().post(ClassesAdapter.ToggleEvent(-1))
                    if (binding.backDrop.visibility == View.VISIBLE)
                        toggleFabMode()
                }

            })
            .withAccountHeader(headerResult)
            .addDrawerItems(
                itemClasses,
                itemCalender,
//                notificationItem,
                DividerDrawerItem().withSelectable(false),
                itemArchived,
                itemHided,
                DividerDrawerItem().withSelectable(false),
                itemSetting,
                itemHelp
            )
            .addStickyDrawerItems(
                itemDayNight
            )
            .withOnDrawerItemClickListener { _, position, drawerItem ->
                runBlocking {
                    if (viewModel.tab.get() != position && drawerItem != itemDayNight) {
                        drawerItem as MyDrawerItem
                        showFragment(drawerItem.name.getText(this@MainActivity), position)
                    }
                }
                if (drawerItem != itemDayNight)
                    return@withOnDrawerItemClickListener false
                true
            }
            .withToolbar(binding.toolbar)
            .withActionBarDrawerToggleAnimated(true)
            .build()


        val f = Tools.getMode(this)
        if (f != Configuration.UI_MODE_NIGHT_YES)
            Tools.setSystemBarLight(this)
//        Tools.setSystemBarColor(this, R.color.colorPrimaryBackground)
        Tools.changeNavigateionIconColor(
            binding.toolbar,
            resources.getColor(R.color.colorAccent)
        )

        viewModel.badge {
            drawer.updateBadge(itemClasses.identifier, StringHolder(if (it != 0) "$it" else ""))
        }


//        DrawerImageLoader.init(object : AbstractDrawerImageLoader() {
//            override fun set(imageView: ImageView, uri: Uri, placeholder: Drawable) {
//                if (!isRtl())
//                    Glide.with(this@MainActivity).load(uri).placeholder(placeholder).into(imageView)
//                else {
//                    val mBitmap = MediaStore.Images.Media.getBitmap(this@MainActivity.contentResolver, uri)
//                    Glide.with(this@MainActivity).load(Tools.flip(mBitmap)).placeholder(placeholder).into(imageView)
//                }
//            }
//        })

    }

    private fun isNightMode(): Boolean {
        return when (viewModel.savedTheme) {
            DataManager.Theme.THEME_LIGHT -> false
            DataManager.Theme.THEME_DARK -> true
            DataManager.Theme.THEME_UNDEFINED -> {
                when (resources.configuration.uiMode.and(Configuration.UI_MODE_NIGHT_MASK)) {
                    Configuration.UI_MODE_NIGHT_NO -> false
                    Configuration.UI_MODE_NIGHT_YES -> true
                    else -> false
                }
            }
            else -> false
        }
    }

    private fun setUp() {

        ViewAnimation.initShowOut(binding.lytCreate)
        ViewAnimation.initShowOut(binding.lytJoin)
        binding.backDrop.visibility().accept(false)


        viewModel += binding.backDrop.clicks().subscribe {
            toggleFabMode()
        }


        viewModel += binding.fabAdd.clicks().subscribe {
            toggleFabMode()
        }



        mPagerAdapter.count = 2

/*
        binding.viewPager.adapter = mPagerAdapter


        binding.viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(binding.tabs))


        binding.tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                EventBus.getDefault().post(ClassesAdapter.ToggleEvent(-1))

                viewModel.tab.set(tab.position != 0)

                if (binding.backDrop.visibility == View.VISIBLE)
                    toggleFabMode()

                val selectedLayout =
                    (binding.tabs.getChildAt(0) as ViewGroup).getChildAt(tab.position) as LinearLayout
                val selectedText = selectedLayout.getChildAt(1) as TextView
                CalligraphyUtils.applyFontToTextView(
                    binding.tabs.context,
                    selectedText,
                    "fonts/IRANYekanMobileBold.ttf"
                )
                selectedText.textSize = 26f

                binding.viewPager.setCurrentItem(tab.position, true)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                val selectedLayout =
                    (binding.tabs.getChildAt(0) as ViewGroup).getChildAt(tab.position) as LinearLayout
                val selectedText = selectedLayout.getChildAt(1) as TextView
                CalligraphyUtils.applyFontToTextView(
                    binding.tabs.context,
                    selectedText,
                    "fonts/IRANYekanMobileRegular.ttf"
                )
                selectedText.textSize = 26f
            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })
*/


    }


    override fun onPause() {
        super.onPause()
        EventBus.getDefault().post(ClassesAdapter.ToggleEvent(-1))
    }

    private var rotate = false
    private var flag = false


    override fun toggleFabMode() {
        EventBus.getDefault().post(ClassesAdapter.ToggleEvent(-1))

        if (flag)
            return

        rotate = binding.fabAdd.rotateFab(!rotate)
        flag = true
        if (rotate) {
            binding.backDrop.isEnabled = true
            ViewAnimation.fadeIn(binding.backDrop) {
                flag = false
            }
            ViewAnimation.showIn(binding.lytCreate)
            ViewAnimation.showIn(binding.lytJoin)
        } else {
            binding.backDrop.isEnabled = false
            ViewAnimation.fadeOut(binding.backDrop) {
                back_drop.visibility = View.GONE
                flag = false
            }
            ViewAnimation.showOut(binding.lytCreate)
            ViewAnimation.showOut(binding.lytJoin)
        }
    }


    @Suppress("SameParameterValue")
    private fun changeTabsFont(tabLayout: TabLayout, fontName: String) {
        val vg = tabLayout.getChildAt(0) as ViewGroup
        val tabsCount = vg.childCount
        for (j in 0 until tabsCount) {
            val vgTab = vg.getChildAt(j) as ViewGroup
            val tabChildCount = vgTab.childCount
            for (i in 0 until tabChildCount) {
                val tabViewChild = vgTab.getChildAt(i)
                if (tabViewChild is TextView) {
                    CalligraphyUtils.applyFontToTextView(tabLayout.context, tabViewChild, fontName)
                }
            }
        }
    }


    var pos = -1

    @Subscribe
    fun event(toggleEvent: ClassesAdapter.ToggleEvent) {
        pos = when {
            toggleEvent.i != pos -> toggleEvent.i
            else -> -1
        }
    }


    @Subscribe
    fun event(event: EventJoin) {
        openJoinActivity()
    }

    @Subscribe
    fun event(event: EventCreate) {
        openCreateActivity()
    }

    @Subscribe
    fun event(event: EventReminder) {
        openMapActivity()
    }

    override fun openMapActivity() {
        launchActivity<OsmActivity> {}
        Bungee.fade(this)
    }


    private var isPending: Boolean = false
    @Subscribe
    fun event(event: ItemClickEvent) {
        if (!isPending) {
            openCourseActivity(event.item.id)
            isPending = true
            viewModel += Observable
                .timer(500, TimeUnit.MILLISECONDS)
                .subscribe {
                    isPending = false
                }
        }
    }


    override fun onBackPressed() {
        when {
            drawer.isDrawerOpen -> drawer.closeDrawer()
            rotate -> toggleFabMode()
            pos != -1 -> EventBus.getDefault().post(ClassesAdapter.ToggleEvent(-1))
            viewModel.tab.get() != 1 -> drawer.setSelectionAtPosition(1)
            else -> super.onBackPressed()
        }
    }


    override fun openJoinActivity() {
        launchActivity<JoinActivity> {}
        Bungee.fade(this)
    }


    override fun openCreateActivity() {
        launchActivity<CreateActivity> {}
        Bungee.fade(this)
    }


    override fun openCourseActivity(classId: String) {
        launchActivity<CourseActivity> {
            putExtra("classId", classId)
        }
        Bungee.fade(this)
    }


    private fun showFragment(name: String, position: Int) {
        viewModel.tab.set(position)
        try {
            binding.textSwitcher?.setText(name)
        } catch (e: Exception) {
        }
        val fragment = when (name) {
            getString(R.string.classes) -> ClassesFragment.newInstance()
            getString(R.string.calender) -> CalenderFragment.newInstance()
            getString(R.string.notifications) -> NotificationFragment.newInstance()
            getString(R.string.archived_class) -> ArchiveFragment.newInstance()
            getString(R.string.hided_class) -> HideFragment.newInstance()
            getString(R.string.settings) -> SettingFragment.newInstance()
            getString(R.string.help) -> HelpFragment.newInstance()
            else -> null
        }
        supportFragmentManager.commitNow {
            replace(R.id.fragment, fragment!!)
//            setCustomAnimations(R.anim.fade_enter, R.anim.fade_exit)
        }
    }


    @Subscribe
    fun event(event: EventNotify): Unit {
        viewModel.eventNotify()
    }





    override fun onSaveInstanceState(_outState: Bundle) {
        var outState = _outState
        //add the values which need to be saved from the drawer to the bundle
        outState = drawer.saveInstanceState(outState)

        super.onSaveInstanceState(outState)
    }


}


