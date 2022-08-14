#pragma once

#include "sockets.h"
#include <vector>
#include <map>
#include <string>
#include <functional>

namespace exolix::net {
    /**
     * An enum representing the different types of HTTP
     * versions supported for a request or response.
     */
    enum class HttpVersion {
        /**
         * Hyper text transfer protocol version 1.0
         * by the standard.
         */
        HTTP_1_0,

        /**
         * Hyper text transfer protocol version 1.1
         * by the standard.
         */
        HTTP_1_1
    };

    /**
     * The method used to make a request to the server.
     */
    enum class HttpMethod {
        /**
         * Hyper text transfer protocol POST
         * request method from standard.
         */
        POST,

        /**
         * Hyper text transfer protocol GET
         * request method from standard.
         */
        GET,

        /**
         * Hyper text transfer protocol PUT
         * request method from standard.
         */
        PUT,

        /**
         * Hyper text transfer protocol DELETE
         * request method from standard.
         */
        DELETE,

        /**
         * No method was used to make the request,
         * possibly a request sent by the server.
         */
        NONE
    };

    /**
     * A class used for manipulating HTTP request headers
     * and rendering them to strings.
     */
    class HttpHeaders {
    private:
        /**
         * The keys for the HTTP headers, stored in a vector
         * to retain its order for when used by a map, due to STD
         * maps not being ordered.
         */
        std::vector<std::string> headersKeys {};

        /**
         * All of the HTTP headers as a key/value pair in the wrong
         * order, they can be accessed in the correct order with the help
         * of the headersKeys vector.
         */
        std::map<std::string, std::string> headersUnordered {};

        /**
         * The HTTP request path, a path may be empty if the request came
         * from the webserver.
         */
        std::string path;

        /**
         * The HTTP initial line, this line contains the HTTP version, HTTP
         * advance error code, request path and method, and more.
         */
        std::string initialLine;

        /**
         * The HTTP method used to make the request, indicating the action
         * form the server should take.
         */
        HttpMethod method = HttpMethod::NONE;

        /**
         * The HTTP version used to make the request, indicating the version
         * of the protocol used by the standard.
         */
        HttpVersion httpVersion = HttpVersion::HTTP_1_0;

        /**
         * Set the initial line variable in this class as a form of cache to save time
         * and resources, this will combine the HTTP properties into the correct format
         * to be used on top of a header.
         */
        void applyInitialLine();

    public:
        /**
         * Construct an HTTP header storage and manipulation container utility.
         */
        HttpHeaders();

        /**
         * Construct an HTTP header storage and initialie settings from a string of
         * formatted headers, possibly from an HTTP request by a client, or server log.
         * @param headersStrings The formatted HTTP request headers including the initial
         * line and all of the headers, and body.
         */
        explicit HttpHeaders(const std::string &headersStrings);

        /**
         * Set an HTTP header to the given value.
         * @param key The key of the header to set.
         * @param value The value of the header to set.
         */
        void set(const std::string& key, const std::string& value);

        /**
         * Remove an HTTP header from the headers store.
         * @param key The key of the header to remove.
         */
        void remove(const std::string& key);

        /**
         * Get an HTTP header from the headers store.
         * @param key The key of the header to get.
         * @return The value of the header, or an empty string if the header
         */
        std::string get(const std::string& key);

        /**
         * Convert this header storage into a single string by rendering
         * its settings into a usable output, this could be sent over to a client
         * and other.
         * @return The formatted HTTP request headers including the initial
         */
        std::string toString();

        /**
         * Get the HTTP version as human readable string.
         * @return The HTTP version as a human readable string.
         */
        std::string getVersionString();

        /**
         * Get the HTTP method as human readable string.
         * @return The HTTP method as a human readable string.
         */
        std::string getMethodString();

        /**
         * Get the HTTP method as the enum value.
         * @return The HTTP method as the enum value.
         */
        HttpMethod getMethod();

        /**
         * Get the HTTP version as the enum value.
         * @return The HTTP version as the enum value.
         */
        HttpVersion getVersion();

        /**
         * Get the HTTP request path retrieved from the initial line,
         * this will be an empty string if the request came from the server
         * or the client didn't supply a path.
         * @return The HTTP request path.
         */
        std::string getPath();

        /**
         * Get the HTTP request method.
         * @param inMethod The HTTP request method.
         */
        void setMethod(HttpMethod inMethod);

        /**
         * Set the HTTP initial line request version.
         * @param inVersion The HTTP request version.
         */
        void setVersion(HttpVersion inVersion);

        /**
         * Set the HTTP request path, specify an empty string if
         * a method and path will not be used.
         * @param inPath The HTTP request path.
         */
        void setPath(const std::string& inPath);

        /**
         * Get the rendered initial line.
         * @return The rendered initial line.
         */
        std::string getInitialLine();
    };

    /**
     * An HTTP response object which goes out steam to the
     * request client that can render items to the renderer
     * or apply document headers.
     */
    class HttpResponse {
    private:
        HttpHeaders *headers;

    public:
        HttpResponse(HttpHeaders *headers);
        ~HttpResponse();

        HttpHeaders *getHeaders();
    };

    /**
     * A high-level HTTP server class which can be used to
     * build HTTP API servers with the cross-platform support.
     */
    class HttpServer {
    private:
        SocketServer *server;
        std::function<void(HttpHeaders *, HttpHeaders *)> onRequest;

    public:
        explicit HttpServer(int port = 80);
        ~HttpServer();

        void bind();
        void block();

        void setOnRequest(std::function<void(HttpHeaders *, HttpHeaders *)> onRequestFn);
    };
}
