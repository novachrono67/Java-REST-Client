package com.licencepro.stockclient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockPrice
{
    private String id;
    private Double price;
    private LocalDateTime time;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public Double getPrice()
    {
        return price;
    }

    public void setPrice(Double price)
    {
        this.price = price;
    }

    public LocalDateTime getTime()
    {
        return time;
    }

    public void setTime(LocalDateTime time)
    {
        this.time = time;
    }

    @Override
    public String toString()
    {
        return "StockPrice{" +
                "id='" + id + '\'' +
                ", price=" + price +
                ", time=" + time +
                '}';
    }
}
