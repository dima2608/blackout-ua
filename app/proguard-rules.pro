# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.kts.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile


### Gson uses generic type information stored in a class file when working with fields. Proguard
# removes such information by default, so configure it to keep all of it.
-keepattributes Signature
# For using GSON @Expose annotation
-keepattributes *Annotation*
# Gson specific classes
-dontwarn sun.misc.**
#-keep class com.google.gson.stream.** { *; }
# Application classes that will be serialized/deserialized over Gson
-keep class com.google.gson.examples.android.model.** { <fields>; }
# Prevent proguard from stripping interface information from TypeAdapter, TypeAdapterFactory,
# JsonSerializer, JsonDeserializer instances (so they can be used in @JsonAdapter)
-keep class * extends com.google.gson.TypeAdapter
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer
# Prevent R8 from leaving Data object members always null

### Retrofit 2
# Platform calls Class.forName on types which do not exist on Android to determine platform.
-dontnote retrofit2.Platform
# Platform used when running on RoboVM on iOS. Will not be used at runtime.
# Platform used when running on Java 8 VMs. Will not be used at runtime.
# Retain generic type information for use by reflection by converters and adapters.
-keepattributes Signature
# Retain declared checked exceptions for use by a Proxy instance.
-keepattributes Exceptions

-dontwarn retrofit2.adapter.rxjava.CompletableHelper$** # https://github.com/square/retrofit/issues/2034
#To use Single instead of Observable in Retrofit interface
#Retrofit does reflection on generic parameters. InnerClasses is required to use Signature and
# EnclosingMethod is required to use InnerClasses.
-keepattributes Signature, InnerClasses, EnclosingMethod
# Retain service method parameters when optimizing.
-keepclassmembers,allowshrinking,allowobfuscation interface * {
    @retrofit2.http.* <methods>;
}
# Retrofit does reflection on method and parameter annotations.
-keepattributes RuntimeVisibleAnnotations, RuntimeVisibleParameterAnnotations
# Ignore annotation used for build tooling.
# Ignore JSR 305 annotations for embedding nullability information.
-dontwarn javax.annotation.**
# Guarded by a NoClassDefFoundError try/catch and only used when on the classpath.
-dontwarn kotlin.Unit
# Top-level functions that can only be used by Kotlin.
-dontwarn retrofit2.KotlinExtensions
-dontwarn retrofit2.KotlinExtensions$*
# With R8 full mode, it sees no subtypes of Retrofit interfaces since they are created with a Proxy
# and replaces all potential values with null. Explicitly keeping the interfaces prevents this.
-if interface * { @retrofit2.http.* <methods>; }
-keep,allowobfuscation interface <1>

### OkHttp3
-dontwarn okhttp3.**
-dontwarn okio.**
-dontwarn javax.annotation.**
# A resource is loaded with a relative path so the package of this class must be preserved.
-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase

-dontwarn org.slf4j.impl.StaticLoggerBinder

# Firebase Core
-keep class com.google.firebase.** { *; }
-dontwarn com.google.firebase.**

# Firebase Cloud Messaging (FCM)
-keep class com.google.firebase.messaging.** { *; }
-keep class com.google.firebase.iid.** { *; }
-dontwarn com.google.firebase.messaging.**

-keep class com.github.SmartToolFactory.ComposeScreenshot.** { *; }

-keep class com.tgym.domain.Entity { *; }
-keep class * implements com.tgym.domain.Entity { *; }

-keep class com.tgym.data.local.datastore.PreferencesSerializer { *; }
-keepclassmembers class com.tgym.data.local.datastore.PreferencesSerializer {
    public static ** INSTANCE;
}

-keepclassmembers enum * { *; }
-keepattributes Exceptions
-keep class retrofit2.** { *; }
-keep class com.google.gson.** { *; }
 -keep,allowobfuscation,allowshrinking interface retrofit2.Call
 -keep,allowobfuscation,allowshrinking class retrofit2.Response
 -keep,allowobfuscation,allowshrinking class kotlin.coroutines.Continuation

  # Keep generic signature of Call, Response (R8 full mode strips signatures from non-kept items).
  -keep,allowobfuscation,allowshrinking interface retrofit2.Call
  -keep,allowobfuscation,allowshrinking class retrofit2.Response

  # With R8 full mode generic signatures are stripped for classes that are not
  # kept. Suspend functions are wrapped in continuations where the type argument
  # is used.
  -keep,allowobfuscation,allowshrinking class kotlin.coroutines.Continuation


  ##---------------Begin: proguard configuration for Gson  ----------
  # Gson uses generic type information stored in a class file when working with fields. Proguard
  # removes such information by default, so configure it to keep all of it.
  -keepattributes Signature

  # For using GSON @Expose annotation
  -keepattributes *Annotation*

  # Gson specific classes
  -dontwarn sun.misc.**
  #-keep class com.google.gson.stream.** { *; }

   -keepclassmembers class **.R$* {
       public static <fields>;
   }

  # Application classes that will be serialized/deserialized over Gson
  -keep class com.google.gson.examples.android.model.** { <fields>; }

  # Prevent proguard from stripping interface information from TypeAdapter, TypeAdapterFactory,
  # JsonSerializer, JsonDeserializer instances (so they can be used in @JsonAdapter)
  -keep class * extends com.google.gson.TypeAdapter
  -keep class * implements com.google.gson.TypeAdapterFactory
  -keep class * implements com.google.gson.JsonSerializer
  -keep class * implements com.google.gson.JsonDeserializer

  -keep class com.google.gson.reflect.TypeToken
  -keep class * extends com.google.gson.reflect.TypeToken
  -keep public class * implements java.lang.reflect.Type


  # Retain generic signatures of TypeToken and its subclasses with R8 version 3.0 and higher.
  -keep,allowobfuscation,allowshrinking class com.google.gson.reflect.TypeToken
  -keep,allowobfuscation,allowshrinking class * extends com.google.gson.reflect.TypeToken

  ##---------------End: proguard configuration for Gson  ----------

  # With R8 full mode generic signatures are stripped for classes that are not
  # kept. Suspend functions are wrapped in continuations where the type argument
  # is used.
  -keep,allowobfuscation,allowshrinking class kotlin.coroutines.Continuation
  -keep,allowobfuscation,allowshrinking interface retrofit2.Call
  -keep,allowobfuscation,allowshrinking class retrofit2.Response

  # R8 full mode strips generic signatures from return types if not kept.
  -if interface * { @retrofit2.http.* public *** *(...); }
  -keep,allowoptimization,allowshrinking,allowobfuscation class <3>
-keep class org.slf4j.impl.StaticLoggerBinder { *; }

-keep class * {
  @com.google.gson.annotations.SerializedName <fields>;
}

-if class *
-keepclasseswithmembers class <1> {
    <init>(...);
    @com.google.gson.annotations.SerializedName <fields>;
}
-keep class androidx.lifecycle.LiveData { *; }

-keep class  com.iku.community_chat.data.room.entity.** { *; }
-keep class androidx.appcompat.widget.** { *; }
#Jsoup
-keep public class org.jsoup.** {
public *;
}
-keepnames class com.iku.user.profile.data.room.models.** { *; }
-keepnames class com.iku.community_courses.data.room.entity.** { *; }
-keeppackagenames org.jsoup.nodes

# Keep generic signature of Call, Response (R8 full mode strips signatures from non-kept items).
-keep,allowobfuscation,allowshrinking interface retrofit2.Call
-keep,allowobfuscation,allowshrinking class retrofit2.Response

# With R8 full mode generic signatures are stripped for classes that are not
# kept. Suspend functions are wrapped in continuations where the type argument
# is used.
-keep,allowobfuscation,allowshrinking class kotlin.coroutines.Continuation

-keepattributes SourceFile,LineNumberTable
-renamesourcefileattribute SourceFile
-repackageclasses


# Firebase  https://github.com/firebase/firebase-android-sdk/issues/4900
-keep public class com.google.firebase.** { *;}
-keep class com.google.android.gms.internal.** { *;}
-keepclasseswithmembers class com.google.firebase.FirebaseException


# https://github.com/square/retrofit/issues/3751#issuecomment-1564410089
-keep,allowobfuscation,allowshrinking class kotlin.coroutines.Continuation
-keep,allowobfuscation,allowshrinking interface retrofit2.Call
-keep,allowobfuscation,allowshrinking class retrofit2.Response
-if interface * { @retrofit2.http.* public *** *(...); }
-keep,allowoptimization,allowshrinking,allowobfuscation class <3>

# https://github.com/google/gson/commit/43396e45fd1f03e408e0e83b168a72a0f3e0b84e#diff-5da161239475717e284b3a9a85e2f39256d739fb7564ae7fda7f79cee000c413
-keepclasseswithmembers,allowobfuscation,includedescriptorclasses class * {
    @com.google.gson.annotations.SerializedName <fields>;
}

#Auto generated by studio
-dontwarn com.android.build.api.component.AndroidTest
-dontwarn com.android.build.api.component.UnitTest
-dontwarn com.android.build.api.instrumentation.AsmClassVisitorFactory
-dontwarn com.android.build.api.instrumentation.FramesComputationMode
-dontwarn com.android.build.api.instrumentation.InstrumentationParameters
-dontwarn com.android.build.api.instrumentation.InstrumentationScope
-dontwarn com.android.build.api.variant.AndroidComponentsExtension$DefaultImpls
-dontwarn com.android.build.api.variant.AndroidComponentsExtension
-dontwarn com.android.build.api.variant.AndroidTest
-dontwarn com.android.build.api.variant.ApplicationVariant
-dontwarn com.android.build.api.variant.Component
-dontwarn com.android.build.api.variant.HasAndroidTest
-dontwarn com.android.build.api.variant.Instrumentation
-dontwarn com.android.build.api.variant.LibraryVariant
-dontwarn com.android.build.api.variant.Variant
-dontwarn com.android.build.api.variant.VariantSelector
-dontwarn com.android.build.gradle.AppExtension
-dontwarn com.android.build.gradle.BaseExtension
-dontwarn com.android.build.gradle.LibraryExtension
-dontwarn com.android.build.gradle.TestExtension
-dontwarn com.android.build.gradle.api.AndroidBasePlugin
-dontwarn com.android.build.gradle.api.ApplicationVariant
-dontwarn com.android.build.gradle.api.BaseVariant
-dontwarn com.android.build.gradle.api.TestVariant
-dontwarn com.android.build.gradle.api.UnitTestVariant
-dontwarn com.android.build.gradle.internal.CompileOptions
-dontwarn com.android.build.gradle.internal.dsl.LintOptions
-dontwarn com.android.build.gradle.tasks.JdkImageInput
-dontwarn com.google.devtools.ksp.gradle.KspTaskJvm
-dontwarn javax.lang.model.SourceVersion
-dontwarn javax.lang.model.element.Element
-dontwarn javax.lang.model.element.Modifier
-dontwarn javax.lang.model.type.TypeKind
-dontwarn javax.lang.model.type.TypeMirror
-dontwarn javax.lang.model.type.TypeVisitor
-dontwarn javax.lang.model.util.SimpleAnnotationValueVisitor8
-dontwarn javax.lang.model.util.SimpleElementVisitor8
-dontwarn javax.lang.model.util.SimpleTypeVisitor8
-dontwarn javax.tools.SimpleJavaFileObject
-dontwarn org.gradle.api.Action
-dontwarn org.gradle.api.DefaultTask
-dontwarn org.gradle.api.DomainObjectCollection
-dontwarn org.gradle.api.DomainObjectSet
-dontwarn org.gradle.api.JavaVersion
-dontwarn org.gradle.api.Plugin
-dontwarn org.gradle.api.Project
-dontwarn org.gradle.api.ProjectState
-dontwarn org.gradle.api.Task
-dontwarn org.gradle.api.Transformer
-dontwarn org.gradle.api.artifacts.ArtifactView$ViewConfiguration
-dontwarn org.gradle.api.artifacts.ArtifactView
-dontwarn org.gradle.api.artifacts.Configuration
-dontwarn org.gradle.api.artifacts.ConfigurationContainer
-dontwarn org.gradle.api.artifacts.Dependency
-dontwarn org.gradle.api.artifacts.DependencySet
-dontwarn org.gradle.api.artifacts.ResolvableDependencies
-dontwarn org.gradle.api.artifacts.component.ComponentIdentifier
-dontwarn org.gradle.api.artifacts.component.ProjectComponentIdentifier
-dontwarn org.gradle.api.artifacts.dsl.DependencyHandler
-dontwarn org.gradle.api.artifacts.transform.CacheableTransform
-dontwarn org.gradle.api.artifacts.transform.TransformAction
-dontwarn org.gradle.api.artifacts.transform.TransformSpec
-dontwarn org.gradle.api.attributes.Attribute
-dontwarn org.gradle.api.attributes.AttributeContainer
-dontwarn org.gradle.api.file.ConfigurableFileCollection
-dontwarn org.gradle.api.file.Directory
-dontwarn org.gradle.api.file.DirectoryProperty
-dontwarn org.gradle.api.file.FileCollection
-dontwarn org.gradle.api.file.FileTree
-dontwarn org.gradle.api.internal.DefaultDomainObjectSet
-dontwarn org.gradle.api.invocation.Gradle
-dontwarn org.gradle.api.plugins.ExtensionContainer
-dontwarn org.gradle.api.plugins.PluginContainer
-dontwarn org.gradle.api.provider.ListProperty
-dontwarn org.gradle.api.provider.Property
-dontwarn org.gradle.api.provider.Provider
-dontwarn org.gradle.api.provider.ProviderFactory
-dontwarn org.gradle.api.specs.Spec
-dontwarn org.gradle.api.tasks.CacheableTask
-dontwarn org.gradle.api.tasks.Classpath
-dontwarn org.gradle.api.tasks.Input
-dontwarn org.gradle.api.tasks.Internal
-dontwarn org.gradle.api.tasks.Optional
-dontwarn org.gradle.api.tasks.OutputDirectory
-dontwarn org.gradle.api.tasks.TaskContainer
-dontwarn org.gradle.api.tasks.TaskProvider
-dontwarn org.gradle.api.tasks.compile.CompileOptions
-dontwarn org.gradle.api.tasks.compile.JavaCompile
-dontwarn org.gradle.process.CommandLineArgumentProvider
-dontwarn org.gradle.util.GradleVersion
-dontwarn org.gradle.workers.WorkAction
-dontwarn org.gradle.workers.WorkParameters
-dontwarn org.jetbrains.kotlin.gradle.internal.KaptTask
-dontwarn org.spongycastle.asn1.ASN1ObjectIdentifier
-dontwarn org.spongycastle.crypto.BlockCipher
-dontwarn org.spongycastle.crypto.CipherParameters
-dontwarn org.spongycastle.crypto.engines.AESFastEngine
-dontwarn org.spongycastle.crypto.modes.CBCBlockCipher
-dontwarn org.spongycastle.crypto.paddings.PaddedBufferedBlockCipher
-dontwarn org.spongycastle.crypto.params.KeyParameter
-dontwarn org.spongycastle.crypto.params.ParametersWithIV

-keep public class com.google.firebase.** { *; }
-keep class com.google.android.gms.internal.** { *; }
-keepclasseswithmembers class com.google.firebase.FirebaseException
-keep class androidx.datastore.*.** {*;}

-keep class com.google.billing.model.** { *; }
-keep class com.android.vending.billing.** { *; }