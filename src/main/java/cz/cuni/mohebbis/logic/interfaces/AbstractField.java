package cz.cuni.mohebbis.logic.interfaces;

public abstract class AbstractField<T> {

    public abstract T Add(T x);
    public abstract T Subtract(T x);
    public abstract T Multiply(T x);
    public abstract T Divide(T x);
    public abstract T Reciprocal();
    public abstract T Negation();
    public abstract T GetZero();
    public abstract T GetOne();
    public abstract String GetString();
    protected boolean _isZero;  // Instance variable

    public boolean IsZero() {
        return _isZero;
    }

}
