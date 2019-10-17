package blockfacts.io.blockfacts_java_sdk.v1.Models;

import java.util.List;

public class BlockfactsNormalizationModel {
	public String exchange;
    public String pair;
    public double price;
    public List<BlockfactsTradeModel> included;
    public List<BlockfactsTradeModel> excluded;
    public long timestamp;
    public long normalizationTimestamp;
    public String algorithm;
}
