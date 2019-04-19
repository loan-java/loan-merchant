function changeTwoDecimal(x) { //保留2位小数
	var f_x = parseFloat(x);
	if(isNaN(f_x)) {
		return 0;
	}
	var f_x = Math.round(x * 100) / 100;
	var s_x = f_x.toString();
	var pos_decimal = s_x.indexOf('.');
	if(pos_decimal < 0) {
		pos_decimal = s_x.length;
		s_x += '.';
	}
	while(s_x.length <= pos_decimal + 2) {
		s_x += '0';
	}
	return s_x;
}

function queryUrlParam(name) { // 截取url的字段
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if(r) return unescape(r[2]);
	return '';
}