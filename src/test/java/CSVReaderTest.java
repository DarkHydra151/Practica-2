import com.example.CSVReader;
import com.example.DataReader;
import com.example.Transaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVReaderTest {

    @Test
    public void testReadTransactions() throws IOException {
        File tempFile = File.createTempFile("test", ".csv");
        tempFile.deleteOnExit();

        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write("01-01-2024,1000.0,Доходи\n");
            writer.write("05-01-2024,-200.0,Продукти\n");
            writer.write("10-01-2024,-500.0,Розваги\n");
        }

        DataReader<Transaction> reader = new CSVReader();
        List<Transaction> transactions = reader.read(tempFile.getAbsolutePath());

        Assertions.assertEquals(3, transactions.size(), "Кількість транзакцій неправильна");
        Assertions.assertEquals("01-01-2024", transactions.get(0).getDate());
        Assertions.assertEquals(-200.0, transactions.get(1).getAmount());
        Assertions.assertEquals("Розваги", transactions.get(2).getDescription());
    }
}
