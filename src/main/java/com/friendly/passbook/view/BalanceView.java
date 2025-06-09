package com.friendly.passbook.view;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.friendly.passbook.dto.AccountDto;
import com.friendly.passbook.service.AccountService;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@UIScope
@Route("/balance-view")
@StyleSheet("https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css")
public class BalanceView extends VerticalLayout {
    @Autowired
    public BalanceView(AccountService accountService, com.friendly.passbook.repository.AccountRepository accountRepository) {
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        getStyle().set("background-color", "#f8f9fa");

        // Header
        Header header = new Header();
        H1 title = new H1("Friendly Society Online Banking");
        title.getElement().getClassList().add("display-4");
        Arrays.asList("bg-primary", "text-white", "p-4", "w-100").forEach(cls -> header.getElement().getClassList().add(cls));
        header.add(title);
        add(header);

        // Hardcoded user (simulate logged-in user)
        String hardcodedAccountNumber = "1234567890";
        AccountDto dto = null;
        try {
            dto = accountService.getAccountBalance(hardcodedAccountNumber);
        } catch (Exception ex) {
            // If not found, create the account for demo purposes
            com.friendly.passbook.entity.Account testAccount = new com.friendly.passbook.entity.Account();
            testAccount.setAccountNumber(hardcodedAccountNumber);
            testAccount.setOwnerName("Test User");
            testAccount.setBalance(new java.math.BigDecimal("1000.00"));
            accountRepository.save(testAccount);
            dto = new AccountDto(hardcodedAccountNumber, "Test User", new java.math.BigDecimal("1000.00"));
        }

        // Hero section for balance
        Div hero = new Div();
        Arrays.asList("container", "my-5", "p-5", "bg-white", "rounded", "shadow").forEach(cls -> hero.getElement().getClassList().add(cls));
        H1 balanceTitle = new H1("Account Balance");
        balanceTitle.getElement().getClassList().add("mb-4");
        Paragraph balance = new Paragraph("Balance: £" + dto.balance());
        Arrays.asList("display-5", "fw-bold", "text-success").forEach(cls -> balance.getElement().getClassList().add(cls));
        Paragraph owner = new Paragraph("Account Holder: " + dto.ownerName());
        Arrays.asList("lead", "mb-0").forEach(cls -> owner.getElement().getClassList().add(cls));
        Paragraph accNum = new Paragraph("Account Number: " + dto.accountNumber());
        Arrays.asList("text-muted", "mb-0").forEach(cls -> accNum.getElement().getClassList().add(cls));
        hero.add(balanceTitle, balance, owner, accNum);
        add(hero);

        // Footer
        Footer footer = new Footer();
        Paragraph copyright = new Paragraph("© Friendly Society " + java.time.Year.now());
        footer.add(copyright);
        Arrays.asList("bg-light", "text-center", "p-3", "w-100", "mt-auto").forEach(cls -> footer.getElement().getClassList().add(cls));
        add(footer);
    }
}
