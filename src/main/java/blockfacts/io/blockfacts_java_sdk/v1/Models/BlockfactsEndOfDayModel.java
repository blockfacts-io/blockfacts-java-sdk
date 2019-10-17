package blockfacts.io.blockfacts_java_sdk.v1.Models;

import java.time.LocalDateTime;

public class BlockfactsEndOfDayModel {
	public String exchange;
    public String pair;
    public double volume;
    public double low;
    public double high;
    public double open;
    public double close;
    public long timestamp;
    public LocalDateTime date;
}
