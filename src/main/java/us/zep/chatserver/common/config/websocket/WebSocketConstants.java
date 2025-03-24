package us.zep.chatserver.common.config.websocket;

public final class WebSocketConstants {
	public static final String WS_ENDPOINT = "/ws";
	public static final String ALLOWED_ORIGIN = "*";
	public static final String APPLICATION_DESTINATION_PREFIX = "/app";
	public static final String SIMPLE_BROKER_PREFIX = "/topic";

	public static final String DISCONNECT_DESTINATION = "/queue/disconnect";

	public static final String DISCONNECT_PAYLOAD = "DISCONNECT";


	private WebSocketConstants() {
	}
}