cmake_minimum_required(VERSION 3.2)
project(Exolix_Examples)

function(add_example EXAMPLE_NAME)
    file(GLOB_RECURSE EXAMPLE_FILES ${EXAMPLE_NAME}/*.cc ${EXAMPLE_NAME}/*.h)

    add_executable(${EXAMPLE_NAME}.example ${EXAMPLE_FILES})

    target_link_libraries(${EXAMPLE_NAME}.example PUBLIC exolix)
    target_link_directories(${EXAMPLE_NAME}.example PUBLIC ${CMAKE_SOURCE_DIR})
endfunction()

add_example(helloWorld)