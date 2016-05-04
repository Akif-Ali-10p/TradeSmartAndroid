package com.iba.tradesmart.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.iba.tradesmart.helpers.SocialNetworksHelper;
import com.iba.tradesmart.config.AppConfig;
import com.iba.tradesmart.views.LoginActivityView;
import com.tenpearls.android.activities.BaseActivity;
import com.tenpearls.android.interfaces.Controller;
import com.tenpearls.android.interfaces.ServiceSecondaryEventHandler;
import com.tenpearls.android.views.BaseView;

import java.util.Collections;

public class LoginActivity extends BaseActivity implements ServiceSecondaryEventHandler {
    //region Variables
    private CallbackManager callbackManager;
    //endregion

    //region Lifecycle
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        configureGoogleSignIn();

        if(AppConfig.getInstance().isSocialLogin())
            navigateToHomeActivity();
    }

    @Override
    protected com.tenpearls.android.service.ServiceFactory getServiceFactory() {
        return null;
    }

    @Override
    public BaseView getViewForController(Controller controller) {
        return new LoginActivityView(controller);
    }

    @Override
    public boolean hasToolbar() {
        return true;
    }
    //endregion

    //region handleActivityResults
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        handleFacebookLoginResult(requestCode, resultCode, data);

        if (requestCode == SocialNetworksHelper.RC_SIGN_IN) {
            handleGoogleLoginResult(SocialNetworksHelper.getGoogleSignInResult(data));
        }
    }

    private void handleGoogleLoginResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            AppConfig.getInstance().setSocialLogin(true);
            navigateToHomeActivity();
        }else{
            setEnabledSocialLoginView();
        }
    }

    private void handleFacebookLoginResult(int requestCode, int resultCode, Intent data) {
        try {
            callbackManager.onActivityResult(requestCode, resultCode, data);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    //endregion

    //region Social Login
    public void onFbLogin() {
        callbackManager = SocialNetworksHelper.facebookLoginWithReadPermissions(this, Collections.singletonList("email"), new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                AppConfig.getInstance().setSocialLogin(true);
                navigateToHomeActivity();
            }

            @Override
            public void onCancel() {
                setEnabledSocialLoginView();
            }

            @Override
            public void onError(FacebookException error) {
                setEnabledSocialLoginView();
            }
        });
    }

    private void configureGoogleSignIn() {
        SocialNetworksHelper.configureGoogleSignIn(getApplicationContext(), this);
    }

    public void onGoogleLogin() {
        Intent signInIntent = SocialNetworksHelper.getGoogleSigninIntent();
        startActivityForResult(signInIntent, SocialNetworksHelper.RC_SIGN_IN);
    }

    public boolean onLinkedInAuthorization(String authorizationUrl) {
        if(authorizationUrl.startsWith("http://iba.edu.pk")){
            Uri uri = Uri.parse(authorizationUrl);
            String stateToken = uri.getQueryParameter(SocialNetworksHelper.STATE_PARAM);

            if(!SocialNetworksHelper.STATE.equals(stateToken)){
                linkedInLoginFailure();
                return true;
            }

            String authorizationToken = uri.getQueryParameter(SocialNetworksHelper.RESPONSE_TYPE_VALUE);

            if(authorizationToken==null){
                linkedInLoginFailure();
                return true;
            }

            AppConfig.getInstance().setSocialLogin(true);
            navigateToHomeActivity();
        }else{
            loadWebView(authorizationUrl);
        }
        return true;
    }

    private void loadWebView(String authorizationUrl) {
        ((LoginActivityView) view).webViewLoadUrl(authorizationUrl);
    }

    private void linkedInLoginFailure() {
        ((LoginActivityView) view).destroyWebview();
        setEnabledSocialLoginView();
    }
    //endregion

    //region Start Next Activity
    private void navigateToHomeActivity(){
        Intent quizSelection = new Intent(this, HomeActivity.class);
        startActivity(quizSelection);
        finish();
    }
    //endregion

    //region Helper Functions
    private void setEnabledSocialLoginView() {
        ((LoginActivityView) view).setEnabledSocialLoginView(true);
    }
    //endregion

    //region ServiceSecondaryEventHandler
    @Override
    public void willStartCall() {
        showLoader();
    }

    @Override
    public void didFinishCall(boolean isSuccess) {
        hideLoader();
    }
    //endregion

}
