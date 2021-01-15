public class Course {
	
	public String CourseName;
	public int PeriodNum;
	public int MainChoiceCnt;
	public int AltChoiceCnt;
	
	public Course(String CourseName,int MainChoiceCnt, int AltChoiceCnt) {
		this.CourseName = CourseName;
		this.MainChoiceCnt = MainChoiceCnt;
		this.AltChoiceCnt = AltChoiceCnt;
	}
	
	public Course(String CourseName,int PeriodNum, int MainChoiceCnt, int AltChoiceCnt ) {
		this.CourseName = CourseName;
		this.PeriodNum = PeriodNum;
		this.MainChoiceCnt = MainChoiceCnt;
		this.AltChoiceCnt = AltChoiceCnt;
	}
}
