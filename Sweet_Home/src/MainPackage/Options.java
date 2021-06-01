package MainPackage;

public class Options {
	String option;
	int option_price;
	public String getOption() {
		return option;
	}
	public void setOption(String option) {
		this.option = option;
	}
	public int getOption_price() {
		return option_price;
	}
	public void setOption_price(int option_price) {
		this.option_price = option_price;
	}
	
	//생성자
	public Options() {}
	
	public Options(String option, int option_price){
		this.option = option;
		this.option_price = option_price;
	}
	public String toString() {
		return option + " "+option_price+"원";
	}
	

}
