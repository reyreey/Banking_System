package utility;

import model.AccountDTO;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AccountGroup {
    public static void groupByBalance(List<AccountDTO> accountList){

        Map<String,List<AccountDTO>> accountByBalance=accountList
                .stream()
                .collect(Collectors.groupingBy(a -> {
                    if (a.getAccountBalance()<=10000000) return "Group C";
                    else if (a.getAccountBalance()<=500000000) return "Group B";
                    return "Group A";
                }));

        accountByBalance
                .forEach((group,a)-> System.out.format("Account %s: %s\n",group,a
                        .stream()
                        .map(AccountDTO::getAccountNumber)
                        .toList()
                        )
                );
    }
}
