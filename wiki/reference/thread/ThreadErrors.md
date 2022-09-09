# Enum: `ThreadErrors`
- **Include**: `<exolix>`
- **Location**: `lib/thread/thread.h`

A list of possible error codes and a success code for the threading system.

# Values
 - `0` `Ok` No errors has occurred.
 - `1` `ThreadAlreadyBlocked` The thread is already blocked when trying to block it again.
 - `2` `ThreadNotActive` An operation was performed that required an active thread, but the thread was dead.
