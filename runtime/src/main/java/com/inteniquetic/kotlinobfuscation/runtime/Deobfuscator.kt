package com.inteniquetic.kotlinobfuscation.runtime

import com.inteniquetic.kotlinobfuscation.runtime.revealBytes as coreRevealBytes
import com.inteniquetic.kotlinobfuscation.runtime.revealString as coreRevealString

object Deobfuscator {
    /**
     * Reveals an obfuscated UTF-8 string using the native runtime.
     *
     * @throws IllegalStateException if the native runtime is not initialized
     */
    fun revealString(
        dataIn: ByteArray,
        meta: ByteArray,
        k0: ByteArray,
        k1: ByteArray
    ): String {
        KotlinObfuscation.checkInitialized()
        return coreRevealString(
            dataIn = dataIn,
            meta = meta,
            k0 = k0,
            k1 = k1
        )
    }

    /**
     * Reveals obfuscated binary data using the native runtime.
     *
     * @throws IllegalStateException if the native runtime is not initialized
     */
    fun revealBytes(
        dataIn: ByteArray,
        meta: ByteArray,
        k0: ByteArray,
        k1: ByteArray
    ): ByteArray {
        KotlinObfuscation.checkInitialized()
        return coreRevealBytes(
            dataIn = dataIn,
            meta = meta,
            k0 = k0,
            k1 = k1
        )
    }
}
