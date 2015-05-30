package john.my7;

import java.io.Serializable;

/**
 * Created by John on 20.05.2015.
 */
public class Temperature implements Comparable<Temperature>, Serializable{
    private int unit;
    private int fraction;
    public Temperature(int unit, int fraction){
        this.unit = unit;
        this.fraction = fraction;
    }
    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public int getFraction() {
        return fraction;
    }

    public void setFraction(int fraction) {
        this.fraction = fraction;
    }

    public String getUnitString(){
        return Integer.toString(unit);
    }
    public String getFractionString(){
        return Integer.toString(fraction);
    }

    public void incremenTenth(){
        if (unit == 30){
            return;
        }
        fraction++;
        if (fraction > 9) {
            fraction = 0;
            unit++;
        }
    }

    public void decremenTenth(){
        if (unit == 5 && fraction == 0){
            return;
        }
        fraction--;
        if (fraction < 0) {
            fraction = 9;
            unit--;
        }
    }

    @Override
    public String toString() {
        return getUnitString()+"."+getFractionString();
    }

    @Override
    public int compareTo(Temperature a) {
        if (unit > a.unit){
            return 1;
        }
        else if (unit == a.unit){
            if (fraction > a.fraction)
                return 1;
            else if (fraction<a.fraction)
                return -1;
            else return 0;
        }
        else return -1;
    }
}
