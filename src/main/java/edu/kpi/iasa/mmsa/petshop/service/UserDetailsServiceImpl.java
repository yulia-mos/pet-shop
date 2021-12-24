package edu.kpi.iasa.mmsa.petshop.service;

import edu.kpi.iasa.mmsa.petshop.configuration.security.AccountPrincipal;
import edu.kpi.iasa.mmsa.petshop.exception.AccountNotFoundException;
import edu.kpi.iasa.mmsa.petshop.model.Account;
import edu.kpi.iasa.mmsa.petshop.repository.AccountRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService{
    private final AccountRepository accountRepository;
    @Override
    public AccountPrincipal loadUserByUsername(String username) throws AccountNotFoundException {
        Account account = accountRepository.findByUsername(username).orElseThrow(AccountNotFoundException::new);
        return new AccountPrincipal(account);
    }

}
