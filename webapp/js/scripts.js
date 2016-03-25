String.prototype.format = function() {
  var args = arguments;
  return this.replace(/{(\d+)}/g, function(match, number) {
    return typeof args[number] != 'undefined'
        ? args[number]
        : match
        ;
  });
};

 

//$(".form-delete button[type=submit]").click(deleteAnswer);
$(".qna-comment-slipp").on("click",".link-delete-article",deleteAnswer);

function deleteAnswer(e) {
	e.preventDefault();
	
	var btnDelete = $(this);
	var dataString = btnDelete.parent().closest(".form-delete").serialize();
	
	$.ajax({
		type:'post',
		url: '/api/qna/deleteAnswer',
		data: dataString,
		dataType: 'json',
		error: function (xhr, status) {
			alert("error");
	    },
	    success: function (json, status) {
	    	if (json.status) {
	    		
	    		btnDelete.closest('article').remove();
	    		// 새롭게 만들어진 리스트 갯수 세서 숫자 갱신
	    		// 데이터에서 받아와야 하는거 아닌가?
	    		var updatedNumOfComments = $(".qna-comment-slipp-articles").find(".article").length;
	    		$(".qna-comment-count strong").text(updatedNumOfComments);
	    	}
	    }
	});
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
		error: function (xhr, status) {
			alert("error");
	    },
		success: function(json, status) {
			var answerTemplate = $("#answerTemplate").html();
			var template = answerTemplate.format(json.writer, new Date(json.createdDate), json.contents, json.answerId, json.answerId);
			$(".qna-comment-slipp-articles").prepend(template);
			
			var updatedNumOfComments = $(".qna-comment-slipp-articles").find(".article").length;
    		$(".qna-comment-count strong").text(updatedNumOfComments);
		}
	});
}


