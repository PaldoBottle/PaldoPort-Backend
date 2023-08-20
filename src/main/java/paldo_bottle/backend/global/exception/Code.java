package paldo_bottle.backend.global.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Code {
    /**
     * The request succeeded. The result meaning of "success" depends on the HTTP method:
     *
     * GET: The resource has been fetched and transmitted in the message body.
     * HEAD: The representation headers are included in the response without any message body.
     * PUT or POST: The resource describing the result of the action is transmitted in the message body.
     * TRACE: The message body contains the request message as received by the server.
     */
    OK(200),
    /**
     * The request succeeded, and a new resource was created as a result.
     * This is typically the response sent after POST requests, or some PUT requests.
     */
    CREATED(201),
    /**
     * The server cannot or will not process the request due to something that is perceived to be a client error
     * (e.g., malformed request syntax, invalid request message framing, or deceptive request routing).
     */
    BAD_REQUEST(400),

    /**
     * Although the HTTP standard specifies "unauthorized", semantically this response means "unauthenticated".
     * That is, the client must authenticate itself to get the requested response.
     */
    UNAUTHORIZED(401),

    /**
     * The client does not have access rights to the content; that is, it is unauthorized,
     * so the server is refusing to give the requested resource.
     * Unlike 401 Unauthorized, the client's identity is known to the server.
     */
    FORBIDDEN(403),
    /**
     * The server cannot find the requested resource.
     * In the browser, this means the URL is not recognized.
     * In an API, this can also mean that the endpoint is valid but the resource itself does not exist.
     * Servers may also send this response instead of 403 Forbidden to hide the existence of a resource from an unauthorized client.
     * This response code is probably the most well known due to its frequent occurrence on the web.
     */
    NOT_FOUND(404),

    /**
     * The request method is known by the server but is not supported by the target resource.
     * For example, an API may not allow calling DELETE to remove a resource.
     */
    METHOD_NOT_ALLOWED(405),

    /**
     * This response is sent when the web server,
     * after performing server-driven content negotiation,
     * doesn't find any content that conforms to the criteria given by the user agent.
     */
    NOT_ACCEPTABLE(406),

    /**
     * This is similar to 401 Unauthorized but authentication is needed to be done by a proxy.
     */
    PROXY_AUTHENTICATION_REQUIRED(407),

    // REQUEST_TIMEOUT
    // CONFLICT
    // GONE
    // LENGTH_REQUIRED
    // PRECONDITION FAILED
    // PAYLOAD TOO LARGE
    // URI TOO LONG
    // UNSUPPORTED MEDIA TYPE
    // RANGE NOT SATISFIABLE
    // EXPECTATION FAILED
    // IM A TEAPOT
    // MISDIRECTED REQUEST
    // UPGRADE_REQUIRED
    // PRECONDITION REQUIRED
    // TOO MANY REQUESTS
    // REQUEST HEADER FIELDS TOO LARGE
    // UNAVAILABLE FOR LEGAL REASON

    // server error
    /**
     * The server has encountered a situation it does not know how to handle.
     */
    INTERNAL_SERVER_ERROR(500),

    /**
     * The request method is not supported by the server and cannot be handled.
     * The only methods that servers are required to support (and therefore that must not return this code) are GET and HEAD.
     */
    NOT_IMPLEMENTED(501),

    /**
     * This error response means that the server,
     * while working as a gateway to get a response needed to handle the request, got an invalid response.
     */
    BAD_GATEWAY(502);

    //    SERVICE_UNAVAILABLE(503),

    private final int value;
}
