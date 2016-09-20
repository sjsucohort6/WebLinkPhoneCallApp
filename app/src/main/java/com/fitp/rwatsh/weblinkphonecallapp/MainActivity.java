package com.fitp.rwatsh.weblinkphonecallapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void launchBrowser(View view) {
        TextView urlTextView = (TextView) findViewById(R.id.urlEditText);
        String url = urlTextView.getText().toString();
        if (!url.startsWith("http://") && !url.startsWith("https://"))
            url = "http://" + url;

        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }

    public void finishActivityA(View v) {
        this.finish();
    }

    public void makeCall(View v) {
        Intent intent = new Intent(Intent.ACTION_DIAL);

        TextView phoneTextView = (TextView) findViewById(R.id.phoneText);
        String phone = phoneTextView.getText().toString();

        intent.setData(Uri.parse("tel:" + phone));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            requestPermissions(new String[] {Manifest.permission.CALL_PHONE}, 1);
            return;
        }
        startActivity(intent);
    }
}
