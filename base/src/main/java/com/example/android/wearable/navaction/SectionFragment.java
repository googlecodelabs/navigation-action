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

import android.app.Fragment;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Basic section container.
 *
 * <p>TODO: Replace these with implementations to match your apps functionality.
 */
public class SectionFragment extends Fragment {

    public enum Section {
        Sun(R.string.sun_title, R.drawable.ic_sun_black_24dp),
        Moon(R.string.moon_title, R.drawable.ic_moon_black_24dp),
        Earth(R.string.earth_title, R.drawable.ic_earth_black_24dp),
        Settings(R.string.settings_title, R.drawable.ic_settings_black_24dp);

        final int titleRes;
        final int drawableRes;

        Section(final int titleRes, final int drawableRes) {
            this.titleRes = titleRes;
            this.drawableRes = drawableRes;
        }
    }

    /**
     * Helper method to quickly create sections.
     *
     * @param section The {@link Section} to use.
     * @return A new SectionFragment with arguments set based on the provided Section.
     */
    public static SectionFragment getSection(final Section section) {
        final SectionFragment newSection = new SectionFragment();
        final Bundle arguments = new Bundle();
        arguments.putSerializable(EXTRA_SECTION, section);
        newSection.setArguments(arguments);
        return newSection;
    }

    public static final String EXTRA_SECTION =
            "com.example.android.wearable.navaction.EXTRA_SECTION";

    private Section mSection;
    private ImageView mEmojiView;
    private TextView mTitleView;

    @Nullable
    @Override
    public View onCreateView(
            LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_section, container, false);
        mEmojiView = (ImageView) view.findViewById(R.id.emoji);
        mTitleView = (TextView) view.findViewById(R.id.title);

        if (getArguments() != null) {
            mSection = (Section) getArguments().getSerializable(EXTRA_SECTION);
            final Drawable imageDrawable =
                    ContextCompat.getDrawable(getContext(), mSection.drawableRes);
            mEmojiView.setImageDrawable(imageDrawable);
            mTitleView.setText(getResources().getString(mSection.titleRes));
        }

        return view;
    }
}
