/*
 * Copyright 2021 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

package dev.icerock.moko.web3.hex

import com.soywiz.kbignum.BigInt
import com.soywiz.kbignum.bi
import kotlin.jvm.JvmInline

@JvmInline
internal value class HexStringValueClass(private val value: String): HexString {
    init {
        require(withoutPrefix.matches(Regex("[0-9a-f]+")))
    }

    override val withoutPrefix: String get() = value.removePrefix(HEX_PREFIX)
    override val prefixed: String get() = "$HEX_PREFIX$withoutPrefix"
    override val bigInt: BigInt get() = value.bi(RADIX)

    companion object {
        const val HEX_PREFIX = "0x"
        const val RADIX = 16
    }
}
