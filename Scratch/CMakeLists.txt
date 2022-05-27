file(GLOB_RECURSE sources "Scratch/**/*.cxx" "Scratch/**/*.h" "Scratch/*.cxx" "Scratch/*.h")

add_library (
    Scratch
    ${sources}
)
