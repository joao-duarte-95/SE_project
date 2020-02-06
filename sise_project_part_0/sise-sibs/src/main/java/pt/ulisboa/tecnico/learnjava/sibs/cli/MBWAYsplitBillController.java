package pt.ulisboa.tecnico.learnjava.sibs.cli;

import pt.ulisboa.tecnico.learnjava.bank.exceptions.AccountException;
import pt.ulisboa.tecnico.learnjava.bank.services.Services;
import pt.ulisboa.tecnico.learnjava.sibs.exceptions.OperationException;
import pt.ulisboa.tecnico.learnjava.sibs.exceptions.SibsException;

public class MBWAYsplitBillController {
	
	public Friend [] friends;
	public int count = 0 ;
	
	public void resetCount() {
		this.count = 0;
	}
	
	public Friend[] getFriends() {
		return friends;
	}

	public void setFriends(Friend[] friends) {
		this.friends = friends;
	}

	public void addFriend (String phoneNumber , int amount) {
		Friend friend = new Friend(phoneNumber, amount);
		this.friends[count] = friend;
		count ++;
	}
	
	public void MBWAYsplitBill(int numberOfFriends, int amount, Services service) throws SibsException, AccountException, OperationException {
		if (count == numberOfFriends) {
			String target = friends[0].phoneNumber;
			for (int i = 1; i < numberOfFriends; i++) {
				if (correctAmount(numberOfFriends, amount) == false) {
					System.out.println("Something is wrong. Did you set the bill amount right?");
					return;
				}
				MBWAYtransferController tc = new MBWAYtransferController();
				tc.MBWAYtransfer(MBWAYsplitBillController.class, friends[i].phoneNumber, target, friends[i].amount, service);
			}
			System.out.println("Bill payed successfully!"); resetCount();
		} else if (count < numberOfFriends){
			System.out.println("Oh no! One friend is missing."); resetCount();
		} else {
			System.out.println("Oh no! Too many friends."); resetCount();
		}
	}
	
	public boolean correctAmount(int numberOfFriends, int amount) {
		int count = 0;
		for (int i = 0; i < numberOfFriends; i++) {
			count = count + friends[i].amount;
		}
		if (count == amount) {
			return true;
		} else {
			return false;
		}
	}
	
}
