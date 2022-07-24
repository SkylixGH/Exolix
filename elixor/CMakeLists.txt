file(
        GLOB_RECURSE Elixor_SRC_FILES
        src/*.cxx
        src/*.hxx
        src/*.c
        src/*.h
)

add_library(Elixor STATIC ${Elixor_SRC_FILES})
target_include_directories(Elixor PUBLIC include)

# IXWebSocket [ixwebsocket]
add_subdirectory(external/machinezone/IXWebSocket)

target_link_libraries(Elixor PRIVATE ixwebsocket)
target_include_directories(Elixor PUBLIC ${ixwebsocket_SOURCE_DIR})
