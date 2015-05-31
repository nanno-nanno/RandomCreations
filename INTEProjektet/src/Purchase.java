public class Purchase {

	private Store store;
	private Customer customer;
	private Receipt receipt;

	public Purchase(Store store, Customer customer, Receipt receipt) {
		if(store== null)
			throw new NullPointerException("Store cannot be null.");
		if(customer == null)
			throw new NullPointerException("Customer cannot be null.");
		if(receipt == null)
			throw new NullPointerException("Receipt cannot be null.");
		this.store = store;
		this.customer = customer;
		this.receipt = receipt;
	}

	public Store getStore() {
		return store;
	}

	public Customer getCustomer() {
		return customer;
	}

	public Receipt getreceipt() {
		return receipt;
	}

	public String toString() {
		return "--Purchase--" + "\nStore: " + store.toString() + "\nCustomer: "
				+ customer.getName() + "\nCurrent wallet: "
				+ customer.getCash() + "\nReceipt: \n" + receipt.toString();
	}
}