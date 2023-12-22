package controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import board.Board;
import board.BoardDAO;
import member.Member;
import member.MemberDAO;
import reply.Reply;
import reply.ReplyDAO;
import voter.Voter;
import voter.VoterDAO;

@WebServlet("*.do") // '/' 이하의 경로에서 do로 끝나는
public class MainController extends HttpServlet {
	
	private static final long serialVersionUID = 10L;
	// 필드
	MemberDAO mDAO; 
	BoardDAO bDAO;
	ReplyDAO rDAO;
	VoterDAO vDAO;
	
    public MainController() { //생성자
    	mDAO = new MemberDAO();
    	bDAO = new BoardDAO();
    	rDAO = new ReplyDAO();
    	vDAO = new VoterDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 한글 인코딩
		request.setCharacterEncoding("utf-8");
		// 세션에 응답할 컨텐츠 유형
		response.setContentType("text/html; charset=utf-8");
		
		// 경로 설정(mapping)
		// uri = context(/) + 파일경로(.do)
		String uri = request.getRequestURI();
		System.out.println(uri);
		
		// command 패턴
		String command = uri.substring(uri.lastIndexOf("/"));
		System.out.println(command);
		
		String nextPage = ""; // 이동 페이지 초기화
		
		// 세션 객체 생성(로그인 인증)
		HttpSession session = request.getSession();
		
		// memberview에 출력 할 객체 생성 후 37행! 
		/*
		 * PrintWriter out = response.getWriter();
		 */
		if(command.equals("/main.do")) {
			// 메인 페이지에 게시글 보내기
			List<Board> boardList = bDAO.getBoardList(); // 회원정보를 db에서 가져옴
			request.setAttribute("boardList", boardList);
		/*	System.out.println(boardList.size() + " 개"); 	*/	
	
			if(boardList.size() >= 3) {
				// 게시글 3개를 저장할 배열 생성
				Board[] newBoards = {boardList.get(0), boardList.get(1), boardList.get(2)};
				request.setAttribute("boardList", newBoards);
			}
			nextPage = "/main.jsp";
			
		}else if(command.equals("/memberlist.do")) {
			// 회원 정보를 db에서 가져옴
			List<Member> memberList = mDAO.getMemberList();
			request.setAttribute("memberList", memberList);
			nextPage = "/member/memberlist.jsp"; // 이동할 페이지	
			
		}else if(command.equals("/joinform.do")) { // 회원가입폼
			
			nextPage = "/member/joinform.jsp"; 
			
		}else if(command.equals("/insertmember.do")) {
			//빈 회원 객체 생성 > 파라미터 데이터를 받아와서 세팅
			
			// 폼 데이터 받기
			String id = request.getParameter("id");
			String passwd = request.getParameter("passwd");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String gender = request.getParameter("gender");
			
			// 객체에 데이터 세팅
			Member m = new Member(); 
			m.setId(id);
			m.setPasswd(passwd);
			m.setName(name);
			m.setEmail(email);
			m.setGender(gender);
			// db에 저장
			mDAO.insertMember(m);
			
			// 자동 로그인
			session.setAttribute("sessionID", m.getId()) ; // 아이디를 가져와서 sessionID(세션이름)발급
			session.setAttribute("sessionName", m.getName()); // 이름을 가져와서 sessionName(세션이름) 발급
			
			// 회원 가입 후 이동
			nextPage = "index.jsp"; 
		}else if(command.equals("/memberview.do")) {
			String id = request.getParameter("id");
			
			 Member member = mDAO.getMember(id);
			 
			 // model 생성
			 request.setAttribute("member", member);
			 
			nextPage = "/member/memberview.jsp"; //회원 정보 상세보기 페이지로 이동
			
		// 로그인 폼 페이지로 이동
		}else if(command.equals("/loginform.do")) {
			nextPage = "/member/loginform.jsp";
		// 로그인 처리
		}else if(command.equals("/login.do")) {
			// 아이디와 비번 파라미터 받기
			String id = request.getParameter("id");
			String passwd = request.getParameter("passwd");
			
			// 빈 객체를 생성해서 아이디와 비번 세팅
			Member m = new Member();
			m.setId(id);
			m.setPasswd(passwd);
			
			// 로그인 인증
			Member member = mDAO.checkLogin(m);
			String name = member.getName();
//			boolean result = mDAO.checkLogin(m);
			if(name != null) { // result가 true면 세션 발급! >> name이 null이 아니면
				session.setAttribute("sessionId", id); // 아이디 세션발급
				session.setAttribute("sessionName", name); //이름 세션발급 
				// 로그인 후 페이지 이동
				nextPage = "/index.jsp";
			}else {
				// 로그인 에러를 모델로 보내기
				String error = "아이디나 비밀번호를 다시 확인 해 주세요!";
				request.setAttribute("error", error);
				// 에러 발생 후 페이지 이동
				nextPage = "/member/loginform.jsp";
				}
			}else if(command.equals("/logout.do")) {
				session.invalidate(); // 모든 세션 삭제
				nextPage = "/index.jsp";
		}
		
/*=================================== 게시판 >> boardlist.jsp (+페이지,검색처리) ==============================================*/
		if(command.equals("/boardlist.do")) {
			
			// 페이지 처리
			String pageNum = request.getParameter("pageNum");
			if(pageNum == null) { // 페이지 번호를 클릭하지 않았을 때의 default값(기본값)
				pageNum = "1";
			}
			// 현재 페이지
			int currentPage = Integer.parseInt(pageNum);
			
			// 페이지 당 게시글 수 - 10(pageSize)
			int pageSize = 10;
			
			// 1페이지 : 1번(1페이지의 첫번째 행 startRow)/ 2페이지 : 11번글부터,, / 3페이지: 21번글 부터,,,
			int startRow = (currentPage -1) * pageSize + 1;
			System.out.println("페이지의 첫 행 : " + startRow);
			
			// 시작페이지(startPage) : 12행 - 2페이지, 22행 - 3페이지
			int startPage = startRow / pageSize + 1;
			
			// 종료(끝) 페이지 : 전체 게시글 총 수 / 페이지 당 개수
			int totalRow = bDAO.getBoardCount();
			int endPage = totalRow / pageSize;
			
			// 페이지 당 개수(10)로 나누어 떨어지지 않는 경우 코딩
			endPage = (totalRow % pageSize == 0) ? endPage : endPage + 1; 
			System.out.println("총 게시글 수 : " + totalRow);
			System.out.println("마지막 페이지 : " + endPage);
			
			// 검색 처리
			String _field = request.getParameter("field"); // 임시로 저장
			String _keyword = request.getParameter("keyword");
			
			String field = "";
			String keyword = "";
			//null 처리
			if(_field != null) { // 필드 값이 있는 경우
				field = _field;
			}else { // 필드값 값이 없는 경우(디폴트) 
				field = "title";
			}
			if(_keyword != null) { // 검색어가 있는 경우
				keyword = _keyword;
			}else {	// 검색어가 없는 경우(디폴트)
				keyword = "";
			}
			
			// db에서 페이지 목록 메서드 호출
//			List<Board> boardList = bDAO.getBoardList(currentPage);
			
			// 검색처리
//			List<Board> boardList = bDAO.getBoardList(field, keyword);
			
			// 페이지와 검색 처리
			List<Board> boardList = bDAO.getBoardList(field, keyword, currentPage);
			
			// model로 생성 (MVC) 
			request.setAttribute("boardList", boardList);
			request.setAttribute("page", currentPage);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("field", field);
			request.setAttribute("keyword", keyword);
			
			nextPage = "/board/boardlist.jsp";
		// 글쓰기 버튼 눌렀을 때, 글쓰기 폼으로 이동	
		}else if(command.equals("/writeform.do")) {
			nextPage = "/board/writeform.jsp";
		// 
		}else if(command.equals("/write.do")) {
			
			String realFolder = "C:\\jspworks\\members\\src\\main\\webapp\\upload";
			int maxSize = 10*1024*1024; //10MB
			String encType = "utf-8"; // 파일 이름 한글 인코딩
			DefaultFileRenamePolicy policy = new DefaultFileRenamePolicy();
			
			// 5가지 인자=데이터(request, realFolder, maxSize, encType, policy)
			MultipartRequest multi = new MultipartRequest(request, realFolder, maxSize, encType, policy);
			
			// 폼데이터 받기
			String title = multi.getParameter("title");
			String content = multi.getParameter("content");
			
			// 세션 가져오기 (id) >> writeform.jsp에서 id를 받지 않으므로 세션발급된 id불러오기!
			String id = (String)session.getAttribute("sessionId");
			
			// file 속성 : <?>동적 자료구조로 ..어떤 자료형이 들어올지 모르므로!
			// file 파라미터 추출
			Enumeration<?> files = multi.getFileNames();
			String filename ="";
			
			while(files.hasMoreElements()) { // 파일이름이 있는 동안 반복
				String userFilename = (String)files.nextElement();
				
				// 실제 저장될 이름
				filename = multi.getFilesystemName(userFilename);
			}
			
			// db에 저장
			Board b = new Board();
			b.setTitle(title);
			b.setContent(content);
			b.setFilename(filename);
			b.setId(id);
			
			// write메서드 실행, DAO 호출
			bDAO.write(b);
			
		}else if(command.equals("/boardview.do")) {
			// 글 제목에서 요청한 글 번호 받기
			int bno = Integer.parseInt(request.getParameter("bno"));
			// 글 상세보기 처리
			Board board = bDAO.getBoard(bno);
			// 세션 아이디 가져옴
			String id = (String)session.getAttribute("sessionId");
			
			// 좋아요 개수 - 해당 게시글의 개수를 출력
			int voteCount = vDAO.voteCount(bno);
				// 반환값이 있으면(db에 있음) 밑에서 request로 모델 생성, 뷰보내기
			
			// 하트 상태 바꾸기 (toggle 방식)
			boolean sw = false;
			int result = vDAO.checkVoter(bno, id); // 게시글 번호, 세션 아이디
			if(result == 0) { // 좋아요 저장 안된상태
				sw = true;
			}else {
				sw = false;
			}
			
			//댓글 목록보기
			List<Reply> replyList = rDAO.getReplyList(bno);
			
			// 모델 생성해서 뷰로 보내기
			request.setAttribute("board", board);
			request.setAttribute("replyList", replyList); // 댓글 목록
			request.setAttribute("voteCount", voteCount); // 좋아요 수 ($로 받아주기 >> boardview)
			request.setAttribute("sw", sw); // 상태 보냄
			
			nextPage = "/board/boardview.jsp";
		}else if(command.equals("/deleteboard.do")) {
			int bno = Integer.parseInt(request.getParameter("bno"));
			// 삭제 처리
			bDAO.deleteboard(bno);
			
			nextPage = "/boardlist.do";
		}else if(command.equals("/updateboardform.do")) {
			//수정을 위해서 게시글 가져오기
			int bno = Integer.parseInt(request.getParameter("bno"));
			//게시글 1건 가져오기
			Board board = bDAO.getBoard(bno);
			// 모델 생성 (가져오기 위한..)
			request.setAttribute("board", board);
			
			nextPage = "/board/updateboardform.jsp";
			
		}else if(command.equals("/updateboard.do")) {
			
			// 게시글 bno번호(hidden),제목, 내용을 파라미터로 받음
			String realFolder = "C:\\jspworks\\members\\src\\main\\webapp\\upload";
			int maxSize = 10*1024*1024; //10MB
			String encType = "utf-8"; // 파일 이름 한글 인코딩
			DefaultFileRenamePolicy policy = new DefaultFileRenamePolicy();
			
			// 5가지 인자=데이터(request, realFolder, maxSize, encType, policy)
			MultipartRequest multi = new MultipartRequest(request, realFolder, maxSize, encType, policy);
			
			// 폼데이터 받기
			int bno = Integer.parseInt(multi.getParameter("bno"));
			String title = multi.getParameter("title");
			String content = multi.getParameter("content");
			
			// file 속성 : <?>동적 자료구조로 ..어떤 자료형이 들어올지 모르므로!
			// file 파라미터 추출
			Enumeration<?> files = multi.getFileNames();
			String filename ="";
			
			while(files.hasMoreElements()) { // 파일이름이 있는 동안 반복
				String userFilename = (String)files.nextElement();
				
				// 실제 저장될 이름
				filename = multi.getFilesystemName(userFilename);
			}
			
			// db에 저장
			Board b = new Board();
			b.setTitle(title);
			b.setContent(content);
			b.setFilename(filename);
			b.setBno(bno); //boardDAO / 334행에서 multi로 폼데이터 받기
			
			if(filename != null ) {
				bDAO.updateboard(b);
			}else {
				bDAO.updateboardNofile(b);
			}
			
//			nextPage = "/boardlist.do";	
		}else if(command.equals("/like.do")) {
			int bno =  Integer.parseInt(request.getParameter("bno"));
			String id = request.getParameter("id");
			
			// 좋아요 추가 (insert)
			Voter voter = new Voter();
			voter.setBno(bno);
			voter.setMid(id);
			
			// 2.좋아요가 저장되어있는 지 유무 체크
			int result = vDAO.checkVoter(bno, id);
			if(result == 0) { // db에 없으면(저장 안됨)
				vDAO.insertVote(voter); // 좋아요 추가 (한번 클릭한 후)
			}else { // result == 1
				vDAO.deleteVote(voter); // 좋아요 삭제 (한번 또 클릭하면 삭제)
				
			}
		}
		
/*=================================== 댓글 구현 >> Replay.java ==================================================*/
		if(command.equals("/insertreply.do")) {
			//댓글 폼 데이터 받기
			int bno = Integer.parseInt(request.getParameter("bno"));
			String rcontent = request.getParameter("rcontent");
			String replyer = request.getParameter("replyer");
			
			// 댓글 등록 처리
			Reply r = new Reply();
			r.setBno(bno);
			r.setRcontent(rcontent);
			r.setReplyer(replyer);
			
			rDAO.insertreply(r);
		}if(command.equals("/deletereply.do")) {
			int rno = Integer.parseInt(request.getParameter("rno"));
			//삭제 처리 메서드 호출
			rDAO.deletereply(rno);
		}
		
		
		// redirect와 forward 구분하기 ..
		// 새로고침 시, 게시글 중복 생성 문제 해결 || 글 수정 후 새로고침 시, 작성일 시간 연장 문제 해결
		if(command.equals("/write.do") || command.equals("/updateboard.do")) {
			response.sendRedirect("boardlist.do");
		}else if(command.equals("/insertreply.do") || command.equals("/deletereply.do") || command.equals("/like.do")) {
			int bno = Integer.parseInt(request.getParameter("bno"));
			response.sendRedirect("/boardview.do?bno=" + bno);
		}else {
			RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
			dispatch.forward(request, response);
		}
	}
}
