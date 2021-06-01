package delivery;
//장바구니 총합을 계산하고 쓰기 위한 클래스. SweetDelivery 클래스와 연동된다.
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Total {
	private int point; //포인트 

	
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	
	public Total() {
		
	}
	//생성자 오버로딩
	public Total(int point) {
		this.point = point;
	}
	
	
	//메서드 오버로딩- 포인트---------------------------------------------------------------------------------------//
	public void WriteToFile(int point) {
	//읽기- 읽어서 기존 값에 받아온 값을 더해야 한다.
	 String path =".\\src\\resource\\Text\\Total.txt";
	 File file = new File(path);
	 int sum=0;
	 int plus;
	 try {
		 	DataInputStream dis = new DataInputStream(new FileInputStream(path));
		 	while(true) {
		 		String text = dis.readLine();
		 		if(text == null) {
		 			break;
		 	}
		 	String value = text; //읽은 값을 문자열로 저장		 	

			plus = Integer.parseInt(value); //문자열을 정수로 바꿈.
			sum = plus + point; //기존 값과 합쳤음.
			}
			//br.close();
		}catch (Exception e) {
			e.printStackTrace();
		}		
		
	//쓰기
	FileWriter writer = null;
	String message = String.valueOf(sum); //새롭게 계산한 값을 파일에 쓰기 위해 문자열로 바꾸었다.
	try {
		//true는 기존 내용에 이어서 쓰는 것, 기존내용을 없애고 새로 쓰려면 false .-> 매번 값을 받을 때마다 갱신된다.
		writer = new FileWriter(path,false); 
		writer.write(message);
		writer.flush();
		

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