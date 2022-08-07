#include <string>
#include <iostream>

using std::string;
using std::getenv;
using std::cout;

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
    cout << "[ " << tag << " ] " << str << "\n";
}

void info(const string &str) {
    print("Info", str);
}

void error(const string &str) {
    print("Error", str);
}

void verbose(const string &str) {
    if (isVerbose()) {
        print("Verbose", str);
    }
}

void debug(const string &str) {
    if (isDebug()) print("Debug", str);
}

void config(const string &str) {
    print("Config", str);
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

    return 0;
}
