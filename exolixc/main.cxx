#include <string>
#include <iostream>
#include <exolix/Compiler.hxx>

using std::string;
using std::getenv;
using std::cout;
using skylix::exolix::Compiler;

short int debugOn = 0;
short int verboseOn = 0;

bool isDebug() {
    if (debugOn == 0) {
        debugOn = getenv("EXOLIX_DEBUG_COMPILE") != NULL ? 2 : 1;
    }

    return debugOn == 2;
}

bool isVerbose() {
    if (verboseOn == 0) {
        verboseOn = getenv("EXOLIX_VERBOSE_COMPILE") != NULL ? 2 : 1;
    }

    return verboseOn == 2;
}

void print(const string &tag, const string &str) {
    cout << tag << " " << str << "\n";
}

void info(const string &str) {
    print("[ Info ]", str);
}

void error(const string &str) {
    print("\x1b[31m" "[ Error ] " "\x1b[0m", str);
}

void verbose(const string &str) {
    if (isVerbose()) {
        print("[ Verbose ]", str);
    }
}

void debug(const string &str) {
    if (isDebug()) print("[ Debug ]", str);
}

void config(const string &str) {
    print("[ Config ]", str);
}

void ok(const string &str) {
    if (isVerbose()) print("\x1b[32m" "[ OK ]" "\x1b[0m", str);
}

int main(const int argc, const char* const argv[]) {
    if (isVerbose()) {
        cout << "\n";

        config("Verbose mode was enabled for environment variable EXOLIX_VERBOSE_COMPILE");
        config("To disable verbose mode, remove the EXOLIX_VERBOSE_COMPILE environment variable");
        verbose("This is a verbose status message, verbose messages will now be visible following onwards");
    }

    if (isDebug()) {
        cout << "\n";

        config("Debug mode was enabled from environment variable EXOLIX_DEBUG_COMPILE");
        config("To disable debug mode, remove the EXOLIX_DEBUG_COMPILE environment variable");
        debug("This is a debug message, debug messages will now be visible following onwards");

        cout << "\n   ---   ---   [    EXOLIX DEBUG    ]   ---   ---   \n\n";
    }

    if (argv[1] == NULL) {
        error("No entry file was provided");
        return 1;
    }

    Compiler compiler(argv[1]);
    compiler.validate();

    debug("Input entry file size is " + std::to_string(compiler.getInputSize()) + " bytes");
    ok("Found source file at " + string(argv[1]));

    compiler.compile();
    return 0;
}
