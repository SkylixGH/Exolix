use elixor::sockets::{SocketProtocol, SocketServerBuilder};

fn main() {
	let mut server = SocketServerBuilder::new("localhost", 8080).protocol(SocketProtocol::Tcp).build();
	if let Err(error) = server.bind() {
		println!("Failed: {:?}", error);
	}
}
