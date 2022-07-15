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

Be sure to have the following packages installed on your system too!
 - Glog - Google-Glog
