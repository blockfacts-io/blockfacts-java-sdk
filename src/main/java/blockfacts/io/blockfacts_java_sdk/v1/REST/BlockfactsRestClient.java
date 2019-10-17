package blockfacts.io.blockfacts_java_sdk.v1.REST;

public class BlockfactsRestClient {
	
	public AssetEndpoints Assets;
	public BlockfactsEndpoints Blockfacts;
	public ExchangeEndpoints Exchanges;
	
	public BlockfactsRestClient(String key, String secret) {
		this.Assets = new AssetEndpoints(key, secret);
		this.Blockfacts = new BlockfactsEndpoints(key, secret);
		this.Exchanges = new ExchangeEndpoints(key, secret);
	}
	
	public void SetKey(String key) {
		this.Assets.key = key;
		this.Blockfacts.key = key;
		this.Exchanges.key = key;
	}
	
	public void SetSecret(String secret) {
		this.Assets.secret = secret;
		this.Blockfacts.secret = secret;
		this.Exchanges.secret = secret;
	}
}
