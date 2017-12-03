package papadogiannis.stefanos.tutoranytime;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.util.Date;

public class AddressActiv2 extends Custom implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener
{

    public static final long UPDATE_INTERVAL_IN_MILLISECONDS = 10000;
    public static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS = UPDATE_INTERVAL_IN_MILLISECONDS / 2;
    protected final static String REQUESTING_LOCATION_UPDATES_KEY = "requesting-location-updates-key";
    protected final static String LOCATION_KEY = "location-key";
    protected final static String LAST_UPDATED_TIME_STRING_KEY = "last-updated-time-string-key";
    protected GoogleApiClient mGoogleApiClient;
    protected LocationRequest mLocationRequest;
    protected Location mCurrentLocation;
    protected Boolean mRequestingLocationUpdates;
    protected String mLastUpdateTime;
    private GoogleMap googleMap;
    private MarkerOptions myMarker;
    private String longitude;
    private String latitude;
    private static int gps;
    private Bundle savedInstanceState2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.address2);
        setTouchNClick(R.id.next);
        setTouchNClick(R.id.back);

        savedInstanceState2 = savedInstanceState;

//        AsyncTask asyncTask = new AsyncTask() {
//            @Override
//            protected Object doInBackground(Object[] objects) {
                try {
                    initializeMap();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
        mRequestingLocationUpdates = false;
        mLastUpdateTime = "";
        updateValuesFromBundle(savedInstanceState2);
        buildGoogleApiClient();
        mGoogleApiClient.connect();
        boolean isGPS = true;
        LocationAvailability locationAvailability = LocationServices.FusedLocationApi.getLocationAvailability(mGoogleApiClient);
        if ( locationAvailability == null )
            checkGps();
        else {
            isGPS = locationAvailability.isLocationAvailable();
        }

        if(!isGPS)
            checkGps();
//                return null;
//            }
//        };
//        asyncTask.execute();

    }

    private void updateValuesFromBundle(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            // Update the value of mRequestingLocationUpdates from
            // the Bundle, and make sure that
            // the Start Updates and Stop Updates buttons are correctly
            // enabled or disabled.
            if (savedInstanceState.keySet().contains(REQUESTING_LOCATION_UPDATES_KEY)) {
                mRequestingLocationUpdates = savedInstanceState.getBoolean(REQUESTING_LOCATION_UPDATES_KEY);
            }
            // Update the value of mCurrentLocation from
            // the Bundle and update the UI to show the
            // correct latitude and longitude.
            if (savedInstanceState.keySet().contains(LOCATION_KEY)) {
                // Since LOCATION_KEY was found in the
                // Bundle, we can be sure that mCurrentLocation
                // is not null.
                mCurrentLocation = savedInstanceState.getParcelable(LOCATION_KEY);
            }
            // Update the value of mLastUpdateTime from the Bundle and update the UI.
            if (savedInstanceState.keySet().contains(LAST_UPDATED_TIME_STRING_KEY)) {
                mLastUpdateTime=savedInstanceState.getString(LAST_UPDATED_TIME_STRING_KEY);
            }
            //updateValues();
            if (mCurrentLocation != null) {
                latitude = String.valueOf(mCurrentLocation.getLatitude());
                longitude = String.valueOf(mCurrentLocation.getLongitude());
            }
        }
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        createLocationRequest();
    }

    protected void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(UPDATE_INTERVAL_IN_MILLISECONDS);
        mLocationRequest.setFastestInterval(FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    public void startUpdates() {
        if (!mRequestingLocationUpdates) {
            mRequestingLocationUpdates = true;
            startLocationUpdates();
        }
    }

    public void stopUpdates() {
        if (mRequestingLocationUpdates) {
            mRequestingLocationUpdates = false;
            stopLocationUpdates();
        }
    }

    protected void startLocationUpdates() {
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
    }

    private void updateValues() {
        if (mCurrentLocation != null) {
            latitude = String.valueOf(mCurrentLocation.getLatitude());
            longitude = String.valueOf(mCurrentLocation.getLongitude());
            googleMap.clear();
            myMarker.position(new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude)));
            createZoomAnimation();
            googleMap.addMarker(myMarker);
            TextView textView = (TextView) findViewById(R.id.empty_map_error);
            textView.setVisibility(View.GONE);
        }
    }

    private void createZoomAnimation() {
        CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(Double.parseDouble(latitude),
                Double.parseDouble(longitude))).zoom(17).build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    protected void stopLocationUpdates() {
        LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
    }

    @Override
    protected void onStart() {
        super.onStart();
//        mGoogleApiClient.connect();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        initializeMap();
//        if (mGoogleApiClient.isConnected() && mRequestingLocationUpdates) {
//            startLocationUpdates();
//        }
//        boolean isGPS = true;
//        LocationAvailability locationAvailability = LocationServices.FusedLocationApi.getLocationAvailability(mGoogleApiClient);
//        if ( locationAvailability == null )
//            checkGps();
//        else {
//            isGPS = locationAvailability.isLocationAvailable();
//        }
//
//        if(!isGPS)
//            checkGps();
    }

    public void checkGps(){
        final Dialog dialog=new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.gps);
        dialog.setCancelable(false);
        dialog.show();

        Button gpsSettings = (Button) dialog.findViewById(R.id.gps_set_button);
        gpsSettings.setOnTouchListener(new View.OnTouchListener() {
            int motionEventFinished = 0;

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    Drawable d = view.getBackground();
                    d.mutate();
                    d.setAlpha(150);
                    view.setBackgroundDrawable(d);
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP || motionEvent.getAction() == MotionEvent.ACTION_CANCEL) {
                    Drawable d = view.getBackground();
                    d.setAlpha(255);
                    view.setBackgroundDrawable(d);
                    motionEventFinished++;
                }
                if (motionEventFinished == 1) {
                    dialog.dismiss();
                    stopUpdates();
                    startActivityForResult(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS), 0);
                }
                return false;
            }
        });

        Button gpsCancel = (Button) dialog.findViewById(R.id.cancel);
        gpsCancel.setOnTouchListener(new View.OnTouchListener() {
            int motionEventFinished = 0;

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    Drawable d = view.getBackground();
                    d.mutate();
                    d.setAlpha(150);
                    view.setBackgroundDrawable(d);
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP || motionEvent.getAction() == MotionEvent.ACTION_CANCEL) {
                    Drawable d = view.getBackground();
                    d.setAlpha(255);
                    view.setBackgroundDrawable(d);
                    motionEventFinished++;
                }
                if (motionEventFinished == 1) {
                    dialog.dismiss();
                }
                return false;
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mGoogleApiClient.isConnected()) {
            stopLocationUpdates();
        }
    }

    @Override
    public void onConnected(Bundle connectionHint) {
        if (mCurrentLocation == null) {
            mCurrentLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
            mLastUpdateTime = DateFormat.getTimeInstance().format(new Date());
            //updateValues();
            startUpdates();
        }
        if (mRequestingLocationUpdates) {
            startLocationUpdates();
        }
    }

    private void initializeMap() {
        if (googleMap == null) {
            ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap) {
                    AddressActiv2.this.googleMap=googleMap;
                    myMarker = new MarkerOptions();
                    if (TutorAnytimeApp.getPosition().latitude != 0.0f && TutorAnytimeApp.getPosition().longitude != 0.0f)
                    {
                        myMarker.position(TutorAnytimeApp.getPosition());
                        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(myMarker.getPosition().latitude, myMarker.getPosition().longitude), 17.0f));
                        googleMap.addMarker(myMarker);
                    }
                    updateValues();
                }
            });
//            if (googleMap == null) {
//                Toast.makeText(getApplicationContext(), "Ανεπιτυχής δημιουργία του χάρτη!", Toast.LENGTH_SHORT).show();
//            }
        }

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.984124, 23.728013), 10.0f));
    }

    @Override
    public void onClick(View v)
    {
        if (v.getId() == R.id.next)
        {
            boolean validationError = false;
            if (myMarker.getPosition() == null)
            {
                validationError = true;
                TextView textView = (TextView) findViewById(R.id.empty_map_error);
                textView.setVisibility(View.VISIBLE);
            }
            if (validationError)
            {
                return;
            }
            else
            {
                TutorAnytimeApp.setPosition(myMarker.getPosition());
                startActivity(new Intent(this, JobInfo.class));
            }
        }
        else
            finish();
    }

    @Override
    public void onLocationChanged(Location location) {
        mCurrentLocation = location;
        mLastUpdateTime = DateFormat.getTimeInstance().format(new Date());
        updateValues();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState)
    {
        savedInstanceState.putBoolean(REQUESTING_LOCATION_UPDATES_KEY, mRequestingLocationUpdates);
        savedInstanceState.putParcelable(LOCATION_KEY, mCurrentLocation);
        savedInstanceState.putString(LAST_UPDATED_TIME_STRING_KEY, mLastUpdateTime);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onConnectionSuspended(int i) {
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
}
