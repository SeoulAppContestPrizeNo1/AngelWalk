package com.walk.angel.angelwalk.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.walk.angel.angelwalk.Activity.SightsActivity;
import com.walk.angel.angelwalk.R;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

public class MapFragment extends Fragment {


    Button btnTourism;
    MapView mapView;
    ViewGroup MapFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        MapFragment = (ViewGroup) inflater.inflate(R.layout.fragment_map, container, false);

        return MapFragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        createMapView();
    }

    @Override
    public void onPause() {
        super.onPause();

        //MapFragment.removeView(mapView);
    }

    public void createMapView(){
        //다음이 제공하는 MapView객체 생성 및 API Key 설정
        mapView = new MapView(this.getActivity());
        mapView.setDaumMapApiKey("edf139f45cfd471ad648c79df3433d57");

        //xml에 선언된 map_view 레이아웃을 찾아온 후, 생성한 MapView객체 추가
        RelativeLayout mapContainer = (RelativeLayout) getView().findViewById(R.id.map_view);
        mapContainer.addView(mapView);


        // 줌 인
        mapView.zoomIn(true);
        // 줌 아웃
        mapView.zoomOut(true);

        MapPOIItem marker = new MapPOIItem();
        marker.setItemName("서현동");
        marker.setTag(0);
        marker.setMapPoint(MapPoint.mapPointWithGeoCoord(37.38,127.13));
        marker.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양.
        marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.

        MapPOIItem marker2 = new MapPOIItem();
        marker2.setItemName("건국대");
        marker2.setTag(0);
        marker2.setMapPoint(MapPoint.mapPointWithGeoCoord(37.54,127.07));
        marker2.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양.
        marker2.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.

        mapView.addPOIItem(marker);
        mapView.addPOIItem(marker2);
    }
}
