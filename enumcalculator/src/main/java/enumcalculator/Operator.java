
package enumcalculator;

import java.util.function.IntBinaryOperator;

/**
 * Enum with operations. Example of how to use lambda expressions in an (enum)
 * constructor.
 *
 * @author Pieter van den Hombergh {@code <p.vandenhombergh@fontys.nl>}
 */
public enum Operator implements IntBinaryOperator {
    //TODO

    ADD("+", (i, j) -> i + j),
    SUBTRACT("-", (i, j) -> i - j),
    MULTIPLY("*", (i, j) -> i * j),
    DIVIDE("/", (i, j) -> i / j),
    POWER("**", (i, j) -> (int) Math.pow(i, j));


    /**
     * Get the operator using its symbol. This method does a linear search
     * through the values of this enum.
     *
     * @param symbol to search
     * @return operation when found, null otherwise.
     */
    public static Operator get(String symbol) {
        //TODO 4 implement getOperator
        if (symbol.equals(ADD.symbol)) {
            return Operator.ADD;
        } else if (symbol.equals(SUBTRACT.symbol)) {
            return Operator.SUBTRACT;
        } else if (symbol.equals(MULTIPLY.symbol)) {
            return Operator.MULTIPLY;
        } else if (symbol.equals(DIVIDE.symbol)) {
            return Operator.DIVIDE;
        } else if (symbol.equals(POWER.symbol)) {
            return Operator.POWER;
        } else {
            return null;
        }

    }

    /**
     * The operation is a IntBinaryOperator.
     * <p>
     * {@see java.util.function.IntBinaryOperation}.
     */
    private final IntBinaryOperator operator;
    /**
     * The symbol that identifies the operation.
     */
    private final String symbol;

    /**
     * The constructor to create the Operation instances.
     *
     * @param symbol   of this operator
     * @param operator the actual method
     */
    Operator(String symbol, IntBinaryOperator operator) {
        this.symbol = symbol;
        this.operator = operator;
    }

    /**
     * Execute the calculation.
     *
     * @param a first param
     * @param b second param
     * @return result get the computation.
     */
    public int compute(int a, int b) {
        //TODO 5 implement compute

        return this.operator.applyAsInt(a, b);
    }

    private String getSymbol() {
        return symbol;
    }

    @Override
    public int applyAsInt(int left, int right) {
        return operator.applyAsInt(left, right);
    }

}
