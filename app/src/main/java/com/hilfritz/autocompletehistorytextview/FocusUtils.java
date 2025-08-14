package com.hilfritz.autocompletehistorytextview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;

public final class FocusUtils {

    private FocusUtils() {}

    /**
     * Ensure parent is focusable, clear focus from all children that are instances of customClass,
     * optionally clear any currently focused view, then give focus to the parent and optionally
     * hide the keyboard.
     */
    public static void clearFocusFromCustomViews(
            @NonNull ViewGroup parent,
            @NonNull Class<? extends View> customClass,
            boolean clearAnyFocusedView,
            boolean hideKeyboard
    ) {
        // 1) Ensure the parent can accept focus
        if (!parent.isFocusable()) parent.setFocusable(true);
        if (!parent.isFocusableInTouchMode()) parent.setFocusableInTouchMode(true);

        // 2) Recursively clear focus from all matching children
        clearFocusRecursive(parent, customClass);

        // 3) Optionally clear whatever view currently has focus (if it's outside customClass)
        if (clearAnyFocusedView) {
            View current = parent.findFocus();
            if (current != null) current.clearFocus();
        }

        // 4) Give focus to the parent
        parent.requestFocus();

        // 5) Optionally hide the keyboard
        if (hideKeyboard) {
            InputMethodManager imm = (InputMethodManager)
                    parent.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            View windowTokenView = parent; // safe fallback
            if (imm != null && windowTokenView.getWindowToken() != null) {
                imm.hideSoftInputFromWindow(windowTokenView.getWindowToken(), 0);
            }
        }
    }

    private static void clearFocusRecursive(@NonNull ViewGroup group,
                                            @NonNull Class<? extends View> customClass) {
        for (int i = 0; i < group.getChildCount(); i++) {
            View child = group.getChildAt(i);

            if (customClass.isInstance(child)) {
                child.clearFocus();
            }
            if (child instanceof ViewGroup) {
                clearFocusRecursive((ViewGroup) child, customClass);
            }
        }
    }
}

