#include <exolix.hxx>
#include <vector>

using namespace exolix;

int main() {
    std::vector<SOCKET> fds {};

    WinsockTcpServer server([&] (SOCKET socketFd) {
        std::thread t([&] () {
            fds.push_back(socketFd);

            while (true) {
                // Use WSARecv
                LPWSABUF buffer = new WSABUF;
                buffer->buf = new char[1024];
                buffer->len = 1024;

                DWORD flags = 0;
                DWORD bytesReceived = 0;

                WSAOVERLAPPED overlapped;
                ZeroMemory(&overlapped, sizeof(WSAOVERLAPPED));

                int re = WSARecv(socketFd, buffer, 1, &bytesReceived, &flags, &overlapped, NULL);

                if (re == 0) {
                    std::cout << "Client disconnected" << std::endl;
                    fds.erase(std::remove(fds.begin(), fds.end(), socketFd), fds.end());
                    break;
                }

                if (re == -1) {
                    std::cout << "Error while receiving data" << std::endl;
                    std::cout << "Because: " << WSAGetLastError() << std::endl;
                    break;
                }

                std::cout << "Received: " << buffer->buf << std::endl;
            }
        });

        t.detach();
    });

    server.listen("127.0.0.1", 21);

    return 0;
}
