package project.utils.navDrawer;

import com.mikepenz.materialdrawer.model.AbstractBadgeableDrawerItem;

import ir.sinapp.classroom.R;

public class MyDrawerItem extends AbstractBadgeableDrawerItem<MyDrawerItem> {

    @Override
    public int getLayoutRes() {
        return R.layout.drawer_item;
    }


}