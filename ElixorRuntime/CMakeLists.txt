file(GLOB_RECURSE sources "ElixorRuntime/**/*.cxx" "ElixorRuntime/**/*.h" "ElixorRuntime/*.cxx" "ElixorRuntime/*.h")

add_library (
    ElixorRuntime
    ${sources}
)
