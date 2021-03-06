package localsearch.function.math;

import localsearch.function.abstract_function.FuncTwoElement;
import localsearch.function.wrapper.FuncConst;
import localsearch.function.wrapper.FuncVar;
import localsearch.model.IFunction;
import localsearch.model.variable.VarIntLS;

import java.util.Set;

/**
 * @author Hien Hoang (hienhoang2702@gmail.com)
 */
public class FuncMod extends FuncTwoElement {

    public FuncMod(IFunction f1, IFunction f2) {
        super(f1, f2);
    }

    public FuncMod(IFunction function, VarIntLS variable) {
        super(function, new FuncVar(variable));
    }

    public FuncMod(IFunction function, double c) {
        super(function, new FuncConst(c, function.getLocalSearchManager()));
    }

    public FuncMod(VarIntLS var1, VarIntLS var2) {
        super(new FuncVar(var1), new FuncVar(var2));
    }

    public FuncMod(VarIntLS variable, double c) {
        super(new FuncVar(variable), new FuncConst(c, variable.getLocalSearchManager()));
    }


    public FuncMod(VarIntLS variable, IFunction function) {
        super(new FuncVar(variable), function);
    }

    public FuncMod(double c, IFunction function) {
        super(new FuncConst(c, function.getLocalSearchManager()), function);
    }

    public FuncMod(double c, VarIntLS variable) {
        super(new FuncConst(c, variable.getLocalSearchManager()), new FuncVar(variable));
    }

    @Override
    public double getAssignDelta(VarIntLS[] variables, int[] values) {
        for (VarIntLS variable : variables) {
            if (variableSet.contains(variable)) {
                double v1 = f1.getValue() + f1.getAssignDelta(variables, values);
                double v2 = f2.getValue() + f2.getAssignDelta(variables, values);
                return v1 % v2 - value;
            }
        }
        return 0;
    }

    @Override
    public void propagate(Set<VarIntLS> variables) {
        initPropagate();
    }

    @Override
    public void initPropagate() {
        oldValue = value;
        value = f1.getValue() % f2.getValue();
    }
}
