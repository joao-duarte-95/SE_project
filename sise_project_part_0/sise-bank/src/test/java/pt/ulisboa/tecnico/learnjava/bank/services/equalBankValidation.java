package pt.ulisboa.tecnico.learnjava.bank.services;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pt.ulisboa.tecnico.learnjava.bank.domain.Bank;
import pt.ulisboa.tecnico.learnjava.bank.domain.Client;
import pt.ulisboa.tecnico.learnjava.bank.domain.Bank.AccountType;
import pt.ulisboa.tecnico.learnjava.bank.exceptions.AccountException;
import pt.ulisboa.tecnico.learnjava.bank.exceptions.BankException;
import pt.ulisboa.tecnico.learnjava.bank.exceptions.ClientException;

public class equalBankValidation {

	private Bank bank1;
	private Bank bank2;
	private Client client1;
	private Client client2;
	private Client client3;
	private Services services;

	@Before
	public void setUp() throws BankException, ClientException, AccountException {
		this.services = new Services();
		this.bank1 = new Bank("CGD");
		this.bank2 = new Bank("BPI");
		this.client1 = new Client(this.bank1, "José", "Manuel", "123456789", "987654321", "Street", 33);
		this.client2 = new Client(this.bank1, "José", "Manuel", "123456788", "987654321", "Street", 33);
		this.client3 = new Client(this.bank2, "José", "Manuel", "123456788", "987654321", "Street", 33);
	}
	
	@Test
	public void success() throws BankException, AccountException, ClientException {
		String iban1 = this.bank1.createAccount(AccountType.CHECKING, this.client1, 100, 0);
		String iban2 = this.bank1.createAccount(AccountType.CHECKING, this.client2, 100, 0);
		String iban3 = this.bank2.createAccount(AccountType.CHECKING, this.client3, 100, 0);
		assertTrue(services.equalBankValidation(iban1, iban2));
		assertFalse(services.equalBankValidation(iban1, iban3));
		assertFalse(services.equalBankValidation(iban2, iban3));
	}
	
	@After
	public void tearDown() {
		Bank.clearBanks();
	}
	
}
