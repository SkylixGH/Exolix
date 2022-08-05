use std::net::TcpListener;

use crate::core::ElixorError;

/// The types of protocols the SocketServer can run on.
pub enum SocketProtocol {
    /// The SocketServer will run on a TCP protocol.
    Tcp,
    /// The SocketServer will run on a UDP protocol.
    Udp
}

/// All possible server error codes.
pub enum SocketErrors {
    /// The server was called to bind again while already busy with bindings or unbinding.
    BindJobCalledWhileBusy
}

/// A bidirectional socket server.
pub struct SocketServer<'a> {
    /// The host name to bind to.
    pub host_name: &'a str,

    /// The port to bind to.
    pub port: u16,
    
    /// The protocol the server is running on.
    pub protocol: SocketProtocol,

    /// Whether the server is online.
    online: bool,

    /// If the server is starting or stopping.
    working: bool,
}

impl<'a> SocketServer<'a> {
    /// This create a new socket server.
    /// 
    /// * host_name - The host name for the server to bind to.
    /// * port - The port for the server to bind to.
    pub fn new(host_name: &'a str, port: u16) -> Self {
        Self { host_name, port, protocol: SocketProtocol::Tcp, online: false, working: false }
    }

    /// This binds the server to the host name and port.
    pub fn bind(&mut self) -> Result<(), ElixorError<SocketErrors>> {
        let job = if self.working && self.online { "stopping" } else { "starting" };

        Err(ElixorError {
            key: SocketErrors::BindJobCalledWhileBusy,
            reason: &format!("The server is currently busy because it is {}", job)
        })
    }
}
