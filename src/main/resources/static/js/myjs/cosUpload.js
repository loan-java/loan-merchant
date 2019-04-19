function uploadFileOSS(file, callback) {
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
			callback(data);
		},
		error : function (XMLHttpRequest, textStatus, errorThrown) {
	        console.log(XMLHttpRequest.status);
	        console.log(XMLHttpRequest.readyState);
	        console.log(textStatus);
		}
	});
}