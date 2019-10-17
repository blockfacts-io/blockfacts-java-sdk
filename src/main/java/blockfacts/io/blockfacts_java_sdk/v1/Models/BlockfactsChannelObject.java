package blockfacts.io.blockfacts_java_sdk.v1.Models;

import java.util.ArrayList;
import java.util.List;

public class BlockfactsChannelObject {
	public String exchangeName;
    public List<String> pairs;
    
    public BlockfactsChannelObject(String exchangeName, ArrayList<String> pairs) {
    	this.exchangeName = exchangeName;
    	this.pairs = pairs;
    }
}
