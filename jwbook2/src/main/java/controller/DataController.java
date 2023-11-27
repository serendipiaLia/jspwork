package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/data")
public class DataController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DataController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 변수 
		String season = "가을";
		// 배열
		String[] seasons = {"Spring" , "Summer", "Fall", "Winter"};
		// HashMap 자료 - key : value
		Map<String, Object> car = new HashMap<>();
		car.put("brand", "Sonata");
		car.put("cc", 3000);
		
		// Model
		request.setAttribute("season", season);
		request.setAttribute("seasons", seasons);
		// HashMap 자료 모델
		request.setAttribute("car", car);
		
		// Model 전송 >> view(화면)페이지로 이동
		RequestDispatcher dispatcher = request.getRequestDispatcher("/mvc/mvc01.jsp");
		
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
