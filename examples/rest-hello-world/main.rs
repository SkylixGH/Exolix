use elixor::sockets::{SocketProtocol, SocketServer};

fn main() {
    let mut server = SocketServer::new("localhost", 8080);
    
    server.bind();
    if let Err(error) = server.bind() {
        println!("Failed {}", error.reason);
    }
}
