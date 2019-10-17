package blockfacts.io.blockfacts_java_sdk.v1.REST;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import blockfacts.io.blockfacts_java_sdk.v1.Models.*;

public class BlockfactsEndpoints extends Endpoints {
	public HttpClient restClient;
	public HttpRequest restRequest;
	
	public BlockfactsEndpoints(String key, String secret) {
		super(key, secret);
		this.restClient = HttpClient.newHttpClient();
	}
	
	/**
	 * Lists all exchanges that go into the normalization for specific asset-denominator pair.
	 * Reference: https://docs.blockfacts.io/?java#exchanges-in-normalization
	 * @param pairs Asset-denominator pairs (e.g. BTC-USD, BTC-EUR)
	 * @return JsonObject
	 */
	public JsonObject GetExchangesInNormalization(String pairs) {
		pairs = pairs.trim().replace(" ", "");
		
		JsonObject responseData = null;
		
		restRequest = HttpRequest.newBuilder()
	      	      .uri(URI.create(this.blockfactsApiUrl + "/api/v1/blockfacts/normalization/whitelist/" + pairs))
	      	      .header("Content-Type", this.headers.get("Content-Type").toString())
	      	      .header("X-API-KEY", this.headers.get("X-API-KEY").toString())
	      	      .header("X-API-SECRET", this.headers.get("X-API-SECRET").toString())
	      	      .build();
		
		HttpResponse<String> response;
		  try {
			response = restClient.send(restRequest, BodyHandlers.ofString());
	        responseData = new JsonParser().parse(response.body()).getAsJsonObject();
		  } catch (IOException e) {
			e.printStackTrace();
		  } catch (InterruptedException e) {
			e.printStackTrace();
		  }
		  
		  return responseData;
	}
	
	/**
	 * Gets current normalization data for specific asset-denominator pair.
	 * Reference: https://docs.blockfacts.io/?java#current-data
	 * @param assets Asset tickers (e.g. BTC, ETH)
	 * @param denominators Denominator tickers (e.g. USD, EUR)
	 * @return JsonObject
	 */
	public JsonObject GetCurrentData(String assets, String denominators) {
		assets = assets.trim().replace(" ", "");
		denominators = denominators.trim().replace(" ", "");
		
		JsonObject responseData = null;
		
		restRequest = HttpRequest.newBuilder()
	      	      .uri(URI.create(this.blockfactsApiUrl + "/api/v1/blockfacts/price?asset=" + assets + "&denominator=" + denominators))
	      	      .header("Content-Type", this.headers.get("Content-Type").toString())
	      	      .header("X-API-KEY", this.headers.get("X-API-KEY").toString())
	      	      .header("X-API-SECRET", this.headers.get("X-API-SECRET").toString())
	      	      .build();
		
		HttpResponse<String> response;
		  try {
			response = restClient.send(restRequest, BodyHandlers.ofString());
	        responseData = new JsonParser().parse(response.body()).getAsJsonObject();
		  } catch (IOException e) {
			e.printStackTrace();
		  } catch (InterruptedException e) {
			e.printStackTrace();
		  }
		  
		  return responseData;
	}
	
	/**
	 * Gets historical normalization data by asset-denominator, date, time and interval.
	 * Reference: https://docs.blockfacts.io/?java#historical-data
	 * @param asset Asset ticker (e.g. BTC)
	 * @param denominator Denominator ticker (e.g. USD)
	 * @param date Specific date (e.g. 2.8.2019)
	 * @param time Specific time (e.g. 14:01:00)
	 * @param interval Historical interval to cover (e.g. 20 = 14:01:00 - 14:21:00) (Min 0, Max 240)
	 * @param page Optional, our API is always showing 100 results per page in order to improve the performance. You can provide the page parameter in order to query a specific page
	 * @return BlockfactsHistoricalNormalizationResultsModel
	 */
	public BlockfactsHistoricalNormalizationResultsModel GetHistoricalData(String asset, String denominator, String date, String time, int interval, int page) {
		BlockfactsHistoricalNormalizationResultsModel responseData = null;
		
		restRequest = HttpRequest.newBuilder()
      	      .uri(URI.create(this.blockfactsApiUrl + "/api/v1/blockfacts/price/historical?asset=" + asset + "&denominator=" + denominator + "&date=" + date + "&time=" + time + "&interval=" + interval + "&page=" + page))
      	      .header("Content-Type", this.headers.get("Content-Type").toString())
      	      .header("X-API-KEY", this.headers.get("X-API-KEY").toString())
      	      .header("X-API-SECRET", this.headers.get("X-API-SECRET").toString())
      	      .build();
		
		HttpResponse<String> response;
		  try {
			response = restClient.send(restRequest, BodyHandlers.ofString());
	        JsonObject jsonObj = new JsonParser().parse(response.body()).getAsJsonObject();
	        responseData = new Gson().fromJson(jsonObj, BlockfactsHistoricalNormalizationResultsModel.class);
		  } catch (IOException e) {
			e.printStackTrace();
		  } catch (InterruptedException e) {
			e.printStackTrace();
		  }
		  
		  return responseData;
	}
	
	/**
	 * Get historical normalized price by specific point in time.
	 * Reference: https://docs.blockfacts.io/?java#specific-historical-data
	 * @param asset Asset ticker (e.g. BTC)
	 * @param denominator Denominator ticker (e.g. USD)
	 * @param date Specific date (e.g. 2.9.2019)
	 * @param time Specific time (e.g. 14:00:00)
	 * @return BlockfactsNormalizationModel
	 */
	public BlockfactsNormalizationModel GetSpecificHistoricalData(String asset, String denominator, String date, String time) {
		BlockfactsNormalizationModel responseData = null;
		
		restRequest = HttpRequest.newBuilder()
      	      .uri(URI.create(this.blockfactsApiUrl + "/api/v1/blockfacts/price/specific?asset=" + asset + "&denominator=" + denominator + "&date=" + date + "&time=" + time))
      	      .header("Content-Type", this.headers.get("Content-Type").toString())
      	      .header("X-API-KEY", this.headers.get("X-API-KEY").toString())
      	      .header("X-API-SECRET", this.headers.get("X-API-SECRET").toString())
      	      .build();
		
		HttpResponse<String> response;
		  try {
			response = restClient.send(restRequest, BodyHandlers.ofString());
	        JsonObject jsonObj = new JsonParser().parse(response.body()).getAsJsonObject();
	        responseData = new Gson().fromJson(jsonObj, BlockfactsNormalizationModel.class);
		  } catch (IOException e) {
			e.printStackTrace();
		  } catch (InterruptedException e) {
			e.printStackTrace();
		  }
		  
		  return responseData;
	}
	
	/**
	 * Get all running normalization pairs. Resulting in which asset-denominator pairs are currently being normalized inside our internal system.
	 * Reference: https://docs.blockfacts.io/?java#normalization-pairs
	 * @return List of BlockfactsRunningNormalizationPairsTradesModels
	 */
	public List<BlockfactsRunningNormalizationPairsTradesModel> GetNormalizationPairs() {
		List<BlockfactsRunningNormalizationPairsTradesModel> responseData = null;
		
		restRequest = HttpRequest.newBuilder()
      	      .uri(URI.create(this.blockfactsApiUrl + "/api/v1/blockfacts/normalization/trades"))
      	      .header("Content-Type", this.headers.get("Content-Type").toString())
      	      .header("X-API-KEY", this.headers.get("X-API-KEY").toString())
      	      .header("X-API-SECRET", this.headers.get("X-API-SECRET").toString())
      	      .build();
		
		  HttpResponse<String> response;
		  try {
			response = restClient.send(restRequest, BodyHandlers.ofString());
 	        JsonArray jsonObj = new JsonParser().parse(response.body()).getAsJsonArray();
	        responseData = new Gson().fromJson(jsonObj, new TypeToken<List<BlockfactsRunningNormalizationPairsTradesModel>>(){}.getType());
		  } catch (IOException e) {
			e.printStackTrace();
		  } catch (InterruptedException e) {
			e.printStackTrace();
		  }
		  
		  return responseData;
	}
	
	/**
	 * Get normalized end of day data for specific asset-denominator.
	 * Reference: https://docs.blockfacts.io/?java#end-of-day-data
	 * @param asset Asset ticker (e.g. BTC)
	 * @param denominator Denominator ticker (e.g. USD)
	 * @param length Length (representing how many days back from the current day, Min = 0, Max = 20)
	 * @return List of BlockfactsEndOfDayModels
	 */
	public List<BlockfactsEndOfDayModel> GetEndOfDayData(String asset, String denominator, int length) {
		List<BlockfactsEndOfDayModel> responseData = null;
		
		restRequest = HttpRequest.newBuilder()
      	      .uri(URI.create(this.blockfactsApiUrl + "/api/v1/blockfacts/price/endOfDay?asset=" + asset + "&denominator=" + denominator + "&length=" + length))
      	      .header("Content-Type", this.headers.get("Content-Type").toString())
      	      .header("X-API-KEY", this.headers.get("X-API-KEY").toString())
      	      .header("X-API-SECRET", this.headers.get("X-API-SECRET").toString())
      	      .build();
		
		  HttpResponse<String> response;
		  try {
			response = restClient.send(restRequest, BodyHandlers.ofString());
 	        JsonArray jsonObj = new JsonParser().parse(response.body()).getAsJsonArray();
	        responseData = new Gson().fromJson(jsonObj, new TypeToken<List<BlockfactsEndOfDayModel>>(){}.getType());
		  } catch (IOException e) {
			e.printStackTrace();
		  } catch (InterruptedException e) {
			e.printStackTrace();
		  }
		  
		  return responseData;
	}
}
