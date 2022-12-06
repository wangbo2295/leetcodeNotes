package algorithm.changeBillDay;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChangeBillDay<Dog> {

    //修改账单日
    public void changeBillDay(Integer curDate,Integer newBillDate){
        if(newBillDate>curDate){
            //新的账单日在修改当天之后，直接将下个账单日提前到新账单日
            makeBills();
        }else if(newBillDate<curDate){
            //新的账单日在修改当天之前，将下个账单日推迟到下个月的新账单日
        }
    }

    void makeBills(){}




}
