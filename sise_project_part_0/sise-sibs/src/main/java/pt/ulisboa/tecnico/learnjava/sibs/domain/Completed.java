package pt.ulisboa.tecnico.learnjava.sibs.domain;

import pt.ulisboa.tecnico.learnjava.bank.exceptions.AccountException;

public class Completed extends State {

	@Override
	public void process(Sibs sibs, TransferOperation transferOperation) throws AccountException {
	}
	
	@Override
	public void cancel(Sibs sibs, TransferOperation transferOperation) throws AccountException {
	}


}
