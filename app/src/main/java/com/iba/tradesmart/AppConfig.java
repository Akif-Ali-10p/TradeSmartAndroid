package com.iba.tradesmart;

import com.tenpearls.android.utilities.PreferenceUtility;

/**
 * Created on 2/10/2016.
 */
public class AppConfig {

    private static AppConfig instance;

    private String accessToken;

    private boolean isSocialLogin = false;

    private AppConfig() {
    }

    public static AppConfig getInstance() {
        if (instance == null)
            instance = new AppConfig();

        return instance;
    }

    public String getAccessToken() {
        if (accessToken == null) {
            this.setAccessToken(PreferenceUtility.getString(Application.getInstance(), KeyConstants.KEY_ACCESS_TOKEN, ""));
        }

        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
        PreferenceUtility.putString(Application.getInstance(), KeyConstants.KEY_ACCESS_TOKEN, this.accessToken);
    }

    public Boolean isSocialLogin() {
        return PreferenceUtility.getBoolean(Application.getInstance(), KeyConstants.KEY_IS_SOCIAL_LOGIN_ENABLED, false);
    }

    public void setSocialLogin(boolean isSocialLogin) {
        this.isSocialLogin = isSocialLogin;
        PreferenceUtility.putBoolean(Application.getInstance(), KeyConstants.KEY_IS_SOCIAL_LOGIN_ENABLED, this.isSocialLogin);
    }

    public String getUserName() {
        return PreferenceUtility.getString(Application.getInstance(), KeyConstants.KEY_USER_NAME, "");
    }

    public void setUserName(String userName) {
        PreferenceUtility.putString(Application.getInstance(), KeyConstants.KEY_USER_NAME, userName);
    }

    public void setUserId(String userId) {
        PreferenceUtility.putString(Application.getInstance(), KeyConstants.KEY_USER_ID, userId);
    }

    public String getUserId() {
        return PreferenceUtility.getString(Application.getInstance(), KeyConstants.KEY_USER_ID, "");
    }

    public String getUserImage() {
        return PreferenceUtility.getString(Application.getInstance(), KeyConstants.KEY_USER_PROFILE_PIC, "");
    }

    public void setUserImage(String userImage) {
        PreferenceUtility.putString(Application.getInstance(), KeyConstants.KEY_USER_PROFILE_PIC, userImage);
    }

    public void setUserData(String accessToken, String userName, String userImage, String userId){
        setAccessToken(accessToken);
        setUserName(userName);
        setUserImage(userImage);
        setUserId(userId);
    }

    public void logout(){
        setAccessToken("");
    }
}
