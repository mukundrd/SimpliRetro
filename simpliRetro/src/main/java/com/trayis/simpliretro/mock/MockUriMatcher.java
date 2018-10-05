package com.trayis.simpliretro.mock;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

import okhttp3.HttpUrl;

/**
 * Created by mudesai on 9/18/16.
 */
class MockUriMatcher {

    static final String NO_MATCH = "";
    private static final int EXACT = 0;
    private static final int TEXT = 1;

    private String mCode;
    private int mWhich;
    private String mText;
    private ArrayList<MockUriMatcher> mChildren;

    MockUriMatcher(String code) {
        mCode = code;
        mWhich = -1;
        mChildren = new ArrayList<>();
        mText = null;
    }

    private MockUriMatcher() {
        mCode = NO_MATCH;
        mWhich = -1;
        mChildren = new ArrayList<>();
        mText = null;
    }

    void addURI(String authority, String path, String code) {
        if (TextUtils.isEmpty(code)) {
            throw new IllegalArgumentException(String.format("code %s is invalid: cannot be null or empty", code));
        }

        String[] tokens = null;
        if (path != null) {
            String newPath = path;
            // Strip leading slash if present.
            if (path.length() > 1 && path.charAt(0) == '/') {
                newPath = path.substring(1);
            }
            tokens = newPath.split("/");
        }

        int numTokens = tokens != null ? tokens.length : 0;
        MockUriMatcher node = this;
        for (int i = -1; i < numTokens; i++) {
            String token = i < 0 ? authority : tokens[i];
            ArrayList<MockUriMatcher> children = node.mChildren;
            int numChildren = children.size();
            MockUriMatcher child;
            int j;
            for (j = 0; j < numChildren; j++) {
                child = children.get(j);
                if (token.equals(child.mText)) {
                    node = child;
                    break;
                }
            }
            if (j == numChildren) {
                // Child not found, create it
                child = new MockUriMatcher();
                /* if (token.equals("#")) {
                    child.mWhich = NUMBER;
                } else*/
                if (token.startsWith("{") && token.endsWith("}")) {
                    child.mWhich = TEXT;
                } else {
                    child.mWhich = EXACT;
                }
                child.mText = token;
                node.mChildren.add(child);
                node = child;
            }
        }
        node.mCode = code;
    }

    String match(HttpUrl uri) {
        final List<String> pathSegments = uri.pathSegments();
        final int li = pathSegments.size();

        MockUriMatcher node = this;

        if (li == 0 && uri.host() == null) {
            return this.mCode;
        }

        for (int i = -1; i < li; i++) {
            String u = i < 0 ? uri.host() : pathSegments.get(i);
            ArrayList<MockUriMatcher> list = node.mChildren;
            if (list == null) {
                break;
            }
            node = null;
            int lj = list.size();
            for (int j = 0; j < lj; j++) {
                MockUriMatcher n = list.get(j);
                switch (n.mWhich) {
                    case EXACT:
                        if (n.mText.equals(u)) {
                            node = n;
                        }
                        break;
                    case TEXT:
                        node = n;
                        break;
                }
                if (node != null) {
                    break;
                }
            }
            if (node == null) {
                return NO_MATCH;
            }
        }

        return node.mCode;
    }
}
