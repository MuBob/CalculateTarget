package com.ciii.bobmu.calculate24.library;

import java.util.LinkedList;

public class Calculate {
    private Number[] n;
    private int target;

    private LinkedList<Number> numbers;
    private LinkedList<Symble.CalculateType> symbles;

    public Calculate(Number[] n, int target) {
        this.n = n;
        this.target=target;
        numbers=new LinkedList<>();
        symbles=new LinkedList<>();
    }

    public String run(){
        symbles.clear();
        numbers.clear();
        boolean result=pick(n, n.length, 0);
//        System.out.println("numbers="+numbers+", symbles="+symbles);
        String str=null;
        if (result){
            StringBuffer text=new StringBuffer();
           Number number=null;
            Symble.CalculateType symble=null;
            do{
                number = numbers.pollFirst();
                if (number!=null){
                    text.append(number);
                    symble=symbles.pollFirst();
                    text.append(symble==null?" ":symble);
                }

            }while (number!=null);
            text.append("=");
            text.append(target);
//            System.out.println("text="+text.toString());
            str = text.toString();
        }
        return str;
    }

    private boolean pick(Number[] array, int size, int index){
        boolean result=false;
        if (size==index){
           Number[] m=new Number[numbers.size()];
            for (int i = 0; i < numbers.size(); i++) {
                m[i]=numbers.get(i);
            }
            result=calculateArray(m, target);
//            System.out.println("calculate result="+result+", numbers"+numbers+", symbles="+symbles);

        }else{
            for (int i = 0; i < array.length; i++) {
                if (!numbers.contains(array[i])){
                    numbers.offerLast(array[i]);
                    result=pick(array, size, index+1);
                    if (result) break;
                    numbers.pollLast();
                }
            }
        }
        return result;
    }


    public boolean calculateArray(Number[] n, int target){
        boolean result=false;
        if (n.length>=2){
           Number[] m=new Number[n.length-1];
           Number[] result4=calculateTwo(n[0], n[1]);
            for (int j = 0; j < result4.length; j++) {
                m[0]=result4[j];
                if (m[0]==null) break;
                for (int i = 1; i < m.length; i++) {
                    m[i]=n[i+1];
                }
                symbles.offerLast(Symble.TYPES[j]);
                result=calculateArray(m, target);
                if (result) break;
                symbles.pollLast();
            }
        }else{
            result=n[0].isEqual(target);
        }
       return result;
    }

    private Number[] calculateTwo(Number n1,Number n2){
       Number[] result=new Number[4];
        for (int type = 0; type < Symble.TYPES.length; type++) {
            try {
                result[type]= n1.calculate(Symble.TYPES[type], n2);
            } catch (ZeroDividedException e) {
                e.printStackTrace();
                result[type]=null;
            }
        }
        return result;
    }

}
