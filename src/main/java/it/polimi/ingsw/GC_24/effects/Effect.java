package it.polimi.ingsw.GC_24.effects;

public abstract class Effect implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7308486560393380862L;
	
	protected String name;
	
	//constructor
	public Effect(String name){
		this.name = name;
	}

	@Override
	public String toString() {
		return "Effect [name=" + name + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
