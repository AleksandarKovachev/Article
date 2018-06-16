$(document).ready(
		function() {
			$('.sidenav').sidenav();
			$('.parallax').parallax();
			$('select').formSelect();
			$('.modal').modal();
			$(".dropdown-trigger").dropdown({
				constrainWidth : false,
				coverTrigger : false
			});
			
			$("select[required]").css({
				position : 'absolute',
				display : 'inline',
				height : 0,
				padding : 0,
				width : 0
			});

			$('.tooltipped').tooltip({
				delay : 50
			});

			$('input#input_text, textarea#textarea').characterCounter();

			$('.collapsible').collapsible();

			$(".closebtn").click(function() {
				this.parentElement.style.display = 'none';
			});

			$('.datepicker').datepicker(
					{
						monthsFull : [ 'Януари', 'Февруари', 'Март', 'Април',
								'Май', 'Юни', 'Юли', 'Август', 'Септември',
								'Октомври', 'Ноември', 'Декември' ],
						monthsShort : [ 'Януари', 'Февруари', 'Март', 'Април',
								'Май', 'Юни', 'Юли', 'Август', 'Септември',
								'Октомври', 'Ноември', 'Декември' ],
						weekdaysFull : [ 'Неделя', 'Понеделник', 'Вторник',
								'Сряда', 'Четвъртък', 'Петък', 'Събота' ],
						weekdaysShort : [ 'Неделя', 'Понеделник', 'Вторник',
								'Сряда', 'Четвъртък', 'Петък', 'Събота' ],
						today : 'Днес',
						clear : 'Изчисти',
						close : 'Затвори',
						labelMonthNext : 'Следващ месец',
						labelMonthPrev : 'Предишен месец',
						labelMonthSelect : 'Избери месец',
						labelYearSelect : 'Избери година',
						selectMonths : true,
						selectYears : 15,
						format : 'dd.mm.yyyy',
						formatsubmit : 'dd.mm.yyyy'
					});
		});