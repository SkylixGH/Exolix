cmake_minimum_required(VERSION 3.0)

project(native)

file(GLOB SOURCE_FILES "*.cxx" "*.hxx" "**/*.cxx" "**/*.hxx")
add_library(${PROJECT_NAME} SHARED ${SOURCE_FILES} ${CMAKE_JS_SRC})

set_target_properties(${PROJECT_NAME} PROPERTIES PREFIX "" SUFFIX ".node")

target_include_directories(${PROJECT_NAME} PRIVATE ${CMAKE_JS_INC})

target_link_libraries(${PROJECT_NAME} ${CMAKE_JS_LIB})
