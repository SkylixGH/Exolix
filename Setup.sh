# Clone externals
git submodule update --init --recursive

# Generate build directory.
mkdir Build

# Initialize CMake in that directory.
cd Build
cmake ../

# Compile the project.
cmake --build ./

# Return to original directory.
cd ../
