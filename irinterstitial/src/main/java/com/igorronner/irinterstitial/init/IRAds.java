package com.igorronner.irinterstitial.init;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.DrawableRes;

import com.google.android.gms.ads.formats.NativeAppInstallAdView;
import com.igorronner.irinterstitial.R;
import com.igorronner.irinterstitial.preferences.MainPreference;
import com.igorronner.irinterstitial.services.IRInterstitialService;
import com.igorronner.irinterstitial.services.ManagerNativeAd;
import com.igorronner.irinterstitial.services.RemoteConfigService;
import com.igorronner.irinterstitial.views.SplashActivity;

import java.util.Calendar;


public class IRAds {

    public static IRAds.Builder startInit(Context context, String googleClientId) {
        if (MainPreference.getFirstLaunchDate(context) == 0)
            MainPreference.setFirstLaunchDate(context, Calendar.getInstance().getTimeInMillis());
        return new IRAds.Builder(googleClientId);
    }

    private IRAds(final IRAds.Builder builder) {
        ConfigUtil.LOGO = builder.logo;
        ConfigUtil.GOOGLE_CLIENT_ID = builder.googleClientId;
        ConfigUtil.INTERSTITIAL_ID = builder.interstitialId;
        ConfigUtil.NATIVE_AD_ID = builder.nativeAdId;
    }

    public static class Builder {
        @DrawableRes
        private int logo;
        private String googleClientId;
        private IRAds IRAds;
        private String interstitialId;
        private String nativeAdId;

        public Builder(String googleClientId) {
            this.googleClientId = googleClientId;
        }

        public Builder setLogo(@DrawableRes int logo) {
            this.logo = logo;
            return this;
        }

        public Builder setInterstitialId(String interstitialId) {
            this.interstitialId = interstitialId;
            return this;
        }

        public Builder setNativeAdId(String nativeAdId) {
            this.nativeAdId = nativeAdId;
            return this;
        }

        public IRAds build() {
            this.IRAds = new IRAds(this);

            return this.IRAds;
        }

    }

    public static void showInterstitial(final Activity activity, final String titleDialog){
        canShowInterstitial(activity, new RemoteConfigService.ServiceListener<Boolean>() {
            @Override
            public void onComplete(Boolean result) {
                if (result)
                    new IRInterstitialService(activity).showInterstitial(titleDialog);
                else if(activity instanceof SplashActivity)
                    activity.finish();
            }
        });

    }
    public static void showInterstitial(final Activity activity){
        canShowInterstitial(activity, new RemoteConfigService.ServiceListener<Boolean>() {
            @Override
            public void onComplete(Boolean result) {
                if (result)
                    new IRInterstitialService(activity).showInterstitial();
                else if(activity instanceof SplashActivity)
                    activity.finish();
            }
        });

    }

    public static void showInterstitialBeforeIntent(final Activity activity, final Intent intent, boolean finishAll, final String titleDialog){
        canShowInterstitial(activity, new RemoteConfigService.ServiceListener<Boolean>() {
            @Override
            public void onComplete(Boolean result) {
                if (result)
                    new IRInterstitialService(activity).showInterstitialBeforeIntent(activity, intent, titleDialog);
                else if(activity instanceof SplashActivity)
                    activity.finish();
            }
        });
    }
    public static void  showInterstitialBeforeIntent(final Activity activity, final Intent intent, final String titleDialog){
        showInterstitialBeforeIntent(activity, intent, false, titleDialog);
    }



    public static void openSplashScreen(Activity activity){
        activity.startActivity(new Intent(activity, SplashActivity.class));
    }

    public static void canShowInterstitial(Activity activity, RemoteConfigService.ServiceListener<Boolean> serviceListener){
        RemoteConfigService.getInstance(activity).canShowInterstitial(serviceListener);
    }


    public static void loadNativeAd(Activity activity, boolean showProgress){
        ManagerNativeAd.getInstance(activity)
                .setAdmobAdUnitId(ConfigUtil.NATIVE_AD_ID)
                .setShowProgress(showProgress)
                .loadAppInstallAdView((NativeAppInstallAdView) activity.findViewById(R.id.adViewNative));
    }

    public static void loadNativeAd(Activity activity, boolean showProgress, NativeAppInstallAdView nativeAppInstallAdView){
        ManagerNativeAd.getInstance(activity)
                .setAdmobAdUnitId(ConfigUtil.NATIVE_AD_ID)
                .setShowProgress(showProgress)
                .loadAppInstallAdView(nativeAppInstallAdView);
    }
}