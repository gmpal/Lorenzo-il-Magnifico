package it.polimi.ingsw.GC_24.model;

import java.io.Serializable;

public enum State {
	
	STARTING {
		@Override
		public State nextState() {
			return WAITINGFORPLAYERONE;																
		}
	},	
	WAITINGFORPLAYERONE {
		@Override
		public State nextState() {
			return WAITINGFORPLAYERTWO;																
		}
	},
	WAITINGFORPLAYERTWO {
		@Override
		public State nextState() {
			return WAITINGFORPLAYERTHREE;
		}
	},
	WAITINGFORPLAYERTHREE {
		@Override
		public State nextState() {
			return WAITINGFORPLAYERFOUR;
		}
	},
	WAITINGFORPLAYERFOUR {
		@Override
		public State nextState() {
			return RUNNING;
		}
	},
	RUNNING {
		@Override
		public State nextState() {
			return ENDED;
		}
	},
	ENDED {
		@Override
		public State nextState() {
			return ENDED;
		}
	};
	public abstract State nextState();
}
