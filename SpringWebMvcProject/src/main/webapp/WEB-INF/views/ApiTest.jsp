<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!--  이 구문을 넣으면 jQuery를 쓸 수 있다. -->
<script src="https://code.jquery.com/jquery-3.5.0.min.js"
	integrity="sha256-xNzN2a4ltkB44Mc/Jz3pT4iU1cmeR0FkXs4pru/JxaQ="
	crossorigin="anonymous"></script>





</head>
<body>

	ApiTest 페이지 입니다.

	<h2>사용 예제</h2>
	<div>
		<img src="http://[API 요청주소(End Point URL)]?ServiceKey=인증키&[요청변수=값]" />
	</div>

	<input id="viewLandUse" type="button" value="Using ajax view LandUse">
	
	  <div class="commentList"></div>
	  
	    <table class="table-color2">
	  </table>
	  
	  <table class="table-color">
	  
	
	  
<!-- 	  <tr>
	  <td>일반시설 일련번호</td>
	  <td>기준년도</td>
	  <td>시설종류</td>
	  <td>시설명</td>
	  <td>전체집행연장(m)</td>
	  <td>전체집행면적(㎡)</td>
	  <td>미집행연장(m)</td>
	  <td>대표위치명</td>
	  </tr>
	   -->
	  </table>
	  
	  	  
	
	<script>

//클릭 이벤트 등을 걸지 않은 이상 정부api는 제이쿼리와 전혀 상관이 없다.
$(function() {
	   
	
	$('#viewLandUse').click(function(e) {
	   //&numOfRows=10&pageNo=1
	   
	   const ServiceKey = "3iOrxz5OIYvrdMICLIAi0nua9VaH6ikWDCE1E%2B2amNNVTI%2B7h2FizOJnzF%2FRQjkrm6x%2FzNOLBGekey07KUwj8A%3D%3D";
	   const numOfRows = 10;
	   const pageNo = 1;
	 
			 
	   
	   
	   /* 
	   $.ajax({
	      type: "get",
	      url: "/api",
	       headers: {
	         "Content-Type": "application/json"
	      }, //요청 헤더 정보
	      async:false,
	      dataType: "json", //요청한 데이터 형식
	      data: {
	             "numOfRows" : numOfRows,
	             "pageNo" : pageNo
	      	},         
	      success: function(data) { //함수의 매개변수는 통신성공시의 데이터가 저장될 곳.
	    	  
	         	console.log("통신 성공!: " + data);
		      var myItem = data.response.body.items.item;
		      var output = '';
		      var output= '<h4>+myItem.gnrlFcltySn' + '</h4>';
		      var output= '<h4>+myItem.stdrYear' + '</h4>';
		      var output= '<h4>+myItem.fcltyClNm' + '</h4>';
		      var output= '<h4>+myItem.fcltyNm' + '</h4>';
		      var output= '<h4>+myItem.allExcutEt' + '</h4>';
		      var output= '<h4>+myItem.allExcutAr' + '</h4>';
		      var output= '<h4>+myItem.unExcutEt' + '</h4>';
		      var output= '<h4>+myItem.reprsntLcNm' + '</h4>';
		      $('#window').html(output);
	      
	      }, //통신 성공시 처리할 내용들을 함수 내부에 작성.
	      error: function(XMLHttpRequest, textStatus, errorThrown) {
	         console.log("통신 실패!");
	         console.log("통신 실패!  -------------" + "Status: " + textStatus + ' || ' + "Error: " + errorThrown);
	         //alert("Status: " + textStatus); alert("Error: " + errorThrown);
	      } //통신 실패 시 처리할 내용들을 함수 내부에 작성.
	   }); 
	    */
	   
	   //////////////////////////////////////////////////////
	   
	   
	      //	var cnt = 0;
   	
   	function increseCnt() {
   	      cnt++;
   	    }
   	    
	   
   	
   	
   	
	   
	   $.ajax({
            url: '/api',
            type: "POST",
            dataType: "JSON",
  	      	data: {
	             "numOfRows" : numOfRows,
	             "pageNo" : pageNo
	             
	             //"cnt" : cnt
	        /*      "gnrlFcltySn" : gnrlFcltySn,
	             "stdrYear" : stdrYear */
	      	},     
            success: function(data){ // 값이 없다.
            	console.log("통신 eeeee!");
            
            	name = '';
            	name += '<tr class="table-color2">'           +
            	
            	' 			<td>							' +
            	'			'+ '일반시설일련번호' + '             ' +
            	'           </td>                           ' +
            	'			<td>                            ' +
            	'           '+ '기준년도' + '                  ' +
            	'           </td>                           ' +
            	'           <td>                            ' +
             	'			'+ '시설종류' + '                  ' +
            	'           </td>                           ' +
            	'           <td>                            ' +
               	'			'+ '시설명' + '                   ' +
            	'           </td>                           ' +
            	'           <td>                            ' +
            	'			'+ '전체집행연장(m)' + '            ' +
             	'           </td>                           ' +
            	'           <td>                            ' +
            	'			'+ '전체집행면적(㎡)' + '           ' +
             	'           </td>                           ' +
            	'           <td>                            ' +
              	'			'+ '미집행연장(m)' + '             ' +
              	'           </td>                           ' +
            	'           <td>                            ' +
            	'			'+ '대표위치명' + '                ' +
            	'           <td>                            ' ;
            	
             	name += '</tr>' ;
             	
            	$(".table-color2").html(name);	
            
            
               	var cnt = 0;
           		//while (cnt <= 10) {
            	//item에 pageNo,numOfRows,totalCount가 삽입된다.[디버깅모드]
            	$.each(data.response.fields, function(index,item) { //index: 사용된 배열의 index,key , item: 해당인덱스 키가 가진 값
            		
            		//cnt = index;
            	console.log("item 콘솔" + item);
            	console.log("data 콘솔" + data);
            		
            	dataHtml = '';
            		
            	for(var cnt=0; cnt < item.length; cnt++) {
            	dataHtml += ' <tr class="table-color"> ' +
            	
            	' 			<td>							' +
            	'			'+ item[cnt].gnrlFcltySn + '    ' +
            	'           </td>                           ' +
            	'			<td>                            ' +
            	'           '+ item[cnt].stdrYear + '       ' +
            	'           </td>                           ' +
            	'           <td>                            ' +
             	'			'+ item[cnt].fcltyClNm + '           ' +
            	'           </td>                           ' +
            	'           <td>                            ' +
               	'			'+ item[cnt].fcltyNm + '             ' +
            	'           </td>                           ' +
            	'           <td>                            ' +
            	'			'+ item[cnt].allExcutEt + '          ' +
             	'           </td>                           ' +
            	'           <td>                            ' +
            	'			'+ item[cnt].allExcutAr + '          ' +
             	'           </td>                           ' +
            	'           <td>                            ' +
              	'			'+ item[cnt].unExcutEt + '           ' +
              	'           </td>                           ' +
            	'           <td>                            ' +
            	'			'+ item[cnt].reprsntLcNm + '         ' +
            	'           <td>                            ' ;
	             
            	//cnt++; //cnt가 1로 바뀌긴하는데 위로 안올라가고 그대로 함수종료
            	//debugger;
            	
            	//cnt++;
            	//increseCnt();
            	
            	
            	dataHtml += '</tr>' ;
            	
            	$(".table-color").html(dataHtml);
            	
            	//cnt++;
            
            	
            	}
            	});
            	
            //} 	
            	
            },
            error: function(e){
            	console.log("통신 실패!");
            }
         })
	   
	   //////////////////////////////////////////////////////
	   
	});

});




</script>




</body>
</html>