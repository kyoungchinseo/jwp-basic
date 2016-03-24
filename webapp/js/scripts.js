String.prototype.format = function() {
  var args = arguments;
  return this.replace(/{(\d+)}/g, function(match, number) {
    return typeof args[number] != 'undefined'
        ? args[number]
        : match
        ;
  });
};

$(".form-delete button[type=submit]").on('click',deleteAnswer);

//$(".qna-comment").on("click", ".form-delete", deleteAnswer);

function deleteAnswer(e) {
	e.preventDefault();
	console.log("delete button");
	var dataString = $(this).parent().closest("form").serialize();
	console.log(dataString);
	$.ajax({
		type:'post',
		url: '/api/qna/deleteAnswer',
		data: dataString,
		dataType: 'json',
		error: onErrorDelete,
		success: onSuccessDelete,
	});
}

function onErrorDelete(xhr, status) {
	alert("delete error");
}

function onSuccessDelete(json, status) {
	console.log(json);
	console.log($(this).parent().closest("article"));
	
	
	$(this).closest("article.article").remove();
	//location.reload();
}

$(".answerWrite input[type=submit]").on("click", addAnswer);

function addAnswer(e) {
	e.preventDefault();
	var queryString = $("form[name=answer]").serialize();
	
	$.ajax({
		type:'post',
		url: '/api/qna/addanswer',
		data: queryString,
		dataType: 'json',
		error: onError,
		success: onSuccess,
	});
}

function onError(xhr, status) {
	alert("error");
}

function onSuccess(json, status) {
	console.log(json);
	var answerTemplate = $("#answerTemplate").html();
	var template = answerTemplate.format(json.writer, new Date(json.createdDate), json.contents, json.answerId, json.answerId);
	$(".qna-comment-slipp-articles").prepend(template);
	
}



