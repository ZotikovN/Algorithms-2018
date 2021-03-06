package lesson1;

import kotlin.NotImplementedError;

import java.lang.reflect.Array;
import java.util.*;
import java.io.*;

@SuppressWarnings("unused")
public class JavaTasks {
    /**
     * Сортировка времён
     *
     * Простая
     * (Модифицированная задача с сайта acmp.ru)
     *
     * Во входном файле с именем inputName содержатся моменты времени в формате ЧЧ:ММ:СС,
     * каждый на отдельной строке. Пример:
     *
     * 13:15:19
     * 07:26:57
     * 10:00:03
     * 19:56:14
     * 13:15:19
     * 00:40:31
     *
     * Отсортировать моменты времени по возрастанию и вывести их в выходной файл с именем outputName,
     * сохраняя формат ЧЧ:ММ:СС. Одинаковые моменты времени выводить друг за другом. Пример:
     *
     * 00:40:31
     * 07:26:57
     * 10:00:03
     * 13:15:19
     * 13:15:19
     * 19:56:14
     *
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static public void sortTimes(String inputName, String outputName) {
        throw new NotImplementedError();
    }

    /**
     * Сортировка адресов
     *
     * Средняя
     *
     * Во входном файле с именем inputName содержатся фамилии и имена жителей города с указанием улицы и номера дома,
     * где они прописаны. Пример:
     *
     * Петров Иван - Железнодорожная 3
     * Сидоров Петр - Садовая 5
     * Иванов Алексей - Железнодорожная 7
     * Сидорова Мария - Садовая 5
     * Иванов Михаил - Железнодорожная 7
     *
     * Людей в городе может быть до миллиона.
     *
     * Вывести записи в выходной файл outputName,
     * упорядоченными по названию улицы (по алфавиту) и номеру дома (по возрастанию).
     * Людей, живущих в одном доме, выводить через запятую по алфавиту (вначале по фамилии, потом по имени). Пример:
     *
     * Железнодорожная 3 - Петров Иван
     * Железнодорожная 7 - Иванов Алексей, Иванов Михаил
     * Садовая 5 - Сидоров Петр, Сидорова Мария
     *
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static public void sortAddresses(String inputName, String outputName) throws IOException {
        File inputFile = new File(inputName);
        FileWriter output = new FileWriter(new File(outputName));
        Scanner inputScan = new Scanner(inputFile);
        List<List<String>> inputList = new ArrayList<>();
        Map<String, List<String>> outputMap = new TreeMap<>();
        List<String> namesList;
        String local;
        do {
            local = inputScan.nextLine();
            if (!local.matches("^[А-я]+ [А-я]+ - [А-я]+ \\d+$"))
                throw new IllegalArgumentException();
            String[] addresses = local.split(" - ");
            inputList.add(Arrays.asList(addresses[0],addresses[1]));
        } while (inputScan.hasNext());
        inputScan.close();
        for (int i = 0; i < inputList.size(); i++) {
            List<String> inputLocal = inputList.get(i);
            String name = inputLocal.get(0);
            String address = inputLocal.get(1);
            if (outputMap.containsKey(address)) {
                outputMap.get(address).add(name);

            } else {
                List<String> names = new ArrayList<>();
                names.add(name);
                outputMap.put(address, names);
            }
        }
        Iterator<Map.Entry<String, List<String>>> itr = outputMap.entrySet().iterator();
        while (itr.hasNext()) {
            Map.Entry<String, List<String>> entry = itr.next();
            namesList = outputMap.get(entry.getKey());
            StringBuilder namesLocal = new StringBuilder();
            if (outputMap.get(entry.getKey()).size() > 1) {
                for (int i = 0; i < namesList.size() - 1; i++) {
                    namesLocal.append(namesList.toArray()[i]).append(", ");
                }
            }
            namesLocal.append(namesList.toArray()[namesList.size() - 1]);
            output.write(entry.getKey() + " - " + namesLocal + "\n");
        }
        output.close();
    }

    /**
     * Сортировка температур
     *
     * Средняя
     * (Модифицированная задача с сайта acmp.ru)
     *
     * Во входном файле заданы температуры различных участков абстрактной планеты с точностью до десятых градуса.
     * Температуры могут изменяться в диапазоне от -273.0 до +500.0.
     * Например:
     *
     * 24.7
     * -12.6
     * 121.3
     * -98.4
     * 99.5
     * -12.6
     * 11.0
     *
     * Количество строк в файле может достигать ста миллионов.
     * Вывести строки в выходной файл, отсортировав их по возрастанию температуры.
     * Повторяющиеся строки сохранить. Например:
     *
     * -98.4
     * -12.6
     * -12.6
     * 11.0
     * 24.7
     * 99.5
     * 121.3
     */
    static public void sortTemperatures(String inputName, String outputName) throws IOException {
        Reader fileReader = new FileReader(inputName);
        ArrayList<Double> temperatures = new ArrayList<>();
        BufferedReader buffReader = new BufferedReader(fileReader);
        FileWriter fileWriter = new FileWriter(new File(outputName));
        do {
            String local = buffReader.readLine();
            if (local == null)
                break;
            if (Double.valueOf(local) < -273.0 || Double.valueOf(local) > 500.0)
                throw new IllegalArgumentException();
            temperatures.add(Double.valueOf(local));
        } while (true);
        Collections.sort(temperatures);
        for (int i = 0; i <= temperatures.size() - 1; i++) {
            if (i != temperatures.size() - 1)
                fileWriter.write(temperatures.get(i).toString() + "\n");
            else fileWriter.write(temperatures.get(i).toString());
        }
        fileWriter.close();
    }

    /**
     * Сортировка последовательности
     *
     * Средняя
     * (Задача взята с сайта acmp.ru)
     *
     * В файле задана последовательность из n целых положительных чисел, каждое в своей строке, например:
     *
     * 1
     * 2
     * 3
     * 2
     * 3
     * 1
     * 2
     *
     * Необходимо найти число, которое встречается в этой последовательности наибольшее количество раз,
     * а если таких чисел несколько, то найти минимальное из них,
     * и после этого переместить все такие числа в конец заданной последовательности.
     * Порядок расположения остальных чисел должен остаться без изменения.
     *
     * 1
     * 3
     * 3
     * 1
     * 2
     * 2
     * 2
     */
    static public void sortSequence(String inputName, String outputName) {
        throw new NotImplementedError();
    }

    /**
     * Соединить два отсортированных массива в один
     *
     * Простая
     *
     * Задан отсортированный массив first и второй массив second,
     * первые first.size ячеек которого содержат null, а остальные ячейки также отсортированы.
     * Соединить оба массива в массиве second так, чтобы он оказался отсортирован. Пример:
     *
     * first = [4 9 15 20 28]
     * second = [null null null null null 1 3 9 13 18 23]
     *
     * Результат: second = [1 3 4 9 9 13 15 20 23 28]
     */
    static <T extends Comparable<T>> void mergeArrays(T[] first, T[] second) {
        throw new NotImplementedError();
    }
}
