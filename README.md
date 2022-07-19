<div align="center">
    <br />
    <br />
    <img src="resources/ElixorBanner.svg" />
    <br />
    <br />
    <br /> 
    <div>
        <p>Elixor | A framework for creating applications of all types!</p>
        <a href="https://discord.gg/b9vcR6evgG">Discord</a> - <a href="https://skylix.net/docs/elixor">Documentation</a> - <a href="https://skylix.net/projects/elixor">Elixor Homepage</a>
    </div>
</div>

## Installation
Installing Elixor into a CMake project is signifigantly easier as compared to other libraries! Simply add the following CMake code.
```cmake
add_subdirectory(
    # PATH TO ELIXOR DIRECTORY
)

target_link_libraries(
    Elixor
    PRIVATE # PATH TO ELIXOR DIRECTORY
)

target_include_directories(
    Elixor
    PRIVATE # PATH TO ELIXOR DIRECTORY
)
```

## Building
Elixor can be compiled with the following resources:
 - G++/GCC or Clang
 - CMake
 - NodeJS 18.6.0+

**Step 1** <br />
Clone elixor by running the following command:
```
git clone https://github.com/SkylixGH/Elixor
```

**Step 2** <br />
Initialize the setup with the following commands:
```
cd script
npm run setup
```

**Step 3** Final step! <br />
Now we can run a scratch file in elixor to test it out! Elixor uses scratch files in development to test out indevelopment APIs. Scratches are given identifiers which increment, inside of the `scratch` directory is a `main.cxx` file and some other scratch directories, labeled like this, `scratch1`, `scratch2` and so on... Inside of thos directories are `main.cxx` files which are their entry points. <br />
New scratches can be added by running `npm run scratch:add` which will create a new scratch project with an id incrememnting from the last one!
```
npm run scratch -- --id 1 # This will run scratch project number 1, setting this to 0 will run main.cxx in the root scratch project
```
## OS NOTICE
The Elixor team is currrently working on supporting Linux,Windows, and Mac. Currently Linux is being developed the most (;
