package com.xyc.tablayoutviewpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by xyc on 2015/11/10.
 */
public class DemoFragment extends Fragment {

    private View mView;
    private String type = "";

    public static DemoFragment newInstance(String type) {
        DemoFragment demoLazyFragment = new DemoFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        demoLazyFragment.setArguments(bundle);
        return demoLazyFragment;
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.demo_fragment, container, false);
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
