#include <exolix/http.h>

using namespace exolix::http;

int main() {
    HttpServer server;

    server.listen(3000);
    server.block();
    
    return 0;
}
