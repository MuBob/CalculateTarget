package com.ciii.bobmu.calculate24.library;

public class Symble {

    private interface Calculate{
        String toString();
        Number run(Number base, Number runner) throws ZeroDividedException;
    }
    public enum CalculateType implements Calculate{
        ADD{
            @Override
            public Number run(Number base, Number runner){
                    return new Number(base.getNumber()+runner.getNumber());
            }

            @Override
            public String toString() {
                return "+";
            }
        },
        SUBTRACT{
            @Override
            public Number run(Number base, Number runner){
                return new Number(base.getNumber()-runner.getNumber());
            }

            @Override
            public String toString() {
                return "-";
            }
        },
        MULTIPLY{
            @Override
            public Number run(Number base, Number runner){
                return new Number(base.getNumber()*runner.getNumber());
            }

            @Override
            public String toString() {
                return "x";
            }
        },
        DIVIDE{
            @Override
            public Number run(Number base, Number runner)  throws ZeroDividedException{
                if (runner.isZero()) throw new ZeroDividedException();
                return new Number(base.getNumber()/runner.getNumber());
            }

            @Override
            public String toString() {
                return "/";
            }
        },
        ;

    }
    public static final CalculateType[] TYPES={
            CalculateType.ADD,
            CalculateType.SUBTRACT,
            CalculateType.MULTIPLY,
            CalculateType.DIVIDE,
    };


}
