package components;

import java.util.ArrayList;
import java.util.List;

public class Circuit {

	private List<String> buildingBlocks = new ArrayList<>();

	public List<String> getBuildingBlocks() {
		return buildingBlocks;
	} 
	
	public void setBuildingBlocks(List<String> buildingBlocks) {
		this.buildingBlocks = buildingBlocks;
	}
}
