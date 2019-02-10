 package com.icodev76.redditpics;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;

import android.widget.Toast;


import com.github.ybq.android.spinkit.SpinKitView;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.icodev76.redditpics.model.Feed;
import com.icodev76.redditpics.model.children.Children;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

 public class MainActivity extends AppCompatActivity {
     String currentFeed="AsianguysNSFW";
     private final String TAG="Main Activity";
     DrawerLayout drawerLayout;
     Toolbar toolbar;
     ActionBarDrawerToggle actionBarDrawerToggle;
     NavigationView navigationView;
     RecyclerView recyclerView;
     LinearLayoutManager linearLayoutManager;
     CustomAdapter customAdapter;
     List<Children> child=new ArrayList<>();
     //String after_key="";

     //SpinKitView progressBar;

     /*Sprite doubleBounce = new DoubleBounce();
    progressBar.setIndeterminateDrawable(doubleBounce);*/

     /*private boolean loading=true;
     int firstVisibleItem, visibleItemCount, totalItemCount;
     private int previousTotal=0;
     private int visibleThreshold=5;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recyclerview);
        linearLayoutManager=new LinearLayoutManager(this);
        customAdapter=new CustomAdapter(this,child);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(customAdapter);
        //progressBar = findViewById(R.id.spin_kit);

        setUpToolbar();
        getData();
        //Log.d(TAG, "First start: "+after_key);

         recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    //isScrolling = true;
                    //Toast.makeText(getActivity(), "isScrolling is true", Toast.LENGTH_SHORT).show();
                    //Toast.makeText(getActivity(), "currentitems= "+layoutManager.getChildCount(), Toast.LENGTH_LONG).show();
                    //Toast.makeText(getActivity(), "totalitmes= "+layoutManager.getItemCount(), Toast.LENGTH_LONG).show();
                    //Toast.makeText(getActivity(), "scrolledoutitems= "+((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition(), Toast.LENGTH_LONG).show();
                //}
            }

           @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);


               /*visibleItemCount  = linearLayoutManager.getChildCount();
               totalItemCount  = linearLayoutManager.getItemCount();
               firstVisibleItem  = linearLayoutManager.findFirstVisibleItemPosition();

                *//*Log.d(TAG, "onScrolled: IsScrolling= "+loading+" \n");
                Log.d(TAG, "onScrolled: totalItems= "+totalItems+" \n");
                Log.d(TAG, "onScrolled: currentItem= "+currentItem+" \n");
                Log.d(TAG, "onScrolled: scrolledItems= "+scrolledItems+" \n");*//*

                if(loading) {
                    if (totalItemCount  > previousTotal) {
                        loading = false;
                        previousTotal = totalItemCount ;
                    }
                }
                if(!loading && (totalItemCount -visibleItemCount)<=(firstVisibleItem + visibleThreshold))
               {
                   // fetch data with after query for pagination
                   //Toast.makeText(this, "scrolledoutitems= "+ scrolledItems, Toast.LENGTH_LONG).show();

                   //Toast.makeText(getActivity(), "true", Toast.LENGTH_SHORT).show();
                   Log.d(TAG, "url: End reached");
                   loadMore();
                   loading=true;

               }*/

            }

        });


        navigationView=findViewById(R.id.navigationview);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                menuItem.setChecked(true);
                setTitle(menuItem.getTitle());
                String title= (String) menuItem.getTitle();
                currentFeed=title;
                //child.clear();
                //after_key="";
                //Toast.makeText(MainActivity.this, "title: "+title, Toast.LENGTH_SHORT).show();

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerlayout);
                drawer.closeDrawer(GravityCompat.START);
                getData();
                return true;
            }
        });



    }





     private void getData(){

        /* String url=RedditAPI.BASE_URL+currentFeed+"/hot/.json";
         *//*if(url!=""){
             url=url+"?after="+after_key;
         }*//*
         Log.d(TAG, "url: "+url);
         //Toast.makeText(this, url, Toast.LENGTH_SHORT).show();
         *//*if(after_key==null){
             return;
         }*//*
         //Toast.makeText(this, "currentfeed= "+current, Toast.LENGTH_SHORT).show();
*/
         Call<Feed> feed=RedditAPI.getRedditService().getFeed(currentFeed,100);

         feed.enqueue(new Callback<Feed>() {
             @Override
             public void onResponse(Call<Feed> call, Response<Feed> response) {
                 //progressBar.setVisibility(View.GONE);

                 Feed feed=response.body();
                 //after_key=feed.getData().getAfter();
                // child.addAll(feed.getData().getChildren());

                 //customAdapter.notifyDataSetChanged();
                 //after=response.body().getData().getAfter();
                 recyclerView.setAdapter(new CustomAdapter(MainActivity.this,feed.getData().getChildren()));


                 //Log.d(TAG, "onResponse: "+feed.getData().getAfter());

             }

             @Override
             public void onFailure(Call<Feed> call, Throwable t) {
                 //progressBar.setVisibility(View.GONE);
                 Toast.makeText(MainActivity.this, "Error: "+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
             }
         });
     }


     private void setUpToolbar(){
         drawerLayout=findViewById(R.id.drawerlayout);
         toolbar=findViewById(R.id.toolbar);
         setSupportActionBar(toolbar);
         actionBarDrawerToggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
         drawerLayout.addDrawerListener(actionBarDrawerToggle);
         actionBarDrawerToggle.syncState();
     }
}
