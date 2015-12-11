package com.xyc.tablayoutviewpager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by HuDai on 2015/11/10.
 */
public class DemoLazyFragment extends LazyFragment {

    private View mView;
    private String type = "";

    public static DemoLazyFragment newInstance(String type) {
        DemoLazyFragment demoLazyFragment = new DemoLazyFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        demoLazyFragment.setArguments(bundle);
        return demoLazyFragment;
    }


    /**
     * 标志位，标志已经初始化完成
     */
    private boolean isLoadOver;
    /**
     * 是否有数据 ?
     */
    private boolean havaData = false;


    @Override
    protected void lazyLoading() {
        if (!isLoadOver || !isLoad || havaData) {
            return;
        }
        if(!havaData){
//            网络请求，在响应成功之后加入 havaData = true;
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.demo_fragment, container, false);
            isLoadOver = true;
            lazyLoading();
        }
        container = (ViewGroup) mView.getParent();
        if (container != null) {
            container.removeView(mView);
        }
        return mView;
    }


    @Override
    public void onResume() {
        super.onResume();
        TextView tv = (TextView) mView.findViewById(R.id.tv);
        if (null != getArguments()) {
            type = getArguments().getString("type");
            tv.setText(type);
        }
    }

}
