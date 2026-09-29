package ru.nsu.oop.tuviygs.Expressions;

/**
 * бинарные операции.
 */
public class BinExpression extends Expression {

    /**
     * первое выражение.
     */
    protected Expression expression1;

    /**
     * второе выражение.
     */
    protected Expression expression2;

    public BinExpression(String stroke) {
        super(stroke);
    }

    /**
     * задание операции.
     *
     * @param expression1 - первое выражение.
     * @param expression2 - второе выражение
     */
    public BinExpression(Expression expression1, Expression expression2) {
        super();
        this.expression1 = expression1;
        this.expression2 = expression2;

    }

    /**
     * получение первого выражения.
     */
    public Expression getExpression1() {
        return this.expression1;
    }

    /**
     * получение второго выражения.
     */
    public Expression getExpression2() {
        return this.expression2;
    }
}
