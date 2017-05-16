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

        // TODO:NAV Uncomment the following block to add a navigation drawer.
        /*
        mWearableNavigationDrawer =
                (WearableNavigationDrawer) findViewById(R.id.top_navigation_drawer);
        mWearableNavigationDrawer.setAdapter(new NavigationAdapter(this));
        */

        final SectionFragment sunSection = SectionFragment.getSection(DEFAULT_SECTION);
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, sunSection)
                .commit();

        // TODO:ACTIONS Add code for WearableActionDrawer here.
    }

    // TODO:NAV Add NavigationAdapter here.
}
