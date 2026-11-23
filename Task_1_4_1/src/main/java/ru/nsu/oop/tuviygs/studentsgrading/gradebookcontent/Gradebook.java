package ru.nsu.oop.tuviygs.studentsgrading.gradebookcontent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ru.nsu.oop.tuviygs.studentsgrading.enums.ControlType;
import ru.nsu.oop.tuviygs.studentsgrading.enums.Mark;

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
    private int currentTerm;

    /**
     * на бюджете ли.
     */
    private boolean onBudget;


    /**
     * создание зачётной книжки.
     *
     * @param onBudget бюджет.
     */
    public Gradebook(boolean onBudget) {
        this.termMap = new HashMap<Integer, List<ControlGradeRecord>>();
        this.currentTerm = 0;
        this.onBudget = onBudget;
        this.switchToNextTerm();
    }

    /**
     * узнать текущий семестр.
     */
    public int getCurrentTerm() {
        return this.currentTerm;
    }

    /**
     * узнать, на бюджете ли.
     */
    public boolean isOnBudget() {
        return this.onBudget;
    }

    /**
     * узнать оценки определённого семестра.
     *
     * @param termNumber номер семестра.
     */
    public List<ControlGradeRecord> getTermGrades(int termNumber) {
        return this.termMap.get(termNumber);
    }

    /**
     * перевод на бюджет.
     */
    public void setOnBudget() {
        this.onBudget = true;
    }

    /**
     * наступление нового семестра.
     */
    public void switchToNextTerm() {
        this.currentTerm++;
        this.termMap.put(this.currentTerm, new ArrayList<ControlGradeRecord>());
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
                    + ", чем \"неудовлетворительно\"");
            }
        }
        this.termMap.get(currentTerm)
            .add(new ControlGradeRecord(subjectName, type, grade));
    }



}
