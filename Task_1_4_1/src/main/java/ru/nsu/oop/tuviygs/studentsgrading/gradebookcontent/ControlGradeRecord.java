package ru.nsu.oop.tuviygs.studentsgrading.gradebookcontent;

import ru.nsu.oop.tuviygs.studentsgrading.enums.ControlType;
import ru.nsu.oop.tuviygs.studentsgrading.enums.Mark;

/**
 * оценивание контроля.
 */
public record ControlGradeRecord(

    String subjectName,
    ControlType type,
    Mark mark
) {}


