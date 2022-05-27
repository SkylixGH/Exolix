@rem Generate build directory.
mkdir Build

@rem Initialize CMake in that directory.
cd Build
cmake ../

@rem Compile the project.
cmake --build ./

@rem Return to original directory.
cd ../
