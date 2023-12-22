package controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import model.Product;
import model.ProductDAO;

@WebServlet("*.do")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	ProductDAO pdao;
	
    public MainController() {
    	pdao = new ProductDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 한글인코딩
		request.setCharacterEncoding("utf-8");
		
		// command 패턴 (uri)경로 설정
		String uri = request.getRequestURI();
		String command = uri.substring(uri.lastIndexOf("/"));
		String nextPage = "";
		
		// 세션 객체 생성,, (132행부터 쓸 세션 발급)
		HttpSession session = request.getSession(); 

/* -------------------------------------------------------------------
  		메인페이지 >> main.jsp / header.jsp와 footer.jsp
 --------------------------------------------------------------------- */
		if(command.equals("/main.do")) {
			nextPage = "/main.jsp";
		
		}else if(command.equals("/productlist.do")) {
			// 목록 보기 메서드 호출
			List<Product> productList = pdao.getProductList();
			
			// 모델 생성하기
			request.setAttribute("products", productList);
			nextPage = "/product/list.jsp";

		}else if(command.equals("/productform.do")) {
			nextPage = "/product/pform.jsp";

/*------------------------------------------------------------------------------------------------------
		 	상품 등록 >> pform.jsp
-------------------------------------------------------------------------------------------------------*/			
		}else if(command.equals("/insertproduct.do")) {
			
			// 입력폼의 데이터 받기
			String realFolder = "C:\\jspworks\\Market\\src\\main\\webapp\\upload";
			int maxSize = 10*1024*1024; //10MB
			String encType = "utf-8"; // 파일 이름 한글 인코딩
			DefaultFileRenamePolicy policy = new DefaultFileRenamePolicy();
			
			// 5가지 인자=데이터(request, realFolder, maxSize, encType, policy)
			MultipartRequest multi = new MultipartRequest(request, realFolder, maxSize, encType, policy);
			
			String pid = multi.getParameter("pid");
			String pname = multi.getParameter("pname");
			int price = Integer.parseInt(multi.getParameter("price"));
			String description = multi.getParameter("description");
			String category = multi.getParameter("category");
			int pstock = Integer.parseInt(multi.getParameter("pstock"));
			String condition = multi.getParameter("condition");
			
			//file 파라미터 추출
			Enumeration<?> files = multi.getFileNames();
			String pimage ="";
			
			while(files.hasMoreElements()) { // 파일이름이 있는 동안 반복
				String userFilename = (String)files.nextElement();
				
				// 실제 저장될 이름
				pimage = multi.getFilesystemName(userFilename);
			}
			
			// 상품 객체 1개 생성 (한개씩 ..)
			Product product = new Product();
			product.setPid(pid);
			product.setPname(pname);
			product.setPrice(price);
			product.setDescription(description);
			product.setCategory(category);
			product.setPstock(pstock);
			product.setCondition(condition);
			product.setPimage(pimage);
			
			
			// 위의 객체를 db에 등록할 메서드 호출 
			pdao.insertProduct(product); // ProductDAO에 자동 생성
			nextPage = "/productlist.do"; 
			
		}else if(command.equals("/productinfo.do")) {
			String pid = request.getParameter("pid");
			
			// 상품 상세보기 메서드 호출
			Product product = pdao.getProduct(pid); 

			// 모델 생성
			request.setAttribute("product", product);
			nextPage = "/product/pinfo.jsp";
		
/*------------------------------------------------------------------------------------------------------
	 	상품 수정 및 삭제 (상품 편집) >> edit.jsp
 -------------------------------------------------------------------------------------------------------*/
		}else if(command.equals("/editproduct.do")) { 
			String edit = request.getParameter("edit");
			
			List<Product> products = pdao.getProductList();
			request.setAttribute("products", products);
			request.setAttribute("edit", edit); //
			
			nextPage = "/product/edit.jsp";
		
		// 상품삭제	
		}else if(command.equals("/deleteproduct.do")) { 
			String pid = request.getParameter("pid");

			pdao.deleteProduct(pid);
			
			nextPage = "/editproduct.do?edit=delete";
/*			
		// 상품 수정( 수정은 페이지를 따로 만들어야함)

 */
		}else if(command.equals("/updateform.do")) { 
			String pid = request.getParameter("pid");
			
			// 해당 상품코드의 상품을 가져옴
			Product product = pdao.getProduct(pid);
			// 모델 만들기
			request.setAttribute("product", product);
			
			nextPage = "/product/updateform.jsp";
			
		}else if(command.equals("/updateproduct.do")) { 	
			
			// 입력폼의 데이터 받기
			String realFolder = "C:\\jspworks\\Market\\src\\main\\webapp\\upload";
			int maxSize = 10*1024*1024; //10MB
			String encType = "utf-8"; // 파일 이름 한글 인코딩
			DefaultFileRenamePolicy policy = new DefaultFileRenamePolicy();
			
			// 5가지 인자=데이터(request, realFolder, maxSize, encType, policy)
			MultipartRequest multi = new MultipartRequest(request, realFolder, maxSize, encType, policy);
			
			String pid = multi.getParameter("pid");
			String pname = multi.getParameter("pname");
			int price = Integer.parseInt(multi.getParameter("price"));
			String description = multi.getParameter("description");
			String category = multi.getParameter("category");
			int pstock = Integer.parseInt(multi.getParameter("pstock"));
			String condition = multi.getParameter("condition");
			
			//file 파라미터 추출
			Enumeration<?> files = multi.getFileNames();
			String pimage ="";
			
			while(files.hasMoreElements()) { // 파일이름이 있는 동안 반복
				String userFilename = (String)files.nextElement();
				
				// 실제 저장될 이름
				pimage = multi.getFilesystemName(userFilename);
			}
			// 상품 객체 1개 생성 (한개씩 ..)
			Product product = new Product();
			product.setPid(pid);
			product.setPname(pname);
			product.setPrice(price);
			product.setDescription(description);
			product.setCategory(category);
			product.setPstock(pstock);
			product.setCondition(condition);
			product.setPimage(pimage);
			
			//db에 수정할 메서드 호출 - 이미지 파일 유무에 따른 처리
			if(pimage != null) {
				pdao.updateProduct(product);
			}else {
				pdao.updateProductNoImage(product);
			}
			
			nextPage = "/editproduct.do?edit=update";
			
/*----------------------------------------------------------------------------------------------------------			
		상품 주문하기! >> 장바구니 cart.jsp
----------------------------------------------------------------------------------------------------------*/
		}else if(command.equals("/addcart.do")) { // 장바구니 
			String pid = request.getParameter("pid");
			
			// DB에 있던 상품 장바구니로 불러오기
			List<Product> goodsList = pdao.getProductList();
			Product goods = new Product();
			
			// 목록에서 장바구니에 추가한 상품 찾기
			for(int i=0; i<goodsList.size(); i++) {
				goods = goodsList.get(i); // 나중에 밑에서 사용예정 ...
				if(goods.getPid().equals(pid)) // 장바구니에 추가한 상품코드pid와 일치하면
					break; // 일치하면 빠져나옴
			}
			// 상품 세션 발급
			List<Product> list = (ArrayList<Product>)session.getAttribute("cartlist"); // 첨엔 null, 세션 발급 후 getAttribute로 진행
			if(list == null) {
				list = new ArrayList<>();
				session.setAttribute("cartlist", list);
			}
			
			// 요청 아이디의 상품이 기존 장바구니에 있으면 수량 증가
			int cnt = 0; // 장바구니에 추가한 횟수
			
			Product goodsQnt = new Product();
			for(int i=0; i<list.size(); i++) {
				goodsQnt = list.get(i);
				if(goodsQnt.getPid().equals(pid)) { // 상품코드가 같은게 있는지..
					cnt++; // 이전에 추가한 품목이 있으면 또 수량 추가
//					int orderQuantity = goodsQnt.getQuantity() + 1; >> 추가 주문된 상품 객체
					goodsQnt.setQuantity(goodsQnt.getQuantity() + 1); 
				}
			}
			// 장바구니에 추가했을 때, 기본 수량 1로 정함
			if(cnt == 0) {
				goods.setQuantity(1);
				list.add(goods);
			}
			
			//nextPage = "/productinfo.do?pid=" + pid; >> 리다이렉트 부분에 중복방지 생성! 194행
			
		}else if(command.equals("/cart.do")) {
			// 상품 세션 유지
			List<Product> cartlist = (ArrayList<Product>)session.getAttribute("cartlist");
			if(cartlist == null) {
				cartlist = new ArrayList<>();
			}
			
			// 합계
			int sum = 0; // 상품 가격 총 합계
			
			int unit_sum = 0; // 각 품목 별 합계 = 가격 x 수량
			
			for(int i=0; i<cartlist.size(); i++) {
				Product product = cartlist.get(i); // 장바구니에 담긴 품목
				unit_sum = product.getPrice() * product.getQuantity(); // 가격 x 수량
				sum += unit_sum; // 각 품목 별 합계
			}
			
			// 상품세션 아이디 얻기 >> 장바구니 주문하기로 카트아이디 전달!
			String cartId = session.getId(); // 쿠키 JsessionId
			
			request.setAttribute("cartlist", cartlist);
			request.setAttribute("cartId", cartId);
			request.setAttribute("sum", sum);
			
			nextPage = "/product/cart.jsp";
			
		// 장바구니 품목 전체삭제 (로그아웃과 비슷, 세션 삭제)
		}else if(command.equals("/deletecart.do")) {
			session.invalidate();
		// 장바구니 품목 개별 삭제 (비고)	
		}else if(command.equals("/removecart.do")) {
			String pid = request.getParameter("pid");
			// 상품 세션 유지 
			List<Product> cartlist = (ArrayList<Product>)session.getAttribute("cartlist");
			// 장바구니에서 해당 품목을 찾아서 삭제
			for(int i=0; i<cartlist.size(); i++) {
				Product product = cartlist.get(i); // 장바구니에 있는 삭제할 품목을 선택
				if(product.getPid().equals(pid)) {// 삭제요청한 코드와 품목이 일치하면 삭제
					cartlist.remove(product); // arraylist의 삭제 메서드 사용
				}
			}
		}else if(command.equals("/shippingform.do")) { //배송정보
			String cartId =  session.getId();
			request.setAttribute("cartId", cartId);
			
			nextPage = "/product/shippingform.jsp";
		
		// 주문 내역 (주문자 및 배송지 정보)
		}else if(command.equals("/shippinginfo.do")) { 
			// 쿠키 사용 - 주문내역 만들기
			// Cookie("쿠키이름", 객체(쿠키값))
			Cookie shippingId = new Cookie("Shipping_cartId", URLEncoder.encode(request.getParameter("cartId"), "utf-8"));
			Cookie sname = new Cookie("Shipping_sname", URLEncoder.encode(request.getParameter("sname"), "utf-8"));
			Cookie shippingdate = new Cookie("Shipping_shippingdate", URLEncoder.encode(request.getParameter("shippingdate"), "utf-8"));
			Cookie zipcode = new Cookie("Shipping_zipcode", URLEncoder.encode(request.getParameter("zipcode"), "utf-8"));
			Cookie address = new Cookie("Shipping_address", URLEncoder.encode(request.getParameter("address"), "utf-8"));
			
			// 쿠키 유효기간 설정 (1일로 설정)
			shippingId.setMaxAge(24*60*60);
			sname.setMaxAge(24*60*60);
			shippingdate.setMaxAge(24*60*60);
			zipcode.setMaxAge(24*60*60);
			address.setMaxAge(24*60*60);
			
			// 클라이언트 컴으로 쿠키 전송
			response.addCookie(shippingId);
			response.addCookie(sname);
			response.addCookie(shippingdate);
			response.addCookie(zipcode);
			response.addCookie(address);
			
			//1. 쿠키 정보 가져오기
			String shipping_cartId = "";
			String shipping_sname = "";
			String shipping_shippingdate = "";
			String shipping_zipcode = "";
			String shipping_address = "";
			
			Cookie[] cookies = request.getCookies();
			if(cookies != null) {
				for(int i=0; i<cookies.length; i++) {
					Cookie cookie = cookies[i];	// 인덱스 별로 쿠키를 생성
					String cname = cookie.getName(); //쿠키이름 가져오기
					// 한글 복호화(문자열로 변환) >> 디코딩!
					if(cname.equals("Shipping_cartId"))
						shipping_cartId = URLDecoder.decode(cookie.getValue(), "utf-8");
					if(cname.equals("Shipping_sname"))
						shipping_sname = URLDecoder.decode(cookie.getValue(), "utf-8");
					if(cname.equals("Shipping_shippingdate"))
						shipping_shippingdate = URLDecoder.decode(cookie.getValue(), "utf-8");
					if(cname.equals("Shipping_zipcode"))
						shipping_zipcode = URLDecoder.decode(cookie.getValue(), "utf-8");
					if(cname.equals("Shipping_address"))
						shipping_address = URLDecoder.decode(cookie.getValue(), "utf-8");
				}
			}
			
			// 총 합계 
			List<Product> cartlist = (ArrayList<Product>)session.getAttribute("cartlist");
			
			int sum = 0; // 상품 가격 총 합계
			int unit_sum = 0; // 각 품목 별 합계 = 가격 x 수량
			
			for(int i=0; i<cartlist.size(); i++) {
				Product product = cartlist.get(i); // 장바구니에 담긴 품목
				unit_sum = product.getPrice() * product.getQuantity(); // 가격 x 수량
				sum += unit_sum; // 각 품목 별 합계
			}
			
			//2. 모델 만들기
			request.setAttribute("shipping_cartId", shipping_cartId);
			request.setAttribute("shipping_sname", shipping_sname);
			request.setAttribute("shipping_shippingdate", shipping_shippingdate);
			request.setAttribute("shipping_zipcode", shipping_zipcode);
			request.setAttribute("shipping_address", shipping_address);
			request.setAttribute("sum", sum);
			
			nextPage = "/product/orderconfirm.jsp";
		
		// 주문완료 
		}else if(command.equals("/thankscustomer.do")) {
			// 카트 아이디와 배송일 쿠키 정보를 가져와서 모델로 보내기
			String shipping_cartId = "";
			String shipping_shippingdate = "";
			
			Cookie[] cookies = request.getCookies();
			if(cookies != null) {
				for(int i=0; i<cookies.length; i++) {
					Cookie cookie = cookies[i];	// 인덱스 별로 쿠키를 생성
					String cname = cookie.getName(); //쿠키이름 가져오기
					// 한글 복호화(문자열로 변환) >> 디코딩!
					if(cname.equals("Shipping_cartId"))
						shipping_cartId = URLDecoder.decode(cookie.getValue(), "utf-8");
					if(cname.equals("Shipping_shippingdate"))
						shipping_shippingdate = URLDecoder.decode(cookie.getValue(), "utf-8");
				}
			}
			// 2. 모델 만들기
			request.setAttribute("shipping_cartId", shipping_cartId);
			request.setAttribute("shipping_shippingdate", shipping_shippingdate);
			
			// 모든 세션 삭제 >> 구매 완료 후, 장바구니 내역 삭제를 위해.. (하기 쿠키도..)
			session.invalidate();
			
			// 모든 쿠키 삭제 : setMaxAge(0)
			if(cookies != null) {
				for(int i=0; i<cookies.length; i++) {
					Cookie cookie = cookies[i];	// 인덱스 별로 쿠키를 생성
					String cname = cookie.getName(); //쿠키이름 가져오기
					// 한글 복호화(문자열로 변환) >> 디코딩!
					if(cname.equals("Shipping_cartId"))
						cookie.setMaxAge(0);
					if(cname.equals("Shipping_sname"))
						cookie.setMaxAge(0);
					if(cname.equals("Shipping_shippingdate"))
						cookie.setMaxAge(0);
					if(cname.equals("Shipping_zipcode"))
						cookie.setMaxAge(0);
					if(cname.equals("Shipping_address"))
						cookie.setMaxAge(0);
				}
			}
			nextPage = "/product/thankscustomer.jsp";
		}
		
/*------------------------------------------------------------------------------------------------------
 		페이지 이동(forward), 리다이렉트
 -------------------------------------------------------------------------------------------------------*/		
		if(command.equals("/insertproduct.do")) {
			response.sendRedirect("/productlist.do");
			
		}else if(command.equals("/addcart.do")) {
			String pid = request.getParameter("pid");  // 상품 주문버튼 클릭 후, 새로고침 시 상품 수량 추가 방지
			response.sendRedirect("/productinfo.do?pid=" + pid);
			
		}else if(command.equals("/deletecart.do") || command.equals("/removecart.do")) {
			response.sendRedirect("/cart.do");
			
		}else {
			RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
			dispatch.forward(request, response);
		}
	}
}
