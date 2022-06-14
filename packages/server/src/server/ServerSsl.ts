type ServerSsl = false | {
    /**
     * The SSL certificate key.
     */
    key: string;

    /**
     * The SSL certificate.
     */
    cert: string;
}

export default ServerSsl;
