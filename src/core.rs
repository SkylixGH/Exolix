/// An error implementation.
pub struct ElixorError<ErrorKeys> {
    /// The errors codes for the error.
    pub key: ErrorKeys,

    /// The descriptive reason for the error.
    pub reason: String
}

impl <ErrorKeys>ElixorError<ErrorKeys> {
    /// Create a new error.
    /// 
    /// * key - The error key.
    /// * reason - The descriptive reason for the error.
    pub fn new(key: ErrorKeys, reason: &str) -> Self {
        Self { key, reason: String::from(reason) }
    }
}
