import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

class Member implements Serializable{
	private static final long serialVersionUID = -6029250573409898784L;
	
	public final String NAME;
	private int goal;
	private int[] count;
	
	public Member(String NAME, int goal){
		this.NAME = NAME;
		this.goal = goal;
		count = new int[366];
	}
	
	public double getQuota(){
		if(getTotalCount() == 0) return 0;
		double value = (double) getTotalCount() / (double) goal;
		value = value * 1000;
		value = (double) Math.round(value);
		value = value / 10;
		return value;
	}
	
	public double getYearQuota(){
		double value = (double) getToday() / (double) count.length;
		value = value * 1000;
		value = (double) Math.round(value);
		value = value / 10;
		return value;
	}
	
	public int[] getCountArray(){
		return count;
	}
	
	public void setCountArray(int[] count){
		this.count = count;
	}
	
	public int getGoal(){
		return goal;
	}
	
	public void setGoal(int goal){
		this.goal = goal;
	}
	
	public void increaseCount(int incrementor){
		count[getToday()] += incrementor;
	}
	
	public void modifyCount(int modifier){
		count[getToday()] = modifier;
	}
	
	public void modifyCount(int day, int modifier){
		count[day - 1] = modifier;
	}
	
	public int getTotalCount(){
		int totalCount = 0;
		for(int i = 0; i < count.length; i++){
			totalCount += count[i];
		}
		return totalCount;
	}
	
	public int getDailyCount(){
		return count[getToday()];
	}
	
	public int getMissedDays(){
		int missedDays = 0;
		for(int i = 0; i < getToday(); i++){
			if(count[i] == 0){
				missedDays++;
			}
		}
		return missedDays;
	}
	
	public int getRecommendedAverage(){
		double value = ((double)(goal - getTotalCount())) / ((double)(count.length - getToday()));
		return (int)Math.round(value);
	}
	
	public int getHighScore(){
		int highScore = 0;
		for(int i = 0; i < count.length; i++){
			if(count[i] > highScore){
				highScore = count[i];
			}
		}
		return highScore;
	}
	
	public void printArray(){
		for(int i = 0; i < count.length; i++){
			System.out.printf("Day %4s: %s\n", i + 1, count[i]);
		}
	}
	
	public  int getToday(){
		Calendar cal = new GregorianCalendar();
		//return 0;
		return cal.get(Calendar.DAY_OF_YEAR) - 1;
	}
	
	public String toString(){
		return NAME;
	}
}
