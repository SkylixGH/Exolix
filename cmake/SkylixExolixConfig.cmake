set(CMAKE_CXX_STANDARD 17)

set(SkylixExolix_VERSION_MAJOR 0)
set(SkylixExolix_VERSION_MINOR 1)
set(SkylixExolix_VERSION_PATCH 0)

set(SkylixExolix_VERSION "${SkylixExolix_VERSION_MAJOR}.${SkylixExolix_VERSION_MINOR}.${SkylixExolix_VERSION_PATCH}")
message(STATUS "Detected Exolix Commercial Software Framework version ${SkylixExolix_VERSION}")
message(STATUS "Exolix list directory: ${CMAKE_CURRENT_LIST_DIR}")

# Configure exolix library
include_directories(${CMAKE_CURRENT_LIST_DIR}/../include)

# Add library target from '../lib'
add_library(skylixExolix STATIC IMPORTED)

# Set lib props, this basically connects the lib to the target
if(WIN32)
    set_target_properties(skylixExolix PROPERTIES IMPORTED_LOCATION ${CMAKE_CURRENT_LIST_DIR}/../lib/skylixExolix.lib)
elseif(UNIX)
    set_target_properties(skylixExolix PROPERTIES IMPORTED_LOCATION ${CMAKE_CURRENT_LIST_DIR}/../lib/libskylixExolix.a)
endif()
