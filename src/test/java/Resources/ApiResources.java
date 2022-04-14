package Resources;

public enum ApiResources {

	AddPlaceAPI("/maps/api/place/add/json"),
	getPlaceAPI("/maps/api/place/get/json"),
	DeletePlaceAPI("/maps/api/place/delete/json");

	String resources;
	ApiResources(String resources) {
		
		this.resources= resources;
	}
	
	public String getResource()
	{
		return resources;
		
	}
	
}
