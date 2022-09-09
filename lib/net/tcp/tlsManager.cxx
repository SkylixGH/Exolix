#include "tlsManager.hxx"
#include <openssl/ssl.h>
#include <openssl/err.h>

namespace exolix {
    bool TlsManager::openSslInitialized = false;

    void TlsManager::safeInitialize() {
        if (!openSslInitialized) {
            SSL_library_init();
            SSL_load_error_strings();
            OpenSSL_add_all_algorithms();

            openSslInitialized = true;
            return;
        }
    }

    void TlsManager::cleanUp() {
        if (openSslInitialized) {
            ERR_free_strings();
            EVP_cleanup();

            openSslInitialized = false;
            return;
        }
    }
}
