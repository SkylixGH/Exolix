pub struct ElixorError<ErrorKeys> {
    /// The errors codes for the error.
    pub key: ErrorKeys,

    /// The descriptive reason for the error.
    pub reason: String
}