package waterLadderPrice;

import java.math.BigDecimal;
import java.util.*;

@FunctionalInterface
public interface WaterPrice{

    Map<BigDecimal,BigDecimal> ladderPrice(BigDecimal[] price, BigDecimal[] endPoint);

    static WaterPrice generate(){
        return (price,endPoint) -> {
            Map<BigDecimal,BigDecimal> map = new TreeMap<>();
            BigDecimal curMoney = new BigDecimal(0);
            for(int i=1;i<price.length;i++){
                if(endPoint[i].equals(0))   break;
                curMoney = curMoney.add(endPoint[i].subtract(endPoint[i-1]).multiply(price[i-1]));
                map.put(endPoint[i],curMoney);
            }
            return map;
        };
    }

    default BigDecimal calculate(BigDecimal[] price, BigDecimal[] endPoint, BigDecimal curAmount){
        Map<BigDecimal,BigDecimal> map = ladderPrice(price,endPoint);
        Set<Map.Entry<BigDecimal,BigDecimal>> entrySet = map.entrySet();
        Iterator<Map.Entry<BigDecimal, BigDecimal>> iterator = entrySet.iterator();
        Map.Entry<BigDecimal, BigDecimal> entry;
        BigDecimal usage = new BigDecimal(0);
        BigDecimal money = new BigDecimal(0);
        int i = 0;
        while (iterator.hasNext()){
            entry = iterator.next();
            usage = entry.getKey();
            money = entry.getValue();
            if(usage.compareTo(curAmount)>0){
                break;
            }
            i++;
        }
        money = money.add(curAmount.subtract(usage).multiply(price[i]));
        return money;
    }


}
