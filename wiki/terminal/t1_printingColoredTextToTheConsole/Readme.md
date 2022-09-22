In this demonstration, Exolix will showcase how colored text can be printed to the console.

```c++
#include <exolix>

int main() {
    using namespace exolix;
    
    Console::write(TerminalColor("Hello World", ColorHex("ff5555")).render() + "\n");
    
    return 0;
}
```

## Explanation
 - First, Exolix's namespace contents are dumped into the global scope for the main function.
 - Then, an instance of `TerminalColor` is created.
   - The first parameter is the text to colorize
   - The second parameter is the color in a hexadecimal form.

The color may be off by a bit, this is because the terminal only supporting a maximum
of 256 colors, Exolix takes your HEX/RGB input and converts it to the closest color supported in the terminal.

Here's another way to write the same code, but with an RGB value. Just change `ColorHex("ff5555")` to `ColorRgb { 255, 85, 85 }` <br />

An alternative way for parsing the colors is the following:
```c++
#include <exolix>

int main() {
    using namespace exolix;
    
    Console::write(TerminalColor("Hello World").setFg(ColorHex("ffffff").render() + "\n");
    
    return 0;
}
```
