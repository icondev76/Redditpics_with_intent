package com.icodev76.redditpics;

import com.icodev76.redditpics.model.Feed;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public class RedditAPI {
    public static final String BASE_URL="https://www.reddit.com/r/";

    public static RedditService redditService=null;

    public static RedditService getRedditService()
    {
        if(redditService==null)
        {
            Retrofit retrofit=new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            redditService=retrofit.create(RedditService.class);
        }
        return redditService;
    }

    public interface RedditService{
        /*@GET
        Call<Feed> getFeed(@Url String url);*/


        @GET("{feed_name}/hot/.json")
        Call<Feed> getFeed(@Path("feed_name") String feed_name,
                           @Query("limit") int limit);
    }

}
