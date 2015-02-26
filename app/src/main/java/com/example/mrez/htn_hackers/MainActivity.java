package com.example.mrez.htn_hackers;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MainActivity extends Activity {

    /**
     * Global variables *
     */
    GoogleMap googleMap;
    Map<String, BitmapDescriptor> map = new HashMap<String, BitmapDescriptor>();

    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Splash screen
        loadSplashPage();

        // Google Maps
        setHashMap();
        createMapView();

        // retrieve user data from Firebase server
        Firebase.setAndroidContext(this);
        getData();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // accessor to retrieve HTN attendee profile data from Firebase server
    public void getData() {

        // Get a reference to our posts
        Firebase ref = new Firebase("https://htn15-interviews.firebaseio.com");

        // Retrieve new posts as they are added to Firebase
        ref.addChildEventListener(new ChildEventListener() {

            // Attach an listener to read the data at our posts reference
            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousChildKey) {
                ArrayList data = (ArrayList) snapshot.getValue();
                BitmapDescriptor pinColor;
                Gson gson = new Gson();

                /**
                 * Object -> JSON
                 *
                 * convert Firebase default object to JSON string which we can serialize later
                 */

                String json = gson.toJson(data);

                /**
                 * JSON -> Object
                 *
                 * let's store Firebase data in our own class for easy member access
                 */

                Users[] userList = gson.fromJson(json, Users[].class);

                for (Users user : userList){

                    /**
                     *  user data information
                     */

                    float longitute = user.getLongitude();
                    float latitude = user.getLatitude();
                    String name = user.getName();
                    String company = user.getCompany();
                    String phone = user.getPhone();
                    String topSkill = "ANDROID";
                    List<Skills> skills = user.getSkills();

                    int highestRating = 0;

                    // traverse user skill list
                    for (Skills s : skills) {
                        if (s.getRating() > highestRating) {
                            topSkill = s.getName().toUpperCase();
                        }
                    }

                    pinColor = map.get(topSkill);

                    // null check
                    if (pinColor == null) {
                        pinColor = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE);
                    }

                    // drop pin!
                    addMarker(longitute, latitude, name, company, phone, pinColor, topSkill);

                }
            }

        @Override
        public void onChildChanged (DataSnapshot snapshot, String previousChildKey){
        }

        @Override
        public void onChildRemoved (DataSnapshot snapshot){
        }

        @Override
        public void onChildMoved (DataSnapshot snapshot, String previousChildName){
        }

        @Override
        public void onCancelled (FirebaseError firebaseError){
        }
    }

    );
}

    /**
     * Initialises the mapview
     */
    private void createMapView() {
        /**
         * Catch the null pointer exception that
         * may be thrown when initialising the map
         */
        try {
            if (null == googleMap) {
                googleMap = ((MapFragment) getFragmentManager().findFragmentById(
                        R.id.mapView)).getMap();

                /**
                 * If the map is still null after attempted initialisation,
                 * show an error to the user
                 */
                if (null == googleMap) {
                    Toast.makeText(getApplicationContext(),
                            "Error creating map", Toast.LENGTH_SHORT).show();
                }
            }
        } catch (NullPointerException exception) {
            Log.e("mapApp", exception.toString());
        }
    }

    /**
     * Adds a marker to the map
     */
    private void addMarker(float x, float y, String name, String company, String phone, BitmapDescriptor pinColor, String skill) {

        /** Make sure that the map has been initialised **/
        if (null != googleMap) {
            googleMap.addMarker(new MarkerOptions()
                            .icon(pinColor)
                            .position(new LatLng(x, y))
                            .title(name + "  [" + skill + "]")
                            .snippet("Phone: " + phone + "  | Company: " + company)
                            .draggable(true)

            );
        }
    }

    public void setHashMap() {

        /**
         * sets and stores key value pairs for pin color to skills
         */

        map.put("JS", BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        map.put("GO", BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        map.put("C", BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN));
        map.put("ANDROID", BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        map.put("PUBLIC SPEAKING", BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        map.put("IOS", BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
        map.put("ANGULAR", BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
        map.put("C++", BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        map.put("HTML/CSS", BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE));
        map.put("NODEJS", BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET));
        map.put("JAVA", BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
        map.put("PRODUCT DESIGN", BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN));

    }

    /**
     * simple loading overlay on top of main activity
     * dismisses after 3 seconds while data gets retrieved and map populated in the background
     *
     * LOAD TIME: ~3 seconds
     * BOOT TIME: ~1 second
     */
    public void loadSplashPage(){
        image = (ImageView) findViewById(R.id.imageView1);
        image.setImageResource(R.drawable.htn_splash);
        image.postDelayed(new Runnable() { public void run() { image.setVisibility(View.GONE); } }, 4000);
    }
}

