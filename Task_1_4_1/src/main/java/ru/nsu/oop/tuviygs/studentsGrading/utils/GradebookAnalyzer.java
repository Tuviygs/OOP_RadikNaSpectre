package ru.nsu.oop.tuviygs.studentsGrading.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ru.nsu.oop.tuviygs.studentsGrading.enums.ControlType;
import ru.nsu.oop.tuviygs.studentsGrading.enums.Mark;
import ru.nsu.oop.tuviygs.studentsGrading.gradebookContent.ControlGradeRecord;
import ru.nsu.oop.tuviygs.studentsGrading.gradebookContent.Gradebook;

public class GradebookAnalyzer {

    /**
     * Общее количество дифференцированных итоговых оценок в дипломе за 8 семестров
     * (19 экзаменов + 35 дифф. зачетов + 3 защиты практики + 1 защита ВКР).
     */
    private static final int TOTAL_DIPLOMA_GRADES = 58;


    /**
     * получение среднего балла.
     *
     * @param gradebook зачётка
     */
    public double getAverageGrade(Gradebook gradebook) {
        int marksCount = 0;
        int marksSum = 0;
        for (int i = 1; i <= gradebook.getCurrentTerm(); i++) {
            List<ControlGradeRecord> termGrades = gradebook.getTermGrades(i);
            for (ControlGradeRecord gradeRecord : termGrades) {
                if (!gradeRecord.type().equals(ControlType.CREDIT)
                && !gradeRecord.type().equals(ControlType.TEST)) {
                    marksCount++;
                    marksSum += gradeRecord.mark().getValue();
                }
            }
        }
        if (marksCount == 0) {
            return 0.0;
        }
        return (double) marksSum / marksCount;
    }


    /**
     * возможность перехода на бюджет.
     *
     * @param gradebook зачётка
     */
    public boolean possibilityOfBudgetTransferring(Gradebook gradebook) {
        if (!gradebook.isOnBudget()) {
            if (gradebook.getCurrentTerm() > 2) {
                if (!wellPassedTerm(gradebook, gradebook.getCurrentTerm() - 1)) {
                    return false;
                }
                if (!wellPassedTerm(gradebook, gradebook.getCurrentTerm() - 2)) {
                    return false;
                }
                gradebook.setOnBudget();
            } else {
                return false;
            }

        }

        return true;
    }

    /**
     * проверка закрытия семестра на 4/5;
     *
     * @param gradebook зачётка
     * @param termNumber номер семестра
     */
    private boolean wellPassedTerm(Gradebook gradebook, int termNumber) {
        for (ControlGradeRecord gradeRecord : gradebook.getTermGrades(termNumber)) {
            if (gradeRecord.type().equals(ControlType.CREDIT)) {
                if (gradeRecord.mark().equals(Mark.FAILED)) {
                    return false;
                }
            } else if (gradeRecord.type().equals(ControlType.EXAM)
                || gradeRecord.type().equals(ControlType.DIFFERENTIATED_CREDIT)
                || gradeRecord.type().equals(ControlType.FQW_PROTECTION)
                || gradeRecord.type().equals(ControlType.PRACTICE_REPORT_PROTECTION)) {
                if (gradeRecord.mark().getValue() < 4) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Прогнозирование возможности получения красного диплома.
     *
     * @param gradebook зачётка
     * @return true, если красный диплом ещё возможен
     */
    public boolean possibilityOfRedDiploma(Gradebook gradebook) {
        Map<String, ControlGradeRecord> finalGrades = new HashMap<>();

        for (int i = 1; i <= gradebook.getCurrentTerm(); i++) {
            List<ControlGradeRecord> termGrades = gradebook.getTermGrades(i);
            if (termGrades == null) {
                continue;
            }

            for (ControlGradeRecord record : termGrades) {
                ControlType type = record.type();
                Mark mark = record.mark();

                if (type == ControlType.TEST) {
                    continue;
                }

                if (type == ControlType.CREDIT) {
                    if (mark == Mark.FAILED) {
                        return false;
                    }
                    continue;
                }

                finalGrades.put(record.subjectName(), record);
            }
        }

        int excellentCount = 0;

        for (ControlGradeRecord record : finalGrades.values()) {
            Mark mark = record.mark();

            if (mark.getValue() < 4) {
                return false;
            }

            if (record.type() == ControlType.FQW_PROTECTION && mark != Mark.EXCELLENT) {
                return false;
            }

            if (mark == Mark.EXCELLENT) {
                excellentCount++;
            }
        }

        int currentTotal = finalGrades.size();

        int totalExpected = Math.max(TOTAL_DIPLOMA_GRADES, currentTotal);
        int remainingGrades = totalExpected - currentTotal;

        double maxPossibleRatio = (double) (excellentCount + remainingGrades) / totalExpected;
        return maxPossibleRatio >= 0.75;
    }

    /**
     * возможность получения повышенной стипендии.
     *
     * @param gradebook зачётка
     */
    public boolean possibilityOfIncreasedScholarship(Gradebook gradebook) {
        if (!gradebook.isOnBudget()) {
            return false;
        }

        if (gradebook.getCurrentTerm() == 1) {
            return false;
        }
        List<ControlGradeRecord> termGrades = gradebook.getTermGrades(gradebook
            .getCurrentTerm() - 1);
        for (ControlGradeRecord grade : termGrades) {
            if (grade.type().equals(ControlType.CREDIT)) {
                if (!grade.mark().equals(Mark.PASSED)) {
                    return false;
                }
            } else if (!grade.type().equals(ControlType.TEST)) {
                if (!grade.mark().equals(Mark.EXCELLENT)) {
                    return false;
                }
            }
        }
        return true;
    }



}
