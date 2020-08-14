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
	 * Gets last 600 BLOCKFACTS normalized prices for provided asset-denominator pairs.
	 * Reference: https://docs.blockfacts.io/?java#data-snapshot
	 * @param assets Asset tickers (e.g. BTC, ETH)
	 * @param denominators Denominator tickers (e.g. USD, EUR)
	 * @return JsonObject
	 */
	public JsonObject GetSnapshotData(String assets, String denominators) {
		assets = assets.trim().replace(" ", "");
		denominators = denominators.trim().replace(" ", "");
		
		JsonObject responseData = null;
		
		restRequest = HttpRequest.newBuilder()
	      	      .uri(URI.create(this.blockfactsApiUrl + "/api/v1/blockfacts/price/snapshot?asset=" + assets + "&denominator=" + denominators))
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
	 * Gets the snapshot of Blockfacts OHLCV data for provided asset-denominator pairs and intervals.
	 * Reference: https://docs.blockfacts.io/?java#data-snapshot-ohlcv-blockfacts
	 * @param assets Asset tickers (e.g. BTC, ETH)
	 * @param denominators Denominator tickers (e.g. USD, EUR)
	 * @param intervals Intervals (e.g. 1m, 3m, 1h)
	 * @return JsonObject
	 */
	public JsonObject GetOHLCVSnapshotData(String assets, String denominators, String intervals) {
		assets = assets.trim().replace(" ", "");
		denominators = denominators.trim().replace(" ", "");
		intervals = intervals.trim().replace(" ", "");
		
		JsonObject responseData = null;
		
		restRequest = HttpRequest.newBuilder()
	      	      .uri(URI.create(this.blockfactsApiUrl + "/api/v1/blockfacts/price/ohlcv-snapshot?asset=" + assets + "&denominator=" + denominators + "&interval=" + intervals))
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
	 * Gets historical OHLCV data by asset-denominator, date, time and interval.
	 * Reference: https://docs.blockfacts.io/?java#ohlcv-historical-data
	 * @param asset Asset ticker (e.g. BTC)
	 * @param denominator Denominator ticker (e.g. USD)
	 * @param interval 	OHLCV Interval (30s, 1m, 3m, 5m, 15m, 30m, 1h, 2h, 4h, 6h, 12h, 1d, 1w, 1mo)
	 * @param dateStart Specific date to start from (e.g. 5.8.2020)
	 * @param timeStart Specific time to start from (in UTC) (e.g. 14:00:00)
	 * @param dateEnd Specific end date (e.g. 5.8.2020)
	 * @param timeEnd Specific end time (in UTC) (e.g. 14:00:00)
	 * @param page Optional, our API is always showing 100 results per page in order to improve the performance. You can provide the page parameter in order to query a specific page
	 * @return JsonObject
	 */
	public JsonObject GetHistoricalOHLCVData(String asset, String denominator, String interval, String dateStart, String timeStart, String dateEnd, String timeEnd, int page) {
		JsonObject responseData = null;
		
		restRequest = HttpRequest.newBuilder()
      	      .uri(URI.create(this.blockfactsApiUrl + "/api/v1/blockfacts/ohlcv?asset=" + asset + "&denominator=" + denominator + "&interval=" + interval + "&dateStart=" + dateStart + "&timeStart=" + timeStart + "&dateEnd=" + dateEnd + "&timeEnd=" + timeEnd + "&page=" + page))
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
	 * Gets the moving percentage, and difference in price over a certain time period.
	 * Reference: https://docs.blockfacts.io/?java#period-movers
	 * @param denominator Specific date (e.g. 11.8.2020)
	 * @param date Denominator ticker (e.g. USD)
	 * @param interval Interval (oneDay, sevenDay, thirtyDay, ninetyDay, oneYear, twoYear, threeYear, fiveYear)
	 * @param sort 1 - Losers first, -1 - Winners first
	 * @return JsonArray
	 */
	public JsonArray GetPeriodMovers(String denominator, String date, String interval, int sort) {
		JsonArray responseData = null;
		
		restRequest = HttpRequest.newBuilder()
	      	      .uri(URI.create(this.blockfactsApiUrl + "/api/v1/blockfacts/period-movers?denominator=" + denominator + "&date=" + date + "&interval=" + interval + "&sort=" + sort))
	      	      .header("Content-Type", this.headers.get("Content-Type").toString())
	      	      .header("X-API-KEY", this.headers.get("X-API-KEY").toString())
	      	      .header("X-API-SECRET", this.headers.get("X-API-SECRET").toString())
	      	      .build();
		
		HttpResponse<String> response;
		  try {
			response = restClient.send(restRequest, BodyHandlers.ofString());
	        responseData = new JsonParser().parse(response.body()).getAsJsonArray();
		  } catch (IOException e) {
			e.printStackTrace();
		  } catch (InterruptedException e) {
			e.printStackTrace();
		  }
		  
		  return responseData;
	}
	
}
