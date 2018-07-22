package com.walk.angel.angelwalk.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.walk.angel.angelwalk.Activity.SightsActivity;
import com.walk.angel.angelwalk.R;

import net.daum.mf.map.api.MapView;

public class MapFragment extends Fragment {


    Button btnTourism;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup MapFragment = (ViewGroup) inflater.inflate(R.layout.fragment_map, container, false);

//        //다음이 제공하는 MapView객체 생성 및 API Key 설정
//        MapView mapView = new MapView(this.getActivity());
//        mapView.setDaumMapApiKey("edf139f45cfd471ad648c79df3433d57");
//
//        //xml에 선언된 map_view 레이아웃을 찾아온 후, 생성한 MapView객체 추가
//        //RelativeLayout container = (RelativeLayout) findViewById(R.id.map_view);
//        container.addView(mapView);

        return MapFragment;
    }
}
