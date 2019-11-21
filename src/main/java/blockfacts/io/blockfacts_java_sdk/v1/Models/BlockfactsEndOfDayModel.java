package blockfacts.io.blockfacts_java_sdk.v1.Models;

import java.time.LocalDateTime;

public class BlockfactsEndOfDayModel {
	public String exchange;
    public String pair;
    public double volume;
    public double baseVolume;
    public double low;
    public double high;
    public double open;
    public double close;
    public double tradesCount;
    public long timestamp;
    public LocalDateTime date;
}
