This demonstration showcases how text can be printed to the console output.

```c++
#include <exolix>

int main() {
    using namespace exolix;
    
    Console::write("Hello World\n"); // 'Hello World' is printed to the standard output stream
    Console::write("Hello World\n", true); // 'Hello World' is printed to the standard error stream
    
    return 0;
}
```

## Explanation
 - First, we are dumping all the namespace contents into the global namespace of the main function.
 - Then, we are printing the string `Hello World` to the standard output stream.
 - Finally, we are printing the string `Hello World` to the standard error stream.
   - Passing `true` as a second argument switches the output stream to `STDERR`.
