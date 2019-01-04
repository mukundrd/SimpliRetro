package com.trayis.simpliretro.mock

import android.text.TextUtils

import java.util.ArrayList

import okhttp3.HttpUrl

/**
 * Created by mudesai on 9/18/16.
 */
class MockUriMatcher {

    private var mCode: String? = null
    private var mWhich: Int = 0
    private var mText: String? = null
    private var mChildren: ArrayList<MockUriMatcher>? = null

    constructor(code: String) {
        mCode = code
        mWhich = -1
        mChildren = ArrayList()
        mText = null
    }

    private constructor() {
        mCode = NO_MATCH
        mWhich = -1
        mChildren = ArrayList()
        mText = null
    }

    fun addURI(authority: String, path: String?, code: String?) {
        if (TextUtils.isEmpty(code)) {
            throw IllegalArgumentException(String.format("code %s is invalid: cannot be null or empty", code))
        }

        var tokens: Array<String>? = null
        if (path != null) {
            var newPath: String = path
            // Strip leading slash if present.
            if (path.length > 1 && path[0] == '/') {
                newPath = path.substring(1)
            }
            tokens = newPath.split("/".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        }

        val numTokens = tokens?.size ?: 0
        var node = this
        for (i in -1 until numTokens) {
            val token = if (i < 0) authority else tokens!![i]
            val children = node.mChildren
            val numChildren = children!!.size
            var child: MockUriMatcher
            var j: Int
            j = 0
            while (j < numChildren) {
                child = children[j]
                if (token == child.mText) {
                    node = child
                    break
                }
                j++
            }
            if (j == numChildren) {
                // Child not found, create it
                child = MockUriMatcher()
                /* if (token.equals("#")) {
                    child.mWhich = NUMBER;
                } else*/
                if (token.startsWith("{") && token.endsWith("}")) {
                    child.mWhich = TEXT
                } else {
                    child.mWhich = EXACT
                }
                child.mText = token
                node.mChildren!!.add(child)
                node = child
            }
        }
        node.mCode = code
    }

    fun match(uri: HttpUrl): String? {
        val pathSegments = uri.pathSegments()
        val li = pathSegments.size

        var node: MockUriMatcher? = this

        if (li == 0) {
            return this.mCode
        }

        for (i in -1 until li) {
            val u = if (i < 0) uri.host() else pathSegments[i]
            val list = node!!.mChildren ?: break
            node = null
            val lj = list.size
            for (j in 0 until lj) {
                val n = list[j]
                when (n.mWhich) {
                    EXACT -> if (n.mText == u) {
                        node = n
                    }
                    TEXT -> node = n
                }
                if (node != null) {
                    break
                }
            }
            if (node == null) {
                return NO_MATCH
            }
        }

        return node!!.mCode
    }

    companion object {
        const val NO_MATCH = ""
        const val EXACT = 0
        const val TEXT = 1
    }
}
