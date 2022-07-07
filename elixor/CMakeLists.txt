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
