package it.polimi.ingsw.GC_24.model.cards;


public class Card implements java.io.Serializable{	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3554603775414321683L;
	protected String name;
	protected String url;


	public Card(String name, String url) {
		this.name = name;
		this.url=url;
	}

	// getters and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "\n";
	}
}
