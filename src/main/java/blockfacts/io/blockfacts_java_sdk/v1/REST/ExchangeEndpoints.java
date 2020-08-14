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

public class ExchangeEndpoints extends Endpoints {
	public HttpClient restClient;
	public HttpRequest restRequest;
	
	public ExchangeEndpoints(String key, String secret) {
		super(key, secret);
		this.restClient = HttpClient.newHttpClient();
	}
	
	/**
	 * Lists all exchanges that we support.
	 * Reference: https://docs.blockfacts.io/?java#all-exchanges
	 * @return List of BlockfactsExchangeDataModels
	 */
	public List<BlockfactsExchangeDataModel> ListAllExchanges() {
		List<BlockfactsExchangeDataModel> responseData = null;
		
		restRequest = HttpRequest.newBuilder()
      	      .uri(URI.create(this.blockfactsApiUrl + "/api/v1/exchanges"))
      	      .header("Content-Type", this.headers.get("Content-Type").toString())
      	      .header("X-API-KEY", this.headers.get("X-API-KEY").toString())
      	      .header("X-API-SECRET", this.headers.get("X-API-SECRET").toString())
      	      .build();
		
		  HttpResponse<String> response;
		  try {
			response = restClient.send(restRequest, BodyHandlers.ofString());
 	        JsonArray jsonObj = new JsonParser().parse(response.body()).getAsJsonArray();
	        responseData = new Gson().fromJson(jsonObj, new TypeToken<List<BlockfactsExchangeDataModel>>(){}.getType());
		  } catch (IOException e) {
			e.printStackTrace();
		  } catch (InterruptedException e) {
			e.printStackTrace();
		  }
		  
		  return responseData;
	}
	
	/**
	 * Gets information about a specific exchange by its name. Returns information such as which assets are supported, asset ticker info, etc.
	 * Reference: https://docs.blockfacts.io/?java#specific-exchange-data
	 * @param exchange Name of the exchange (e.g. KRAKEN)
	 * @return BlockfactsExchangeDataModel
	 */
	public BlockfactsExchangeDataModel GetSpecificExchangeData(String exchange) {
		BlockfactsExchangeDataModel responseData = null;
		
		restRequest = HttpRequest.newBuilder()
      	      .uri(URI.create(this.blockfactsApiUrl + "/api/v1/exchanges/" + exchange))
      	      .header("Content-Type", this.headers.get("Content-Type").toString())
      	      .header("X-API-KEY", this.headers.get("X-API-KEY").toString())
      	      .header("X-API-SECRET", this.headers.get("X-API-SECRET").toString())
      	      .build();
		
		HttpResponse<String> response;
		  try {
			response = restClient.send(restRequest, BodyHandlers.ofString());
	        JsonObject jsonObj = new JsonParser().parse(response.body()).getAsJsonObject();
	        responseData = new Gson().fromJson(jsonObj, BlockfactsExchangeDataModel.class);
		  } catch (IOException e) {
			e.printStackTrace();
		  } catch (InterruptedException e) {
			e.printStackTrace();
		  }
		  
		  return responseData;
	}
	
	/**
	 * Gets the Blockfacts pair representation of the provided exchange pair.
	 * Reference: https://docs.blockfacts.io/?java#pair-info
	 * @param exchange Name of the exchange (e.g. KRAKEN)
	 * @param pair Pair name query on the provided exchange (e.g. BTCUSD or XBTUSD)
	 * @return JsonObject
	 */
	public JsonObject GetPairInfo(String exchange, String pair) {
		JsonObject responseData = null;
		
		restRequest = HttpRequest.newBuilder()
	      	      .uri(URI.create(this.blockfactsApiUrl + "/api/v1/exchanges/pair-info?exchange=" + exchange + "&pair=" + pair))
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
	 * Gets current trade data for specific asset-denominator pair, from specific exchange(s).
	 * Reference: https://docs.blockfacts.io/?java#current-trade-data
	 * @param assets Asset tickers (e.g. BTC, ETH)
	 * @param denominators Denominator tickers (e.g. USD, EUR)
	 * @param exchanges Exchange names (e.g. KRAKEN, COINBASE)
	 * @return JsonObject
	 */
	public JsonObject GetCurrentTradeData(String assets, String denominators, String exchanges) {
		assets = assets.trim().replace(" ", "");
		denominators = denominators.trim().replace(" ", "");
		exchanges = exchanges.trim().replace(" ", "");
		
		JsonObject responseData = null;
		
		restRequest = HttpRequest.newBuilder()
	      	      .uri(URI.create(this.blockfactsApiUrl + "/api/v1/exchanges/trades?asset=" + assets + "&denominator=" + denominators + "&exchange=" + exchanges))
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
	 * Gets 600 latest trades that happened on the requested exchanges and pairs.
	 * Reference: https://docs.blockfacts.io/?java#snapshot-trade-data
	 * @param assets Asset tickers (e.g. BTC, ETH)
	 * @param denominators Denominator tickers (e.g. USD, EUR)
	 * @param exchanges Exchange names (e.g. KRAKEN, COINBASE)
	 * @return JsonObject
	 */
	public JsonObject GetSnapshotTradeData(String assets, String denominators, String exchanges) {
		assets = assets.trim().replace(" ", "");
		denominators = denominators.trim().replace(" ", "");
		exchanges = exchanges.trim().replace(" ", "");
		
		JsonObject responseData = null;
		
		restRequest = HttpRequest.newBuilder()
	      	      .uri(URI.create(this.blockfactsApiUrl + "/api/v1/exchanges/trades/snapshot?asset=" + assets + "&denominator=" + denominators + "&exchange=" + exchanges))
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
	 * Gets the snapshot of provided exchange(s) OHLCV data for provided asset-denominator pairs and intervals.
	 * Reference: https://docs.blockfacts.io/?java#data-snapshot-ohlcv-exchange
	 * @param assets Asset tickers (e.g. BTC, ETH)
	 * @param denominators Denominator tickers (e.g. USD, EUR)
	 * @param exchanges Exchange names (e.g. KRAKEN, COINBASE)
	 * @param intervals Intervals (e.g. 1m, 3m, 1h)
	 * @return JsonObject
	 */
	public JsonObject GetOHLCVSnapshotData(String assets, String denominators, String exchanges, String intervals) {
		assets = assets.trim().replace(" ", "");
		denominators = denominators.trim().replace(" ", "");
		exchanges = exchanges.trim().replace(" ", "");
		intervals = intervals.trim().replace(" ", "");
		
		JsonObject responseData = null;
		
		restRequest = HttpRequest.newBuilder()
	      	      .uri(URI.create(this.blockfactsApiUrl + "/api/v1/exchanges/trades/ohlcv-snapshot?asset=" + assets + "&denominator=" + denominators + "&exchange=" + exchanges + "&interval=" + intervals))
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
	 * Gets exchange historical price by asset-denominator, exchange, date, time and interval.
	 * Reference: https://docs.blockfacts.io/?java#historical-trade-data
	 * @param asset Asset ticker (e.g. BTC)
	 * @param denominator Denominator ticker (e.g. USD)
	 * @param exchanges Exchange names (e.g. KRAKEN, COINBASE)
	 * @param date Specific date (e.g. 2.9.2019)
	 * @param time Specific time (e.g. 14:00:00)
	 * @param interval Historical interval to cover (e.g. 20 = 14:00:00 - 14:20:00) (Min 0, Max 240)
	 * @param page Optional, our API is always showing 100 results per page in order to improve the performance. You can provide the page parameter in order to query a specific page
	 * @return BlockfactsHistoricalExchangeTradesModel
	 */
	public BlockfactsHistoricalExchangeTradesModel GetHistoricalTradeData(String asset, String denominator, String exchanges, String date, String time, int interval, int page) {
		exchanges = exchanges.trim().replace(" ", "");
		
		BlockfactsHistoricalExchangeTradesModel responseData = null;
		
		restRequest = HttpRequest.newBuilder()
      	      .uri(URI.create(this.blockfactsApiUrl + "/api/v1/exchanges/trades/historical?asset=" + asset + "&denominator=" + denominator + "&exchange=" + exchanges + "&date=" + date + "&time=" + time + "&interval=" + interval + "&page=" + page))
      	      .header("Content-Type", this.headers.get("Content-Type").toString())
      	      .header("X-API-KEY", this.headers.get("X-API-KEY").toString())
      	      .header("X-API-SECRET", this.headers.get("X-API-SECRET").toString())
      	      .build();
		
		HttpResponse<String> response;
		  try {
			response = restClient.send(restRequest, BodyHandlers.ofString());
	        JsonObject jsonObj = new JsonParser().parse(response.body()).getAsJsonObject();
	        responseData = new Gson().fromJson(jsonObj, BlockfactsHistoricalExchangeTradesModel.class);
		  } catch (IOException e) {
			e.printStackTrace();
		  } catch (InterruptedException e) {
			e.printStackTrace();
		  }
		  
		  return responseData;
	}
	
	/**
	 * Gets historical OHLCV data by asset-denominator, exchange, date, time and interval.
	 * Reference: https://docs.blockfacts.io/?java#ohlcv-historical-data-2
	 * @param asset Asset ticker (e.g. BTC)
	 * @param denominator Denominator ticker (e.g. USD)
	 * @param exchange Exchange name (e.g. KRAKEN)
	 * @param interval 	OHLCV Interval (30s, 1m, 3m, 5m, 15m, 30m, 1h, 2h, 4h, 6h, 12h, 1d, 1w, 1mo)
	 * @param dateStart Specific date to start from (e.g. 5.8.2020)
	 * @param timeStart Specific time to start from (in UTC) (e.g. 14:00:00)
	 * @param dateEnd Specific end date (e.g. 5.8.2020)
	 * @param timeEnd Specific end time (in UTC) (e.g. 14:00:00)
	 * @param page Optional, our API is always showing 100 results per page in order to improve the performance. You can provide the page parameter in order to query a specific page
	 * @return JsonObject
	 */
	public JsonObject GetHistoricalOHLCVData(String asset, String denominator, String exchanges, String interval, String dateStart, String timeStart, String dateEnd, String timeEnd, int page) {
		exchanges = exchanges.trim().replace(" ", "");
		
		JsonObject responseData = null;
		
		restRequest = HttpRequest.newBuilder()
      	      .uri(URI.create(this.blockfactsApiUrl + "/api/v1/exchanges/trades/ohlcv?asset=" + asset + "&denominator=" + denominator + "&exchange=" + exchanges + "&interval=" + interval + "&dateStart=" + dateStart + "&timeStart=" + timeStart + "&dateEnd=" + dateEnd + "&timeEnd=" + timeEnd + "&page=" + page))
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
	 * Gets historical exchange trades in specific second.
	 * Reference: https://docs.blockfacts.io/?java#specific-trade-data
	 * @param asset Asset ticker (e.g. BTC)
	 * @param denominator Denominator ticker (e.g. USD)
	 * @param exchanges Exchange name (e.g. KRAKEN, COINBASE)
	 * @param date Specific date (e.g. 2.9.2019)
	 * @param time Specific time (e.g. 14:00:00)
	 * @return List of BlockfactsTradeModels
	 */
	public List<BlockfactsTradeModel> GetSpecificTradeData(String asset, String denominator, String exchanges, String date, String time) {
		exchanges = exchanges.trim().replace(" ", "");
		
		List<BlockfactsTradeModel> responseData = null;
		
		restRequest = HttpRequest.newBuilder()
      	      .uri(URI.create(this.blockfactsApiUrl + "/api/v1/exchanges/trades/specific?asset=" + asset + "&denominator=" + denominator + "&exchange=" + exchanges + "&date=" + date + "&time=" + time))
      	      .header("Content-Type", this.headers.get("Content-Type").toString())
      	      .header("X-API-KEY", this.headers.get("X-API-KEY").toString())
      	      .header("X-API-SECRET", this.headers.get("X-API-SECRET").toString())
      	      .build();
		
		  HttpResponse<String> response;
		  try {
			response = restClient.send(restRequest, BodyHandlers.ofString());
 	        JsonArray jsonObj = new JsonParser().parse(response.body()).getAsJsonArray();
	        responseData = new Gson().fromJson(jsonObj, new TypeToken<List<BlockfactsTradeModel>>(){}.getType());
		  } catch (IOException e) {
			e.printStackTrace();
		  } catch (InterruptedException e) {
			e.printStackTrace();
		  }
		  
		  return responseData;
	}
	
	/**
	 * Gets the total traded volume on all exchanges by asset-denominator and interval.
	 * Reference: https://docs.blockfacts.io/?java#total-trade-volume
	 * @param asset Asset ticker (e.g. BTC)
	 * @param denominator Denominator ticker (e.g. USD)
	 * @param interval Interval (1d, 30d, 60d, 90d)
	 * @return JsonObject
	 */
	public JsonObject GetTotalTradeVolume(String asset, String denominator, String interval) {
		JsonObject responseData = null;
		
		restRequest = HttpRequest.newBuilder()
	      	      .uri(URI.create(this.blockfactsApiUrl + "/api/v1/exchanges/trades/total-volume?asset=" + asset + "&denominator=" + denominator + "&interval=" + interval ))
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
	 * Gets the moving percentage, and difference in price over a certain time period.
	 * Reference: https://docs.blockfacts.io/?java#period-movers-2
	 * @param exchange Exchange name (e.g. KRAKEN)
	 * @param denominator Specific date (e.g. 11.8.2020)
	 * @param date Denominator ticker (e.g. USD)
	 * @param interval Interval (oneDay, sevenDay, thirtyDay, ninetyDay, oneYear, twoYear, threeYear, fiveYear)
	 * @param sort 1 - Losers first, -1 - Winners first
	 * @return JsonArray
	 */
	public JsonArray GetPeriodMovers(String exchange, String denominator, String date, String interval, int sort) {
		JsonArray responseData = null;
		
		restRequest = HttpRequest.newBuilder()
	      	      .uri(URI.create(this.blockfactsApiUrl + "/api/v1/exchanges/period-movers?exchange=" + exchange + "&denominator=" + denominator + "&date=" + date + "&interval=" + interval + "&sort=" + sort))
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
