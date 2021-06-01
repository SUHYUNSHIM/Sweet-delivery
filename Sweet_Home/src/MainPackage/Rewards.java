package MainPackage;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
//쿠폰, 포인트 관리 클래스.
public class Rewards {
	private int point; //포인트 
	private double discount; //할인 쿠폰에 적용될 할인율
	private String couponName; //할인 쿠폰의 이름 
	
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	
	public String getCouponName() {
		return couponName;
	}
	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}
	
	//생성자 오버로딩
	public Rewards(int point) {
		this.point = point;
	}
	public Rewards(double discount, String couponName) {
		this.discount = discount;
		this.couponName = couponName;
	}
	
	//toString-- 쿠폰 전용 
	public String toString() {
		return discount +","+ couponName;
	}
	
	//메서드 오버로딩- 포인트---------------------------------------------------------------------------------------//
	public void WriteToFile(int point) {
	//읽기- 읽어서 기존 값에 받아온 값을 더해야 한다.
	 String path =".\\src\\resource\\rewards\\point.txt";
	 File file = new File(path);
	 int newValue=0;
	 int existingValue;
	 try {
		 	DataInputStream dis = new DataInputStream(new FileInputStream(path));
		 	while(true) {
		 		String text = dis.readLine();
		 		if(text == null) {
		 			break;
		 	}
		 	String value = text; //읽은 값을 문자열로 저장		 	
			System.out.println(value);
			existingValue = Integer.parseInt(value); //문자열을 정수로 바꿈.
			newValue = existingValue+ point; //기존 값과 합쳤음.
			}
			//br.close();
		}catch (Exception e) {
			e.printStackTrace();
		}		
		
	//쓰기
	FileWriter writer = null;
	String message = String.valueOf(newValue); //새롭게 계산한 값을 파일에 쓰기 위해 문자열로 바꾸었다.
	message.trim(); //빈줄 지우기 위해서.
	try {
		//true는 기존 내용에 이어서 쓰는 것, 기존내용을 없애고 새로 쓰려면 false .-> 매번 값을 받을 때마다 갱신된다.
		writer = new FileWriter(path,false); 
		writer.append(message+"\r\n");
		writer.flush();
		
		System.out.println("Done");
	}catch(IOException e) {
		e.printStackTrace();
	}finally {
		try {
			if(writer !=null) writer.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	}
	
	//메서드 오버로딩- 쿠폰---------------------------------------------------------------------------------------//
	public void WriteToFile(double discount , String CouponName ) {
		
		String path = ".\\src\\resource\\rewards\\coupon.txt";
		FileWriter writer = null;
		String message = String.valueOf(discount); //실수로 받은 할인율 값을 문자열로 바꾸었다.
		ArrayList <Rewards> couponList = new ArrayList<Rewards>();
		couponList.add(new Rewards(discount, CouponName));
		
		try {
			//true는 기존 내용에 이어서 쓰는 것, 기존내용을 없애고 새로 쓰려면 false .
			writer = new FileWriter(path,true); 
			String input = couponList.get(couponList.size()-1).getDiscount()+","+couponList.get(couponList.size()-1).getCouponName();
			input.trim(); //빈 줄이들어가는 것 방지
			writer.write(input+"\r\n"); //줄바꿈 되어 저장된다.			
			writer.flush();			
			//System.out.println("Done");
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if(writer !=null) writer.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	

}
