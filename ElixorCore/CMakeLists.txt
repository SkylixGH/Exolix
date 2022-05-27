file(GLOB_RECURSE sources "ElixorCore/**/*.cxx" "ElixorCore/**/*.h" "ElixorCore/*.cxx" "ElixorCore/*.h")

add_library (
    ElixorCore
    ${sources}
)
