package app.wishzee.com.wishzee.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.wishzee.com.wishzee.R;
import app.wishzee.com.wishzee.recyclerview.ViewPagerAdapter;

/**
 * Created by Ajit Gupta on 5/12/2016.
 */
public class TabBarFragment extends BaseFragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getFragmentLayout(), container, false);

        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.tab_bar_layout;
    }

    @Override
    public int getToolbarMenu() {
        return 0;
    }

    @Override
    public void onNetworkConnected() {

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new PublicFeedFragment(), "Public");
        adapter.addFragment(new MyCornerFragment(), "My Corner");
        adapter.addFragment(new MyFeedFragment(), "Public");
        adapter.addFragment(new PrivateFeedFragment(), "Private");
        viewPager.setAdapter(adapter);
    }
}
