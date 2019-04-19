function maskScreen() {
	var b = getMyBgdiv();
	b.style.cssText = "position: absolute; left: 0px; top: 0px; width: 100%; height: 937px; z-index: 1000;";
	// 提示弹框
	var msg = $topWindow().document.createElement("div");
	msg.id = "sys_msg";
	msg.style.cssText = "z-index: 1010; position: absolute; padding: 0px; margin: 0px; width: 20%; top: 40%; left: 40%; text-align: center; color: rgb(0, 0, 0); border: 0px solid #438eb9; background-color: rgb(0, 0, 0), opacity:0; cursor: wait;";
	msg.innerHTML = '<img src=/static/images/loadingi.gif />';
	$topWindow().document.getElementsByTagName("BODY")[0].appendChild(msg);
}

function unMaskScreen() {
	var b = getMyBgdiv();
	b.style.cssText = "display:none;";
	$topWindow().document.getElementById("sys_msg").remove();
}

function getMyBgdiv() {
	var d = $topWindow().$id("_DialogMyBGDiv");
	if (!d) {
		d = $topWindow().document.createElement("div");
		d.id = "_DialogMyBGDiv";
		d.style.cssText = "position:absolute;left:0px;top:0px;width:100%;height:100%;z-index:900";
		var a = '<div style="position:relative;width:100%;height:100%;">';
		var c = '<div id="_DialogBGMask" style="position:absolute;background-color:#333;opacity:0.4;filter:alpha(opacity=40);width:100%;height:100%;"></div>';
		var f = ielt7 ? '<iframe src="about:blank" style="filter:alpha(opacity=0);" width="100%" height="100%"></iframe>'
				: "";
		d.innerHTML = a + c + f + "</div>";
		$topWindow().document.getElementsByTagName("BODY")[0].appendChild(d);
		if (ielt7) {
			var b = d.getElementsByTagName("IFRAME")[0].contentWindow.document;
			b.open();
			b.write("<body style='background-color:#333' oncontextmenu='return false;'></body>");
			b.close();
			b = null
		}
	}
	return d;
};
