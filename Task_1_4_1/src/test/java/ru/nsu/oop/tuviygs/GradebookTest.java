package ru.nsu.oop.tuviygs;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.nsu.oop.tuviygs.studentsGrading.enums.ControlType;
import ru.nsu.oop.tuviygs.studentsGrading.enums.Mark;
import ru.nsu.oop.tuviygs.studentsGrading.gradebookContent.Gradebook;
import ru.nsu.oop.tuviygs.studentsGrading.gradebookContent.GradebookException;
import ru.nsu.oop.tuviygs.studentsGrading.utils.GradebookAnalyzer;

class GradebookTest {

    private Gradebook gradebook;
    private GradebookAnalyzer analyzer;

    @BeforeEach
    void setUp() {
        gradebook = new Gradebook(true);
        analyzer = new GradebookAnalyzer();
    }

    @Test
    void testGradebookValidationExceptions() {
        // Зачёту нельзя поставить 5
        assertThrows(GradebookException.class, () ->
            gradebook.setNewGrade("Физкультура", ControlType.CREDIT, Mark.EXCELLENT)
        );

        // Экзамену нельзя поставить PASSED
        assertThrows(GradebookException.class, () ->
            gradebook.setNewGrade("Матан", ControlType.EXAM, Mark.PASSED)
        );
    }

    @Test
    void testAverageGradeCalculation() {
        assertEquals(0.0, analyzer.getAverageGrade(gradebook));

        gradebook.setNewGrade("Матан", ControlType.EXAM, Mark.EXCELLENT); // 5
        gradebook.setNewGrade("АиСД", ControlType.DIFFERENTIATED_CREDIT, Mark.GOOD); // 4
        // Зачёты и тесты не должны влиять на средний балл
        gradebook.setNewGrade("Физра", ControlType.CREDIT, Mark.PASSED);
        gradebook.setNewGrade("Контрольная", ControlType.TEST, Mark.SATISFACTORY);

        assertEquals(4.5, analyzer.getAverageGrade(gradebook), 0.001);
    }

    @Test
    void testBudgetTransferring() {
        Gradebook contractStudent = new Gradebook(false);

        // 1 семестр - рано переводиться
        contractStudent.setNewGrade("Матан", ControlType.EXAM, Mark.EXCELLENT);
        assertFalse(analyzer.possibilityOfBudgetTransferring(contractStudent));

        // 2 семестр - всё равно рано (нужно 2 закрытых семестра, т.е. минимум 3-й текущий)
        contractStudent.switchToNextTerm();
        contractStudent.setNewGrade("АиСД", ControlType.EXAM, Mark.GOOD);
        contractStudent.setNewGrade("История", ControlType.CREDIT, Mark.PASSED);
        assertFalse(analyzer.possibilityOfBudgetTransferring(contractStudent));

        // 3 семестр - оба прошлых семестра на 4 и 5 -> перевод успешен
        contractStudent.switchToNextTerm();
        assertTrue(analyzer.possibilityOfBudgetTransferring(contractStudent));
        assertTrue(contractStudent.isOnBudget());
    }

    @Test
    void testBudgetTransferringFailsWithSatisfactoryMark() {
        Gradebook contractStudent = new Gradebook(false);
        contractStudent.setNewGrade("Матан", ControlType.EXAM, Mark.SATISFACTORY); // тройка

        contractStudent.switchToNextTerm();
        contractStudent.setNewGrade("АиСД", ControlType.EXAM, Mark.EXCELLENT);

        contractStudent.switchToNextTerm();
        assertFalse(analyzer.possibilityOfBudgetTransferring(contractStudent));
        assertFalse(contractStudent.isOnBudget());
    }

    @Test
    void testIncreasedScholarship() {
        // В 1 семестре стипендии нет
        assertFalse(analyzer.possibilityOfIncreasedScholarship(gradebook));

        gradebook.setNewGrade("Матан", ControlType.EXAM, Mark.EXCELLENT);
        gradebook.setNewGrade("Физра", ControlType.CREDIT, Mark.PASSED);
        gradebook.switchToNextTerm();

        // После исправления бага с Mark.PASSED тест пройдёт
        assertTrue(analyzer.possibilityOfIncreasedScholarship(gradebook));

        // Если появляется четвёрка - повышенная стипендия слетает
        gradebook.setNewGrade("ООП", ControlType.EXAM, Mark.GOOD);
        gradebook.switchToNextTerm();
        assertFalse(analyzer.possibilityOfIncreasedScholarship(gradebook));
    }

    @Test
    void testRedDiplomaPossibility() {
        // На старте красный диплом возможен
        assertTrue(analyzer.possibilityOfRedDiploma(gradebook));

        // Тройка сразу лишает красного диплома
        gradebook.setNewGrade("Физика", ControlType.EXAM, Mark.SATISFACTORY);
        assertFalse(analyzer.possibilityOfRedDiploma(gradebook));
    }

    @Test
    void testRedDiplomaOverwriteGradeInLaterTerm() {
        // Получил 3 в 1 семестре
        gradebook.setNewGrade("Физика", ControlType.EXAM, Mark.SATISFACTORY);
        assertFalse(analyzer.possibilityOfRedDiploma(gradebook));

        // Пересдал на 5 в следующем семестре -> снова претендует на красный диплом
        gradebook.switchToNextTerm();
        gradebook.setNewGrade("Физика", ControlType.EXAM, Mark.EXCELLENT);
        assertTrue(analyzer.possibilityOfRedDiploma(gradebook));

        // ВКР на 4 лишает красного диплома
        gradebook.setNewGrade("Диплом", ControlType.FQW_PROTECTION, Mark.GOOD);
        assertFalse(analyzer.possibilityOfRedDiploma(gradebook));
    }
}
