package br.com.jota.finance.services;

import br.com.jota.finance.DTOs.bankAccountDTOs.BankAccountData;
import br.com.jota.finance.DTOs.bankAccountDTOs.BankAccountDetails;
import br.com.jota.finance.entities.BankAccount;
import br.com.jota.finance.entities.User;
import br.com.jota.finance.repositories.BankAccountRepository;
import org.springframework.stereotype.Service;

@Service
public class BankAccountService {
    private final BankAccountRepository bankAccountRepository;

    public BankAccountService(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    public BankAccountDetails createBankAccount(BankAccountData data, User user) {

        var bankAccount = bankAccountRepository.save(new BankAccount(data.nameAccount(), data.typeAccount(), data.openingBalance(), user));
        return new BankAccountDetails(bankAccount);
    }
}
