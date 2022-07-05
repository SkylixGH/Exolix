const tcp = require("net");

const server = tcp.createServer(socket => {
    socket.on("data", data => {
        console.log(data.toString());
    });
});

server.listen(8080);
