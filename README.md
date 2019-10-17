![alt text](https://blockfacts.io/img/logo/bf-logo@2x.png "BlockFacts official logo")
# BlockFacts Java SDK
Official BlockFacts Java SDK including Rest and WebSocket API support.

## Features

- REST API client with function wrapper for easy API access
- WebSocket API client for real-time data gathering

* [Getting started](#getting-started)
* [Using Rest API Client](#using-rest-api-client)
* [Asset endpoints](#asset-endpoints)
* [BlockFacts endpoints](#blockfacts-endpoints)
* [Exchange endpoints](#exchange-endpoints)
* [Using WebSocket API Client](#using-websocket-api-client)


## Getting started
```java
String key = "your-api-key";
String secret = "your-api-secret";
BlockfactsRestClient restClient = new BlockfactsRestClient(key, secret);

// Test one endpoint
List<BlockfactsAssetModel> response = restClient.Assets.ListAllAssets();
System.out.println(response.get(9).asset + ", " + response.get(9).blockfactsTicker + ", " + response.get(9).isCrypto);
```

## Using Rest API Client

In the examples below, you can see which method is mapped to call its predefined endpoint. You can also read more about authorization and how to obtain an API Key here: https://docs.blockfacts.io/#authorization

**Note**: Many methods return Blockfacts models, just so you don't have to map the JSON response yourself. You can find out more about which models are supported in the *Models* folder.

## Asset endpoints

### List all assets
Get all assets that we support.
- [`List<BlockfactsAssetModel> ListAllAssets()`](https://docs.blockfacts.io/?java#list-all-assets)

```java
List<BlockfactsAssetModel> response = restClient.Assets.ListAllAssets();
```

### Get specific asset
Get specific asset by ticker ID.
- [`BlockfactsAssetModel GetSpecificAsset(String tickerId)`](https://docs.blockfacts.io/?java#specific-asset)

```java
BlockfactsAssetModel response = restClient.Assets.GetSpecificAsset("BTC");
```

## BlockFacts endpoints

### Exchanges in normalization
List exchanges that go into the normalization for specific asset-denominator pair.
- [`JsonObject GetExchangesInNormalization(String pairs)`](https://docs.blockfacts.io/?java#exchanges-in-normalization)

```java
JsonObject response = restClient.Blockfacts.GetExchangesInNormalization("BTC-USD, ETH-USD");
System.out.println(response.getAsJsonObject("BTC-USD").getAsJsonArray("exchanges"));
```

### Current data
Get current normalization data for specific asset-denominator pair.
- [`JsonObject GetCurrentData(String assets, String denominators)`](https://docs.blockfacts.io/?java#current-data)

```java
JsonObject response = restClient.Blockfacts.GetCurrentData("BTC, ETH", "USD, EUR");
System.out.println(response);
```

### Historical data
Get historical normalization data by asset-denominator, date, time and interval.
- [`BlockfactsHistoricalNormalizationResultsModel GetHistoricalData(String asset, String denominator, String date, String time, int interval, int page)`](https://docs.blockfacts.io/?java#historical-data)

```java
BlockfactsHistoricalNormalizationResultsModel response = restClient.Blockfacts.GetHistoricalData("BTC", "USD", "2.9.2019", "14:00:00", 10, 1);
```

### Specific historical data
Get historical normalized price by specific point in time.
- [`BlockfactsNormalizationModel GetSpecificHistoricalData(String asset, String denominator, String date, String time)`](https://docs.blockfacts.io/?java#specific-historical-data)

```java
BlockfactsNormalizationModel response = restClient.Blockfacts.GetSpecificHistoricalData("BTC", "USD", "2.9.2019", "14:00:01");
```

### Normalization pairs
Get all running normalization pairs. Resulting in which asset-denominator pairs are currently being normalized inside our internal system.
- [`List<BlockfactsRunningNormalizationPairsTradesModel> GetNormalizationPairs()`](https://docs.blockfacts.io/?java#normalization-pairs)

```java
List<BlockfactsRunningNormalizationPairsTradesModel> response = restClient.Blockfacts.GetNormalizationPairs();
```

### End of day data
Get normalized end of day data for specific asset-denominator.
- [`List<BlockfactsEndOfDayModel> GetEndOfDayData(String asset, String denominator, int length)`](https://docs.blockfacts.io/?java#end-of-day-data)

```java
List<BlockfactsEndOfDayModel> response = restClient.Blockfacts.GetEndOfDayData("BTC", "USD", 1);
```

## Exchange endpoints

### List all exchanges
List all exchanges that we support.
- [`List<BlockfactsExchangeDataModel> ListAllExchanges()`](https://docs.blockfacts.io/?java#all-exchanges)

```java
List<BlockfactsExchangeDataModel> response = restClient.Exchanges.ListAllExchanges();
```

### Specific exchange data
Get information about a specific exchange by its name. Returns information such as which assets are supported, asset ticker info, etc.
- [`BlockfactsExchangeDataModel GetSpecificExchangeData(String exchange)`](https://docs.blockfacts.io/?java#specific-exchange-data)

```java
BlockfactsExchangeDataModel response = restClient.Exchanges.GetSpecificExchangeData("KRAKEN");
```

### Current trade data
Get current trade data for specific asset-denominator pair, from specific exchange(s).
- [`JsonObject GetCurrentTradeData(String assets, String denominators, String exchanges)`](https://docs.blockfacts.io/?java#current-trade-data)

```java
JsonObject response = restClient.Exchanges.GetCurrentTradeData("BTC, ETH", "USD", "kraken, coinbase");
System.out.println(response);
```

### Historical trade data
Get exchange historical price by asset-denominator, exchange, date, time and interval.
- [`BlockfactsHistoricalExchangeTradesModel GetHistoricalTradeData(String asset, String denominator, String exchanges, String date, String time, int interval, int page)`](https://docs.blockfacts.io/?java#historical-trade-data)

```java
BlockfactsHistoricalExchangeTradesModel response = restClient.Exchanges.GetHistoricalTradeData("BTC", "USD", "KRAKEN", "2.9.2019", "14:00:00", 10, 1);
```

### Specific trade data
Get historical exchange trades in specific second.
- [`List<BlockfactsTradeModel> GetSpecificTradeData(String asset, String denominator, String exchanges, String date, String time)`](https://docs.blockfacts.io/?java#specific-trade-data)

```java
List<BlockfactsTradeModel> response = restClient.Exchanges.GetSpecificTradeData("BTC", "USD", "KRAKEN, COINBASE", "2.9.2019", "14:00:00");
```

### End of day data
Get exchange end of day data for specific asset-denominator and exchange
- [`List<BlockfactsEndOfDayModel> GetEndOfDayData(String asset, String denominator, String exchange, int length)`](https://docs.blockfacts.io/?java#end-of-day-data-2)

```java
List<BlockfactsEndOfDayModel> response = restClient.Exchanges.GetEndOfDayData("BTC", "USD", "KRAKEN", 10);
```

## Using WebSocket API Client
Our WebSocket feed provides real-time market data streams from multiple exchanges at once and the BlockFacts normalized price stream for each second. The WebSocket feed uses a bidirectional protocol, and all messages sent and received via websockets are encoded in a `JSON` format.

### Getting started, connecting and subscribing
In order to connect to our WebSocket server and start receiving messages through this SDK, you first need to define a list of `BlockfactsChannelObject` objects. 

```java
List<BlockfactsChannelObject> channelObjects = new ArrayList<BlockfactsChannelObject>();
channelObjects.add(new BlockfactsChannelObject("BLOCKFACTS", new ArrayList<String>() {{ add("BTC-USD"); }}));
channelObjects.add(new BlockfactsChannelObject("COINBASE", new ArrayList<String>() {{ add("BTC-USD"); add("ETH-USD"); }}));
channelObjects.add(new BlockfactsChannelObject("HEARTBEAT", null));
```

Then you need to `BlockfactsWebSocketClient` instance, and override the `onOpen`, `onMessage`, `onClose` and `onError` event handlers.

After defining the wsClient you can choose to connect to BlockFacts WebSocket server via non-blocking method `Connect` or blocking method `ConnectBlocking`.

Now you can send the `Subscribe` type message in order to start receiving the data. 
We will pass it the `channelObjects` list, defined in the code snippet above.

In the code snippet below you can see full code on how to connect and subscribe to the real-time WebSocket feed.

```java
try {
  BlockfactsWebSocketClient wsClient = new BlockfactsWebSocketClient(key, secret) {
    @Override
    public void onOpen(ServerHandshake handshakedata) {
      System.out.println("New connection opened");
    }
    
    @Override
    public void onMessage(String message) {
      var json = new JsonParser().parse(message).getAsJsonObject();
      String messageType = json.get("type").getAsString();
      
      if(messageType.equals("ping")) {
        // Send Pong message
      }
      
      if(messageType.equals("blockfactsPrice")) {
        // Handle blockfactsPrice
      }
      
      if(messageType.equals("exchangeTrade")) {
        // Handle exchangeTrade
      }
      
      if(messageType.equals("unsubscribed")) {
        // Handle unsubscribed event
      }
      
      if(messageType.equals("heartbeat")) {
        // Handle heartbeat event
      }
      
      if(messageType.equals("error")) {
        // Handle error
      }
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
      System.out.println("Closed with exit code " + code + " additional info: " + reason);
    }

    @Override
    public void onError(Exception ex) {
      System.err.println("An error occurred:" + ex);
    }
  };

  wsClient.ConnectBlocking();
  wsClient.Subscribe(channelObjects);

  } catch (URISyntaxException e) {
    e.printStackTrace();
  }
```

### Unsubscribing
If you wish to unsubscribe from certain channels or pairs, you can do so by sending the `unsubscribe` type message.

```java
wsClient.Unsubscribe(channelObjects);
```

### Ping
Clients can send `ping` type messages to determine if the server is online.

```java
wsClient.Ping();
```

### Pong
Clients must respond to `ping` type messages sent from the server with a `pong` type message.

```java
wsClient.Pong();
```

In order to have a better understanding of our server responses, please refer to: https://docs.blockfacts.io/#server-messages