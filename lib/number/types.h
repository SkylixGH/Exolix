#pragma once

namespace exolix {
    /**
    * Integer 64 bit signed. The maximum value for this is
    * `9223372036854775807` and the minimum value for this is
     * `-9223372036854775808`.
    */
    typedef long long i64;

    /**
    * Integer 32 bit signed. The maximum value for this is
    * `2147483647` and the minimum value for this is `-2147483648`.
    */
    typedef int i32;

    /**
     * Integer 16 bit signed. The maximum value for this is
     * `32767` and the minimum value for this is `-32768`.
     */
    typedef short i16;

    /**
     * Integer 8 bit signed. The maximum value for this is
     * `127` and the minimum value for this is `-128`.
     */
    typedef char i8;

    /**
     * Integer 64 bit unsigned. The maximum value for this is
     * `18446744073709551615` and the minimum value for this is `0`.
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

    /**
     * Floating point 64 bit. The maximum value for this is
     * `1.7976931348623157e+308` and the minimum value for this is
     * `2.2250738585072014e-308`.
     */
    typedef double f64;

    /**
     * Floating point 32 bit. The maximum value for this is
     * `3.4028235e+38` and the minimum value for this is
     * `1.17549435e-38`.
     */
    typedef float f32;
}
