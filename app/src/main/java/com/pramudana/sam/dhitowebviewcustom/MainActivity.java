package com.pramudana.sam.dhitowebviewcustom;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.customtabs.CustomTabsCallback;
import android.support.customtabs.CustomTabsClient;
import android.support.customtabs.CustomTabsIntent;
import android.support.customtabs.CustomTabsServiceConnection;
import android.support.customtabs.CustomTabsSession;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    RecyclerView webRecycler;
    CustomAdapter adapter;
    private final String[] url = {

            "https://www.1cak.com", "https://idn.sch.id", "https://www.9gag.com", "https://www.rei.com"
            , "https://www.gregorypacks.com", "https://www.jack-wolfskin.com", "https://www.deuter.com/"
            , "https://www.marmot.com", "https://www.radiorodja.com/", "https://github.com"
    };
    private CustomTabsClient customTabsClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webRecycler = findViewById(R.id.webRecycler);
        webRecycler.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MainActivity.CustomAdapter();
        webRecycler.setAdapter(adapter);
        CustomTabsServiceConnection customTabsServiceConnection = new CustomTabsServiceConnection() {
            @Override
            public void onCustomTabsServiceConnected(ComponentName name, CustomTabsClient client) {
                customTabsClient = client;
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                customTabsClient = null;
            }
        };
        String packageName = "com.android.chrome";
        CustomTabsClient.bindCustomTabsService(this, packageName, customTabsServiceConnection);
    }

    String[] webName = {

            "1 Cak", "SMP - SMK IDN Madinatul Ilmi", "9 GAG", "REI Outdoor", "GREGORY", "Jack Wolfskin", "Deuter", "Marmot", "Rodja TV", "Github"
    };

    private CustomTabsSession getSession(){
        return customTabsClient.newSession(new CustomTabsCallback() {

            @Override
            public void onNavigationEvent(int navigationEvent, Bundle extras) {
                super.onNavigationEvent(navigationEvent, extras);
            }
        });
    }

    private class CustomAdapter extends RecyclerView.Adapter<MainActivity.CustomAdapter.MyViewHolder> {
        @Override
        public MainActivity.CustomAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.web_layout, null);
            return new MainActivity.CustomAdapter.MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MainActivity.CustomAdapter.MyViewHolder holder, final int position) {

            holder.judul.setText(webName[position]);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (position == 0) {
                        startActivity(new Intent(MainActivity.this, CustomActivity.class).putExtra("site", url));
                    } else if (position == 1) {
                        startActivity(new Intent(MainActivity.this, CustomActivity.class).putExtra("site", url));
                    } else if (position == 2) {
                        startActivity(new Intent(MainActivity.this, CustomActivity.class).putExtra("site", url));
                    } else if (position == 3) {
                        startActivity(new Intent(MainActivity.this, CustomActivity.class).putExtra("site", url));
                    } else if (position == 4) {
                        startActivity(new Intent(MainActivity.this, CustomActivity.class).putExtra("site", url));
                    } else if (position == 5) {
                        CustomTabsIntent.Builder mBuilder = new CustomTabsIntent.Builder(getSession());
                        mBuilder.setToolbarColor(ContextCompat.getColor(MainActivity.this, R.color.indigo_500));
                        mBuilder.setCloseButtonIcon(BitmapFactory.decodeResource(getResources(),
                                R.mipmap.ic_arrow_back_white_24dp));
                        mBuilder.addMenuItem("Share", setMenuItem());
                        mBuilder.setActionButton(BitmapFactory.decodeResource(getResources(),
                                R.mipmap.ic_file_download_white_24dp), "Material Color Picker", addActionButton());
                        mBuilder.setStartAnimations(MainActivity.this, R.anim.slide_in_right, R.anim.slide_out_left);
                        mBuilder.setExitAnimations(MainActivity.this, R.anim.slide_in_left, R.anim.slide_out_right);
                        CustomTabsIntent mIntent = mBuilder.build();
                        mIntent.launchUrl(MainActivity.this, Uri.parse(url[position]));
                    } else if (position == 6) {
                        CustomTabsIntent.Builder mBuilder = new CustomTabsIntent.Builder(getSession());
                        mBuilder.setToolbarColor(ContextCompat.getColor(MainActivity.this, R.color.indigo_500));
                        mBuilder.setCloseButtonIcon(BitmapFactory.decodeResource(getResources(),
                                R.mipmap.ic_arrow_back_white_24dp));
                        mBuilder.addMenuItem("Share", setMenuItem());
                        mBuilder.setActionButton(BitmapFactory.decodeResource(getResources(),
                                R.mipmap.ic_file_download_white_24dp), "Material Color Picker", addActionButton());
                        mBuilder.setStartAnimations(MainActivity.this, R.anim.slide_in_right, R.anim.slide_out_left);
                        mBuilder.setExitAnimations(MainActivity.this, R.anim.slide_in_left, R.anim.slide_out_right);
                        CustomTabsIntent mIntent = mBuilder.build();
                        mIntent.launchUrl(MainActivity.this, Uri.parse(url[position]));
                    } else if (position == 7) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url[position]));
                        startActivity(browserIntent);
                    } else if (position == 8) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url[position]));
                        startActivity(browserIntent);
                    } else if (position == 9) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url[position]));
                        startActivity(browserIntent);
                    }
                }
            });
        }
        private PendingIntent setMenuItem() {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Github Site");
            shareIntent.putExtra(Intent.EXTRA_TEXT, url);
            return PendingIntent.getActivity(MainActivity.this, 0,shareIntent, 0);
        }

        private PendingIntent addActionButton() {
            Intent playStoreIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.netmarble.revolutionthm&hl=in"));
            return PendingIntent.getActivity(MainActivity.this, 0, playStoreIntent, 0);
        }

        @Override
        public int getItemCount() {
            return webName.length;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            TextView judul;

            public MyViewHolder(View itemView) {
                super(itemView);

                judul = itemView.findViewById(R.id.judul);
            }
        }
    }
}
