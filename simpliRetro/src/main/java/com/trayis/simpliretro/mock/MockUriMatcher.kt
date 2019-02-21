package com.trayis.simpliretro.mock

import android.text.TextUtils

import java.util.ArrayList

import okhttp3.HttpUrl

/**
 * Created by mudesai on 9/18/16.
 */
internal class MockUriMatcher {

    private var mJsonFile: String? = null
    private var mKind: Int = 0
    private var mEndPoint: String? = null
    private var mMethod: String? = null
    private var mChildren: ArrayList<MockUriMatcher>? = null

    constructor(code: String) {
        mJsonFile = code
        mKind = -1
        mChildren = ArrayList()
        mEndPoint = null
    }

    private constructor() {
        mJsonFile = NO_MATCH
        mKind = -1
        mChildren = ArrayList()
        mEndPoint = null
    }

    fun addURI(authority: String, path: String?, code: String, method: String?) {
        if (TextUtils.isEmpty(code)) {
            throw IllegalArgumentException(String.format("code %s is invalid: cannot be null or empty", code))
        }

        var tokens: Array<String>? = null
        path?.let {
            var newPath = it
            // Strip leading slash if present.
            if (it.length > 1 && it[0] == '/') {
                newPath = it.substring(1)
            }
            tokens = newPath.split("/".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        }

        val numTokens = tokens?.size ?: 0
        var node = this
        for (i in -1 until numTokens) {
            val token = if (i < 0) authority else tokens!![i]
            node.mChildren?.let {
                var child: MockUriMatcher
                var j = 0
                while (j < it.size) {
                    child = it[j]
                    if (token == child.mEndPoint) {
                        node = child
                        break
                    }
                    j++
                }

                if (j == it.size) {
                    // Child not found, create it
                    child = MockUriMatcher()
                    /* if (token.equals("#")) {
                    child.mKind = NUMBER;
                } else*/
                    if (token.startsWith("{") && token.endsWith("}")) {
                        child.mKind = TEXT
                    } else {
                        child.mKind = EXACT
                    }
                    child.mEndPoint = token
                    node.mChildren?.add(child)
                    node = child
                }
            }
        }
        node.mJsonFile = code
        node.mMethod = method?.toUpperCase() ?: "GET"
    }

    fun match(uri: HttpUrl, method: String): String? {
        val pathSegments = uri.pathSegments()
        val li = pathSegments.size

        var node: MockUriMatcher? = this

        for (i in -1 until li) {
            val u = if (i < 0) uri.host() else pathSegments[i]
            val list = node!!.mChildren ?: break
            node = null
            val lj = list.size
            for (j in 0 until lj) {
                val n = list[j]
                when (n.mKind) {
                    EXACT -> if (n.mEndPoint == u) {
                        node = n
                    }
                    TEXT -> node = n
                }
                if (node != null) {
                    if (node.mMethod == null || method == node.mMethod) {
                        break
                    }
                    node = null
                }
            }
            if (node == null) {
                return NO_MATCH
            }
        }

        return node?.mJsonFile
    }

    companion object {
        const val NO_MATCH = ""
        const val EXACT = 0
        const val TEXT = 1
    }
}
