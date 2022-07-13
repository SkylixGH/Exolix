#include <elixor.hxx>
#include <iostream>

#ifdef _WIN32
#include <io.h>
#include <winsock2.h>
#pragma comment(lib, "ws2_32.lib")
#endif

using namespace std;
using namespace Elixor::Internal;

namespace Elixor {
namespace Server {
namespace Win32 {
Win32Socket::Win32Socket() {
#ifdef _WIN32
  // Implement server
#endif
}
} // namespace Win32
} // namespace Server
} // namespace Elixor
