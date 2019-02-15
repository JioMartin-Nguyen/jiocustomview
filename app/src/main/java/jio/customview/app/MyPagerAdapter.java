package jio.customview.app;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

class MyPagerAdapter extends FragmentPagerAdapter {

    public final int PAGE_COUNT = 5;
    TextView title;
    Context context;

    private final String[] mTabsTitle = {"Events", "News", "Contacts", "History", "Settings"};

    public MyPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    public View getTabView(int position) {
        // Given you have a custom layout in `res/layout/custom_tab.xml` with a TextView and ImageView
        View view = LayoutInflater.from(context).inflate(R.layout.custom_tab_layout, null);
        title = (TextView) view.findViewById(R.id.title);
        title.setText(mTabsTitle[position]);
        return view;
    }

    public void SetOnSelectView(TabLayout tabLayout, int position) {
        TabLayout.Tab tab = tabLayout.getTabAt(position);
        View selected = tab.getCustomView();
        TextView iv_text = (TextView) selected.findViewById(R.id.title);
        iv_text.setTextColor(context.getResources().getColor(R.color.yellow));
    }

    public void SetUnSelectView(TabLayout tabLayout, int position) {
        TabLayout.Tab tab = tabLayout.getTabAt(position);
        View selected = tab.getCustomView();
        TextView iv_text = (TextView) selected.findViewById(R.id.title);
        iv_text.setTextColor(context.getResources().getColor(R.color.gray));
    }

    @Override
    public Fragment getItem(int pos) {

        switch (pos) {

            case 0:
                return new BlankFragment();


            case 1:
                return new BlankFragment();
            case 2:
                return new BlankFragment();

            case 3:
                return new BlankFragment();
            case 4:
                return new BlankFragment();
        }

        return null;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTabsTitle[position];
    }
}
