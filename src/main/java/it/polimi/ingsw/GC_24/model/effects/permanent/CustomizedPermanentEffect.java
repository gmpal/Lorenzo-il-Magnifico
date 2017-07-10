package it.polimi.ingsw.GC_24.model.effects.permanent;

/**
 * This class represent particular effects there are in some cards. They are
 * handled by the name, separately.
 */
public class CustomizedPermanentEffect extends PermanentEffect {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4286424564024571327L;
	private String description;

	public CustomizedPermanentEffect(String name, String description) {
		super(name);
		this.description = description;
	}

	@Override
	public String toString() {
		return description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
