# Hello World
file(GLOB_RECURSE EXAMPLES_FILES_HELLO_WORLD helloWorld/*.cc helloWorld/*.hh)

add_executable(helloWorld ${EXAMPLES_FILES_HELLO_WORLD})
target_link_libraries(helloWorld Exolix)

# TCP Server
file(GLOB_RECURSE EXAMPLES_FILES_TCP_SERVER tcpServer/*.cc tcpServer/*.hh)

add_executable(tcpServer ${EXAMPLES_FILES_TCP_SERVER})
target_link_libraries(tcpServer Exolix)

# HTTP Hello Server
file(GLOB_RECURSE EXAMPLES_FILES_HTTP_HELLO_SERVER httpHelloServer/*.cc httpHelloServer/*.hh)

add_executable(httpHelloServer ${EXAMPLES_FILES_HTTP_HELLO_SERVER})
target_link_libraries(httpHelloServer Exolix)
