package com.appdeepo.offlinemovies;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.DownloadListener;
import android.webkit.URLUtil;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class postdetail extends AppCompatActivity {
    private Button button;
    private long downloadID;
    private BroadcastReceiver onDownloadComplete = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //Fetching the download id received with the broadcast
            long id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
            //Checking if the received broadcast is for our enqueued download by matching download id
            if (downloadID == id) {
                Toast.makeText(postdetail.this, "Download" + yname + " Completed", Toast.LENGTH_SHORT).show();
            }
        }
    };

    TextView oname, oview, keydata, aurthor, dlink, plink,subcategory;
    ImageView oimage;
    String yname, yviews, yimage, ykeydata, yaurthor, ydlink, yplink,ysubcategory;
    RecyclerView recyclerviews1;
    DatabaseReference refs1;
    ArrayList<Model> list;
    MyAdapter adapter;
    LinearLayout internetcheck;
    ScrollView playlinear;
    WebView webview;
    private ProgressBar mProgressBar;

    private DownloadManager downloadManager;
    private long refid;
    private Uri Download_Uri;
    ArrayList<Long> lists = new ArrayList<>();
    private int PERMISSION_STORAGE_CODE = 1000;
    private AdView mAdView;
    private InterstitialAd mInterstitialAdr,mInterstitialAd2r,mInterstitialAd3;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postdetail);


        mAdView = findViewById(R.id.adViewr);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);



        mInterstitialAdr = new InterstitialAd(this);
        mInterstitialAdr.setAdUnitId("ca-app-pub-8013173857441687/8033996012");
        mInterstitialAdr.loadAd(new AdRequest.Builder().build());

        mInterstitialAd2r = new InterstitialAd(this);
        mInterstitialAd2r.setAdUnitId("ca-app-pub-8013173857441687/9347077684");
        mInterstitialAd2r.loadAd(new AdRequest.Builder().build());







        ActionBar myActionBar = getSupportActionBar();
        myActionBar.setTitle("MOVIE DETAILS");


        oimage = (ImageView) findViewById(R.id.pimage);
        oname = (TextView) findViewById(R.id.pname);
        oview = (TextView) findViewById(R.id.pview);
        keydata = (TextView) findViewById(R.id.pkeydata);
        aurthor = (TextView) findViewById(R.id.paurthor);

        yname = getIntent().getStringExtra("name");
        yviews = getIntent().getStringExtra("email");
        yimage = getIntent().getStringExtra("profilepic");
        yaurthor = getIntent().getStringExtra("aurthor");
        ykeydata = getIntent().getStringExtra("keydata");
        ydlink = getIntent().getStringExtra("dlink");
        yplink = getIntent().getStringExtra("plink");
        ysubcategory = getIntent().getStringExtra("subcategory");

        oname.setText(yname);
        oview.setText(yviews);
        keydata.setText(ykeydata);
        aurthor.setText(yaurthor);
        Picasso.get().load(yimage).into(oimage);

        playlinear = (ScrollView) findViewById(R.id.playlinear);


        recyclerviews1 = (RecyclerView) findViewById(R.id.likerecycler);
        LinearLayoutManager horizontalLayoutManagaer1 = new LinearLayoutManager(postdetail.this, LinearLayoutManager.HORIZONTAL, true);
        recyclerviews1.setLayoutManager(horizontalLayoutManagaer1);
        horizontalLayoutManagaer1.setStackFromEnd(true);

        refs1 = FirebaseDatabase.getInstance().getReference("Data");
        Query querys1=refs1.orderByChild("category").equalTo(ysubcategory);

        querys1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot datas : dataSnapshot.getChildren()) {

                    list = new ArrayList<Model>();
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        Model p = dataSnapshot1.getValue(Model.class);
                        list.add(p);
                    }
                    adapter = new MyAdapter(postdetail.this, list);
                    recyclerviews1.setAdapter(adapter);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(postdetail.this, "Opsss.... Something is wrong", Toast.LENGTH_SHORT).show();

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
                    AlertDialog.Builder ab = new AlertDialog.Builder(postdetail.this);
                    ab.setTitle("Hey!");
                    ab.setMessage("Are you sure to exit?");
                    ab.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            //if you want to kill app . from other then your main avtivity.(Launcher)
                            android.os.Process.killProcess(android.os.Process.myPid());
                            System.exit(1);

                            //if you want to finish just current activity

                            finish();
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

        final LinearLayout webviewlinear = (LinearLayout) findViewById(R.id.webviewlinear);
        webviewlinear.setVisibility(webviewlinear.GONE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_DENIED) {
                Log.d("permission", "permission denied to WRITE_EXTERNAL_STORAGE - requesting it");
                String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                requestPermissions(permissions, 1);
            }
        }


        Button playbutton = (Button) findViewById(R.id.playbutton);
        playbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    ActionBar myActionBar1 = getSupportActionBar();
                    myActionBar1.setTitle(yname + "\tVIDEO");
                    myActionBar1.setDisplayHomeAsUpEnabled(true);
                    myActionBar1.setDisplayShowHomeEnabled(true);
                    playlinear.setVisibility(playlinear.GONE);
                    webviewlinear.setVisibility(webviewlinear.VISIBLE);
                    WebView webView = (WebView) findViewById(R.id.webview);

                    final ProgressDialog progressBar = new ProgressDialog(postdetail.this);
                    progressBar.setMessage("Please wait...");

                    webView.loadUrl(yplink);

                    webView.setWebViewClient(new WebViewClient() {
                        public boolean shouldOverrideUrlLoading(WebView view, String url) {
                            view.loadUrl(url);
                            return true;
                        }

                        @Override
                        public void onPageStarted(WebView view, String url, Bitmap favicon) {
                            super.onPageStarted(view, url, favicon);
                            if (!progressBar.isShowing()) {
                                progressBar.show();
                            }
                        }

                        public void onPageFinished(WebView view, String url) {
                            if (progressBar.isShowing()) {
                                progressBar.dismiss();
                            }
                        }

                        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                            if (progressBar.isShowing()) {
                                progressBar.dismiss();
                            }
                        }
                    });
                    webView.setDownloadListener(new DownloadListener() {
                        @Override
                        public void onDownloadStart(String url, String userAgent,
                                                    String contentDisposition, String mimeType,
                                                    long contentLength) {
                            DownloadManager.Request request = new DownloadManager.Request(
                                    Uri.parse(url));
                            request.setMimeType(mimeType);
                            String cookies = CookieManager.getInstance().getCookie(url);
                            request.addRequestHeader("cookie", cookies);
                            request.addRequestHeader("User-Agent", userAgent);
                            request.setDescription("Downloading OM's File...");
                            request.setTitle(yname + ".mp4");
                            request.allowScanningByMediaScanner();
                            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                            request.setDestinationInExternalPublicDir(
                                    Environment.DIRECTORY_DOWNLOADS, URLUtil.guessFileName(
                                            url, contentDisposition, mimeType));


                            DownloadManager dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                            dm.enqueue(request);
                            Toast.makeText(getApplicationContext(), "Enqueing your " + yname + " Downloading  File", Toast.LENGTH_LONG).show();


                        }
                    });

                }


        });




        Button downbutton = (Button) findViewById(R.id.downbutton);
        downbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mInterstitialAdr.isLoaded()) {
                    mInterstitialAdr.show();
                } else {

                    startDownloading();
                }
            }
        });


    }


    private void startDownloading() {

        String urlet = ydlink;
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(urlet));

        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
        request.setTitle(yname);
                request.allowScanningByMediaScanner();
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(
                Environment.DIRECTORY_DOWNLOADS, ""+System.currentTimeMillis());



        DownloadManager dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        long id = dm.enqueue(request);
        Toast.makeText(getApplicationContext(), "Enqueing your " + yname + " Downloading  File", Toast.LENGTH_LONG).show();



        DownloadManager.Query q = new DownloadManager.Query();
        q.setFilterById(id);
        Cursor cursor = dm.query(q);
        cursor.moveToFirst();
        int bytes_downloaded = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR));
        cursor.close();




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
            } else {
                internetcheck = (LinearLayout) findViewById(R.id.internetcheck);
                internetcheck.setVisibility(internetcheck.VISIBLE);

            }
        }




    @Override
    public boolean onSupportNavigateUp() {

        if (((LinearLayout) findViewById(R.id.webviewlinear)).VISIBLE == View.VISIBLE) {
                ((LinearLayout) findViewById(R.id.webviewlinear)).setVisibility(View.GONE);
                ((WebView) findViewById(R.id.webview)).destroy();
                ((ScrollView) findViewById(R.id.playlinear)).setVisibility(View.VISIBLE);


            } else {
                super.onBackPressed();
            }
          return true;

    }

    @Override
    public void onBackPressed() {
        if (mInterstitialAd2r.isLoaded()) {
            mInterstitialAd2r.show();
        } else {

            super.onBackPressed();

        }
    }
    }

