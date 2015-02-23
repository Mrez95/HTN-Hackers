package com.example.mrez.htn_hackers;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MainActivity extends Activity {

    /** Global variables **/
    GoogleMap googleMap;
    Map<String,BitmapDescriptor> map = new HashMap<String,BitmapDescriptor>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setHashMap();

        // create map view
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
                List<Objects> data =  (List<Objects>) snapshot.getValue();
                BitmapDescriptor pinColor;
                for(Object obj : data) {

                    /**
                     * Object -> JSON
                     *
                     * convert Firebase default object to JSON string which we can serialize later
                     */
                    Gson gson = new Gson();
                    String json = gson.toJson(obj);

                    /**
                     * JSON -> Object
                     *
                     * let's store Firebase data in our own class for easy member access
                     */

                    UserProfile users = gson.fromJson(json, UserProfile.class);
                    List<Skills> skills = users.getSkills();

                    /**
                     *  user data information
                     */
                    float longitute = users.getLongitude();
                    float latitude = users.getLatitude();
                    String name = users.getName();
                    String company = users.getCompany();
                    String phone = users.getPhone();
                    String topSkill = "ANDROID";

                    int highestRating = 0;

                    // traverse user skill list
                    for(Skills s : skills)
                    {
                        if (s.getRating() > highestRating){
                            topSkill = s.getName().toUpperCase();
                        }
                    }

                    System.out.println("EDDIE EDDIE EDDIE EDDIE");
                    pinColor = map.get(topSkill);

                    // null check
                    if (pinColor == null) {
                        pinColor = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE);
                    }

                    // drop pin!
                    addMarker(longitute, latitude, name, company, phone, pinColor);
                }
            }

            @Override
            public void onChildChanged(DataSnapshot snapshot, String previousChildKey) {}

            @Override
            public void onChildRemoved(DataSnapshot snapshot) {}

            @Override
            public void onChildMoved(DataSnapshot snapshot,String previousChildName) {}

            @Override
            public void onCancelled(FirebaseError firebaseError) {}
        });
    }

    /**
     * Initialises the mapview
     */
    private void createMapView(){
        /**
         * Catch the null pointer exception that
         * may be thrown when initialising the map
         */
        try {
            if(null == googleMap){
                googleMap = ((MapFragment) getFragmentManager().findFragmentById(
                        R.id.mapView)).getMap();

                /**
                 * If the map is still null after attempted initialisation,
                 * show an error to the user
                 */
                if(null == googleMap) {
                    Toast.makeText(getApplicationContext(),
                            "Error creating map", Toast.LENGTH_SHORT).show();
                }
            }
        } catch (NullPointerException exception){
            Log.e("mapApp", exception.toString());
        }
    }

    /**
     * Adds a marker to the map
     */
    private void addMarker(float x, float y, String name, String company, String phone, BitmapDescriptor pinColor){

        /** Make sure that the map has been initialised **/
        if(null != googleMap){
            googleMap.addMarker(new MarkerOptions()
                            .icon(pinColor)
                            .position(new LatLng(x, y))
                            .title(name)
                            .snippet("Phone: " + phone + "  | Company: " + company)
                            .draggable(true)

            );
        }
    }

    public void setHashMap(){
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

    @Override
    public String toString(){
        return "";
    }
}

