/*
 * Copyright (C) 2017 Dominik Mosberger <https://github.com/mosberger>
 * Copyright (C) 2013 Leszek Mzyk <https://github.com/imbryk>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.sufficientlysecure.htmltextview;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Parcel;

//TODO fix AndroidX import androidx.annotation.NonNull;
//TODO fix AndroidX import androidx.annotation.Nullable;

import android.text.Layout;
import android.text.Spanned;
import android.text.style.BulletSpan;

/**
 * Class to use Numbered Lists in TextViews.
 * The span works the same as {@link android.text.style.BulletSpan} and all lines of the entry have
 * the same leading margin.
 */
public class NumberSpan extends BulletSpan {
    private final int mNumberGapWidth;
    private final String mNumber;

    public static final int STANDARD_GAP_WIDTH = 10;

    public NumberSpan(int gapWidth, int number) {
        super();
        mNumberGapWidth = gapWidth;
        mNumber = Integer.toString(number).concat(".");
    }

    public NumberSpan(int number) {
        this(STANDARD_GAP_WIDTH, number);
    }

    public NumberSpan(Parcel src) {
        super(src);
        mNumberGapWidth = src.readInt();
        mNumber = src.readString();
    }

    public void writeToParcel(/*TODO fix AndroidX @NonNull*/ Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(mNumberGapWidth);
        dest.writeString(mNumber);
    }

    public int getLeadingMargin(boolean first) {
        return 2 * STANDARD_GAP_WIDTH + mNumberGapWidth;
    }

    @Override
    public void drawLeadingMargin(/*TODO fix AndroidX @NonNull*/ Canvas c, /*TODO fix AndroidX @NonNull*/ Paint p, int x, int dir,
                                  int top, int baseline, int bottom, /*TODO fix AndroidX @NonNull*/ CharSequence text,
                                  int start, int end, boolean first, /*TODO fix AndroidX @Nullable*/ Layout l) {
        if (((Spanned) text).getSpanStart(this) == start) {
            Paint.Style style = p.getStyle();

            p.setStyle(Paint.Style.FILL);

            if (c.isHardwareAccelerated()) {
                c.save();
                c.drawText(mNumber, x + dir, baseline, p);
                c.restore();
            } else {
                c.drawText(mNumber, x + dir, (top + bottom) / 2.0f, p);
            }

            p.setStyle(style);
        }
    }
}
