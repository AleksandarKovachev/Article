(function($) {
	$(function() {
		$('.button-collapse').sideNav();
		$('.parallax').parallax();
	}); // end of document ready
})(jQuery); // end of jQuery name space

$(document).ready(function() {
	$('.dropdown-button2').dropdown({
		inDuration : 300,
		outDuration : 225,
		constrain_width : false,
		hover : true,
		gutter : ($('.dropdown-content').width() * 3) / 2.5 + 5,
		belowOrigin : true,
		alignment : 'left'
	});
});

// select
$(document).ready(function() {
	$('select').material_select();

	$("select[required]").css({
		position : 'absolute',
		display : 'inline',
		height : 0,
		padding : 0,
		width : 0
	});

	$(".dropdown-button").dropdown();
});

// init modal
$(document).ready(function() {
	// the "href" attribute of .modal-trigger must specify the modal ID that
	// wants to be triggered
	$('.modal').modal();
});

$(document).ready(function() {
	$('.tooltipped').tooltip({
		delay : 50
	});
});

$(document).ready(function() {
	$('input#input_text, textarea#textarea').characterCounter();
});

$(document).ready(function() {
	$('.collapsible').collapsible();
});

// Datas
$('.datepicker').pickadate(
		{
			monthsFull : [ 'Януари', 'Февруари', 'Март', 'Април', 'Май', 'Юни',
					'Юли', 'Август', 'Септември', 'Октомври', 'Ноември',
					'Декември' ],
			monthsShort : [ 'Януари', 'Февруари', 'Март', 'Април', 'Май',
					'Юни', 'Юли', 'Август', 'Септември', 'Октомври', 'Ноември',
					'Декември' ],
			weekdaysFull : [ 'Неделя', 'Понеделник', 'Вторник', 'Сряда',
					'Четвъртък', 'Петък', 'Събота' ],
			weekdaysShort : [ 'Неделя', 'Понеделник', 'Вторник', 'Сряда',
					'Четвъртък', 'Петък', 'Събота' ],
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

$(".closebtn").click(function() {
	this.parentElement.style.display = 'none';
});