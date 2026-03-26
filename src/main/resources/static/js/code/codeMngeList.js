/**
 *	File Name			: codeMngeList.js
 *	File Description	: 코드 관리 화면 전용 스크립트 (이벤트 위임 및 데이터 바인딩 보강)
 *
 *	Date				Author				Description
 *	--------------------------------------------------------
 *	2026-03-18			yeoun1107			최초작성
 *	2026-03-18			yeoun1107			commUtil 공통 함수 적용 및 로직 간소화
 *	2026-03-18			yeoun1107			이벤트 위임 적용 및 데이터 바인딩 오류 수정
 */

$(function() {
	const code = {
		selectedGroupCd: null,
		selectedGroupNm: null,
		isGroupEdit: false,
		isDetailEdit: false,

		/**
		 * @Description		: 초기화 메서드
		 * @method			: init
		 */
		init: function() {
			this.bindEvents();
			this.getGroupList();
		},

		/**
		 * @Description		: 이벤트 바인딩 (이벤트 위임 방식 적용)
		 * @method			: bindEvents
		 */
		bindEvents: function() {
			const _this = this;

			// 1. 그룹 검색 및 엔터키 이벤트
			$("#btnSearchGroup").on("click", function() { _this.getGroupList(); });
			$("#searchGroupNm").on("keyup", function(e) { if (e.key === "Enter") _this.getGroupList(); });
			
			// 2. 그룹 폼 액션
			$("#btnSaveGroup").on("click", function() { _this.executeSaveGroup(); });
			$("#btnResetGroup").on("click", function() { _this.clearGroupForm(); });

			// 3. 상세 폼 액션
			$("#btnSaveDetail").on("click", function() { _this.executeSaveDetail(); });
			$("#btnResetDetail").on("click", function() { _this.clearDetailForm(); });

			// 4. 그룹 목록 이벤트 위임 (행 클릭 및 수정/삭제 버튼)
			$("#groupListBody").on("click", "tr.row-hover", function(e) {
				const $tr = $(this);
				const $target = $(e.target);

				// 버튼 클릭 시에는 해당 버튼 이벤트만 실행되도록 분기
				if ($target.hasClass("edit-group")) {
					_this.bindGroupForm($tr.data());
					return;
				}
				if ($target.hasClass("btn-delete-group")) {
					const groupCd = $tr.data("cd");
					if (confirm(`[${groupCd}] 을 삭제하시겠습니까?`)) {
						_this.executeDeleteGroup(groupCd);
					}
					return;
				}

				// 행 클릭 시 (조회 로직)
				_this.selectedGroupCd = $tr.data("cd");
				_this.selectedGroupNm = $tr.data("nm");
				
				$("#groupListBody tr").removeClass("active");
				$tr.addClass("active");

				_this.getDetailList();
			});

			// 5. 상세 목록 이벤트 위임
			$("#detailListBody").on("click", "tr.row-hover, tr.deleted", function(e) {
				const $tr = $(this);
				const $target = $(e.target);

				if ($target.hasClass("edit-detail")) {
					_this.bindDetailForm($tr.data());
					return;
				}
				if ($target.hasClass("btn-delete-detail")) {
					const detailCd = $tr.data("cd");
					if (confirm(`[${detailCd}] 를 삭제하시겠습니까?`)) {
						_this.executeDeleteDetail(detailCd);
					}
					return;
				}

				// 강조 처리
				$("#detailListBody tr").removeClass("active");
				$tr.addClass("active");
			});
		},

		// ==========================================
		// 1. 코드 그룹 (Master) 관련 로직
		// ==========================================

		getGroupList: function() {
			const _this = this;
			commUtil.ajax({
				url: "/api/settings/code/groups",
				method: "GET",
				data: { groupNm: $("#searchGroupNm").val() },
				success: function(res) {
					if (res.status === "SUCCESS") {
						_this.renderGroupList(res.data);
					}
				}
			});
		},

		renderGroupList: function(data) {
			const $body = $("#groupListBody");
			$body.empty();

			if (!data || data.length === 0) {
				$body.append('<tr><td colspan="3" class="text-center text-secondary p-4">그룹이 존재하지 않습니다.</td></tr>');
				return;
			}

			data.forEach(function(item) {
				const isActive = (this.selectedGroupCd === item.groupCd) ? 'active' : '';
				const tr = `<tr class="${isActive} row-hover" data-cd="${item.groupCd}" data-nm="${item.groupNm}" data-desc="${item.groupDesc || ''}">
					<td>${item.groupCd}</td>
					<td>${item.groupNm}</td>
					<td class="text-center">
						<button type="button" class="btn-table-link primary edit-group">수정</button>
						<button type="button" class="btn-table-link danger btn-delete-group">삭제</button>
					</td>
				</tr>`;
				$body.append(tr);
			}.bind(this));
		},

		bindGroupForm: function(data) {
			this.isGroupEdit = true;
			$("#groupFormTitle").text("그룹 정보 수정");
			
			// data-cd, data-nm, data-desc 를 각각 groupCd, groupNm, groupDesc로 매핑
			const mappedData = { 
				groupCd: data.cd, 
				groupNm: data.nm, 
				groupDesc: data.desc 
			};
			commUtil.bindForm("groupForm", mappedData);
			
			$("#groupCd").attr("readonly", true);
			$("#groupNm").focus();
			$("#btnSaveGroup").text("수정");
		},

		clearGroupForm: function() {
			this.isGroupEdit = false;
			$("#groupFormTitle").text("신규 그룹 추가");
			$("#groupForm")[0].reset();
			$("#groupCd").attr("readonly", false);
			$("#btnSaveGroup").text("신규 추가");
		},

		executeSaveGroup: function() {
			const _this = this;
			const formData = commUtil.serializeObject("groupForm");

			if (!formData.groupCd || !formData.groupNm) {
				commUtil.alert("그룹코드와 그룹명은 필수입력 입니다.");
				return;
			}

			commUtil.ajax({
				url: "/api/settings/code/groups",
				method: _this.isGroupEdit ? "PUT" : "POST",
				data: formData,
				success: function(res) {
					if (res.status === "SUCCESS") {
						commUtil.alert("성공적으로 저장되었습니다.");
						_this.clearGroupForm();
						_this.getGroupList();
					} else {
						commUtil.alert("오류가 발생했습니다: " + res.message);
					}
				}
			});
		},


		executeDeleteGroup: function(groupCd) {
			const _this = this;
			commUtil.ajax({
				url: `/api/settings/code/groups/${groupCd}`,
				method: "DELETE",
				success: function(res) {
					if (res.status === "SUCCESS") {
						if (_this.selectedGroupCd === groupCd) {
							_this.selectedGroupCd = null;
							$("#detailCard").addClass("d-none");
							$("#noSelectionMsg").show();
						}
						_this.getGroupList();
					}
				}
			});
		},

		// ==========================================
		// 2. 상세 코드 (Detail) 관련 로직
		// ==========================================

		getDetailList: function() {
			const _this = this;
			commUtil.ajax({
				url: "/api/settings/code/details",
				method: "GET",
				data: { groupCd: _this.selectedGroupCd },
				success: function(res) {
					if (res.status === "SUCCESS") {
						$("#noSelectionMsg").hide();
						$("#detailCard").removeClass("d-none");
						$("#selectedGroupName").text(_this.selectedGroupNm);
						_this.renderDetailList(res.data);
						_this.clearDetailForm();
					}
				}
			});
		},

		renderDetailList: function(data) {
			const $body = $("#detailListBody");
			$body.empty();

			if (!data || data.length === 0) {
				$body.append('<tr><td colspan="4" class="text-center text-secondary p-4">상세코드가 존재하지 않습니다.</td></tr>');
				return;
			}

			data.forEach(function(item) {
				const rowClass = item.useYn === 'N' ? 'deleted' : 'row-hover';
				const tr = `<tr class="${rowClass}" data-cd="${item.detailCd}" data-nm="${item.codeNm}" 
								data-order="${item.sortOrder}" data-desc="${item.codeDesc || ''}">
					<td>${item.detailCd}</td>
					<td>${item.codeNm}</td>
					<td>${item.sortOrder}</td>
					<td class="text-center">
						<button type="button" class="btn-table-link success edit-detail">수정</button>
						<button type="button" class="btn-table-link danger btn-delete-detail">삭제</button>
					</td>
				</tr>`;
				$body.append(tr);
			});
		},

		bindDetailForm: function(data) {
			this.isDetailEdit = true;
			$("#detailFormTitle").text("상세 코드 정보 수정");
			
			const mappedData = { 
				detailCd: data.cd, 
				codeNm: data.nm, 
				sortOrder: data.order, 
				codeDesc: data.desc 
			};
			commUtil.bindForm("detailForm", mappedData);
			
			$("#detailCd").attr("readonly", true);
			$("#codeNm").focus();
			$("#btnSaveDetail").text("수정");
		},

		clearDetailForm: function() {
			this.isDetailEdit = false;
			$("#detailFormTitle").text("신규 상세 코드 추가");
			$("#detailForm")[0].reset();
			$("#detailCd").attr("readonly", false);
			$("#btnSaveDetail").text("신규 추가");
		},

		executeSaveDetail: function() {
			const _this = this;
			const formData = commUtil.serializeObject("detailForm");
			formData.groupCd = _this.selectedGroupCd;

			if (!formData.detailCd || !formData.codeNm) {
				commUtil.alert("코드와 코드명은 필수입력 입니다.");
				return;
			}

			commUtil.ajax({
				url: "/api/settings/code/details",
				method: _this.isDetailEdit ? "PUT" : "POST",
				data: formData,
				success: function(res) {
					if (res.status === "SUCCESS") {
						commUtil.alert("성공적으로 저장되었습니다.");
						_this.getDetailList();
					} else {
						commUtil.alert("오류가 발생했습니다: " + res.message);
					}
				}
			});
		},


		executeDeleteDetail: function(detailCd) {
			const _this = this;
			commUtil.ajax({
				url: `/api/settings/code/details/${_this.selectedGroupCd}/${detailCd}`,
				method: "DELETE",
				success: function(res) {
					if (res.status === "SUCCESS") {
						_this.getDetailList();
					}
				}
			});
		}
	};

	code.init();
});
