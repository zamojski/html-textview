package org.sufficientlysecure.htmltextview;

import android.view.View;

import androidx.annotation.Nullable;

/**
 * This listener can define what happens when the a tag is clicked
 */
public interface OnClickATagListener {
    void onClick(View widget, String spannedText, @Nullable String href);
}
