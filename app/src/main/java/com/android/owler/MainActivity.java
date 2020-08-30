package com.android.owler;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.fonts.Font;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // VREME
        WebView webView = (WebView) findViewById(R.id.vreme);
        String url = "file:///android_asset/wapi.html";
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);

        // Radio
        WebView ww = (WebView) findViewById(R.id.radio);
        String u = "file:///android_asset/player.html";
        ww.getSettings().setJavaScriptEnabled(true);
        ww.setWebViewClient(new WebViewClient());
        ww.loadUrl(u);

        // sat
        WebView ss = (WebView) findViewById(R.id.sat);
        String d = "file:///android_asset/sat.html";
        ss.getSettings().setJavaScriptEnabled(true);
        ss.setWebViewClient(new WebViewClient());
        ss.loadUrl(d);

        // KALENDAR
        MaterialCalendarView materialCalView = findViewById(R.id.kalendar);
        materialCalView.setDateSelected(CalendarDay.today(), true);

        // CC
        WebView cc = (WebView) findViewById(R.id.cc);
        String c = "file:///android_asset/info.html";
        cc.getSettings().setJavaScriptEnabled(true);
        // cc.setWebViewClient(new WebViewClient()); NEKA OTVORI FEED U DEFAULT USER BROWSER-U
        cc.loadUrl(c);

        // pregledač
        Button gg = (Button) findViewById(R.id.pocetnaStr);
        Button btn = (Button) findViewById(R.id.opn);
        WebView bb = (WebView) findViewById(R.id.br);
        Button homeBtn = (Button) findViewById(R.id.hmb);

        bb.setVisibility(View.GONE);
        homeBtn.setVisibility(View.GONE);
        gg.setVisibility(View.GONE);

        // browser
        WebView vv = (WebView) findViewById(R.id.br);
        String v = "https://google.com/";
        vv.getSettings().setJavaScriptEnabled(true);
        vv.setWebViewClient(new WebViewClient());
        vv.loadUrl(v);
    }

    public void otvori(View view)
    {
        Button btn = (Button) findViewById(R.id.opn);
        Button gg = (Button) findViewById(R.id.pocetnaStr);
        Button homeBtn = (Button) findViewById(R.id.hmb);
        WebView bb = (WebView) findViewById(R.id.vreme);
        WebView aa = (WebView) findViewById(R.id.radio);
        WebView qq = (WebView) findViewById(R.id.sat);
        WebView c2 = (WebView) findViewById(R.id.cc);
        MaterialCalendarView mc = (MaterialCalendarView) findViewById(R.id.kalendar);
        ImageButton ib = (ImageButton) findViewById(R.id.profilna);
        FontAwesome fw1 = (FontAwesome) findViewById(R.id.podaci);
        FontAwesome fw2 = (FontAwesome) findViewById(R.id.podaci2);
        WebView preg = (WebView) findViewById(R.id.br);

        // SAKRIVANJE ELEMENATA
        btn.setVisibility(View.GONE);
        aa.setVisibility(View.GONE);
        qq.setVisibility(View.GONE);
        c2.setVisibility(View.GONE);
        mc.setVisibility(View.GONE);
        bb.setVisibility(View.GONE);
        ib.setVisibility(View.GONE);
        fw1.setVisibility(View.GONE);
        fw2.setVisibility(View.GONE);

        homeBtn.setVisibility(View.VISIBLE);
        gg.setVisibility(View.VISIBLE);
        preg.setVisibility(View.VISIBLE);

        // KOD IZNAD SAKRIVA DUGME I WEBVIEW STAVLJA NA FRONT VIEW!!!
    }

    public void zatvori(View view)
    {
        Button btn = (Button) findViewById(R.id.opn);
        Button homeBtn = (Button) findViewById(R.id.hmb);
        WebView bb = (WebView) findViewById(R.id.vreme);
        WebView aa = (WebView) findViewById(R.id.radio);
        WebView qq = (WebView) findViewById(R.id.sat);
        WebView c2 = (WebView) findViewById(R.id.cc);
        MaterialCalendarView mc = (MaterialCalendarView) findViewById(R.id.kalendar);
        ImageButton ib = (ImageButton) findViewById(R.id.profilna);
        FontAwesome fw1 = (FontAwesome) findViewById(R.id.podaci);
        FontAwesome fw2 = (FontAwesome) findViewById(R.id.podaci2);
        WebView preg = (WebView) findViewById(R.id.br);
        Button gg = (Button) findViewById(R.id.pocetnaStr);

        // SAKRIVANJE ELEMENATA
        btn.setVisibility(View.VISIBLE);
        homeBtn.setVisibility(View.GONE);
        aa.setVisibility(View.VISIBLE);
        qq.setVisibility(View.VISIBLE);
        c2.setVisibility(View.VISIBLE);
        mc.setVisibility(View.VISIBLE);
        bb.setVisibility(View.VISIBLE);
        ib.setVisibility(View.VISIBLE);
        fw1.setVisibility(View.VISIBLE);
        fw2.setVisibility(View.VISIBLE);
        gg.setVisibility(View.GONE);

        // pregledač prikaži
        preg.setVisibility(View.GONE);

        // browser
        WebView vv = (WebView) findViewById(R.id.br);
        String v = "https://google.com/";
        vv.getSettings().setJavaScriptEnabled(true);
        vv.setWebViewClient(new WebViewClient());
        vv.loadUrl(v);
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(R.drawable.warn)
                .setTitle("Izlazak iz aplikacije Owler")
                .setMessage("Da li ste sigurni da želite da zatvorite aplikaciju?")
                .setPositiveButton("Da", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton("Ne", null)
                .show();
    }

    @Override
    public void onPause() {
        show_Notification();
        moveTaskToBack(true);
        super.onPause();
    }

    @TargetApi(Build.VERSION_CODES.O)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)

    // notifikacija
    public void show_Notification(){

        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
        String CHANNEL_ID="KANAL2";
        NotificationChannel notificationChannel=new NotificationChannel(CHANNEL_ID,"name",NotificationManager.IMPORTANCE_LOW);
        PendingIntent pendingIntent=PendingIntent.getActivity(getApplicationContext(),1,intent,0);
        Notification notification=new Notification.Builder(getApplicationContext(),CHANNEL_ID)
                .setContentText("Povratak u aplikaciju vršite klikom na dugme ispod.")
                .setContentTitle("Owler Aplikacija")
                .setContentIntent(pendingIntent)
                .addAction(R.drawable.notif,"POVRATAK U APLIKACIJU",pendingIntent)
                .setChannelId(CHANNEL_ID)
                .setSmallIcon(R.drawable.notif)
                .build();

        NotificationManager notificationManager=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(notificationChannel);
        notificationManager.notify(1,notification);


    }

    public void runGoogle(View view)
    {
        // browser
        WebView vv = (WebView) findViewById(R.id.br);
        String v = "https://google.com/";
        vv.getSettings().setJavaScriptEnabled(true);
        vv.setWebViewClient(new WebViewClient());
        vv.loadUrl(v);
    }
}