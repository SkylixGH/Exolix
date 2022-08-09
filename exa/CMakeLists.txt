# Hello World
file(GLOB_RECURSE EXAMPLES_FILES_HELLO_WORLD helloWorld/*.cc helloWorld/*.hh)
add_executable(helloWorld ${EXAMPLES_FILES_HELLO_WORLD})

# TCP Server
file(GLOB_RECURSE EXAMPLES_FILES_TCP_SERVER tcpServer/*.cc tcpServer/*.hh)
add_executable(tcpServer ${EXAMPLES_FILES_TCP_SERVER})
