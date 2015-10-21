package com.bei.ui.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bei.R;
import com.bei.bean.TravelPart;
import com.bei.bean.TravelPic;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobPointer;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by xinwenbo on 15/10/9.
 */
public class ImagePagerFragment extends Fragment {

    private ViewPager viewPager;

    public static ImagePagerFragment getInstance(Bundle imgs) {
        ImagePagerFragment fragment = new ImagePagerFragment();
        fragment.setArguments(imgs);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.f_image_pager, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        TravelPart travelPart = (TravelPart) bundle.getSerializable("part");

        viewPager = (ViewPager) view.findViewById(R.id.viewpager);

        BmobQuery<TravelPic> query = new BmobQuery<>();
        query.addWhereRelatedTo("pics", new BmobPointer(travelPart));
        query.findObjects(getActivity(), new FindListener<TravelPic>() {

            @Override
            public void onSuccess(List<TravelPic> object) {
                if (object != null && object.size() > 0) {
                    List<SimpleDraweeView> viewList = new ArrayList<SimpleDraweeView>();
                    for (TravelPic t : object) {
                        SimpleDraweeView simpleDraweeView = new SimpleDraweeView(getActivity());
                        simpleDraweeView.setImageURI(Uri.parse(t.getPic().getFileUrl(getActivity())));
                        simpleDraweeView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (!ImagePagerFragment.this.isResumed()) {//fragment被回收
                                    return;
                                }
                                final FragmentManager fragmentManager = getFragmentManager();
                                if (fragmentManager != null) {
                                    fragmentManager.popBackStack();//回退栈
                                }

                            }
                        });
                        viewList.add(simpleDraweeView);
                    }

                    viewPager.setAdapter(new ImagePagerAdapter(viewList));
                }
            }

            @Override
            public void onError(int code, String msg) {
                Log.e("PICS", "查询失败：" + code + "-" + msg);
            }
        });
    }

    private class ImagePagerAdapter extends PagerAdapter {

        private List<SimpleDraweeView> viewList;

        public ImagePagerAdapter(List<SimpleDraweeView> viewList) {
            this.viewList = viewList;
        }

        @Override
        public int getCount() {
            return viewList.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(viewList.get(position % viewList.size()));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(viewList.get(position % viewList.size()));
            return viewList.get(position);
        }

    }

}
