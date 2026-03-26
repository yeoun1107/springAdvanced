/**
 *	File Name			: CodeServiceTest.java
 *	File Description	: 코드 관리 서비스 계층 단위 테스트
 *
 *	Date				Author				Description
 *	--------------------------------------------------------
 *	2026-03-18			yeoun1107			최초작성
 */
package com.study.spring.code.service;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import com.study.spring.code.vo.CodeGroupVo;
import com.study.spring.code.vo.CodeDetailVo;

@SpringBootTest
@Transactional
public class CodeServiceTest {

	@Autowired
	private CodeService codeService;

	@Test
	@DisplayName("1. 코드 그룹 생성 및 목록 조회 테스트")
	public void createAndListCodeGroupTest() throws Exception {
		// Given
		CodeGroupVo groupVo = CodeGroupVo.builder()
				.groupCd("TEST_GRP")
				.groupNm("테스트 그룹")
				.groupDesc("테스트 설명")
				.regId("admin")
				.build();
		
		// When
		int result = codeService.createCodeGroup(groupVo);
		List<CodeGroupVo> list = codeService.retrieveCodeGroupList(new CodeGroupVo());
		
		// Then
		assertEquals(1, result);
		assertTrue(list.size() > 0);
		assertTrue(list.stream().anyMatch(g -> g.getGroupCd().equals("TEST_GRP")));
	}

	@Test
	@DisplayName("2. 상세 코드 생성 및 그룹별 목록 조회 테스트")
	public void createAndListCodeDetailTest() throws Exception {
		// Given
		CodeDetailVo detailVo = CodeDetailVo.builder()
				.groupCd("REGN_CD") // 기존에 init.sql에 존재하는 그룹 코드 사용
				.detailCd("Z")
				.codeNm("제주특별자치도")
				.sortOrder(9)
				.regId("admin")
				.build();
		
		// When
		int result = codeService.createCodeDetail(detailVo);
		CodeDetailVo searchVo = new CodeDetailVo();
		searchVo.setGroupCd("REGN_CD");
		List<CodeDetailVo> list = codeService.retrieveCodeDetailList(searchVo);
		
		// Then
		assertEquals(1, result);
		assertTrue(list.size() > 0);
		assertTrue(list.stream().anyMatch(d -> d.getDetailCd().equals("Z")));
	}

	@Test
	@DisplayName("3. 코드 Soft Delete 테스트 (use_yn 업데이트)")
	public void deleteCodeTest() throws Exception {
		// Given
		CodeGroupVo groupVo = CodeGroupVo.builder()
				.groupCd("DEL_GRP")
				.groupNm("삭제 예정 그룹")
				.regId("admin")
				.build();
		codeService.createCodeGroup(groupVo);
		
		// When
		int result = codeService.deleteCodeGroup(groupVo);
		CodeGroupVo deletedGroup = codeService.retrieveCodeGroup("DEL_GRP");
		
		// Then
		assertEquals(1, result);
		assertEquals("N", deletedGroup.getUseYn());
	}
}
