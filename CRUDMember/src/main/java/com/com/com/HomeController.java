package com.com.com;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {

		return "main";
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Locale locale, Model model) {
		MemberDB db = new MemberDB();
		String html = db.selectData();
		model.addAttribute("list", html);

		return "list";
	}

	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public String insert(Locale locale, Model model) {

		return "insert";
	}

	@RequestMapping(value = "/insert_action", method = RequestMethod.POST)
//	POST로 작성하면 URL에 정보가 노출되지 않음
	public String insertAction(HttpServletRequest request, Locale locale, Model model) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String now = sdf.format(Calendar.getInstance().getTime());
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("name");
		String birthday = request.getParameter("birthday");
		String address = request.getParameter("address");

		Member mb = new Member(id, pwd, name, birthday, address, now, now);
		MemberDB db = new MemberDB();
		boolean isSuccess = db.insertData(mb);
		if (isSuccess) {
			model.addAttribute("message", "정보 입력이 완료되었습니다.");
		} else {
			model.addAttribute("message", "DB Error");
		}

		return "message";

	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create(Locale locale, Model model) {

		MemberDB db = new MemberDB();
		boolean isSuccess = db.createTable();
		if (isSuccess) {

			model.addAttribute("message", " 테이블이 생성되었습니다.");
		} else {
			model.addAttribute("message", "DB Error");
		}
		return "tableMessage";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(Locale locale, Model model, @RequestParam("idx") int idx) {

		MemberDB db = new MemberDB();
		Member selectMember = db.detailsData(idx);

		if (selectMember != null) {
			model.addAttribute("idx", selectMember.idx);
			model.addAttribute("id", selectMember.id);
			model.addAttribute("pwd", selectMember.pwd);
			model.addAttribute("name", selectMember.name);
			model.addAttribute("address", selectMember.address);
			model.addAttribute("birthday", selectMember.birthday);
		}

		return "update";
	}

	@RequestMapping(value = "/update_action", method = RequestMethod.GET)
	public String updateAction(Locale locale, Model model, @RequestParam("idx") int idx, @RequestParam("id") String id,
			@RequestParam("pwd") String pwd, @RequestParam("name") String name, @RequestParam("address") String address,
			@RequestParam("birthday") String birthday) {

		MemberDB db = new MemberDB();

		db.updateData(idx, id, pwd, name, address, birthday);

		model.addAttribute("message", "데이터가 수정되었습니다.");
		return "message";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(Locale locale, Model model, @RequestParam("idx") int idx) {

		return "delete";
	}

	@RequestMapping(value = "/delete_action", method = RequestMethod.GET)
	public String deleteAction(Locale locale, Model model, @RequestParam("idx") int idx) {

		MemberDB db = new MemberDB();

		db.deleteData(idx);

		model.addAttribute("message", "정보가 삭제되었습니다.");
		return "message";

	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search(Locale locale, Model model) {

		return "search";
	}

	@RequestMapping(value = "/search_action", method = RequestMethod.GET)
	public String searchAction(Locale locale, Model model, @RequestParam("name") String name) {

		MemberDB db = new MemberDB();
		String searchName = db.searchData(name);

		model.addAttribute("searchResult", searchName);
		return "searchResult";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Locale locale, Model model) {
		return "edit";
	}

	@RequestMapping(value = "/edit_action", method = RequestMethod.GET)
	public String edit_action(Locale locale, Model model, @RequestParam("id") String id,
			@RequestParam("pwd") String pwd) {

		MemberDB db = new MemberDB();
		int idx = db.verificationData(id, pwd);
		// id, pwd에 해당하는 값을 DB에 인덱스 = idx
		if (idx > 0) {
			// id, pwd와 일치하는 값이 db에 있다면
			return "/update";
		} else {
			model.addAttribute("message", "DB Error");
			return "message";
		}
	}

	@RequestMapping(value = "/logon", method = RequestMethod.GET)
	public String logon(Locale locale, Model model) {

		return "logon";
	}

	@RequestMapping(value = "/login_action", method = RequestMethod.POST)
	public String login_action(HttpServletRequest request, Locale locale, Model model) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");

		MemberDB db = new MemberDB();
		
		boolean isSuccess = db.loginDB(id,pwd);
		if(isSuccess) {
			HttpSession session = request.getSession();
			//Spring Web MVC에서는 HttpSession을 주입해야 할 때, 내부적으로 Servlet Container에게 Session을 달라고 합니다.
			//그리고 그 때 Servlet Container가 HttpSession을 생성해줍니다. 
			session.setAttribute("is_login", true);
			//setAttribute는 선택한 요소의 속성값을 정한다.
			//setAttribute( 'attributename', 'attributevalue' )
			return "redirect:/";
			//redirect뒤에 아무것도 없기때문에 메인페이지로 돌아가게 해줌

		}
		//return "redirect:/login";
		//로그인에 실패했다면 로그인 페이지로 새로고침 함
		model.addAttribute("message", "로그인 실패");
		return "loginFailedMessage";
	}
}