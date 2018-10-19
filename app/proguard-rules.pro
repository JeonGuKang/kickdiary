# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/kihun/Library/Android/sdk/tools/proguard/proguard-android.txt
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

# for retrofit2
-dontwarn retrofit2.**
-dontwarn okio.**
-dontwarn javax.annotation.**
-keep class retrofit2.** { *; }

# glide
-keepnames class com.named.app.util.MyGlideModule
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}

-dontwarn org.joda.convert.**

# Lambda
-dontwarn java.lang.invoke.*


-dontwarn sun.misc.**

-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
   long producerIndex;
   long consumerIndex;
}

-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}

-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}
# TNK AD 라이브러리
-keep class com.tnkfactory.** { *;}
-keepattributes Signature
-keepattributes Exceptions

#google
-libraryjars libs
-keep class android.support.v4.app.** { *; }
-keep interface android.support.v4.app.** { *; }
-keep class android.support.v7.widget.** { *; }
-keep class android.support.design.widget.** { *; }

#-keep class android.** { *; }
-keep class com.strongbulb.kickdiary.model.** { *; }

-keepattributes *Annotation*
#Fablic Crashlytics
-keepattributes SourceFile,LineNumberTable

#kotlin
-keep class kotlin.** { *; }
-keep class kotlin.Metadata { *; }
-dontwarn kotlin.**
-keepclassmembers class **$WhenMappings {
    <fields>;
}
-keepclassmembers class kotlin.Metadata {
    public <methods>;
}
-assumenosideeffects class kotlin.jvm.internal.Intrinsics {
    static void checkParameterIsNotNull(java.lang.Object, java.lang.String);
}

# For enumeration classes, see http://proguard.sourceforge.net/manual/examples.html#enumerations
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keep class com.google.firebase.provider.FirebaseInitProvider

-keep class com.facebook.marketing.internal.MarketingInitProvider

-keep class com.facebook.internal.FacebookInitProvider

-keep class com.facebook.FacebookContentProvider

-keep class okhttp3.Headers {*; }
-keep class okio.Okio {*; }

 #okhttp3 - start
#A resource is loaded with a relative path so the package of this class must be preserved.
-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase
# Animal Sniffer compileOnly dependency to ensure APIs are compatible with older versions of Java.
-dontwarn org.codehaus.mojo.animal_sniffer.*
# OkHttp platform used only on JVM and when Conscrypt dependency is available.
-dontwarn okhttp3.internal.platform.ConscryptPlatform
 #okhttp3 - end