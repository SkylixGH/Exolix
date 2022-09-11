#pragma once

namespace exolix {
    /**
    * Integer 64 bit signed. The maximum value for this is
    * `9223372036854775807` and the minimum value for this is
    * `-9223372036854775807`.
    */
    typedef long long i64;

    /**
    * Integer 32 bit signed. The maximum value for this is
    * `2147483647` and the minimum value for this is `-2147483647`.
    */
    typedef int i32;

    /**
     * Integer 16 bit signed. The maximum value for this is
     * `32767` and the minimum value for this is `-32767`.
     */
    typedef short i16;

    /**
     * Integer 8 bit signed. The maximum value for this is
     * `127` and the minimum value for this is `-127`.
     */
    typedef char i8;

    /**
     * Integer 64 bit unsigned. The maximum value for this is
     * `9223372036854775807` and the minimum value for this is `0`.
     */
    typedef unsigned long long u64;

    /**
     * Integer 32 bit unsigned. The maximum value for this is
     * `4294967295` and the minimum value for this is `0`.
     */
    typedef unsigned int u32;

    /**
     * Integer 16 bit unsigned. The maximum value for this is
     * `65535` and the minimum value for this is `0`.
     */
    typedef unsigned short u16;

    /**
     * Integer 8 bit unsigned. The maximum value for this is
     * `255` and the minimum value for this is `0`.
     */
    typedef unsigned char u8;
}
