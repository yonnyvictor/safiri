/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Servlet Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloWorld
*/

package com.lighteye.safiri.backend;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class MyServlet extends HttpServlet {

    //
    // Firebase keys shared with client applications
    private static final String IBX = "inbox";
    private static final String CH = "channels";
    private static final String REQLOG = "requestLog";

    private String channels;
    private String inbox;

    private DatabaseReference firebase;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        String credential = config.getInitParameter("credential");
        log(credential);
        String databaseUrl = config.getInitParameter("databaseUrl");
        log(databaseUrl);
        channels = config.getInitParameter("channels");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setServiceAccount(config.getServletContext().getResourceAsStream(credential))
                .setDatabaseUrl(databaseUrl)
                .build();
        FirebaseApp.initializeApp(options);
        firebase = FirebaseDatabase.getInstance().getReference();

        //firebase.child("request").child("123dasda").setValue("Processing");

        firebase.child("bookingsRequest").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String key = dataSnapshot.getKey();
                firebase.child("request").child(key).setValue("Processing");
                log("onChildAdded called");
                log(key);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //firebase.child("request").child("123dasda2").setValue("Processing2");
        log("doGetCalled");
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

    }
}
