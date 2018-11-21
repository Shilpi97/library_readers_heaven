package bean;

public class dashboardBean {

	private int yearly_sale;
	private int monthly_sale;
	private int weekly_sale;
	private int total_book;
	private int new_arrival;
	public void setYearly_sale(int yearly_sale) {
		this.yearly_sale = yearly_sale;
	}
	public void setMonthly_sale(int monthly_sale) {
		this.monthly_sale = monthly_sale;
	}
	public void setWeekly_sale(int Weekly_sale) {
		this.weekly_sale = Weekly_sale;
	}
	public int getYearly_sale() {
		return yearly_sale;
	}
	public int getMonthly_sale() {
		return monthly_sale;
	}
	public int getWeekly_sale() {
		return weekly_sale;
	}
	public void setTotal_book(int total_book) {
		this.total_book = total_book;
	}
	public int getTotal_book() {
		return total_book;
	}
	public void setNew_arrival(int new_arrival) {
		this.new_arrival = new_arrival;
	}
	public int getNew_arrival() {
		return new_arrival;
	}
}
