package blockfacts.io.blockfacts_java_sdk.v1.Models;

import java.time.LocalDateTime;

public class BlockfactsOHLCModel {
	public String exchange;
    public String pair;
    public double volume;
    public double baseVolume;
    public double low;
    public double high;
    public double open;
    public double close;
    public long tradesCount;
    public long timestamp;
    public long exchangeOpenTime;
    public long exchangeCloseTime;
    public String interval;
    public LocalDateTime date;
}
