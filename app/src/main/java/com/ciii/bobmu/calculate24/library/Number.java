package com.ciii.bobmu.calculate24.library;

public class Number {

    private double number;

    public Number(double number) {
        this.number = number;
    }

    public double getNumber() {
        return number;
    }

    public void setNumber(double number) {
        this.number = number;
    }

    public boolean isEqual(double target){
        return this.number==target;
    }

    public boolean isZero(){
        return isEqual(0);
    }


    public Number calculate(Symble.CalculateType type, Number number) throws ZeroDividedException{
        return type.run(this, number);
    }



    @Override
    public String toString() {
        return String.valueOf(number);
    }

}
