package blockfacts.io.blockfacts_java_sdk.v1.REST;

import org.json.simple.JSONObject;

public class Endpoints {
	public String key;
	public String secret;
	public JSONObject headers;
	public String blockfactsApiUrl;
	
	public Endpoints(String key, String secret) {
		this.blockfactsApiUrl = "https://api.blockfacts.io";
		this.key = key;
		this.secret = secret;
		this.headers = new JSONObject();
		this.headers.put("Content-Type", "application/json");
		this.headers.put("X-API-KEY", this.key);
		this.headers.put("X-API-SECRET", this.secret);
	}
}
