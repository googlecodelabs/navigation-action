/*
Copyright 2017 The Android Open Source Project

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */
package com.example.android.wearable.navaction;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.wearable.activity.WearableActivity;
import android.support.wearable.view.drawer.WearableActionDrawer;
import android.support.wearable.view.drawer.WearableNavigationDrawer;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;
import com.example.android.wearable.navaction.SectionFragment.Section;

public class MainActivity extends WearableActivity {

    private static final Section DEFAULT_SECTION = Section.Sun;

    private WearableNavigationDrawer mWearableNavigationDrawer;
    private WearableActionDrawer mWearableActionDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWearableNavigationDrawer =
                (WearableNavigationDrawer) findViewById(R.id.top_navigation_drawer);
        mWearableNavigationDrawer.setAdapter(new NavigationAdapter(this));

        final SectionFragment sunSection = SectionFragment.getSection(DEFAULT_SECTION);
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, sunSection)
                .commit();

        mWearableActionDrawer = (WearableActionDrawer) findViewById(R.id.bottom_action_drawer);
        mWearableActionDrawer.setOnMenuItemClickListener(
                new WearableActionDrawer.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        mWearableActionDrawer.closeDrawer();
                        switch (menuItem.getItemId()) {
                            case R.id.action_edit:
                                Toast.makeText(
                                                MainActivity.this,
                                                R.string.action_edit_todo,
                                                Toast.LENGTH_SHORT)
                                        .show();
                                return true;
                            case R.id.action_share:
                                Toast.makeText(
                                                MainActivity.this,
                                                R.string.action_share_todo,
                                                Toast.LENGTH_SHORT)
                                        .show();
                                return true;
                        }
                        return false;
                    }
                });
    }

    private final class NavigationAdapter
            extends WearableNavigationDrawer.WearableNavigationDrawerAdapter {

        private final Context mContext;
        private Section mCurrentSection = DEFAULT_SECTION;

        NavigationAdapter(final Context context) {
            mContext = context;
        }

        @Override
        public String getItemText(int index) {
            return mContext.getString(SectionFragment.Section.values()[index].titleRes);
        }

        @Override
        public Drawable getItemDrawable(int index) {
            return mContext.getDrawable(SectionFragment.Section.values()[index].drawableRes);
        }

        @Override
        public void onItemSelected(int index) {
            SectionFragment.Section selectedSection = SectionFragment.Section.values()[index];

            // Only replace the fragment if the section is changing.
            if (selectedSection == mCurrentSection) {
                return;
            }
            mCurrentSection = selectedSection;

            final SectionFragment sectionFragment = SectionFragment.getSection(selectedSection);
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, sectionFragment)
                    .commit();

            // No actions are available for the settings specific fragment, so the drawer
            // is locked closed. For all other SelectionFragments, it is unlocked.
            if (selectedSection == SectionFragment.Section.Settings) {
                mWearableActionDrawer.lockDrawerClosed();
            } else {
                mWearableActionDrawer.unlockDrawer();
            }
        }

        @Override
        public int getCount() {
            return SectionFragment.Section.values().length;
        }
    }
}
