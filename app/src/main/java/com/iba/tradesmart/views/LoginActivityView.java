package com.iba.tradesmart.views;

import android.app.Dialog;
import android.content.DialogInterface;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.iba.tradesmart.activities.LoginActivity;
import com.iba.tradesmart.R;
import com.iba.tradesmart.helpers.SocialNetworksHelper;
import com.tenpearls.android.components.ImageView;
import com.tenpearls.android.interfaces.Controller;
import com.tenpearls.android.views.BaseView;

/**
 * Created  on 19/01/2016.
 */
public class LoginActivityView extends BaseView {

    private WebView webViewAuthorization;
    private Dialog dialog;

    private ImageView imgFbLoginButton;
    private ImageView imgLinkedinLoginButton;
    private ImageView imgGoogleLoginButton;

    public LoginActivityView(Controller controller) {
        super(controller);
    }

    @Override
    protected int getViewLayout() {
        return R.layout.view_login_activity;
    }

    @Override
    public void onCreate() {
        imgFbLoginButton = findViewById(R.id.imgFbLoginButton);
        imgLinkedinLoginButton = findViewById(R.id.imgLinkedinLoginButton);
        imgGoogleLoginButton = findViewById(R.id.imgGoogleLoginButton);

        initializeWebViewDialog();
    }

    private void linkedInWebViewAuthorization() {
        showLoader();
        webViewAuthorization.loadUrl(SocialNetworksHelper.AUTHORIZATION_URL);
    }

    private void initializeWebViewDialog() {
        webViewAuthorization = new WebView(getBaseActivity());

        dialog = new Dialog(getBaseActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        webViewAuthorization.requestFocus(View.FOCUS_DOWN);
        webViewAuthorization.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String authorizationUrl) {
                return ((LoginActivity) controller).onLinkedInAuthorization(authorizationUrl);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                if(!dialog.isShowing()) {
                    hideLoader();
                    dialog.setContentView(view);
                    dialog.show();
                }
            }
        });

        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                setEnabledSocialLoginView(true);
            }
        });
    }

    @Override
    protected int getToolbarId() {
        return R.id.toolbar;
    }

    @Override
    public void setActionListeners() {

        imgFbLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setEnabledSocialLoginView(false);
                ((LoginActivity) getBaseActivity()).onFbLogin();
            }
        });

        imgLinkedinLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setEnabledSocialLoginView(false);
                linkedInWebViewAuthorization();
            }
        });

        imgGoogleLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setEnabledSocialLoginView(false);
                ((LoginActivity) getBaseActivity()).onGoogleLogin();
            }
        });

    }

    public void setEnabledSocialLoginView(boolean isEnable) {
        imgFbLoginButton.setEnabled(isEnable);
        imgGoogleLoginButton.setEnabled(isEnable);
        imgLinkedinLoginButton.setEnabled(isEnable);
    }

    @Override
    protected int getProgressBarId() {
        return R.id.progressBar;
    }

    public void webViewLoadUrl(String authorizationUrl){
        webViewAuthorization.loadUrl(authorizationUrl);
    }

    public void destroyWebview(){

        if(dialog != null)
            dialog.dismiss();
    }
}
