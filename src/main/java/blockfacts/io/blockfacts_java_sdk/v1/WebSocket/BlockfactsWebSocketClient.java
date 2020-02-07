package blockfacts.io.blockfacts_java_sdk.v1.WebSocket;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.util.List;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;
import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import blockfacts.io.blockfacts_java_sdk.v1.Models.BlockfactsChannelObject;
import blockfacts.io.blockfacts_java_sdk.v1.Models.BlockfactsSubscribeMessage;

public class BlockfactsWebSocketClient extends WebSocketClient {
	public String key;
	public String secret;
	public String blockfactsWebsocketUrl;
	
	public BlockfactsWebSocketClient(URI serverUri) {
		super(serverUri);
	}
	
	public BlockfactsWebSocketClient(String key, String secret) throws URISyntaxException {
		super(new URI("wss://ws.blockfacts.io/v1/"));
		this.key = key;
		this.secret = secret;
	}
	
	
	@Override
	public void onOpen(ServerHandshake handshakedata) {
		System.out.println("New connection opened");
	}
	
	@Override
	public void onMessage(String message) {
		System.out.println("Received message: " + message);
	}

	@Override
	public void onClose(int code, String reason, boolean remote) {
		System.out.println("Closed with exit code " + code + " additional info: " + reason);
	}

	@Override
	public void onError(Exception ex) {
		System.err.println("An error occurred:" + ex);
	}
	
	/**
	 * Connects to BlockFacts websocket server in a blocking way.
	 */
	public void ConnectBlocking() {
		try {
			connectBlocking();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Connects to BlockFacts websocket server in a non-blocking way;
	 */
	public void Connect() {
		connect();
	}
	
	/**
	 * Subscribe method used for subscribing to BlockFacts real-time crypto data stream.
	 * @param channels List of BlockfactsChannelObjects to subscribe to
	 */
	public void Subscribe(List<BlockfactsChannelObject> channels, Boolean snapshot, String id) {
		BlockfactsSubscribeMessage subscribeMessage = new BlockfactsSubscribeMessage();
		
		subscribeMessage.type = "subscribe";
		subscribeMessage.snapshot = snapshot;
		if(id != "" || id != null) subscribeMessage.id = id;
		subscribeMessage.X_API_KEY = this.key;
        subscribeMessage.X_API_SECRET = this.secret;
        subscribeMessage.channels = channels;
        
        String subscribeMsg = new Gson().toJson(subscribeMessage).replace("X_API_KEY", "X-API-KEY").replace("X_API_SECRET", "X-API-SECRET").replace("exchangeName", "name");
        send(subscribeMsg);
	}
	
	/**
	 * Raw subscribe method used for subscribing to BlockFacts real-time crypto data stream.
	 * Reference: Reference: https://docs.blockfacts.io/#subscribe
	 * @param message Json string containing the subscribe message
	 */
	public void SubscribeRaw(String message) {
		send(message);
	}
	
	/**
	 * Unsubscribe method used to unsubscribe from certain channels or pairs.
	 * @param channels List of BlockfactsChannelObjects to unsubscribe from
	 */
	public void Unsubscribe(List<BlockfactsChannelObject> channels) {
		BlockfactsSubscribeMessage subscribeMessage = new BlockfactsSubscribeMessage();
		
		subscribeMessage.type = "unsubscribe";
        subscribeMessage.channels = channels;
        
        String subscribeMsg = new Gson().toJson(subscribeMessage).replace("exchangeName", "name");
        send(subscribeMsg);
	}
	
	/**
	 * Raw unsubscribe method used to unsubscribe from certain channels or pairs.
	 * Reference: https://docs.blockfacts.io/#unsubscribe
	 * @param message Json string containing the unsubscribe message
	 */
	public void UnsubscribeRaw(String message) {
		send(message);
	}
	
	/**
	 * Sends a ping type message to the server to determine if the server is online.
	 * Reference: https://docs.blockfacts.io/#ping
	 */
	public void Ping() {
		JSONObject ping = new JSONObject();
		ping.put("type", "ping");
		
		String pingMsg = new Gson().toJson(ping);
		send(pingMsg);
	}
	
	/**
	 * Sends a pong type message to the server to let the server know that the client is still connected.
	 * Reference: https://docs.blockfacts.io/#pong
	 */
	public void Pong() {
		JSONObject pong = new JSONObject();
		pong.put("type", "pong");
		
		String pongMsg = new Gson().toJson(pong);
		send(pongMsg);
	}
}
