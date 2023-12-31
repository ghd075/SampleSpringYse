package kr.or.ddit.book.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.book.dao.BookDAO;

/*
 * 일반적으로 서비스 레이어는 인터페이스와 클래스를 함께 사용한다.
 * 스프링은 직접 클래스를 생성하는 것을 지양하고 인터페이스를 통해 접근하는 것을 권장하는 프레임워크이다.
 */
@Service
public class BookServiceImpl implements IBookService {

	/*
	 * Service 클래스는 비즈니스 클래스가 위치하는 곳이다.
	 * 스프링 MVC 구조에서 서비스 클래스는 컨트롤러와 DAO를 연결하는 역할을 한다.
	 * 
	 * 어노테이션(@) Service는 스프링에 서비스 클래스임을 알려준다.
	 * 
	 * 데이터베이스 접근을 위해 BookDAO 인스턴스를 주입받는다.
	 * 클래스의 이름이 Impl로 끝나는 것은 implements의 약자로 관습에 따른다.
	 * Impl이 붙고 안 붙고에 따라 클래스인지 인터페이스인지 구별하기 쉽다.
	 */
	@Inject
	private BookDAO dao;
	
	
	/**
	 * <p>책 등록</p>
	 * @since SampleSpringYse
	 * @author ddit
	 * @param map 등록할 책 데이터
	 * @return 성공 책ID, 실패 null
	 */
	@Override
	public String insertBook(Map<String, Object> map) {
		// status 변수에는 영향받은 행 수가 담긴다.
		// insert 구문은 입력이 성공하면 1, 실패하면 0을 리턴한다.
		int status = dao.insert(map);
		if(status == 1) {
			// 결과가 성공일 시, map 인스턴스에 book 테이블의 pk인 book_id가 담겨있다.
			return map.get("book_id").toString();
		}
		return null;
	}


	/**
	 * <p>책 상세보기</p>
	 * @since SampleSpringYse
	 * @author ddit
	 * @param map 책 ID
	 * @return ID에 해당하는 책 정보
	 */
	@Override
	public Map<String, Object> selectBook(Map<String, Object> map) {
		// 서비스 내 detail 함수는 dao를 호출한 결과를 바로 리턴하는 일만 한다.
		return dao.selectBook(map);
	}

	
	/**
	 * <p>책 수정</p>
	 * @since SampleSpringYse
	 * @author ddit
	 * @param map 책 ID
	 * @return 성공 1(true), 실패 0(false)	
	 */
	@Override
	public boolean updateBook(Map<String, Object> map) {
		// 수정의 경우 입력과는 다르게 PK를 가져오거나 하는 절차가 필요 없으므로 행이 정상적으로 영향 받았는지만 검사하면 된다.
		int status = dao.updateBook(map);
		return status == 1;
	}

	/**
	 * <p>책 삭제</p>
	 * @since SampleSpringYse
	 * @author ddit
	 * @param map 책 ID
	 * @return 성공 1(true), 실패 0(false)	
	 */
	@Override
	public boolean removeBook(Map<String, Object> map) {
		// 삭제의 경우 수정과 동일하게 한개의 행이 제대로 영향받았는지만 검사하면 된다.
		int status = dao.removeBook(map);
		return status == 1;
	}


	/**
	 * <p>책 목록</p>
	 * @since SampleSpringYse
	 * @author ddit
	 * @param 현재는 없음
	 * @return 성공 List(책), 실패 null	
	 */	
	@Override
	public List<Map<String, Object>> selectBookList(Map<String, Object> map) {
		return dao.selectBookList(map);
	}

}
