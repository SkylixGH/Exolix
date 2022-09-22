project(SkylixExolix)
cmake_minimum_required(VERSION 3.2)

set(CMAKE_CXX_STANDARD 17)

# Add project files
file(GLOB_RECURSE SkylixExolix_LIB_FILES "lib/*.cxx")
file(GLOB_RECURSE SkylixExolix_INCLUDE_FILES "lib/*.hxx")
add_library(skylixExolix ${SkylixExolix_LIB_FILES} ${SkylixExolix_INCLUDE_FILES})
include_directories(include)

# Write to include file
file(WRITE include/exolix "#pragma once\n\n")
foreach(file ${SkylixExolix_INCLUDE_FILES})
    file(APPEND include/exolix "#include \"${file}\"\n")
endforeach()

# Add examples
file(GLOB SkylixExolix_EXAMPLES_LIST RELATIVE ${CMAKE_CURRENT_SOURCE_DIR}/example ${CMAKE_CURRENT_SOURCE_DIR}/example/*)
foreach(example ${SkylixExolix_EXAMPLES_LIST})
    message(STATUS "Exolix adding example: ${example}")
    add_subdirectory(example/${example})
endforeach()

# Add tests
enable_testing()
function(setup_test NAME)
    add_executable("SkylixExolix_TESTS_${NAME}" ${CMAKE_CURRENT_SOURCE_DIR}/test/${NAME} ${CMAKE_CURRENT_SOURCE_DIR}/test/${NAME}/test.cxx)
    target_link_libraries("SkylixExolix_TESTS_${NAME}" PRIVATE skylixExolix)
    add_test(NAME "SkylixExolix_TESTS_${NAME}" COMMAND $<TARGET_FILE:SkylixExolix_TESTS_${NAME}>)
endfunction()

file(GLOB SkylixExolix_TESTS_LIST RELATIVE ${CMAKE_CURRENT_SOURCE_DIR}/test ${CMAKE_CURRENT_SOURCE_DIR}/test/*)
foreach(test ${SkylixExolix_TESTS_LIST})
    message(STATUS "Exolix adding test: ${test}")
    setup_test(${test})
endforeach()

# Add windows libs
if (WIN32)
    target_link_libraries(skylixExolix PUBLIC ws2_32)
endif ()

# Install Exolix
install(TARGETS skylixExolix DESTINATION lib)
install(FILES include/exolix DESTINATION include)
install(FILES cmake/SkylixExolixConfig.cmake DESTINATION cmake)
