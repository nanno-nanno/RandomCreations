

public class Money{

	private Currency currency;
	private int amount;
	
	private final int USDTOSEK = 7;

	public Money(Currency currency, int amount) {
		if (amount < 0)
			throw new IllegalArgumentException("Amount can't be less than 0");
		if(currency == null)
			throw new NullPointerException("Currency cannot be null.");
		this.currency = currency;
		this.amount = amount;
	}

	public Money exchangeCurrency(Money m){
		if(m.getCurrency().getCode().equals("SEK")){ //Avrundar nerat
			double rest = (double)m.getAmount()*0.13757;
			
			return new Money(new Currency("USD","D"),(int)rest);
		}
		if (m.getCurrency().getCode().equals("USD")){
			return new Money(new Currency("SEK","kr"),USDTOSEK*m.getAmount());
		}
			throw new IllegalArgumentException("Money maste vara SEK eller USD");
		
	}

	public Currency getCurrency() {
		return currency;
	}

	public int getAmount() {
		return amount;
	}

	public boolean equals(Money other) {
		return this.getCurrency().equals(other.getCurrency()) && this.getAmount() == other.getAmount();
	}

	public String toString() {
		return currency + " " + amount;
	}

}
