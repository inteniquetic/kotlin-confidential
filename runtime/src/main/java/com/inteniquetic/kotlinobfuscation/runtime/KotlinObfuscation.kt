package com.inteniquetic.kotlinobfuscation.runtime

import android.util.Log

object KotlinObfuscation {

    @Volatile
    private var isLoaded = false

    /**
     * Initializes the native JNI runtime.
     *
     * This method MUST be called exactly once during application startup
     * (for example, in Application.onCreate()) before invoking any other
     * methods in this object.
     *
     * Internally, this loads the native library `libconfctl_runtime.so`.
     * Subsequent calls after a successful load are no-ops.
     *
     * Failure to load the native library (e.g. missing ABI, incorrect
     * packaging, or incompatible binary) is logged and leaves the runtime
     * in an uninitialized state. Any attempt to call native methods
     * without successful initialization will result in an exception.
     */
    fun initialize() {
        if (isLoaded) return
        try {
            System.loadLibrary("confctl_runtime")
            isLoaded = true
        } catch (e: UnsatisfiedLinkError) {
            Log.e("Kotlinobfuscation", "JNI load failed", e)
        }
    }

    /**
     * Ensures the native runtime has been initialized.
     *
     * @throws IllegalStateException if initialize() has not been called
     *         successfully prior to invoking a native method.
     */
    internal fun checkInitialized() {
        check(isLoaded) {
            "Kotlinobfuscation.initialize() must be called before using native methods"
        }
    }
}