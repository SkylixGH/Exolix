#include <elixor.hxx>
#include <iostream>

using namespace Elixor::Server::Linux;
using namespace Elixor::Server::MacOS;
using namespace Elixor::Server::Win32;
using namespace Elixor::Internal;
using namespace std;

int main() { new LinuxSocket("localhost", 8080); }
