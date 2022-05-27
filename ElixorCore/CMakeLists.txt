file(GLOB_RECURSE sources "Source/**/*.cxx" "Source/**/*.h")

add_library (
    ElixorCore
    ${sources}
)
