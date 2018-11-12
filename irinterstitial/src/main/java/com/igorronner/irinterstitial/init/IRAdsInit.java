package com.igorronner.irinterstitial.init;

import android.app.Activity;
import android.support.annotation.DrawableRes;


public class IRAdsInit {


    // Builder
    public static IRAdsInit.Builder start() {
        return new IRAdsInit.Builder();
    }

    private IRAdsInit(final IRAdsInit.Builder builder) {
        ConfigUtil.LOGO = builder.logo;
        ConfigUtil.INTERSTITIAL_ID = builder.interstitialId;
        ConfigUtil.NATIVE_AD_ID = builder.nativeAdId;
        ConfigUtil.PUBLISHER_INTERSTITIAL_ID = builder.publisherInterstitialId;
        ConfigUtil.PRODUCT_SKU = builder.productSku;
        ConfigUtil.APP_PREFIX = builder.appPrefix;
    }

    public static class Builder {
        @DrawableRes
        private int logo;
        private IRAdsInit IRAdsInit;
        private String interstitialId;
        private String nativeAdId;
        private String publisherInterstitialId;
        private String productSku;
        // Prefix for concat keys on remote config...
        private String appPrefix;

        public Builder() {
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

        public Builder setPublisherInterstitialId(String publisherInterstitialId) {
            this.publisherInterstitialId = publisherInterstitialId;
            return this;
        }

        public Builder enablePurchace(String productSku){
            this.productSku = productSku;
            return this;
        }

        public Builder setAppPrefix(String appPrefix){
            this.appPrefix = appPrefix;
            return this;
        }

        public IRAdsInit build() {
            this.IRAdsInit = new IRAdsInit(this);

            return this.IRAdsInit;
        }

    }


}