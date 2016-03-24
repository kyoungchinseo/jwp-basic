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


function deleteAnswer(e) {
	e.preventDefault();
	console.log("delete button");
	var btnDelete = $(this);
	var dataString = btnDelete.parent().closest("form").serialize();
	
	console.log(dataString);
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
		}
	});
}


