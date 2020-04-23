package fr.triangle.pangolin.dataframe;

public abstract class MathColumnOperation {

	public Number op(Column<? extends Number> c) throws ArithmeticException{
		Number var = 0;
		for (Number n : c.liste) {
			var = doOp(var,n);
			if(var == null)
				throwArithExc();
		}
		var = doEndOp(var,c);
		if(var == null)
			throwArithExc();
		return var;
	}
	
	protected void throwArithExc()throws ArithmeticException{
		throw new ArithmeticException("On ne peut pas "+name()+" sur des string");
	}
	
	protected abstract Integer opOnInteger(Integer a, Integer b) throws ArithmeticException;
	protected abstract Double opOnDouble(Double a, Double b) throws ArithmeticException;
	
	protected abstract int endOpOnInteger(Integer a, Column<Integer> c) throws ArithmeticException;
	protected abstract double endOpOnDouble(Double a, Column<Double> c) throws ArithmeticException;

	@SuppressWarnings("unchecked")
	protected Number doEndOp(Number var, Column<? extends Number> c) throws ArithmeticException {
		if (c.type.equals(Integer.class))
			return endOpOnInteger(var.intValue(),(Column<Integer>)c);
		else if (c.type.equals(Double.class))
			return endOpOnDouble(var.doubleValue(),(Column<Double>)c);
		//on a une string
		return null;
	}

	
	protected Number doOp(Number a, Number b) throws ArithmeticException {
		if (a instanceof Integer)
			return opOnInteger(a.intValue(),b.intValue());
		else if (a instanceof Double)
			return opOnDouble(a.doubleValue(), b.doubleValue());
		//on a une string
		return null;
	}
	
	protected String name() {
		return "Generic";
	}
}
