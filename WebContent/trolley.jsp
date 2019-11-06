<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%	
	//获取项目名
	String path = request.getContextPath();
	//获取tomcat 协议+地址+端口号+ 获取项目名
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	//获取tomcat 协议+地址+端口号
	String imgPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/xmpic/";
	
%>
<html>
<head>
    <meta charset="UTF-8">
    <title>购物车</title>
    <link rel="stylesheet" href="css/index.css">
</head>
<body>
    <div class="order_head">
        <div class="head_background">
            <div class="head_box">
                <a href="index.html" class="head_left_a"><img src="img/logo.jpg" alt="" class="head_left_p"></a>
                <h1 class="head_h1">${user.uname }的购物车</h1>
                <div class="head_right">
                    <span class="head_right_in"> ${user.uname }/${user.username } </span>
                    <span class="head_right_in">|</span>
                    <a href="" class="head_right_in">${user.uname }的订单</a>
                </div>
            </div>
        </div>
    </div>
    <div class="trolley_background">
        <div class="trolley_background_in">
            <div class="tro_tab_h">
                <div class="col tro_tab_check">
                    <h1 class="tro_tab_check_p">√</h1>
                    <span class="tro_tab_check_sp">全选</span>
                </div>
                <div class="col tro_tab_img">
                </div>
                <p class="col tro_tab_name">商品名称</p>
                <div class="col tro_tab_price">单价</div>
                <div class="col tro_tab_num">数量</div>
                <div class="col tro_tab_total">小计</div>
                <div class="col tro_tab_action">操作</div>
            </div>
            
            <c:set var="sum_price" value="0"></c:set>
            <c:set var="goods_price" value="0"></c:set>

            <c:forEach items="${trolleys}" var="trolley">

	            <div class="tro_tab_h1">
	                <div class="col tro_tab_check">
	                    <h1 class="tro_tab_check_p" style="background-color: #fff">
							<input type="checkbox" name="ids">
						</h1>
	                    <span class="tro_tab_check_sp"></span>
	                </div>
	                <div class="col tro_tab_img">
	                    <img src="<%=imgPath %>${trolley.goods.pic}" alt="">
	                </div>
	                <div class="col tro_tab_name">
	               		<li class="tro_tab_name_li1" style="font-size: 16px;">${trolley.goods.gname }&nbsp;${trolley.goods.color }</li>
	                </div>
	                <div class="col tro_tab_price">
	                    <span  id="price">${trolley.goods.price }</span><span>元</span>
	                    
	                </div>
	                <div class="col tro_tab_num">
	                    <a class="tro_tab_num_p1" id="subtract" href="javascript:void(0)" onclick="addOrDeleteNumber(${trolley.tid},${trolley.number-1 })">-</a>
	                    <input type="text" value="${trolley.number }" id="num">
	                   <c:set var="goods_count" value="${goods_count+trolley.number }"></c:set>
	                    <a class="tro_tab_num_p2" id="plus" href="javascript:void(0)" onclick="addOrDeleteNumber(${trolley.tid},${trolley.number+1 })">+</a>
	                </div>
	                <div class="col tro_tab_total ">
	                <c:set var="sum_price" value="${sum_price+trolley.goods.price*trolley.number }"></c:set>
	                	<span class="tro_tab_total_value" id="prices" >${trolley.goods.price*trolley.number }</span>元
	                </div>
	                <div class="col tro_tab_action" style="cursor: pointer;width: 40px;height: 40px;" onclick="deleteTrolley(${trolley.tid})">删除</div>
	            </div>
            </c:forEach>
        </div>
        
        <div class="tro_close_bot ">
            <!--<p class="tro_bot_ppp">+</p>-->
            <p class="tro_close_p "> <a href="index?func=indexInfo">继续购物 </a>  
            | 共<span>${trolleys.size() }</span>种商品
            | 共<span>${goods_count }</span>件商品
            </p>
            <p class="tro_close_p_c">合计:<span id="close">${sum_price }</span>元</p>
            <p class="tro_close_p_r" style="cursor: pointer;" onclick="pay(${sum_price },${goods_count })">去结算</p>
        </div>
    </div>
   <jsp:include page="footer.jsp"></jsp:include>
</body>
<script type="text/javascript">
function addOrDeleteNumber(tid,number){
	if(number<1){
	number = 1;
	$("#num").val(number);
	alert("商品数量不能小于1，如果你不想买了，请删除此条记录");
	}
	if(number>5){
		number =5;
		$("#num").val(number);
		alert("每人限购五件，库存不足~");
	}
	window.location="trolley?func=addOrDeleteNumber&tid="+tid+"&number="+number;

}

function deleteTrolley(tid){
	if(confirm("确定要删除吗?")){
		window.location="trolley?func=deleteTrolley&tid="+tid;
		
	}
	
}

function pay(sum_price,goods_count){
	//判断如果商品个数是否为空或者金额是否为空
	if(sum_price==0||goods_count==0){
		alert("请先选择要购买的商品");
		window.location="index?func=indexInfo";
	}else{
		window.location="orders?func=createOrders&sumPrice="+sum_price+"&goodsCount="+goods_count;
		
	}
}


</script>


</html>























