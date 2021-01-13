package com.example.ys;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface MyWebApi {

    @GET("mywebapi/public/api/users")
    Call<List<Post>> getPosts();

    @GET("mywebapi/public/api/users/login/{id}/{pass}")
    Call<List<Post>> loginuser(@Path("id") String id,@Path("pass") String pass);
    //@FormUrlEncoded
    //@PUT("mywebapi/public/api/applications/add")
    //Call<String> getstringscalar(@Field("User") String User,@Field("Password") String Password,@Field("Role") String Role);

    @DELETE("mywebapi/public/api/users/delete/{id}")
    Call<String> deleteacc1(@Path("id") String id);

    @FormUrlEncoded
    @PUT("mywebapi/public/api/users/update/{id}")
    Call<PUT> changepass(@Path("id") String id,@Field("Password") String Password);


    @FormUrlEncoded
    @PUT("mywebapi/public/api/users/add")
    Call<PUT> adduser(@Field("User") String User,@Field("Password") String Password,@Field("Role") String Role);

    @FormUrlEncoded
    @PUT("mywebapi/public/api/applications/add")
    Call<PUT> addapplication(@Field("User") String User  ,@Field("FName") String FName, @Field("LName") String LName ,@Field("RollNumber") String RollNumber, @Field("Program") String Program, @Field("Section") String Section, @Field("Code1") String Code1, @Field("Code2") String Code2, @Field("cordinateCmnt") String  cordinateCmnt, @Field("hodCmnt") String hodCmnt , @Field("Status") String Status);
}
