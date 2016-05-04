package com.iba.tradesmart.helpers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import java.util.Collection;

/**
 * Created on 2/12/2016.
 */
public class SocialNetworksHelper {

    /************** Facebook SignIn ***************/
    private static boolean isFbLoggedIn = false;

    public static Boolean isLoggedInViaFacebook() {
        return isFbLoggedIn;
    }

    public static CallbackManager facebookLoginWithReadPermissions(Activity activity, Collection<String> loginParameters, FacebookCallback<LoginResult> facebookCallback) {
        CallbackManager callbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().logOut();
        LoginManager.getInstance().logInWithReadPermissions(activity, loginParameters);
        LoginManager.getInstance().registerCallback(callbackManager, facebookCallback);

        return callbackManager;
    }

    public static void facebookLogout(){
        if(isLoggedInViaFacebook())
            LoginManager.getInstance().logOut();
    }

    public static void setIsFbLoggedIn() {
        isFbLoggedIn = true;
    }
    /************** End Facebook SignIn ***************/

    /************** LinkedIn SignIn ***************/
    public static final int RC_LINKEDIN_SIGN_IN = 8001;

    /*CONSTANT FOR THE AUTHORIZATION PROCESS*/

    /****FILL THIS WITH YOUR INFORMATION*********/
    //This is the public api key of our application
    public static final String API_KEY = "771v1nvlyeo5pa";
    //This is the private api key of our application
    public static final String SECRET_KEY = "UrRJ8McyOf2hcZgw";
    //This is any string we want to use. This will be used for avoid CSRF attacks. You can generate one here: http://strongpasswordgenerator.com/
    public static final String STATE = "E3ZYKC1T6H2yP4z";
    /*********************************************/

    //These are constants used for build the urls
    public static final String AUTHORIZATION_URL = "https://www.linkedin.com/uas/oauth2/authorization?response_type=code&client_id=771v1nvlyeo5pa&state=E3ZYKC1T6H2yP4z&redirect_uri=http%3A%2F%2Fiba.edu.pk";
    public static final String ACCESS_TOKEN_URL = "https://www.linkedin.com/uas/oauth2/accessToken";
    public static final String SECRET_KEY_PARAM = "client_secret";
    public static final String RESPONSE_TYPE_PARAM = "response_type";
    public static final String GRANT_TYPE_PARAM = "grant_type";
    public static final String GRANT_TYPE = "authorization_code";
    public static final String RESPONSE_TYPE_VALUE ="code";
    public static final String CLIENT_ID_PARAM = "client_id";
    public static final String STATE_PARAM = "state";
    public static final String REDIRECT_URI_PARAM = "redirect_uri";
    /*---------------------------------------*/

    /************** End LinkedIn SignIn ***************/

    /************** Google SignIn ***************/
    public static final int RC_SIGN_IN = 9001;

    public static GoogleApiClient googleApiClient;

    public static void configureGoogleSignIn(Context context, FragmentActivity activity) {
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        buildGoogleClient(context, activity, new GoogleApiClient.OnConnectionFailedListener() {
            @Override
            public void onConnectionFailed(ConnectionResult connectionResult) {

            }
        }, googleSignInOptions);
    }

    private static void buildGoogleClient(Context context, FragmentActivity activity, GoogleApiClient.OnConnectionFailedListener connectionFailedListener, GoogleSignInOptions googleSignInOptions){
        googleApiClient = new GoogleApiClient.Builder(context)
                .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions)
                .build();

        googleApiClient.connect();
    }

    public static GoogleSignInResult getGoogleSignInResult(Intent data){
        return Auth.GoogleSignInApi.getSignInResultFromIntent(data);
    }

    public static void googleLogout(){
        if(googleApiClient.isConnected()) {
            Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
                @Override
                public void onResult(Status status) {

                }
            });
        }
    }

    public static Intent getGoogleSigninIntent(){
        return Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
    }
    /************** End Google SignIn ***************/

}
