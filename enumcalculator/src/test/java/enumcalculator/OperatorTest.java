
package enumcalculator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * Parameterized test for enum operation. Look in [Kaczanowski, ch 3] on how to
 * use parameterized tests.
 *
 * @author Pieter van den Hombergh {@code <p.vandenhombergh@fontys.nl>}
 */
public class OperatorTest {

    /**
     * Most of the test input, including the operator symbol. The
     * ArgumentsAccessor object is taken apart in the test method. In following
     * exercises you will have to do that on your own. The test data is
     * collected in a set of comma separator lines or records, in which you can
     * access the columns with an index, zero based.
     *
     * @param args ArgumentAccessor that gives access to a csv record.
     */
    @ParameterizedTest
    @CsvSource({
            // message, symbol, expected result, a,b
            "add      , '+'," + (2 + 3) + ", 2, 3 ",
            "subtract , '-'," + (2 - 3) + ", 2, 3 ", //comma separated string
            "multiply , '*'," + (2 * 3) + ", 2, 3 ",
            "divide   , '/'," + (2 / 3) + ", 2, 3 ",
            "power    ,'**'," + (2 * 2 * 2) + ", 2, 3 "
    })
    public void testOperator(ArgumentsAccessor args) {
        var message = args.getString(0);
        var symbol = args.getString(1);
        int expected = args.getInteger(2);
        int a = args.getInteger(3);
        int b = args.getInteger(4);
        // use all inputs in the assertThat
        assertThat(Operator.get(symbol).compute(a, b)).isEqualTo(expected).as(message);
//        fail( "test not yet implemented" );
    }
}
