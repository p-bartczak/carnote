package pl.com.wrzosdev.carnote.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Model danych pojedynczego tankowania
 */
public class TankUpRecord implements Serializable
{
    /**
     * Data wykonania tankowania
     */
    private Date tankUpDate;

    /**
     * Przebieg auta w momencie tankowania
     */
    private Integer mileage;

    /**
     * Litry dolane
     */
    private Integer liters;

    /**
     * Zapłacona kwota w PLN
     */
    private Integer costInPln;

    public TankUpRecord(Date tankUpDate, Integer mileage, Integer liters, Integer costInPln)
    {
        this.tankUpDate = tankUpDate;
        this.mileage = mileage;
        this.liters = liters;
        this.costInPln = costInPln;
    }

    public Date getTankUpDate()
    {
        return tankUpDate;
    }

    public void setTankUpDate(Date tankUpDate)
    {
        this.tankUpDate = tankUpDate;
    }

    public Integer getMileage()
    {
        return mileage;
    }

    public void setMileage(Integer mileage)
    {
        this.mileage = mileage;
    }

    public Integer getLiters()
    {
        return liters;
    }

    public void setLiters(Integer liters)
    {
        this.liters = liters;
    }

    public Integer getCostInPln()
    {
        return costInPln;
    }

    public void setCostInPln(Integer costInPln)
    {
        this.costInPln = costInPln;
    }
}
