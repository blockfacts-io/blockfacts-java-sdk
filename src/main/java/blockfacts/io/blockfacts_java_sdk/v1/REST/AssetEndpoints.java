package blockfacts.io.blockfacts_java_sdk.v1.REST;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;

import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import blockfacts.io.blockfacts_java_sdk.v1.Models.*;

public class AssetEndpoints extends Endpoints {
	public HttpClient restClient;
	public HttpRequest restRequest;
	
	public AssetEndpoints(String key, String secret) {
		super(key, secret);
		this.restClient = HttpClient.newHttpClient();
	}
	
	/**
	 * Lists all assets that we support.
	 * Reference: https://docs.blockfacts.io/?java#list-all-assets
	 * @return List of BlockfactsAssetModel
	 */
	public List<BlockfactsAssetModel> ListAllAssets() {
		List<BlockfactsAssetModel> responseData = null;
		
		restRequest = HttpRequest.newBuilder()
      	      .uri(URI.create(this.blockfactsApiUrl + "/api/v1/assets"))
      	      .header("Content-Type", this.headers.get("Content-Type").toString())
      	      .header("X-API-KEY", this.headers.get("X-API-KEY").toString())
      	      .header("X-API-SECRET", this.headers.get("X-API-SECRET").toString())
      	      .build();
		
		  HttpResponse<String> response;
		  try {
			response = restClient.send(restRequest, BodyHandlers.ofString());
 	        JsonArray jsonObj = new JsonParser().parse(response.body()).getAsJsonArray();
	        responseData = new Gson().fromJson(jsonObj, new TypeToken<List<BlockfactsAssetModel>>(){}.getType());
		  } catch (IOException e) {
			e.printStackTrace();
		  } catch (InterruptedException e) {
			e.printStackTrace();
		  }
		  
		  return responseData;
	}
	
	/**
	 * Gets specific asset by ticker ID.
	 * Reference: https://docs.blockfacts.io/?java#specific-asset
	 * @param tickerId BlockFacts asset ticker (e.g. BTC)
	 * @return BlockfactsAssetModel
	 */
	public BlockfactsAssetModel GetSpecificAsset(String tickerId) {
		BlockfactsAssetModel responseData = null;
		
		restRequest = HttpRequest.newBuilder()
      	      .uri(URI.create(this.blockfactsApiUrl + "/api/v1/assets/" + tickerId))
      	      .header("Content-Type", this.headers.get("Content-Type").toString())
      	      .header("X-API-KEY", this.headers.get("X-API-KEY").toString())
      	      .header("X-API-SECRET", this.headers.get("X-API-SECRET").toString())
      	      .build();
		
		HttpResponse<String> response;
		  try {
			response = restClient.send(restRequest, BodyHandlers.ofString());
	        JsonObject jsonObj = new JsonParser().parse(response.body()).getAsJsonObject();
	        responseData = new Gson().fromJson(jsonObj, BlockfactsAssetModel.class);
		  } catch (IOException e) {
			e.printStackTrace();
		  } catch (InterruptedException e) {
			e.printStackTrace();
		  }
		  
		  return responseData;
	}
}
