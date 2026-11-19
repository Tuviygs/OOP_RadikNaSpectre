package ru.nsu.oop.tuviygs.studentsGrading;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ru.nsu.oop.tuviygs.studentsGrading.enums.ControlType;
import ru.nsu.oop.tuviygs.studentsGrading.enums.Mark;

/**
 * зачётная книжка.
 */
public class Gradebook {

    /**
     * словарь оценок по семестрам.
     */
    private Map<Integer, List<ControlGradeRecord>> termMap;

    /**
     * текущий семестр.
     */
    int currentTerm;

    /**
     * на бюджете ли.
     */
    boolean onBudget;


    /**
     * создание зачётной книжки.
     *
     * @param onBudget бюджет.
     */
    public Gradebook(boolean onBudget) {
        this.termMap = new HashMap<Integer, List<ControlGradeRecord>>();
        this.currentTerm = 0;
        this.switchToNextTerm();
        this.onBudget = onBudget;
    }

    /**
     * наступление нового семестра.
     */
    public void switchToNextTerm() {
        this.currentTerm++;
        this.termMap.put(this.currentTerm, new ArrayList<ControlGradeRecord>());
        if (currentTerm > 2) {
            this.onBudget = possibilityOfBudgetTransferring();
        }
    }

    /**
     * поставить оценку.
     *
     * @param subjectName дисциплина.
     * @param type тип контроля
     * @param grade оценка
     */
    public void setNewGrade(String subjectName, ControlType type, Mark grade) {
        if (type.equals(ControlType.CREDIT)) {
            if (grade.getValue() > 1) {
                throw new GradebookException("Зачёт может быть только сдан или нет");
            }
        } else {
            if (grade.getValue() <= 1) {
                throw new GradebookException("Отметка не может быть меньше"
                    + ", чем \"удовлетворительно\"");
            }
        }
        this.termMap.get(currentTerm).
            add(new ControlGradeRecord(subjectName, type, grade));
    }

    /**
     * получение среднего балла.
     */
    public double getAverageGrade() {
        int marksCount = 0;
        int marksSum = 0;
        for (Map.Entry<Integer, List<ControlGradeRecord>> termGrades
            : termMap.entrySet()) {
            for (ControlGradeRecord gradeRecord : termGrades.getValue()) {
                if (!gradeRecord.type().equals(ControlType.CREDIT)) {
                    marksCount++;
                    marksSum += gradeRecord.mark().getValue();
                }
            }
        }

        return (double) marksSum / marksCount;
    }


    public boolean possibilityOfBudgetTransferring() {
        boolean possibility = true;
        if (this.onBudget) {
            if (currentTerm > )
            for (ControlGradeRecord gradeRecord : termMap.get(currentTerm)alue()) {
                \
            }
        } else {
            throw new GradebookException("Student is already on a bubget");
        }


        return possibility;
    }

    private boolean wellCompletedTerm(int termNumber) {

    }


}
