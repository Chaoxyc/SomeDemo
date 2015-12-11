package com.xyc.tablayoutviewpager;


import android.support.v4.app.Fragment;

/**
 * Created by xyc on 2015/12/9.
 */
public abstract class LazyFragment extends Fragment {
    /**
     * Fragment当前是否应该加载
     */
    protected boolean isLoad;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isLoad = true;
            onLoading();
        } else {
            isLoad = false;
            onCancelLoading();
        }
    }

    /**
     * 加载数据
     */
    protected void onLoading() {
        lazyLoading();
    }

    /**
     * 不加载数据
     */
    protected void onCancelLoading() {
    }

    /**
     * 初始化
     */
    protected abstract void lazyLoading();
}
