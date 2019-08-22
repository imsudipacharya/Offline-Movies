package com.appdeepo.offlinemovies;

import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.os.Process;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DatabaseReference reference,p18,pp18;
    Query ref1;
    DatabaseReference songref;
    DatabaseReference hindref,refs;
    DatabaseReference engref;
    DatabaseReference nepref;
    DatabaseReference japref;
    DatabaseReference korref;
    DatabaseReference otherref;
    DatabaseReference eduref;
    DatabaseReference seriesref;
    DatabaseReference bhojref;
    RecyclerView plus18,plu18,recy,recyclerView,recyclerview1,songrecycle,hindrecycle,engrecycle,neprecycle,japrecycle,korrecycle,otherrecycle,edurecycle,seriesrecycle,bhojrecycle;
    ArrayList<Model> list;
    MyAdapter adapter;
    SearchView searchView;
    private LinearLayout parentLayout;
    private Object MenuItem;
    LinearLayout internetcheck;
    private Menu menu;
    private AdView mAdView;
    private InterstitialAd mInterstitialAd,mInterstitialAda,mInterstitialAdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        Toolbar toolbar = findViewById(R.id.toolbar);

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

searchView = (SearchView) findViewById(R.id.searchtext);


        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-8013173857441687/7072020717");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        mInterstitialAda = new InterstitialAd(this);
        mInterstitialAda.setAdUnitId("ca-app-pub-8013173857441687/9347077684");
        mInterstitialAda.loadAd(new AdRequest.Builder().build());






        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);




        recy = (RecyclerView) findViewById(R.id.recyclerview1);
        plu18 = (RecyclerView) findViewById(R.id.plu18);
        plus18 = (RecyclerView) findViewById(R.id.plus18);

        recyclerView = (RecyclerView) findViewById(R.id.myRecycler);
        recyclerview1 = (RecyclerView) findViewById(R.id.myrecyclerview1);
        songrecycle = (RecyclerView) findViewById(R.id.songsrecyclerview);
        hindrecycle = (RecyclerView) findViewById(R.id.Hindirecyclerview);
        engrecycle = (RecyclerView) findViewById(R.id.englishrecyclerview);
        neprecycle = (RecyclerView) findViewById(R.id.Nepalirecyclerview);
        japrecycle = (RecyclerView) findViewById(R.id.japaneserecyclerview);
        korrecycle = (RecyclerView) findViewById(R.id.koreanrecyclerview);
        otherrecycle = (RecyclerView) findViewById(R.id.othersrecyclerview);
        edurecycle = (RecyclerView) findViewById(R.id.educationalrecyclerview);
        seriesrecycle = (RecyclerView) findViewById(R.id.seriesrecyclerview);
        bhojrecycle = (RecyclerView) findViewById(R.id.bhojpurirecyclerview);



        LinearLayoutManager horizontalLayoutManagaeri = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        plu18.setLayoutManager(horizontalLayoutManagaeri);
        LinearLayoutManager horizontalLayoutManagaerii = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, true);
        plus18.setLayoutManager(horizontalLayoutManagaerii);




        LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, true);
        recyclerView.setLayoutManager(horizontalLayoutManagaer);

        LinearLayoutManager hrizontalLayoutManagaer = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, true);
        recyclerview1.setLayoutManager(hrizontalLayoutManagaer);

        LinearLayoutManager horizontalLayoutManagae = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, true);
        recy.setLayoutManager(horizontalLayoutManagae);

        LinearLayoutManager horizontalLayoutManagaer2 = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, true);
        songrecycle.setLayoutManager(horizontalLayoutManagaer2);

        LinearLayoutManager horizontalLayoutManagaer3 = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, true);
        hindrecycle.setLayoutManager(horizontalLayoutManagaer3);

        LinearLayoutManager horizontalLayoutManagaer4 = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, true);
        engrecycle.setLayoutManager(horizontalLayoutManagaer4);

        LinearLayoutManager horizontalLayoutManagaern = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, true);
        neprecycle.setLayoutManager(horizontalLayoutManagaern);

        LinearLayoutManager horizontalLayoutManagaer5 = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, true);
        japrecycle.setLayoutManager(horizontalLayoutManagaer5);


        LinearLayoutManager horizontalLayoutManagaer6 = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, true);
        korrecycle.setLayoutManager(horizontalLayoutManagaer6);

        LinearLayoutManager horizontalLayoutManagaer7 = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, true);
        otherrecycle.setLayoutManager(horizontalLayoutManagaer7);

        LinearLayoutManager horizontalLayoutManagaer8 = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, true);
        edurecycle.setLayoutManager(horizontalLayoutManagaer8);

        LinearLayoutManager horizontalLayoutManagaer9 = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, true);
        seriesrecycle.setLayoutManager(horizontalLayoutManagaer9);

        LinearLayoutManager horizontalLayoutManagaer10 = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, true);
        bhojrecycle.setLayoutManager(horizontalLayoutManagaer10);


        hrizontalLayoutManagaer.setStackFromEnd(true);
        horizontalLayoutManagaer.setStackFromEnd(true);
        horizontalLayoutManagae.setStackFromEnd(true);
        horizontalLayoutManagaer2.setStackFromEnd(true);
        horizontalLayoutManagaer3.setStackFromEnd(true);
        horizontalLayoutManagaer4.setStackFromEnd(true);
        horizontalLayoutManagaer5.setStackFromEnd(true);
        horizontalLayoutManagaer6.setStackFromEnd(true);
        horizontalLayoutManagaer7.setStackFromEnd(true);
        horizontalLayoutManagaer8.setStackFromEnd(true);
        horizontalLayoutManagaer9.setStackFromEnd(true);
        horizontalLayoutManagaer10.setStackFromEnd(true);
        horizontalLayoutManagaern.setStackFromEnd(true);
        horizontalLayoutManagaeri.setStackFromEnd(false);
        horizontalLayoutManagaerii.setStackFromEnd(true);





        Button above18plus = (Button) findViewById(R.id.above18back);
        above18plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {

                    LinearLayout internetshow = (LinearLayout) findViewById(R.id.internetshow);
                    internetshow.setVisibility(internetshow.VISIBLE);
                    LinearLayout aboves18 = (LinearLayout) findViewById(R.id.aboves18);
                    aboves18.setVisibility(aboves18.GONE);
                }
            }
        });









        Button plus18btn = (Button) findViewById(R.id.above18btn);
        plus18btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mInterstitialAda.isLoaded()) {
                    mInterstitialAda.show();
                } else {


                    LinearLayout first18 = (LinearLayout) findViewById(R.id.first18);
                    LinearLayout second18 = (LinearLayout) findViewById(R.id.second18);
                    first18.setVisibility(first18.VISIBLE);
                    second18.setVisibility(second18.VISIBLE);
                    p18 = FirebaseDatabase.getInstance().getReference("Data");
                    Query querya = p18.orderByChild("category").equalTo("Above18");

                    querya.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot datas : dataSnapshot.getChildren()) {

                                list = new ArrayList<Model>();
                                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                    Model p = dataSnapshot1.getValue(Model.class);
                                    list.add(p);
                                }
                                adapter = new MyAdapter(MainActivity.this, list);
                                plu18.setAdapter(adapter);

                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Toast.makeText(MainActivity.this, "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();

                        }
                    });


                    pp18 = FirebaseDatabase.getInstance().getReference("Data");
                    Query queryb = pp18.orderByChild("category").equalTo("Above18");

                    queryb.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot datas : dataSnapshot.getChildren()) {

                                list = new ArrayList<Model>();
                                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                    Model p = dataSnapshot1.getValue(Model.class);
                                    list.add(p);
                                }
                                adapter = new MyAdapter(MainActivity.this, list);
                                plus18.setAdapter(adapter);

                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Toast.makeText(MainActivity.this, "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();

                        }
                    });

                }
            }
        });

        Button no18 = (Button) findViewById(R.id.wrongclick);
        no18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mInterstitialAda.isLoaded()) {
                    mInterstitialAda.show();
                } else {
                    LinearLayout internetshow = (LinearLayout) findViewById(R.id.internetshow);
                    internetshow.setVisibility(internetshow.VISIBLE);
                    LinearLayout aboves18 = (LinearLayout) findViewById(R.id.aboves18);
                    aboves18.setVisibility(aboves18.GONE);

                }
            }
        });








        reference = FirebaseDatabase.getInstance().getReference().child("Data");



        refs=FirebaseDatabase.getInstance().getReference("Data");
        refs.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list = new ArrayList<Model>();
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    Model p = dataSnapshot1.getValue(Model.class);
                    list.add(p);
                }
                adapter = new MyAdapter(MainActivity.this,list);
                recyclerview1.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });



        LinearLayoutManager horizontalLayoutManagaer1 = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL,true);
        recy.setLayoutManager(horizontalLayoutManagaer1);
        horizontalLayoutManagaer1.setStackFromEnd(true);

        ref1=FirebaseDatabase.getInstance().getReference("Data");
        Query query1=ref1.orderByChild("category").equalTo("Halldubbed");

        query1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot datas: dataSnapshot.getChildren()){

                    list = new ArrayList<Model>();
                    for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                    {
                        Model p = dataSnapshot1.getValue(Model.class);
                        list.add(p);
                    }
                    adapter = new MyAdapter(MainActivity.this,list);
                    recy.setAdapter(adapter);

                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();

            }
        });


        songref=FirebaseDatabase.getInstance().getReference("Data");
        Query query2=songref.orderByChild("category").startAt("songs");

        query2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot datas: dataSnapshot.getChildren()){

                    list = new ArrayList<Model>();
                    for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                    {
                        Model p = dataSnapshot1.getValue(Model.class);
                        list.add(p);
                    }
                    adapter = new MyAdapter(MainActivity.this,list);
                    songrecycle.setAdapter(adapter);

                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();

            }
        });



        hindref=FirebaseDatabase.getInstance().getReference("Data");
        Query query3=hindref.orderByChild("category").equalTo("Hindi");

        query3.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot datas: dataSnapshot.getChildren()){

                    list = new ArrayList<Model>();
                    for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                    {
                        Model p = dataSnapshot1.getValue(Model.class);
                        list.add(p);
                    }
                    adapter = new MyAdapter(MainActivity.this,list);
                    hindrecycle.setAdapter(adapter);

                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();

            }
        });


        engref=FirebaseDatabase.getInstance().getReference("Data");
        Query query4=engref.orderByChild("category").equalTo("English");


        query4.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot datas: dataSnapshot.getChildren()){

                    list = new ArrayList<Model>();
                    for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                    {
                        Model p = dataSnapshot1.getValue(Model.class);
                        list.add(p);
                    }
                    adapter = new MyAdapter(MainActivity.this,list);
                    engrecycle.setAdapter(adapter);

                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();

            }
        });


        nepref=FirebaseDatabase.getInstance().getReference("Data");
        Query query5=nepref.orderByChild("category").equalTo("Nepali");

        query5.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot datas: dataSnapshot.getChildren()){

                    list = new ArrayList<Model>();
                    for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                    {
                        Model p = dataSnapshot1.getValue(Model.class);
                        list.add(p);
                    }
                    adapter = new MyAdapter(MainActivity.this,list);
                    neprecycle.setAdapter(adapter);

                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();

            }
        });


        japref=FirebaseDatabase.getInstance().getReference("Data");
        Query query6=japref.orderByChild("category").equalTo("Japanese");

        query6.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot datas: dataSnapshot.getChildren()){

                    list = new ArrayList<Model>();
                    for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                    {
                        Model p = dataSnapshot1.getValue(Model.class);
                        list.add(p);
                    }
                    adapter = new MyAdapter(MainActivity.this,list);
                    japrecycle.setAdapter(adapter);

                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();

            }
        });




        korref=FirebaseDatabase.getInstance().getReference("Data");
        Query query7=korref.orderByChild("category").equalTo("Korean");

        query7.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot datas: dataSnapshot.getChildren()){

                    list = new ArrayList<Model>();
                    for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                    {
                        Model p = dataSnapshot1.getValue(Model.class);
                        list.add(p);
                    }
                    adapter = new MyAdapter(MainActivity.this,list);
                    korrecycle.setAdapter(adapter);

                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();

            }
        });




        otherref=FirebaseDatabase.getInstance().getReference("Data");
        Query query8=otherref.orderByChild("category").equalTo("Other");

        query8.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot datas: dataSnapshot.getChildren()){

                    list = new ArrayList<Model>();
                    for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                    {
                        Model p = dataSnapshot1.getValue(Model.class);
                        list.add(p);
                    }
                    adapter = new MyAdapter(MainActivity.this,list);
                    otherrecycle.setAdapter(adapter);

                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();

            }
        });



        eduref=FirebaseDatabase.getInstance().getReference("Data");
        Query query9=eduref.orderByChild("category").equalTo("Education");

        query9.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot datas: dataSnapshot.getChildren()){

                    list = new ArrayList<Model>();
                    for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                    {
                        Model p = dataSnapshot1.getValue(Model.class);
                        list.add(p);
                    }
                    adapter = new MyAdapter(MainActivity.this,list);
                    edurecycle.setAdapter(adapter);

                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();

            }
        });



        seriesref=FirebaseDatabase.getInstance().getReference("Data");
        Query query10=seriesref.orderByChild("category").equalTo("Series");

        query10.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot datas: dataSnapshot.getChildren()){

                    list = new ArrayList<Model>();
                    for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                    {
                        Model p = dataSnapshot1.getValue(Model.class);
                        list.add(p);
                    }
                    adapter = new MyAdapter(MainActivity.this,list);
                    seriesrecycle.setAdapter(adapter);

                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();

            }
        });


        bhojref=FirebaseDatabase.getInstance().getReference("Data");
        Query query11=bhojref.orderByChild("category").equalTo("Bhojpuri");

        query11.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot datas: dataSnapshot.getChildren()){

                    list = new ArrayList<Model>();
                    for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                    {
                        Model p = dataSnapshot1.getValue(Model.class);
                        list.add(p);
                    }
                    adapter = new MyAdapter(MainActivity.this,list);
                    bhojrecycle.setAdapter(adapter);

                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();

            }
        });
        Button ok = (Button) findViewById(R.id.ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean connected = false;
                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                        connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                    //super.onStart();we are connected to a network
                    internetcheck = (LinearLayout) findViewById(R.id.internetcheck);
                    internetcheck.setVisibility(internetcheck.GONE);

                    connected = true;



                } else {
                    AlertDialog.Builder ab = new AlertDialog.Builder(MainActivity.this);
                    ab.setTitle("Hey!");
                    ab.setMessage("Are you sure to exit?");
                    ab.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            //if you want to kill app . from other then your main avtivity.(Launcher)
                            Process.killProcess(Process.myPid());
                            System.exit(1);

                            //if you want to finish just current activity

                            MainActivity.this.finish();
                        }
                    });
                    ab.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    ab.show();
                }


            }



        });









    }








    @Override
    public void onBackPressed() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            DrawerLayout drawer = findViewById(R.id.drawer_layout);
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);

            } else {
                AlertDialog.Builder ab = new AlertDialog.Builder(MainActivity.this, R.style.MyDialogTheme);
                ab.setTitle("DO YOU WANT TO EXIT OFFLINE MOVIES");
                ab.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        //if you want to kill app . from other then your main avtivity.(Launcher)
                        android.os.Process.killProcess(android.os.Process.myPid());
                        System.exit(1);

                        //if you want to finish just current activity

                        MainActivity.this.finish();
                    }
                });
                ab.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                ab.show();
            }
        }
        }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();




        return super.onOptionsItemSelected(item);

    }




    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent i = new Intent(MainActivity.this,MainActivity.class);
            startActivity(i);
            // Handle the camera action
        } else if (id == R.id.nav_series) {
            LinearLayout internetsho = (LinearLayout) findViewById(R.id.copyright);
            LinearLayout internetshow = (LinearLayout) findViewById(R.id.internetshow);
internetsho.setVisibility(internetsho.VISIBLE);
            internetshow.setVisibility(internetshow.GONE);
        }

        else if (id == R.id.nav_above18) {
            LinearLayout internetshow = (LinearLayout) findViewById(R.id.internetshow);
            internetshow.setVisibility(internetshow.GONE);
            LinearLayout aboves18 = (LinearLayout) findViewById(R.id.aboves18);
            aboves18.setVisibility(aboves18.VISIBLE);


        } else if (id == R.id.nav_share) {

            try {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "OFFLINE MOVIES");
                String shareMessage= "\nLet me recommend you this application\n\n";
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                startActivity(Intent.createChooser(shareIntent, "Share App Using"));
            } catch(Exception e) {
                //e.toString();
            }

        }        else if (id == R.id.nav_send) {
            AlertDialog.Builder ab = new AlertDialog.Builder(MainActivity.this , R.style.MyDialogTheme);
            ab.setTitle("DO YOU WANT TO EXIT OFFLINE MOVIES");
            ab.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    //if you want to kill app . from other then your main avtivity.(Launcher)
                    android.os.Process.killProcess(android.os.Process.myPid());
                    System.exit(1);

                    //if you want to finish just current activity

                    MainActivity.this.finish();
                }
            });
            ab.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            ab.show();
        }



        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    protected void onStart() {
        super.onStart();

        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //super.onStart();we are connected to a network
            connected = true;

            if (reference !=null){

                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        list = new ArrayList<Model>();
                        for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                        {
                            Model p = dataSnapshot1.getValue(Model.class);
                            list.add(p);
                        }
                        adapter = new MyAdapter(MainActivity.this,list);
                        recyclerView.setAdapter(adapter);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(MainActivity.this, "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();
                    }
                });

            }

            if (searchView !=null){
                  searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String s) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String s) {
                        search(s);

                        return true;
                    }


                });


            }


        } else {
            internetcheck = (LinearLayout) findViewById(R.id.internetcheck);
            internetcheck.setVisibility(internetcheck.VISIBLE);
        }



    }


    private void search(String str) {
        ArrayList<Model> mylist = new ArrayList<>();
        for (Model model: list){

            if (model.getName().toLowerCase().contains(str.toLowerCase())){

                mylist.add(model);
            }
            adapter = new MyAdapter(MainActivity.this,mylist);
            recyclerView.setAdapter(adapter);
        }
    }



    }






