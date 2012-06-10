package project.DirectionOfPhoto;

import java.io.IOException;

import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ZoomControls;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;

public class MapActivity extends com.google.android.maps.MapActivity {
	private String path;
	private float[] mapAddr = new float[2];
	private GeoPoint addPoint;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map);
		// 取得Bundle資料
		Bundle bundle = this.getIntent().getExtras();
		if (bundle != null)
			path = bundle.getString("Image");

		// 取得JPG檔額外資訊

		// ZOOM
		LinearLayout linearLayout = (LinearLayout) findViewById(R.id.zoomView);
		MapView mapView = (MapView) findViewById(R.id.mapView);
		ZoomControls myZoom = (ZoomControls) mapView.getZoomControls();
		linearLayout.addView(myZoom);

		Drawable mdrawable = this.getResources().getDrawable(
				R.drawable.ic_launcher);
		MapOverlay mapOverlay = new MapOverlay(mdrawable);
		// 加入經緯度
		// 取得JPG檔額外資訊
		try {
			ExifInterface exifInterface = new ExifInterface(path);
			if (exifInterface.getLatLong(mapAddr)) {
				addPoint = new GeoPoint((int) (mapAddr[0] * 1E6),
						(int) (mapAddr[1] * 1E6));
				OverlayItem overlayItem = new OverlayItem(addPoint, "", "");
				mapOverlay.addOverlayItem(overlayItem);
				mapView.getOverlays().add(mapOverlay);
			} else
				addPoint = new GeoPoint(24968134, 121195464);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * GeoPoint addPoint = new GeoPoint(24968134, 121195464); OverlayItem
		 * overlayItem = new OverlayItem(addPoint, "", "");
		 * mapOverlay.addOverlayItem(overlayItem);
		 * mapView.getOverlays().add(mapOverlay);
		 */
		// 設定addPoint為中心
		mapView.getController().setCenter(addPoint);
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

}
