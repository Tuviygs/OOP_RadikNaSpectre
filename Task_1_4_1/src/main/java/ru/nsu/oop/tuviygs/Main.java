package ru.nsu.oop.tuviygs;

import java.util.List;
import java.util.Scanner;
import ru.nsu.oop.tuviygs.studentsGrading.gradebookContent.ControlGradeRecord;
import ru.nsu.oop.tuviygs.studentsGrading.gradebookContent.Gradebook;
import ru.nsu.oop.tuviygs.studentsGrading.utils.GradebookAnalyzer;
import ru.nsu.oop.tuviygs.studentsGrading.gradebookContent.GradebookException;
import ru.nsu.oop.tuviygs.studentsGrading.enums.ControlType;
import ru.nsu.oop.tuviygs.studentsGrading.enums.Mark;

/**
 * мейн класс.
 */
public class Main {


    /**
     * мейн.
     *
     * @param args аргументы.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GradebookAnalyzer analyzer = new GradebookAnalyzer();

        System.out.println("=== Создание зачётной книжки ===");
        System.out.print("Студент на бюджете? (true / false): ");
        boolean onBudget = Boolean.parseBoolean(scanner.nextLine().trim());

        Gradebook gradebook = new Gradebook(onBudget);
        System.out.println("Зачётка создана! Текущий семестр: " + gradebook.getCurrentTerm()
            + ", бюджет: " + gradebook.isOnBudget());

        while (true) {
            printMenu(gradebook);
            System.out.print("Выберите действие: ");
            String choice = scanner.nextLine().trim();

            try {
                switch (choice) {
                    case "1" -> addGradeInteractive(scanner, gradebook);
                    case "2" -> {
                        gradebook.switchToNextTerm();
                        System.out.println("-> Перешли на " + gradebook.getCurrentTerm()
                            + " семестр.");
                    }
                    case "3" -> printGrades(gradebook);
                    case "4" -> System.out.printf("-> Текущий средний балл: %.2f%n",
                        analyzer.getAverageGrade(gradebook));
                    case "5" -> {
                        boolean result = analyzer.possibilityOfBudgetTransferring(gradebook);
                        System.out.println("-> Перевод на бюджет возможен / выполнен: " + result
                            + " (текущий статус бюджета: " + gradebook.isOnBudget() + ")");
                    }
                    case "6" -> System.out.println("-> Повышенная стипендия в этом семестре: "
                        + analyzer.possibilityOfIncreasedScholarship(gradebook));
                    case "7" -> System.out.println("-> Красный диплом возможен: "
                        + analyzer.possibilityOfRedDiploma(gradebook));
                    case "0" -> {
                        System.out.println("Выход из программы.");
                        return;
                    }
                    default -> System.out.println("Неизвестная команда, попробуйте снова.");
                }
            } catch (GradebookException e) {
                System.out.println("[Ошибка зачётки]: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("[Ошибка ввода]: неверный номер типа контроля или оценки.");
            }
        }
    }

    /**
     * меню действий.
     *
     * @param gradebook зачётка
     */
    private static void printMenu(Gradebook gradebook) {
        System.out.println("\n-----------------------------------------");
        System.out.println("Семестр: " + gradebook.getCurrentTerm()
            + " | Бюджет: " + (gradebook.isOnBudget() ? "Да" : "Нет"));
        System.out.println("1. Поставить оценку в текущий семестр");
        System.out.println("2. Перейти на следующий семестр");
        System.out.println("3. Посмотреть все оценки");
        System.out.println("4. Узнать средний балл");
        System.out.println("5. Проверить перевод на бюджет");
        System.out.println("6. Проверить повышенную стипендию");
        System.out.println("7. Проверить шанс на красный диплом");
        System.out.println("0. Выход");
    }

    /**
     * выбор действия.
     *
     * @param scanner сканер
     * @param gradebook зачётка
     */
    private static void addGradeInteractive(Scanner scanner, Gradebook gradebook) {
        System.out.print("Введите название предмета: ");
        String subject = scanner.nextLine().trim();

        System.out.println("Выберите тип контроля:");
        System.out.println("  1. TEST");
        System.out.println("  2. EXAM");
        System.out.println("  3. DIFFERENTIATED_CREDIT");
        System.out.println("  4. CREDIT");
        System.out.println("  5. PRACTICE_REPORT_PROTECTION");
        System.out.println("  6. FQW_PROTECTION");
        System.out.print("Номер типа контроля: ");

        ControlType type = switch (scanner.nextLine().trim()) {
            case "1" -> ControlType.TEST;
            case "2" -> ControlType.EXAM;
            case "3" -> ControlType.DIFFERENTIATED_CREDIT;
            case "4" -> ControlType.CREDIT;
            case "5" -> ControlType.PRACTICE_REPORT_PROTECTION;
            case "6" -> ControlType.FQW_PROTECTION;
            default -> throw new IllegalArgumentException();
        };

        System.out.println("Выберите оценку:");
        System.out.println("  1. EXCELLENT (5)");
        System.out.println("  2. GOOD (4)");
        System.out.println("  3. SATISFACTORY (3)");
        System.out.println("  4. UNSATISFACTORY (2)");
        System.out.println("  5. PASSED (1)");
        System.out.println("  6. FAILED (0)");
        System.out.print("Номер оценки: ");

        Mark mark = switch (scanner.nextLine().trim()) {
            case "1" -> Mark.EXCELLENT;
            case "2" -> Mark.GOOD;
            case "3" -> Mark.SATISFACTORY;
            case "4" -> Mark.UNSATISFACTORY;
            case "5" -> Mark.PASSED;
            case "6" -> Mark.FAILED;
            default -> throw new IllegalArgumentException();
        };

        gradebook.setNewGrade(subject, type, mark);
        System.out.println("-> Оценка успешно добавлена!");
    }

        /**
         * вывести оценки.
         *
         * @param gradebook зачётка
         */
    private static void printGrades(Gradebook gradebook) {
        System.out.println("=== Оценки в зачётке ===");
        for (int term = 1; term <= gradebook.getCurrentTerm(); term++) {
            System.out.println("Семестр " + term + ":");
            List<ControlGradeRecord> records = gradebook.getTermGrades(term);
            if (records == null || records.isEmpty()) {
                System.out.println("  (оценок нет)");
            } else {
                for (ControlGradeRecord r : records) {
                    System.out.println("  - " + r.subjectName()
                        + " [" + r.type() + "]: " + r.mark());
                }
            }
        }
    }
}