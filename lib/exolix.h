#pragma once

#ifdef __linux__
#elif _POSIX_VERSION
    #error "Posix is not supported"
#endif

#ifdef _WIN32
    #define WIN32_LEAN_AND_MEAN
#endif

#include <string>

/**
 * Exolix Software Framework
 *
 * This software is protected by the Skylix
 * FOSS Software License. Please read the License
 * file for more information.
 */
namespace exolix {}
