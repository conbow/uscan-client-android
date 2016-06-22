# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/connorbowman/Library/Android/sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Retrofit
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions

# Custom
-dontwarn okio.** # For Retrofit OkHTTP
-keep class com.connorbowman.uscan.models.** { *; }

# TODO Temporary fix for proguard issue with animating "progress" for drawer icon (aka Hamburger)
-keep class android.support.v7.app.ActionBarDrawerToggle$* { *; }