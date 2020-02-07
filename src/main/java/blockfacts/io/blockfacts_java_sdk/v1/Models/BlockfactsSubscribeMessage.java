package blockfacts.io.blockfacts_java_sdk.v1.Models;

import java.util.List;

public class BlockfactsSubscribeMessage {
	public String type;
	public Boolean snapshot;
	public String id;
    public String X_API_KEY;
    public String X_API_SECRET;
    public List<BlockfactsChannelObject> channels;
}