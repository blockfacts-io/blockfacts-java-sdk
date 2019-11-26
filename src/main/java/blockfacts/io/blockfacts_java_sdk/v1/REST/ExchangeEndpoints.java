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
	 * Gets exchange end of day data for specific asset-denominator and exchange.
	 * Reference: https://docs.blockfacts.io/?java#end-of-day-data-2
	 * @param asset Asset ticker (e.g. BTC)
	 * @param denominator Denominator ticker (e.g. USD)
	 * @param exchange Exchange name (e.g. KRAKEN)
	 * @param length Length (representing how many days back from the current day, Min = 0, Max = 20)
	 * @return List of BlockfactsEndOfDayModels
	 */
	public List<BlockfactsOHLCModel> GetEndOfDayData(String asset, String denominator, String exchange, int length) {
		List<BlockfactsOHLCModel> responseData = null;
		
		restRequest = HttpRequest.newBuilder()
      	      .uri(URI.create(this.blockfactsApiUrl + "/api/v1/exchanges/trades/endOfDay?asset=" + asset + "&denominator=" + denominator + "&exchange=" + exchange + "&length=" + length))
      	      .header("Content-Type", this.headers.get("Content-Type").toString())
      	      .header("X-API-KEY", this.headers.get("X-API-KEY").toString())
      	      .header("X-API-SECRET", this.headers.get("X-API-SECRET").toString())
      	      .build();
		
		  HttpResponse<String> response;
		  try {
			response = restClient.send(restRequest, BodyHandlers.ofString());
 	        JsonArray jsonObj = new JsonParser().parse(response.body()).getAsJsonArray();
	        responseData = new Gson().fromJson(jsonObj, new TypeToken<List<BlockfactsOHLCModel>>(){}.getType());
		  } catch (IOException e) {
			e.printStackTrace();
		  } catch (InterruptedException e) {
			e.printStackTrace();
		  }
		  
		  return responseData;
	}
}
