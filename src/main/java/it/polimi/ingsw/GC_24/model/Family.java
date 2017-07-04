package it.polimi.ingsw.GC_24.model;

import it.polimi.ingsw.GC_24.model.dice.SetOfDice;

public class Family implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8845801385694798977L;
	private FamilyMember member1;
	private FamilyMember member2;
	private FamilyMember member3;
	private FamilyMember member4;

	// constructor

	/**
	 * when creating a family, it creates a family of neutral members. Then this
	 * family is set from a SetOfDice
	 */
	public Family(PlayerColour playerColour) {
		this.member1 = new FamilyMember(playerColour);
		this.member2 = new FamilyMember(playerColour);
		this.member3 = new FamilyMember(playerColour);
		this.member4 = new FamilyMember(playerColour);

	}

	/** sets the family with a SetOfDice after it has been created */
	public void setFamily(SetOfDice dice) {
		this.member1.setMember(dice.getDie1());
		this.member2.setMember(dice.getDie2());
		this.member3.setMember(dice.getDie3());
		this.member4.setMemberValue(0);

	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		if (member1.isAvailable())
			builder.append("Member 1 --> " + member1 + "\n");
		if (member2.isAvailable())
			builder.append("Member 2 --> " + member2 + "\n");
		if (member3.isAvailable())
			builder.append("Member 3 --> " + member3 + "\n");
		if (member4.isAvailable())
			builder.append("Member 4 --> " + member4 + "\n");
		return builder.toString();
	}

	// TODO:test
	public FamilyMember getMemberfromString(String number) {
		if (number.equals("1")) {
			return member1;
		}
		if (number.equals("2")) {
			return member2;
		}
		if (number.equals("3")) {
			return member3;
		}
		if (number.equals("4")) {
			return member4;
		} else
			return null;
	}

	/**
	 * @return true if all the family members in the family are available,
	 *         false otherwise.
	 */
	public boolean isFull() {
		return member1.isAvailable() && member2.isAvailable() && member3.isAvailable() && member4.isAvailable();
	}
	
	/**
	 * @return true if all the family members in the family are not available,
	 *         false otherwise.
	 */
	public boolean isEmpty() {
		return !member1.isAvailable() && !member2.isAvailable() && !member3.isAvailable() && !member4.isAvailable();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((member1 == null) ? 0 : member1.hashCode());
		result = prime * result + ((member2 == null) ? 0 : member2.hashCode());
		result = prime * result + ((member3 == null) ? 0 : member3.hashCode());
		result = prime * result + ((member4 == null) ? 0 : member4.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Family other = (Family) obj;
		if (member1 == null) {
			if (other.member1 != null)
				return false;
		} else if (!member1.equals(other.member1))
			return false;
		if (member2 == null) {
			if (other.member2 != null)
				return false;
		} else if (!member2.equals(other.member2))
			return false;
		if (member3 == null) {
			if (other.member3 != null)
				return false;
		} else if (!member3.equals(other.member3))
			return false;
		if (member4 == null) {
			if (other.member4 != null)
				return false;
		} else if (!member4.equals(other.member4))
			return false;
		return true;
	}

	// getters and setters
	public FamilyMember getMember1() {
		return member1;
	}

	public void setMember1(FamilyMember member1) {
		this.member1 = member1;
	}

	public FamilyMember getMember2() {
		return member2;
	}

	public void setMember2(FamilyMember member2) {
		this.member2 = member2;
	}

	public FamilyMember getMember3() {
		return member3;
	}

	public void setMember3(FamilyMember member3) {
		this.member3 = member3;
	}

	public FamilyMember getMember4() {
		return member4;
	}

	public void setMember4(FamilyMember member4) {
		this.member4 = member4;
	}

}