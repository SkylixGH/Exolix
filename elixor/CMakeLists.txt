file(GLOB_RECURSE sources "src/**/*.cxx" "src/*.cxx")

add_library (
    elixor
    ${sources}
)
