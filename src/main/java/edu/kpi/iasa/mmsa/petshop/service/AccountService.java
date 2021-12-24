package edu.kpi.iasa.mmsa.petshop.service;

import edu.kpi.iasa.mmsa.petshop.dto.AccountDto;
import edu.kpi.iasa.mmsa.petshop.exception.AccountNotFoundException;
import edu.kpi.iasa.mmsa.petshop.exception.RoleNotFoundException;
import edu.kpi.iasa.mmsa.petshop.exception.ShelterNotFoundException;
import edu.kpi.iasa.mmsa.petshop.model.Account;
import edu.kpi.iasa.mmsa.petshop.repository.AccountRepository;
import edu.kpi.iasa.mmsa.petshop.repository.RoleRepository;
import edu.kpi.iasa.mmsa.petshop.repository.ShelterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;
    private final ShelterRepository shelterRepository;
    private final PasswordEncoder passwordEncoder;

    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }

    public Account saveAccount(AccountDto newAccount) {

        Account account=Account.builder()
                .username(newAccount.getUsername())
                .firstName(newAccount.getFirstName())
                .lastName(newAccount.getLastName())
                .password(passwordEncoder.encode(newAccount.getPassword()))
                .email(newAccount.getEmail())
                .role(roleRepository.findById(newAccount.getRoleId()).orElseThrow(RoleNotFoundException::new))
                .shelter(shelterRepository.findById(newAccount.getShelterId()).orElseThrow(ShelterNotFoundException::new))
                .build();
        return accountRepository.save(account);
    }

    public Account getAccountById(Long id) {
        Optional<Account> account = accountRepository.findById(id);
        if (account.isPresent()) {
            return account.get();
        }
        throw new AccountNotFoundException();
    }

    public Account updateAccountById(Long id, AccountDto updatedAccount) {
        Optional<Account> account = accountRepository.findById(id);
        if (account.isPresent()) {;
            Account oldAccount = account.get();
            updatedAccount(oldAccount, updatedAccount);
            return accountRepository.save(oldAccount);

        }
        throw new AccountNotFoundException();
    }

    private void updatedAccount(Account oldAccount, AccountDto updatedAccount) {
        oldAccount.setEmail(updatedAccount.getEmail());
        oldAccount.setFirstName(updatedAccount.getFirstName());
        oldAccount.setLastName(updatedAccount.getLastName());
        oldAccount.setPassword(passwordEncoder.encode(updatedAccount.getPassword()));
        oldAccount.setRole(roleRepository.findById(updatedAccount.getRoleId()).orElseThrow(RoleNotFoundException::new));
        oldAccount.setShelter(shelterRepository.findById(updatedAccount.getShelterId()).orElseThrow(ShelterNotFoundException::new));
        oldAccount.setUsername(updatedAccount.getUsername());
    }

    public String deleteAccountById(Long id) {
        if (accountRepository.findById(id).isPresent()) {
            accountRepository.deleteById(id);
            return "Status was successfully deleted";
        }
        throw new AccountNotFoundException();
    }
}
