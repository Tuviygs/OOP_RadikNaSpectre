package ru.nsu.oop.tuviygs.studentsGrading.gradebookContent;

import ru.nsu.oop.tuviygs.studentsGrading.enums.ControlType;
import ru.nsu.oop.tuviygs.studentsGrading.enums.Mark;

/**
 * оценивание контроля.
 */
public record ControlGradeRecord(

    String subjectName,
    ControlType type,
    Mark mark
    ) {}


