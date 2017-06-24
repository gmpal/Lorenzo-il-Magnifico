package it.polimi.ingsw.GC_24.model;

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
			return PERIOD1_ROUND1;
		}
	},
	PERIOD1_ROUND1 {
		@Override
		public State nextState() {
			return PERIOD1_ROUND2;
		}
	},
	PERIOD1_ROUND2 {
		@Override
		public State nextState() {
			return PERIOD2_ROUND1;
		}
	},
	PERIOD2_ROUND1 {
		@Override
		public State nextState() {
			return PERIOD2_ROUND2;
		}
	},
	PERIOD2_ROUND2 {
		@Override
		public State nextState() {
			return PERIOD3_ROUND1;
		}
	},
	PERIOD3_ROUND1 {
		@Override
		public State nextState() {
			return PERIOD3_ROUND2;
		}
	},
	PERIOD3_ROUND2 {
		@Override
		public State nextState() {
			return ENDED;
		}
	},
	ENDED {
		@Override
		public State nextState() {
			return null;
		}
	};
	
	
	public abstract State nextState();
}
