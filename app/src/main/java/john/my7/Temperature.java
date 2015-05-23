package john.my7;

/**
 * Created by John on 20.05.2015.
 */
public class Temperature implements Comparable<Temperature>{
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
        fraction++;
        if (fraction > 9) {
            fraction = 0;
            unit++;
        }
    }

    public void decremenTenth(){
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
    public int compareTo(Temperature another) {
        double th = (double)unit + (double)fraction/(double)10;
        double an = (double)another.getUnit() + (double)another.getFraction()/(double)10;
        if (th-an<0)
            return 1;
        if (th-an>0)
            return -1;
        return 0;
    }
}
