# Merge-sort
Performs merge sort on files with string or numeric data

ТЗ для поступления на бесплатную учебу-стажировку в Ш.И.Ф.Т. от компании ЦФТ

Данная программа предназначена для слияния двух и более файлов, методом сортировки слиянием. 

Входные файлы должны быть текстовыми (*.txt) и содержать данные одного из двух видов: целые числа или строки. Данные должны быть записаны в столбик, где каждая строка файла – это новый элемент. Строки могут содержать любые не пробельные символы. 

Файлы должны быть предварительно отсортированы. Программа поддерживает два типа сортировки: по возрастанию и по убыванию.

Результатом работы программы будет являться новый файл с объединенным содержанием входных файлов, отсортированных по возрастанию или по убыванию путем сортировки слиянием всех входных файлов. 

Если во входных файлах с целыми числами будет обнаружена строка, она будет записана в результирующий файл в том месте, где она была обнаружена. Числа, записанные с пробелами, точками или с запятыми воспринимаются программой как строка.

Максимальное число, которое распознает программа это 9 223 372 036 854 775 807, а минимальное это -9 223 372 036 854 775 808. Числа больше максимального и меньше минимального будут восприниматься программой как строки.

Если во входных файлах нарушен порядок сортировки, то в результирующем файле также будут ошибки сортировки после элемента, который нарушал порядок сортировки во входном файле. 

Запуск программы через командную строку.

java -jar Merge-Sort.jar  <режим сортировки> <тип данных> <имя выходного файла> <имя входного файла1> <имя входного файла2> <…>

Параметры программы задаются при запуске через аргументы командной строки по порядку и пишутся через пробел:

1.	Режим сортировки: 
-a – по возрастанию, -d – по убыванию. Необязательный параметр, если он не указан, то по умолчанию сортируем по возрастанию.

2.	Тип данных:
 -i – целые числа, -s – строки. Обязательный параметр.
 
3.	Имя выходного файла -  обязательно.

4.	Остальные параметры – имена входных файлов, не менее одного обязательно.

Примеры запуска из командной строки:

java –jar Merge-Sort.jar -i -a out.txt in1.txt (для целых чисел по возрастанию)

java –jar Merge-Sort.jar -s out.txt in1.txt in2.txt (для строк по возрастанию)

java –jar Merge-Sort.jar -d -s out.txt in1.txt in2.txt in3.txt (для строк по убыванию)

