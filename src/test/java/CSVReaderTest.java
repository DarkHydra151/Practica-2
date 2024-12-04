import com.example.Transaction;
import com.example.CSVReader;
import com.example.DataReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CSVReaderTest {
    @Test
    public void testReadTransactions() {
        String testCSV = "01-01-2024,1000.0,Доходи\n" +
                "05-01-2024,-200.0,Продукти\n" +
                "10-01-2024,-500.0,Розваги";

        DataReader<Transaction> reader = new CSVReader();
        List<Transaction> transactions = reader.read(testCSV);

        Assertions.assertEquals(3, transactions.size(), "Кількість транзакцій неправильна");
        Assertions.assertEquals("01-01-2024", transactions.get(0).getDate());
        Assertions.assertEquals(-200.0, transactions.get(1).getAmount());
        Assertions.assertEquals("Розваги", transactions.get(2).getDescription());
    }


}