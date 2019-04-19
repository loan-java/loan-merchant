KindEditor.plugin('cosupload',function(K) {
	var self = this;
	var name = 'cosupload';
	// 点击图标时执行
	self.clickToolbar(name, function(){
		appendDiv(function(){
			$("#kindeditor_imgFile").off("change").on("change", function (e) {
				var file = e.target.files[0];
				var form = new FormData();
				form.append("file", file);
				$.ajax({
					url : "/upload/img_upload_ajax",
					type : "POST",
					dataType : "json",
					processData : false,
					contentType : false,
					data : form,
					success : function(data) {
						self.html(self.html() + "<img src='" + data.data.absolutePath + "' />");
					    $("#kindeditor_imgFile").val("");
					},
					error : function (XMLHttpRequest, textStatus, errorThrown) {
				        console.log(XMLHttpRequest.status);
				        console.log(XMLHttpRequest.readyState);
				        console.log(textStatus);
					}
				});
			});
			
			// 弹出文件选择框
			setTimeout(function(){
				$("#kindeditor_imgFile").click();
			}, 0);
		});
	});
});

function appendDiv(callback){
	// 插入一个file类型input
	var div = document.createElement("div");
	div.innerHTML = '<input type="file" id="kindeditor_imgFile" name="kindeditor_imgFile" style="display:none;" />';
	window.document.body.appendChild(div);
	callback();
}
