use std::net::TcpListener;

use crate::core::ElixorError;

/// The types of protocols the SocketServer can run on.
pub enum SocketProtocol {
    /// The SocketServer& will run on a TCP protocol.
    Tcp,
    /// The SocketServer will run on a UDP protocol.
    Udp
}

/// All possible server error codes.
pub enum SocketErrors {
    /// The server was called to bind again while already busy with bindings or unbinding.
    BindJobCalledWhileBusy,

    /// The server was called to bind while already bound and online.
    AreadyOnline
}

/// A bidirectional socket server.
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
    working: bool,
}

impl SocketServer {
    /// This create a new socket server.
    /// 
    /// * host_name - The host name for the server to bind to.
    /// * port - The port for the server to bind to.
    pub fn new(host_name: String, port: u16) -> Self {
        Self { host_name, port, protocol: SocketProtocol::Tcp, online: false, working: false }
    }

    /// Set the hosting protocol for the server.
    /// 
    /// * protocol - The protocol to run the server on.
    pub fn set_protocol(&mut self, protocol: SocketProtocol) {
        self.crash();

        self.protocol = protocol;
    }

    /// Set the server host name.
    /// 
    /// * host_name - The host name to bind to.
    pub fn set_host(&mut self, host_name: String) {
        self.crash();

        self.host_name = host_name;
    }

    /// Set the server port.
    /// 
    /// * port - The port to bind to. Use 0 for no port.
    pub fn set_port(&mut self, port: u16) {
        self.crash();

        self.port = port;
    }

    /// Check to see if the server is online.
    pub fn is_online(&mut self) -> bool {
        return self.online;
    }
    
    /// Crash the server if it is running.
    fn crash(&mut self) {
        if self.online {
            panic!("Cannot modify server bind settings while online.");
        }
    }

    /// This binds the server to the host name and port.
    pub fn bind(&mut self) -> Result<(), ElixorError<SocketErrors>> {
        if self.working {
            return Err(ElixorError::new(
                SocketErrors::BindJobCalledWhileBusy,
                format!("The server is currently busy because it is {}", if self.working && self.online { "stopping" } else { "starting" }) 
            ));
        }

        if self.online {

        }

        self.online = true;

        Ok(())
    }
}
