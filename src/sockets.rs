use std::net::TcpListener;

use thiserror::Error;

/// The types of protocols the SocketServer can run on.
#[derive(Debug, Clone, Copy, PartialEq, Eq)]
pub enum SocketProtocol {
	/// The SocketServer& will run on a TCP protocol.
	Tcp,
	/// The SocketServer will run on a UDP protocol.
	Udp
}

/// All possible server error codes.
#[derive(Error, Debug)]
pub enum SocketError {
	/// The server was called to bind again while already busy with bindings or unbinding.
	#[error("Attempted to bind while the server is busy because it is {state}.")]
	BindJobCalledWhileBusy { state: String },

	/// Attempted to modify settings while the server is online.
	#[error("Attempted to modify settings while the server is online.")]
	SettingsModifiedWhileOnline,

	/// The server was called to bind while already bound and online.
	#[error("Attempted to bind while the server is online.")]
	AreadyOnline
}

/// A bidirectional socket server.
#[derive(Debug, Clone)]
pub struct SocketServer {
	/// The host name to bind to.
	pub host_name: String,

	/// The port to bind to.
	pub port: u16,

	/// The protocol the server is running on.
	protocol: SocketProtocol,

	/// Whether the server is online.
	online: bool,

	/// If the server is starting or stopping.
	working: bool
}

impl SocketServer {
	pub(crate) fn new(host_name: String, port: u16, protocol: SocketProtocol) -> Self {
		Self {
			host_name,
			port,
			protocol,
			online: false,
			working: false
		}
	}

	/// Check to see if the server is online.
	pub fn is_online(&mut self) -> bool {
		self.online
	}

	/// This binds the server to the host name and port.
	pub fn bind(&mut self) -> Result<(), SocketError> {
		if self.working {
			return Err(SocketError::BindJobCalledWhileBusy {
				state: if self.working && self.online { "stopping" } else { "starting" }.into()
			});
		}

		if self.online {}

		self.online = true;

		Ok(())
	}
}

/// Constructs a bidirectional socket server.
pub struct SocketServerBuilder {
	host_name: String,
	port: u16,
	protocol: SocketProtocol
}

impl SocketServerBuilder {
	/// Create a new SocketServerBuilder.
	///
	/// * host_name - The host name for the server to bind to.
	/// * port - The port for the server to bind to.
	pub fn new<S>(host_name: S, port: u16) -> Self
	where
		S: AsRef<str>
	{
		Self {
			host_name: host_name.as_ref().to_string(),
			port,
			protocol: SocketProtocol::Tcp
		}
	}

	/// Set the hosting protocol for the server.
	///
	/// * protocol - The protocol to run the server on.
	pub fn protocol(&mut self, protocol: SocketProtocol) -> &mut Self {
		self.protocol = protocol;
		self
	}

	/// Build the server.
	pub fn build(&self) -> SocketServer {
		SocketServer::new(self.host_name.clone(), self.port, self.protocol)
	}
}
