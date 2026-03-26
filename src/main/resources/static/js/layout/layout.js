/**
 *	File Name			: layout.js
 *	File Description	: 공통 레이아웃 UI 전용 스크립트 (사이드바 토글 및 서브메뉴 동작)
 *
 *	Date				Author					Description
 * --------------------------------------------------------------
 * 	2026-03-16			yeoun1107				최초작성
 */

$(function() {
	const layout = {
		
		/**
		 * @Description		: 초기화 메서드
		 * @method			: init
		 * @Writter			: yeoun1107
		 * @Date			: 2026-03-16
		 */
		init: function() {
			this.bindEvents();
		},

		/**
		 * @Description		: 이벤트 바인딩
		 * @method			: bindEvents
		 * @Writter			: yeoun1107
		 * @Date			: 2026-03-16
		 */
		bindEvents: function() {
			const _this = this;

			// 1. 사이드바 토글 버튼 (햄버거 버튼) 클릭
			$("#sidebarToggle").on("click", function() {
				_this.toggleSidebar();
			});

			// 2. 서브메뉴가 있는 메뉴 클릭
			$(".has-sub > a").on("click", function(e) {
				e.preventDefault();
				_this.toggleSubMenu($(this).parent());
			});
			
			// 3. 모바일용 오버레이 클릭 시 사이드바 닫기
			$(".sidebar-overlay").on("click", function() {
				_this.toggleSidebar();
			});
		},

		/**
		 * @Description		: 사이드바 접기/펴기 토글 실행
		 * @method			: toggleSidebar
		 * @Writter			: yeoun1107
		 * @Date			: 2026-03-16
		 */
		toggleSidebar: function() {
			const $wrapper = $(".layout-wrapper");
			
			// 데스크탑: .collapsed 클래스로 조정
			// 모바일: .show-sidebar 클래스로 조정
			if (window.innerWidth > 992) {
				$wrapper.toggleClass("collapsed");
				
				// 사이드바를 접을 때 열려있는 서브메뉴가 있다면 모두 닫기
				if ($wrapper.hasClass("collapsed")) {
					$(".has-sub.open").removeClass("open").find(".sub-menu").hide();
				}
			} else {
				$wrapper.toggleClass("show-sidebar");
			}
		},

		/**
		 * @Description		: 서브메뉴 접기/펴기 토글 실행 (아코디언)
		 * @method			: toggleSubMenu
		 * @param			: $targetMenu (Has-sub class element)
		 * @Writter			: yeoun1107
		 * @Date			: 2026-03-16
		 */
		toggleSubMenu: function($targetMenu) {
			const _this = this;
			const $subMenu = $targetMenu.find(".sub-menu");

			// 이미 열려있는 다른 서브메뉴는 닫고 싶다면 (아코디언 옵션)
			/*
			$targetMenu.siblings(".has-sub.open").each(function() {
				$(this).removeClass("open").find(".sub-menu").slideUp(200);
			});
			*/

			// 현재 메뉴 토글
			if ($targetMenu.hasClass("open")) {
				$targetMenu.removeClass("open");
				$subMenu.slideUp(200);
			} else {
				$targetMenu.addClass("open");
				$subMenu.slideDown(200);
			}
		}
	};

	layout.init();
});
