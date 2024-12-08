import com.example.Transaction;
import com.example.TransactionAnalyzer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.util.Arrays;
import java.util.List;

public class TransactionAnalyzerTest {
    @Test
    public void testFindTopExpenses() {
        List<Transaction> transactions = Arrays.asList(
                new Transaction("01-01-2024", -100.0, "Продукти"),
                new Transaction("02-01-2024", -300.0, "Розваги"),
                new Transaction("03-01-2024", -50.0, "Транспорт"),
                new Transaction("04-01-2024", -400.0, "Оренда"),
                new Transaction("05-01-2024", 500.0, "Доходи") // Це не витрата
        );

        TransactionAnalyzer analyzer = new TransactionAnalyzer(transactions);

        List<Transaction> topExpenses = analyzer.findTopExpenses();

        Assertions.assertEquals(4, topExpenses.size(), "Неправильна кількість витрат у списку");
        Assertions.assertEquals("Оренда", topExpenses.get(0).getDescription());
        Assertions.assertEquals("Розваги", topExpenses.get(1).getDescription());
        Assertions.assertEquals("Продукти", topExpenses.get(2).getDescription());
    }
}
