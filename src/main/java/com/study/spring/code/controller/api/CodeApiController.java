package com.study.spring.code.controller.api;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.study.spring.code.service.CodeService;
import com.study.spring.code.vo.CodeGroupVo;
import com.study.spring.code.vo.CodeDetailVo;
import com.study.spring.common.model.CommonResponseDto;
import com.study.spring.common.model.SessionUserDto;
import com.study.spring.utils.UserContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 *	File Name			: CodeApiController.java
 *	File Description	: 코드 관리 REST API 컨트롤러
 *
 *	Date				Author				Description
 *	--------------------------------------------------------
 *	2026-03-18			yeoun1107			최초작성
 *	2026-03-18			yeoun1107			GET/POST/PUT/DELETE 모든 CRUD 메서드 완비
 *  2026-03-25			yeoun1107			전역 에러 처리 핸들러를 사용한 리팩토링(코드 간결화)
 */
@Slf4j
@RestController
@RequestMapping("/api/settings/code")
@RequiredArgsConstructor
public class CodeApiController {

	private final CodeService codeService;

	/**
	 *  @Description  코드 그룹 목록 조회
	 *  @method 	  retrieveCodeGroupList
	 *  @Writter 	  yeoun1107
	 *  @Date 		  2026-03-18
	 *  @return 	  CommonResponseDto
	 *  @throws 	  Exception
	 *  
	 *  @EditHistory
	 *  Date					Author				Description
	 *  --------------------------------------------------------------
	 *  2026-03-18			    yeoun1107			최초작성
	 *  2026-03-25				yeoun1107			전역 에러 처리 핸들러를 사용한 리팩토링(코드 간결화)
	 */
	@GetMapping("/groups")
	public CommonResponseDto<List<CodeGroupVo>> retrieveCodeGroupList(CodeGroupVo codeGroupVo) throws Exception {

			// 1. 코드 그룹 목록 조회
			List<CodeGroupVo> list = codeService.retrieveCodeGroupList(codeGroupVo);

			// 2. 응답 전송
			return CommonResponseDto.success(list);
	}


	/**
	 *  @Description  코드 상세 목록 조회 (그룹 코드 기준)
	 *  @method 	  retrieveCodeDetailList
	 *  @Writter 	  yeoun1107
	 *  @Date 		  2026-03-18
	 *  @return 	  CommonResponseDto
	 *  @throws 	  Exception
	 *  
	 *  @EditHistory
	 *  Date					Author				Description
	 *  --------------------------------------------------------------
	 *  2026-03-18			    yeoun1107			최초작성
	 *  2026-03-25				yeoun1107			전역 에러 처리 핸들러를 사용한 리팩토링(코드 간결화)
	 */
	@GetMapping("/details")
	public CommonResponseDto<List<CodeDetailVo>> retrieveCodeDetailList(CodeDetailVo codeDetailVo) throws Exception {

			// 1. 코드 상세 목록 조회
			List<CodeDetailVo> list = codeService.retrieveCodeDetailList(codeDetailVo);

			// 2. 응답 전송
			return CommonResponseDto.success(list);
	}


	/**
	 *  @Description  코드 그룹 등록
	 *  @method 	  createCodeGroup
	 *  @Writter 	  yeoun1107
	 *  @Date 		  2026-03-18
	 *  @return 	  CommonResponseDto
	 *  @throws 	  Exception
	 *  
	 *  @EditHistory
	 *  Date					Author				Description
	 *  --------------------------------------------------------------
	 *  2026-03-18			    yeoun1107			최초작성
	 *  2026-03-25				yeoun1107			전역 에러 처리 핸들러를 사용한 리팩토링(코드 간결화)
	 */
	@PostMapping("/groups")
	public CommonResponseDto<Integer> createCodeGroup(@RequestBody CodeGroupVo codeGroupVo) throws Exception {

			// 1. 로그인 사용자 정보 세팅
			SessionUserDto loginUser = UserContext.getUser();
			codeGroupVo.setRegId(loginUser != null ? loginUser.getUserId() : "ANONYMOUS"); 
			
			// 2. 코드 그룹 등록
			int result = codeService.createCodeGroup(codeGroupVo);

			// 3. 응답 전송
			return CommonResponseDto.success(result);

	}


	/**
	 *  @Description  코드 그룹 수정
	 *  @method 	  updateCodeGroup
	 *  @Writter 	  yeoun1107
	 *  @Date 		  2026-03-18
	 *  @return 	  CommonResponseDto
	 *  @throws 	  Exception
	 *  
	 *  @EditHistory
	 *  Date					Author				Description
	 *  --------------------------------------------------------------
	 *  2026-03-18			    yeoun1107			최초작성
	 *  2026-03-25				yeoun1107			전역 에러 처리 핸들러를 사용한 리팩토링(코드 간결화)
	 */
	@PutMapping("/groups")
	public CommonResponseDto<Integer> updateCodeGroup(@RequestBody CodeGroupVo codeGroupVo) throws Exception {

			// 1. 로그인 사용자 정보 세팅
			SessionUserDto loginUser = UserContext.getUser();
			codeGroupVo.setUpdId(loginUser != null ? loginUser.getUserId() : "ANONYMOUS");
			
			// 2. 코드 그룹 수정
			int result = codeService.updateCodeGroup(codeGroupVo);

			// 3. 응답 전송
			return CommonResponseDto.success(result);
	}


	/**
	 *  @Description  코드 상세 등록
	 *  @method 	  createCodeDetail
	 *  @Writter 	  yeoun1107
	 *  @Date 		  2026-03-18
	 *  @return 	  CommonResponseDto
	 *  @throws 	  Exception
	 *  
	 *  @EditHistory
	 *  Date					Author				Description
	 *  --------------------------------------------------------------
	 *  2026-03-18			    yeoun1107			최초작성
	 *  2026-03-25				yeoun1107			전역 에러 처리 핸들러를 사용한 리팩토링(코드 간결화)
	 */
	@PostMapping("/details")
	public CommonResponseDto<Integer> createCodeDetail(@RequestBody CodeDetailVo codeDetailVo) throws Exception {

			// 1. 로그인 사용자 정보 세팅
			SessionUserDto loginUser = UserContext.getUser();
			codeDetailVo.setRegId(loginUser != null ? loginUser.getUserId() : "ANONYMOUS");
			
			// 2. 코드 상세 등록
			int result = codeService.createCodeDetail(codeDetailVo);

			// 3. 응답 전송
			return CommonResponseDto.success(result);
	}


	/**
	 *  @Description  코드 상세 수정
	 *  @method 	  updateCodeDetail
	 *  @Writter 	  yeoun1107
	 *  @Date 		  2026-03-18
	 *  @return 	  CommonResponseDto
	 *  @throws 	  Exception
	 *  
	 *  @EditHistory
	 *  Date					Author				Description
	 *  --------------------------------------------------------------
	 *  2026-03-18			    yeoun1107			최초작성
	 *  2026-03-25				yeoun1107			전역 에러 처리 핸들러를 사용한 리팩토링(코드 간결화)
	 */
	@PutMapping("/details")
	public CommonResponseDto<Integer> updateCodeDetail(@RequestBody CodeDetailVo codeDetailVo) throws Exception {

			// 1. 로그인 사용자 정보 세팅
			SessionUserDto loginUser = UserContext.getUser();
			codeDetailVo.setUpdId(loginUser != null ? loginUser.getUserId() : "ANONYMOUS");
			
			// 2. 코드 상세 수정
			int result = codeService.updateCodeDetail(codeDetailVo);

			// 3. 응답 전송
			return CommonResponseDto.success(result);
	}


	/**
	 *  @Description  코드 그룹 삭제 (Soft Delete)
	 *  @method 	  deleteCodeGroup
	 *  @Writter 	  yeoun1107
	 *  @Date 		  2026-03-18
	 *  @return 	  CommonResponseDto
	 *  @throws 	  Exception
	 *  
	 *  @EditHistory
	 *  Date					Author				Description
	 *  --------------------------------------------------------------
	 *  2026-03-18			    yeoun1107			최초작성
	 *  2026-03-25				yeoun1107			전역 에러 처리 핸들러를 사용한 리팩토링(코드 간결화)
	 */
	@DeleteMapping("/groups/{groupCd}")
	public CommonResponseDto<Integer> deleteCodeGroup(@PathVariable String groupCd) throws Exception {

			// 1. 로그인 사용자 정보 세팅
			SessionUserDto loginUser = UserContext.getUser();

			// 2. 코드그룹 파라미터 세팅
			CodeGroupVo groupVo = CodeGroupVo.builder()
					.groupCd(groupCd)
					.updId(loginUser != null ? loginUser.getUserId() : "ANONYMOUS")
					.build();
			
			// 3. 코드 그룹 삭제
			int result = codeService.deleteCodeGroup(groupVo);

			// 4. 응답 전송
			return CommonResponseDto.success(result);
	}

	
	/**
	 *  @Description  코드 상세 삭제 (Soft Delete)
	 *  @method 	  deleteCodeDetail
	 *  @Writter 	  yeoun1107
	 *  @Date 		  2026-03-18
	 *  @return 	  CommonResponseDto
	 *  @throws 	  Exception
	 *  
	 *  @EditHistory
	 *  Date					Author				Description
	 *  --------------------------------------------------------------
	 *  2026-03-18			    yeoun1107			최초작성
	 *  2026-03-25				yeoun1107			전역 에러 처리 핸들러를 사용한 리팩토링(코드 간결화)
	 */
	@DeleteMapping("/details/{groupCd}/{detailCd}")
	public CommonResponseDto<Integer> deleteCodeDetail(@PathVariable String groupCd, @PathVariable String detailCd) throws Exception {

			// 1. 로그인 사용자 정보 세팅
			SessionUserDto loginUser = UserContext.getUser();

			// 2. 코드 상세 파라미터 세팅
			CodeDetailVo detailVo = CodeDetailVo.builder()
					.groupCd(groupCd)
					.detailCd(detailCd)
					.updId(loginUser != null ? loginUser.getUserId() : "ANONYMOUS")
					.build();
					
			// 3. 코드 상세 삭제
			int result = codeService.deleteCodeDetail(detailVo);

			// 4. 응답 전송
			return CommonResponseDto.success(result);
		
	}
}
