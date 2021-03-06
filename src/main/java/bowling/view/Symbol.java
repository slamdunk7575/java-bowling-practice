package bowling.view;

import bowling.domain.state.Continue;
import bowling.domain.state.Miss;
import bowling.domain.state.Spare;
import bowling.domain.state.State;

public enum Symbol {
    STRIKE("X") {
        @Override
        public String getSymbol(State state) {
            return STRIKE.toString();
        }
    },

    SPARE("|/") {
        @Override
        public String getSymbol(State state) {
            String spareFirst = ((Spare) state).getFirst().toString();
            if(checkGutter(spareFirst)) {
                spareFirst = GUTTER_SINGLE.toString();
            }
            return spareFirst + SPARE.toString();
        }
    },

    GUTTER_SINGLE("-") {
        @Override
        public String getSymbol(State state) {
            return GUTTER_SINGLE.toString();
        }
    },

    MISS("|") {
        @Override
        public String getSymbol(State state) {
            String missFirst = ((Miss) state).getFirstPin().toString();
            String missSecond = ((Miss) state).getSecondPin().toString();

            if(checkGutter(missFirst)) {
                missFirst = GUTTER_SINGLE.toString();
            }

            if(checkGutter(missSecond)) {
                missSecond = GUTTER_SINGLE.toString();
            }
            return missFirst + MISS.toString() + missSecond;
        }
    },

    READY("") {
        @Override
        public String getSymbol(State state) {
            return READY.toString();
        }
    },

    CONTINUE("") {
        @Override
        public String getSymbol(State state) {
            String continueFirst = ((Continue) state).getFirst().toString();

            if(checkGutter(continueFirst)) {
                continueFirst = Symbol.GUTTER_SINGLE.toString();
            }
            return continueFirst;
        }
    },

    GUTTER("-|-") {
        @Override
        public String getSymbol(State state) {
            return GUTTER.toString();
        }
    };

    private String symbol;

    Symbol(String symbol) {
        this.symbol = symbol;
    }

    public abstract String getSymbol(State state);

    private static boolean checkGutter(String felledPin) {
        return Integer.parseInt(felledPin) == 0;
    }

    @Override
    public String toString() {
        return symbol;
    }
}
