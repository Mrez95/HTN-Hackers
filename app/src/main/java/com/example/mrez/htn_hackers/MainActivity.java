package com.example.mrez.htn_hackers;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);

        // retrieve user info data from Firebase server
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

                for(Object obj : data) {

                    // Firebase Object -> JSON
                    Gson gson = new Gson();
                    String json = gson.toJson(obj);

                    /**
                     * JSON -> Object
                     *
                     * let's store Firebase data in our own class for easy member access
                     */

                    UserProfile users = gson.fromJson(json, UserProfile.class);
                    List<Skills> skills = users.getSkills();

                    // retrieve and store list of all skills for current user
                    for(Skills s : skills)
                    {
                        System.out.println(s.getName());
                    }
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
}

