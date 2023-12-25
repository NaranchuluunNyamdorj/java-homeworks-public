package homework6;

class ComplexNum < T extends Number > {
    private T re;
    private T im;

    public ComplexNum (T r, T i){
        this.re = r;
        this.im = i;
    }

    public T getReal() {
        return re;
    }

    public void setReal(T real) {
        this.re = real;
    }

    public T getImaginary() {
        return im;
    }

    public void setImaginary(T imaginary) {
        this.im = imaginary;
    }

    public ComplexNum < Integer > add (ComplexNum < T > c){
        return new ComplexNum <Integer> (
                re.intValue () + c.re.intValue (),
                im.intValue () + c.im.intValue ()
        );
    }

    public ComplexNum < Integer > subtract (ComplexNum < T > c){
        return new ComplexNum <Integer> (
                re.intValue () - c.re.intValue (),
                im.intValue () - c.im.intValue ()
        );
    }

    public String toString (){
        return re + "+" + im + "i";
    }
}

public class ComplexNumber{
    public static void main (String[]args){
        ComplexNum < Integer > c1 = new ComplexNum <> (2, 3);
        ComplexNum < Integer > c2 = new ComplexNum <> (1, 2);
        ComplexNum < Integer > c3 = c1.add (c2);
        ComplexNum < Integer > c4 = c1.subtract (c2);

        System.out.println ("Нийлбэр:");
        System.out.println (c1 +" + " +c2 +" = " +c3);
        System.out.println ("Ялгавар:");
        System.out.println (c1 +" - " +c2 +" = " +c4);
    }
}