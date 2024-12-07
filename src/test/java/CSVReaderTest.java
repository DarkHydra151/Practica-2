
import com.example.Transaction;
import com.example.TransactionAnalyzer;
import com.example.CSVReader;
import com.example.DataReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVReaderTest {

    @Test
    public void testReadTransactions() throws IOException {
        // Створення тимчасового файлу
        File tempFile = File.createTempFile("test", ".csv");
        tempFile.deleteOnExit(); // Забезпечує видалення файлу після завершення тесту

        // Запис CSV даних у файл
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write("01-01-2024,1000.0,Доходи\n");
            writer.write("05-01-2024,-200.0,Продукти\n");
            writer.write("10-01-2024,-500.0,Розваги\n");
        }

        // Створення об'єкта для читання даних з CSV
        DataReader<Transaction> reader = new CSVReader();
        List<Transaction> transactions = reader.read(tempFile.getAbsolutePath());  // Передаємо шлях до файлу

        // Перевірка результатів
        Assertions.assertEquals(3, transactions.size(), "Кількість транзакцій неправильна");
        Assertions.assertEquals("01-01-2024", transactions.get(0).getDate());
        Assertions.assertEquals(-200.0, transactions.get(1).getAmount());
        Assertions.assertEquals("Розваги", transactions.get(2).getDescription());
    }

    @Test
    public void testFindTopExpenses() {
        List<Transaction> transactions = List.of(
                new Transaction("01-01-2024", -100.0, "Продукти"),
                new Transaction("02-01-2024", -300.0, "Розваги"),
                new Transaction("03-01-2024", -50.0, "Транспорт"),
                new Transaction("04-01-2024", -400.0, "Оренда"),
                new Transaction("05-01-2024", 500.0, "Доходи") // Не враховувати, бо це не витрата
        );

        // Створення аналізатора транзакцій
        TransactionAnalyzer analyzer = new TransactionAnalyzer(transactions);
        List<Transaction> topExpenses = analyzer.findTopExpenses();

        // Перевірка результатів
        Assertions.assertEquals(3, topExpenses.size(), "Неправильна кількість витрат у списку");
        Assertions.assertEquals("Оренда", topExpenses.get(0).getDescription());
        Assertions.assertEquals("Розваги", topExpenses.get(1).getDescription());
    }
}
