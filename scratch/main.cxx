#include <elixor.hxx>

using namespace Elixor::System::Process;
using namespace std;

int main() {
    auto iuac = ThisProcess::isElevated();

    cout << (iuac
        ? "The process is elevated"
        : "The process is NOT elevated") << "\n";

    return 0;
}
