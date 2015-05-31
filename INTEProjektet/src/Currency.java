

public class Currency {
	
	private String code; // Antingen USD eller SEK
	private String symbol; // Antingen D eller kr
	
	protected Currency(String code, String symbol){
		if ((code.equals("USD") || code.equals("SEK"))) {}
		else
			throw new IllegalArgumentException("Code argument does not fit the parameters");
		if ((symbol.equals("D")) || (symbol.equals("kr"))) {}
		else
			throw new IllegalArgumentException("Symbol argument does not fit the parameters");
		this.code = code;
		this.symbol = symbol;
	}
	
	public String getCode(){return code;}
	public String getSymbol(){return symbol;}
	
	public boolean equals(Currency other) {
		return this.getCode().equals(other.getCode()) && this.getSymbol().equals(other.getSymbol());
	}
	
	@Override
	public String toString(){
		return code + ", " + symbol; // Bra?
	}
}
