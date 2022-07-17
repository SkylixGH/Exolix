file(
        GLOB Elixor_SRC_FILES
        src/**/*.cxx
        src/**/*.hxx
        src/**/**/*.cxx
        src/**/**/*.hxx
        src/*.cxx
        src/*.hxx
)

add_library(Elixor STATIC ${Elixor_SRC_FILES})
target_include_directories(Elixor PUBLIC include)

include(${CMAKE_SOURCE_DIR}/cmake/cpm-cmake/CPM.cmake/cmake/CPM.cmake)

CPMAddPackage(
        NAME ixwebsocket
        GITHUB_REPOSITORY machinezone/IXWebSocket
        VERSION 11.4.3
)

target_link_libraries(Elixor PRIVATE ixwebsocket)
target_include_directories(Elixor PRIVATE ${ixwebsocket_SOURCE_DIR})
