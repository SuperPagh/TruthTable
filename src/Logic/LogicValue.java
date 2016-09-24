package Logic;

public enum LogicValue {
        TRUE('T'), FALSE('F');

        public char getChar() {
            return value;
        }

        private char value;

        LogicValue(char value) {
            this.value = value;
        }
    }