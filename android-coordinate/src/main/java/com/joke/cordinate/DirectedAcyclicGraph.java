package com.joke.cordinate;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.Pools;
import android.support.v4.util.SimpleArrayMap;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * A class which represents a simple directed acyclic graph.
 */
final class DirectedAcyclicGraph<T> {
    private final Pools.Pool<ArrayList<T>> mListPool = new Pools.SimplePool<>(10);
    private final SimpleArrayMap<T, ArrayList<T>> mGraph = new SimpleArrayMap<>();

    private final ArrayList<T> mSortResult = new ArrayList<>();
    private final HashSet<T> mSortTmpMarked = new HashSet<>();

    void addNode(@NonNull T node) {
        if (!mGraph.containsKey(node)) {
            mGraph.put(node, null);
        }
    }

    boolean contains(@NonNull T node) {
        return mGraph.containsKey(node);
    }

    void addEdge(@NonNull T node, @NonNull T incomingEdge) {
        if (!mGraph.containsKey(node) || !mGraph.containsKey(incomingEdge)) {
            throw new IllegalArgumentException("All nodes must be present in the graph before"
                    + " being added as an edge");
        }

        ArrayList<T> edges = mGraph.get(node);
        if (edges == null) {
            // If edges is null, we should try and get one from the pool and add it to the graph
            edges = getEmptyList();
            mGraph.put(node, edges);
        }
        // Finally add the edge to the list
        edges.add(incomingEdge);
    }

    @Nullable
    List getIncomingEdges(@NonNull T node) {
        return mGraph.get(node);
    }

    @Nullable
    List getOutgoingEdges(@NonNull T node) {
        ArrayList<T> result = null;
        for (int i = 0, size = mGraph.size(); i < size; i++) {
            ArrayList<T> edges = mGraph.valueAt(i);
            if (edges != null && edges.contains(node)) {
                if (result == null) {
                    result = new ArrayList<>();
                }
                result.add(mGraph.keyAt(i));
            }
        }
        return result;
    }

    boolean hasOutgoingEdges(@NonNull T node) {
        for (int i = 0, size = mGraph.size(); i < size; i++) {
            ArrayList<T> edges = mGraph.valueAt(i);
            if (edges != null && edges.contains(node)) {
                return true;
            }
        }
        return false;
    }

    void clear() {
        for (int i = 0, size = mGraph.size(); i < size; i++) {
            ArrayList<T> edges = mGraph.valueAt(i);
            if (edges != null) {
                poolList(edges);
            }
        }
        mGraph.clear();
    }

    @NonNull
    ArrayList<T> getSortedList() {
        mSortResult.clear();
        mSortTmpMarked.clear();

        // Start a DFS from each node in the graph
        for (int i = 0, size = mGraph.size(); i < size; i++) {
            dfs(mGraph.keyAt(i), mSortResult, mSortTmpMarked);
        }

        return mSortResult;
    }

    private void dfs(final T node, final ArrayList<T> result, final HashSet<T> tmpMarked) {
        if (result.contains(node)) {
            // We've already seen and added the node to the result list, skip...
            return;
        }
        if (tmpMarked.contains(node)) {
            throw new RuntimeException("This graph contains cyclic dependencies");
        }
        // Temporarily mark the node
        tmpMarked.add(node);
        // Recursively dfs all of the node's edges
        final ArrayList<T> edges = mGraph.get(node);
        if (edges != null) {
            for (int i = 0, size = edges.size(); i < size; i++) {
                dfs(edges.get(i), result, tmpMarked);
            }
        }
        // Unmark the node from the temporary list
        tmpMarked.remove(node);
        // Finally add it to the result list
        result.add(node);
    }

    int size() {
        return mGraph.size();
    }

    @NonNull
    private ArrayList<T> getEmptyList() {
        ArrayList<T> list = mListPool.acquire();
        if (list == null) {
            list = new ArrayList<>();
        }
        return list;
    }

    private void poolList(@NonNull ArrayList<T> list) {
        list.clear();
        mListPool.release(list);
    }
}